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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.eif.viko.motiejus.entities.Country;
import lt.eif.viko.motiejus.entities.Destinations;

/**
 *
 * @author motsa
 */
public class DAOCountryDb implements DAO<Country> {

    private Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private Destinations destinations = new Destinations();

    public DAOCountryDb() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        connection = database.getConnection();
    }

    @Override
    public List<Country> load() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Country");
            Boolean next = resultSet.first();
            while (next == true) {
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setLanguage(resultSet.getString("language"));
                country.setCurrency(resultSet.getString("currency"));
                country.setCapitalCity(resultSet.getString("capitalCity"));
                country.setGeneralInformation(resultSet.getString("generalInformation"));
                country.setClimateSummerAvg(resultSet.getInt("climateSummerAvg"));
                country.setClimateWinterAvg(resultSet.getInt("climateWinterAvg"));
                destinations.pushToCountries(country);
                next = resultSet.next();
            }
            return destinations.getCountries();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Country get(Object name) {
        String yesname = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Country WHERE name='Germany'");
            resultSet.first();
            yesname = resultSet.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        Country country = new Country();
        country.setName(yesname);
        return country;
    }

    @Override
    public void insert(Country object) {

    }

    @Override
    public void update(Country object) {

    }

    @Override
    public void delete(Country object) {

    }

}
