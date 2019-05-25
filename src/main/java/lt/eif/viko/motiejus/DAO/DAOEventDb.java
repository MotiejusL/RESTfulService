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
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.ParseException;
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
    List<Event> events = new ArrayList<>();

    public DAOEventDb(String countryName) throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
        this.countryName = countryName;
    }

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

    @Override
    public Event get(Object id) {
        return null;

    }

    @Override
    public void insert(Event object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Event VALUES (" + object.getId() + ", '" + object.getName() + "', DATE('" + object.getDate() + "'), '" + object.getTime() + "', '" + object.getCity() + "', '" + object.getDescription() + "', '" + object.getCountryName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Event object) {

    }

    @Override
    public void delete(Event object) {

    }
}
