/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.motiejus.DAO.DAO;
import lt.eif.viko.motiejus.DAO.DAOCountryDb;
import lt.eif.viko.motiejus.entities.Country;

/**
 *
 * @author motsa
 */
@Path("/{countryName}")
@Produces(MediaType.APPLICATION_JSON)
public class CountriesResource {
    String countryName;
    DAO dao;
    
    public CountriesResource(String countryName) throws SQLException, ClassNotFoundException {
        dao = new DAOCountryDb();
        this.countryName = countryName;
    }
    
    @Context
    UriInfo uriInfo;
    
    @GET
    public Country getCountry() {
        Object StringAsObject = countryName;
        Country country = (Country) dao.get(StringAsObject);
        
            UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                    .path(DestinationsResource.class, "getCountriesResource");
            Link link = Link.fromUri(builder.build(countryName)).rel("self").build();
            country.setLink(link);

        return country;
    }
    
    @PUT
    @Consumes("application/json")
    public Response updateCountry(Country country) {
        dao.update(country);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }
    
    @DELETE
    public Response deleteCountry() {
        Country country = (Country) dao.get(countryName);
        dao.delete(country);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }
    
    @Path("/events")
    public EventsResource getEventsResource() throws SQLException, ClassNotFoundException {
        EventsResource eventsResource = new EventsResource(countryName);
        return eventsResource;
    }
    
    @Path("/activities")
    public TopThingsToDoResource getActivities() throws SQLException, ClassNotFoundException {
        TopThingsToDoResource activitiesResource = new TopThingsToDoResource(countryName);
        return activitiesResource;
    }
    
}
