/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;

/**
 *
 * @author motsa
 */
@JsonRootName(value = "Country")
public class Country {
    
    public Country() {}
    
    private String language;
    private String currency;
    private String capitalCity;
    private String generalInformation;
    private int climateSummerAvg;
    private int climateWinterAvg;
    private List<String> topThingsToDo = new ArrayList<>();
    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    private Link link;

    @JsonProperty("link")
    public URI getLink() {
        return link.getUri();
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    @JsonProperty("capitalCity")
    public String getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    @JsonProperty("generalInformation")
    public String getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(String generalInformation) {
        this.generalInformation = generalInformation;
    }
    
    @JsonProperty("climateSummerAvg")
    public int getClimateSummerAvg() {
        return climateSummerAvg;
    }

    public void setClimateSummerAvg(int climateSummerAvg) {
        this.climateSummerAvg = climateSummerAvg;
    }

    @JsonProperty("climateWinterAvg")
    public int getClimateWinterAvg() {
        return climateWinterAvg;
    }

    public void setClimateWinterAvg(int climateWinterAvg) {
        this.climateWinterAvg = climateWinterAvg;
    }

    @JsonProperty("topThingsToDo")
    public List<String> getTopThingsToDo() {
        return topThingsToDo;
    }

    public void setTopThingsToDo(List<String> topThingsToDo) {
        this.topThingsToDo = topThingsToDo;
    }
}
