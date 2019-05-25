/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.motiejus.DAO.DAO;
import lt.eif.viko.motiejus.DAO.DAOEventDb;
import lt.eif.viko.motiejus.entities.Country;
import lt.eif.viko.motiejus.entities.Event;

/**
 *
 * @author motsa
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventsResource {
    DAO dao;
    String countryName;
    
    public EventsResource(String countryName) throws SQLException, ClassNotFoundException {
        dao = new DAOEventDb(countryName);
        this.countryName = countryName;
    }
    
    @Context
    UriInfo uriInfo;
    
    @GET
    public List<Event> loadEvents() {
        List<Event> events = dao.load();
        
        UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                    .path(DestinationsResource.class, "getCountriesResource");
        
        for (Event event : events) {
            Link lnk = Link.fromUri(builder.build(countryName) + "/" + event.getId()).rel("self").build();
            event.setLink(lnk);
        }
        
        return events;
    }
    
    @POST
    @Consumes("application/json")
    public Response createCountry(Event event) {
        dao.insert(event);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + event.getId()).rel("self").build();
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).location(lnk.getUri()).build();
    }
    
    
}
