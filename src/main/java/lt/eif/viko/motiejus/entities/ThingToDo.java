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
 * Represents information about ThingToDO
 *
 * @author motsa
 */
@JsonRootName(value = "ThingToDo")
public class ThingToDo {

    /**
     * Default ThingToDo constructor
     */
    public ThingToDo() {
    }

    private int id;
    private String name;
    private String countryName;
    private String Description;
    private Link link;

    /**
     * Get uri of link
     *
     * @return uri of thingtodo
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
     * @return id of thingtodo
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
     * Get name
     *
     * @return name of thingtodo
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
     * Get countryName
     *
     * @return countryName of thingtodo
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
     * Get description
     *
     * @return description of thingtodo
     */
    @JsonProperty("description")
    public String getDescription() {
        return Description;
    }

    /**
     * Set description
     *
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
