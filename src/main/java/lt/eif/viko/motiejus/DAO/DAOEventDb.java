/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.eif.viko.motiejus.entities.Country;
import lt.eif.viko.motiejus.entities.Destinations;
import lt.eif.viko.motiejus.entities.Event;

/**
 *
 * @author motsa
 */
public class DAOEventDb implements DAO<Event> {

    private Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private Destinations destinations = new Destinations();
    private String countryName;

    public DAOEventDb(String countryName) throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
        this.countryName = countryName;
    }

    @Override
    public List<Event> load() {
        List<Event> events = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Event WHERE countryName ='" + countryName + "'");
            Boolean next = resultSet.first();
            while (next == true) {
                Event event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                LocalDate date = resultSet.getDate("date").toLocalDate();
                event.setDate(date);
                LocalTime localTime = resultSet.getObject("time", LocalTime.class);
                event.setTime(localTime);
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

    @Override
    public Event get(Object id) {
        return null;

    }

    @Override
    public void insert(Event object) {

    }

    @Override
    public void update(Event object) {

    }

    @Override
    public void delete(Event object) {
        
    }
}
