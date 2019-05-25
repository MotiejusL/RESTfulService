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
 *
 * @author motsa
 */
public class Destinations {
    
    public Destinations() {}
    
    private List<Country> countries = new ArrayList<>();
    private Link link;

    @JsonProperty("link")
    public URI getLink() {
        return link.getUri();
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @JsonProperty("countrie")
    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
    
    public void pushToCountries(Country country) {
        countries.add(country);
    }
    
    public void deleteFromCountries(Country country) {
        countries.remove(country);
    }
}
