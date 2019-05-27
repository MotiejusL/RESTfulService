/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.motiejus.DAO.DAOEventDb;
import lt.eif.viko.motiejus.entities.Event;

/**
 * Class to represent an object of class Event as a resource for rest service
 *
 * @author motsa
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventsResource {

    DAOEventDb dao;
    String countryName;

    /**
     * EventsResource constructor
     *
     */
    public EventsResource(String countryName) throws SQLException, ClassNotFoundException {
        dao = new DAOEventDb(countryName);
        this.countryName = countryName;
    }

    @Context
    UriInfo uriInfo;

    /**
     * Method used to load events from database
     *
     * @return a list of class Event
     */
    @GET
    public List<Event> loadEvents(
            @QueryParam("city") String city,
            @QueryParam("date") String date) {
        List<Event> events = new ArrayList<>();
        if (city == null && date == null) {
            events = dao.load();
        } else if (city != null && date == null) {
            events = dao.loadByCity(city);
        } else if (city == null && date != null) {
            events = dao.loadByDate(date);
        }

        UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                .path(DestinationsResource.class, "getCountriesResource");

        for (Event event : events) {
            Link lnk = Link.fromUri(builder.build(countryName) + "/events/" + event.getId()).rel("self").build();
            event.setLink(lnk);
        }

        return events;
    }

    /**
     * Method used insert new Event into database
     *
     * @return a a response that event was inserted
     */
    @POST
    @Consumes("application/json")
    public Response createEvent(Event event) {
        dao.insert(event);
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).build();
    }

    /**
     * Method used to get Event from database
     *
     * @return an Event object
     */
    @GET
    @Path("/{eventId}")
    public Event getEvent(@PathParam("eventId") int id) {
        Event event = (Event) dao.get(id);
        UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                .path(DestinationsResource.class, "getCountriesResource");
        Link lnk = Link.fromUri(builder.build(countryName) + "/events/" + event.getId()).rel("self").build();
        event.setLink(lnk);
        return event;
    }

    /**
     * Method used to delete event from database
     *
     * @return a response that event was deleted
     */
    @DELETE
    @Path("/{eventId}")
    public Response deleteEvent(@PathParam("eventId") int id) {
        Event event = (Event) dao.get(id);
        dao.delete(event);

        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
    }

    /**
     * Method used to update Event from database
     *
     * @return a response that Event was updated
     */
    @PUT
    @Path("/{eventId}")
    @Consumes("application/json")
    public Response updateCountry(Event event) {
        dao.update(event);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

}
