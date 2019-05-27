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
 * Class to represent an object of class Countries as a resource for rest
 * service
 *
 * @author motsa
 */
@Path("/{countryName}")
@Produces(MediaType.APPLICATION_JSON)
public class CountriesResource {

    String countryName;
    DAO dao;

    /**
     * CountriesResource constructor
     *
     */
    public CountriesResource(String countryName) throws SQLException, ClassNotFoundException {
        dao = new DAOCountryDb();
        this.countryName = countryName;
    }

    @Context
    UriInfo uriInfo;

    /**
     * Method used to get Country from database
     *
     * @return an object of class Country
     */
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

    /**
     * Method used to update Country from database
     *
     * @return a response that object was updated
     */
    @PUT
    @Consumes("application/json")
    public Response updateCountry(Country country) {
        dao.update(country);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

    /**
     * Method used to delete Country from database
     *
     * @return a response that object was deleted
     */
    @DELETE
    public Response deleteCountry() {
        Country country = (Country) dao.get(countryName);
        dao.delete(country);
        if (country == null) {
            return Response.status((javax.ws.rs.core.Response.Status.NOT_FOUND)).build();
        }
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

    /**
     * Method used for events path
     *
     * @return EventResource that represents new path
     */
    @Path("/events")
    public EventsResource getEventsResource() throws SQLException, ClassNotFoundException {
        EventsResource eventsResource = new EventsResource(countryName);
        return eventsResource;
    }

    /**
     * Method used for activities path
     *
     * @return TopThingsToDoResource that represents new path
     */
    @Path("/activities")
    public TopThingsToDoResource getActivities() throws SQLException, ClassNotFoundException {
        TopThingsToDoResource activitiesResource = new TopThingsToDoResource(countryName);
        return activitiesResource;
    }

}
