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
 * DAO implementation for creating country objects from DB
 *
 * @author motsa
 */
public class DAOCountryDb implements DAO<Country> {

    private final Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private final Destinations destinations;

    /**
     * DAO country object constructor
     *
     */
    public DAOCountryDb() throws SQLException, ClassNotFoundException {
        this.destinations = new Destinations();
        Database database = new Database();
        connection = database.getConnection();
    }

    /**
     * load all country objects
     *
     */
    @Override
    public List<Country> load() {
        destinations.getCountries().clear();
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

    /**
     * loads all country object by language
     *
     */
    public List<Country> loadByLanguage(String language) {
        destinations.getCountries().clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Country WHERE language ='" + language + "'");
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

    /**
     * loads all country objects by currency
     *
     */
    public List<Country> loadByCurrency(String currency) {
        destinations.getCountries().clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Country WHERE currency = '" + currency + "'");
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

    /**
     * get a country object by name
     *
     */
    @Override
    public Country get(Object name) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Country WHERE name = '" + name + "'");
            resultSet.first();
            Country country = new Country();
            country.setId(resultSet.getInt("id"));
            country.setName(resultSet.getString("name"));
            country.setLanguage(resultSet.getString("language"));
            country.setCurrency(resultSet.getString("currency"));
            country.setCapitalCity(resultSet.getString("capitalCity"));
            country.setGeneralInformation(resultSet.getString("generalInformation"));
            country.setClimateSummerAvg(resultSet.getInt("climateSummerAvg"));
            country.setClimateWinterAvg(resultSet.getInt("climateWinterAvg"));
            return country;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * insert a country object into DB
     *
     */
    @Override
    public void insert(Country object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Country VALUES (" + object.getId() + ", '" + object.getName() + "', '" + object.getLanguage() + "', '" + object.getCurrency() + "', '" + object.getCapitalCity() + "', '" + object.getGeneralInformation() + "', " + object.getClimateSummerAvg() + ", " + object.getClimateWinterAvg() + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * updates a country object from DB
     *
     */
    @Override
    public void update(Country object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE Country SET id =" + object.getId() + ", name = '" + object.getName() + "', language = '" + object.getLanguage() + "', currency = '" + object.getCurrency() + "', capitalCity = '" + object.getCapitalCity() + "', generalInformation = '" + object.getGeneralInformation() + "', climateSummerAvg =" + object.getClimateSummerAvg() + ", climateWinterAvg =" + object.getClimateWinterAvg() + " WHERE name = '" + object.getName() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * deletes a country object in DB
     *
     */
    @Override
    public void delete(Country object) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Country WHERE name ='" + object.getName() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DAOCountryDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        destinations.deleteFromCountries(object);
    }

}
