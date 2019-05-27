/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.net.URI;
import javax.ws.rs.core.Link;

/**
 * Represents information about Event
 *
 * @author motsa
 */
@JsonRootName(value = "Event")
public class Event {

    /**
     * Default Event constructor
     */
    public Event() {
    }

    private int id;
    private String name;
    private String date;
    private String time;
    private String city;
    private String description;
    private String countryName;
    private Link link;

    /**
     * Get countryName
     *
     * @return countryName of event
     */
    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    /**
     * Set countryName
     *
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Get name
     *
     * @return name of event
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get uri of link
     *
     * @return uri of event
     */
    @JsonProperty("link")
    public URI getLink() {
        return link.getUri();
    }

    /**
     * Set link
     *
     */
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * Get id
     *
     * @return id of event
     */
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    /**
     * Set id
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get date
     *
     * @return date of event
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * Set date
     *
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get time
     *
     * @return time of event
     */
    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    /**
     * Set time
     *
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get city
     *
     * @return ciy of event
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * Set city
     *
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get description
     *
     * @return description of event
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
