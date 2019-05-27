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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.eif.viko.motiejus.entities.Destinations;
import lt.eif.viko.motiejus.entities.Event;

/**
 * DAO implementation for creating event objects from DB
 *
 * @author motsa
 */
public class DAOEventDb implements DAO<Event> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private final Destinations destinations;
    private final String countryName;
    List<Event> events = new ArrayList<>();

    /**
     * DAO event object constructor
     *
     */
    public DAOEventDb(String countryName) throws SQLException, ClassNotFoundException {
        this.destinations = new Destinations();
        Database database = new Database();
        connection = database.getConnection();
        this.countryName = countryName;
    }

    /**
     * loads all event objects
     *
     */
    @Override
    public List<Event> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Event WHERE countryName ='" + countryName + "'");
            Boolean next = resultSet.first();
            while (next == true) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(resultSet.getDate("date"));
                event.setDate(strDate);
                DateFormat df = new SimpleDateFormat("HH:mm");
                String strTime = df.format(resultSet.getTime("time").getTime());
                event.setTime(strTime);
                event.setCity(resultSet.getString("city"));
                event.setDescription(resultSet.getString("description"));
                event.setCountryName(countryName);
                events.add(event);
                next = resultSet.next();
            }
            return events;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * loads all event objects by city
     *
     */
    public List<Event> loadByCity(String city) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Event WHERE countryName ='" + countryName + "' AND city = '" + city + "'");
            Boolean next = resultSet.first();
            while (next == true) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(resultSet.getDate("date"));
                event.setDate(strDate);
                DateFormat df = new SimpleDateFormat("HH:mm");
                String strTime = df.format(resultSet.getTime("time").getTime());
                event.setTime(strTime);
                event.setCity(resultSet.getString("city"));
                event.setDescription(resultSet.getString("description"));
                event.setCountryName(countryName);
                events.add(event);
                next = resultSet.next();
            }
            return events;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * loads all event objects by date
     *
     */
    public List<Event> loadByDate(String date) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Event WHERE countryName ='" + countryName + "' AND date = '" + date + "'");
            Boolean next = resultSet.first();
            while (next == true) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(resultSet.getDate("date"));
                event.setDate(strDate);
                DateFormat df = new SimpleDateFormat("HH:mm");
                String strTime = df.format(resultSet.getTime("time").getTime());
                event.setTime(strTime);
                event.setCity(resultSet.getString("city"));
                event.setDescription(resultSet.getString("description"));
                event.setCountryName(countryName);
                events.add(event);
                next = resultSet.next();
            }
            return events;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * get event object by its id
     *
     */
    @Override
    public Event get(Object id) {
        int idInt = (int) id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Event WHERE id = " + idInt);
            resultSet.first();
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setName(resultSet.getString("name"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(resultSet.getDate("date"));
            event.setDate(strDate);
            DateFormat df = new SimpleDateFormat("HH:mm");
            String strTime = df.format(resultSet.getTime("time").getTime());
            event.setTime(strTime);
            event.setCity(resultSet.getString("city"));
            event.setDescription(resultSet.getString("description"));
            event.setCountryName(countryName);
            return event;

        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * insert event object into DB
     *
     */
    @Override
    public void insert(Event object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Event VALUES (" + object.getId() + ", '" + object.getName() + "', DATE('" + object.getDate() + "'), '" + object.getTime() + "', '" + object.getCity() + "', '" + object.getDescription() + "', '" + object.getCountryName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * update event object in DB
     *
     */
    @Override
    public void update(Event object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Event SET id =" + object.getId() + ", name = '" + object.getName() + "', date = DATE('" + object.getDate() + "'), time = '" + object.getTime() + "', city = '" + object.getCity() + "', description = '" + object.getDescription() + "', countryName = '" + object.getCountryName() + "' WHERE id =" + object.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * delete event object from DB
     *
     */
    @Override
    public void delete(Event object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Event WHERE id = " + object.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
