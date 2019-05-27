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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.motiejus.DAO.DAOActivitiesDb;
import lt.eif.viko.motiejus.DAO.DAOEventDb;
import lt.eif.viko.motiejus.entities.Event;
import lt.eif.viko.motiejus.entities.ThingToDo;

/**
 *
 * @author motsa
 */
@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
public class TopThingsToDoResource {
    
    DAOActivitiesDb dao;
    String countryName;
    
    public TopThingsToDoResource(String countryName) throws SQLException, ClassNotFoundException {
        dao = new DAOActivitiesDb(countryName);
        this.countryName = countryName;
    }
    
    @Context
    UriInfo uriInfo;
    
    @GET
    public List<ThingToDo> loadActivities() {
        List<ThingToDo> topThingsToDo = new ArrayList<>();
            topThingsToDo = dao.load();
        
        UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                    .path(DestinationsResource.class, "getCountriesResource");
        
        for (ThingToDo activity : topThingsToDo) {
            Link lnk = Link.fromUri(builder.build(countryName) + "/activities/" + activity.getId()).rel("self").build();
            activity.setLink(lnk);
        }
        
        return topThingsToDo;
    }
    
    @GET
    @Path("/{activityId}")
    public ThingToDo getEvent(@PathParam("activityId") int id) {
        ThingToDo thingToDo = (ThingToDo) dao.get(id);
        UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                    .path(DestinationsResource.class, "getCountriesResource");
        Link lnk = Link.fromUri(builder.build(countryName) + "/activities/" + thingToDo.getId()).rel("self").build();
        thingToDo.setLink(lnk);
        return thingToDo;
    }
    
    @POST
    @Consumes("application/json")
    public Response createActivity(ThingToDo thingToDo) {
        dao.insert(thingToDo);
        //Link lnk = Link.fromUri(uriInfo.getPath() + "/" + event.getId()).rel("self").build();
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).build();
    }
    
    @DELETE
    @Path("/{activityId}")
    public Response deleteEvent(@PathParam("eventId") int id) {
        ThingToDo thingToDo = (ThingToDo) dao.get(id);
        dao.delete(thingToDo);
        
        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
    }
    
    @PUT
    @Path("/{activityId}")
    @Consumes("application/json")
    public Response updateCountry(ThingToDo thingToDo) {
        dao.update(thingToDo);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }
    
}
