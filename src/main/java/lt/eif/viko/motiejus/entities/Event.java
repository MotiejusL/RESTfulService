/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.core.Link;


/**
 *
 * @author motsa
 */
@JsonRootName(value = "Event")
public class Event {
    
    public Event() {}
    
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String city;
    private String description;
    private String countryName;
    private Link link;

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @JsonProperty("link")
    public URI getLink() {
        return link.getUri();
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("date")
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonProperty("time")
    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(dtf);
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
