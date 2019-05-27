/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;

/**
 * Represents destinations
 *
 * @author motsa
 */
public class Destinations {

    /**
     * Default Destinations constructor
     */
    public Destinations() {
    }

    private List<Country> countries = new ArrayList<>();
    private Link link;

    /**
     * Get uri from link
     *
     * @return uri of destinations
     */
    @JsonProperty("link")
    public URI getLink() {
        return link.getUri();
    }

    /**
     * Set uri of link
     *
     */
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * Get list of countries
     *
     * @return list of countries of destinations
     */
    @JsonProperty("countries")
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * Set list of countries
     *
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * Push country to country list
     *
     */
    public void pushToCountries(Country country) {
        countries.add(country);
    }

    /**
     * Delete country from countries list
     *
     */
    public void deleteFromCountries(Country country) {
        countries.remove(country);
    }
}
