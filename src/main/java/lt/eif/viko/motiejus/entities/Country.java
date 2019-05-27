/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;

/**
 * Represents information about country
 *
 * @author motsa
 */
@JsonRootName(value = "Country")
public class Country {

    /**
     * Default Country constructor
     */
    public Country() {
    }

    private int id;
    private String name;
    private String language;
    private String currency;
    private String capitalCity;
    private String generalInformation;
    private int climateSummerAvg;
    private int climateWinterAvg;
    private List<String> topThingsToDo = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private Link link;

    /**
     * Get id
     *
     * @return id of country
     */
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    /**
     * set id
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return name of country
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
     * Get events
     *
     * @return list of events
     */
    @JsonIgnore
    @JsonProperty("events")
    public List<Event> getEvents() {
        return events;
    }

    /**
     * set events
     *
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Get link uri
     *
     * @return uri of country
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
     * Get language
     *
     * @return language of country
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * Set language
     *
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get currency
     *
     * @return currency of country
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * Set currency
     *
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Get capital
     *
     * @return capital of country
     */
    @JsonProperty("capitalCity")
    public String getCapitalCity() {
        return capitalCity;
    }

    /**
     * Set capitalCity
     *
     */
    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    /**
     * Get generalInformation
     *
     * @return information of country
     */
    @JsonProperty("generalInformation")
    public String getGeneralInformation() {
        return generalInformation;
    }

    /**
     * Set information
     *
     */
    public void setGeneralInformation(String generalInformation) {
        this.generalInformation = generalInformation;
    }

    /**
     * Get Summer climate
     *
     * @return summer climate of country
     */
    @JsonProperty("climateSummerAvg")
    public int getClimateSummerAvg() {
        return climateSummerAvg;
    }

    /**
     * Set summer climate
     *
     */
    public void setClimateSummerAvg(int climateSummerAvg) {
        this.climateSummerAvg = climateSummerAvg;
    }

    /**
     * Get winter climate
     *
     */
    @JsonProperty("climateWinterAvg")
    public int getClimateWinterAvg() {
        return climateWinterAvg;
    }

    /**
     * Get winter climate
     *
     */
    public void setClimateWinterAvg(int climateWinterAvg) {
        this.climateWinterAvg = climateWinterAvg;
    }

    /**
     * Get list of things to do
     *
     * @return a list of things to do of country
     */
    @JsonIgnore
    @JsonProperty("topThingsToDo")
    public List<String> getTopThingsToDo() {
        return topThingsToDo;
    }

    /**
     * Set top things to do list
     *
     */
    public void setTopThingsToDo(List<String> topThingsToDo) {
        this.topThingsToDo = topThingsToDo;
    }
}
