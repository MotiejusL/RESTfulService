/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.eif.viko.motiejus.entities.Destinations;
import lt.eif.viko.motiejus.entities.ThingToDo;

/**
 * DAO implementation for creating activity objects from DB
 *
 * @author motsa
 */
public class DAOActivitiesDb implements DAO<ThingToDo> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private final Destinations destinations;
    private final String countryName;
    List<ThingToDo> topThingsToDo = new ArrayList<>();

    /**
     * DAO activity object constructor
     *
     */
    public DAOActivitiesDb(String countryName) throws SQLException, ClassNotFoundException {
        this.destinations = new Destinations();
        Database database = new Database();
        connection = database.getConnection();
        this.countryName = countryName;
    }

    /**
     * load all activity objects
     *
     */
    @Override
    public List<ThingToDo> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM topthingstodo WHERE countryName ='" + countryName + "'");
            Boolean next = resultSet.first();
            while (next == true) {
                ThingToDo thingToDo = new ThingToDo();
                thingToDo.setId(resultSet.getInt("id"));
                thingToDo.setName(resultSet.getString("name"));
                thingToDo.setCountryName(resultSet.getString("countryName"));
                thingToDo.setDescription(resultSet.getString("description"));
                topThingsToDo.add(thingToDo);
                next = resultSet.next();
            }
            return topThingsToDo;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * get activity object by id
     *
     */
    @Override
    public ThingToDo get(Object object) {
        int idInt = (int) object;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM topthingstodo WHERE id = " + idInt);
            resultSet.first();
            ThingToDo thingToDo = new ThingToDo();
            thingToDo.setId(resultSet.getInt("id"));
            thingToDo.setName(resultSet.getString("name"));
            thingToDo.setCountryName(resultSet.getString("countryName"));
            thingToDo.setDescription(resultSet.getString("description"));
            return thingToDo;

        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * inserts an activity object into DB
     *
     */
    @Override
    public void insert(ThingToDo object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO topthingstodo VALUES (" + object.getId() + ",'" + object.getName() + "','" + object.getCountryName() + "','" + object.getDescription() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * updates and activity object in DB
     *
     */
    @Override
    public void update(ThingToDo object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE topthingstodo SET id =" + object.getId() + ", name = '" + object.getName() + "', countryName = '" + object.getCountryName() + "', description = '" + object.getDescription() + "' WHERE id =" + object.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * deletes an activity object from DB
     *
     */
    @Override
    public void delete(ThingToDo object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM topthingstodo WHERE id = " + object.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
