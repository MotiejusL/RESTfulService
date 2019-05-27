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
import lt.eif.viko.motiejus.entities.ThingToDo;

/**
 * Class to represent an object of class TopThingsToDo as a resource for rest
 * service
 *
 * @author motsa
 */
@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
public class TopThingsToDoResource {

    DAOActivitiesDb dao;
    String countryName;

    /**
     * TopThingsToDoResource constructor
     *
     */
    public TopThingsToDoResource(String countryName) throws SQLException, ClassNotFoundException {
        dao = new DAOActivitiesDb(countryName);
        this.countryName = countryName;
    }

    @Context
    UriInfo uriInfo;

    /**
     * Method used to get activities list from database
     *
     * @return an object of class Country
     */
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

    /**
     * Method used to get activity by id from database
     *
     * @return an object of class ThingToDO
     */
    @GET
    @Path("/{activityId}")
    public ThingToDo getActivity(@PathParam("activityId") int id) {
        ThingToDo thingToDo = (ThingToDo) dao.get(id);
        UriBuilder builder = UriBuilder.fromResource(DestinationsResource.class)
                .path(DestinationsResource.class, "getCountriesResource");
        Link lnk = Link.fromUri(builder.build(countryName) + "/activities/" + thingToDo.getId()).rel("self").build();
        thingToDo.setLink(lnk);
        return thingToDo;
    }

    /**
     * Method used to create activity and insert it into database
     *
     * @return a response that activity was inserted
     */
    @POST
    @Consumes("application/json")
    public Response createActivity(ThingToDo thingToDo) {
        dao.insert(thingToDo);
        //Link lnk = Link.fromUri(uriInfo.getPath() + "/" + event.getId()).rel("self").build();
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).build();
    }

    /**
     * Method used to delete activity from database
     *
     * @return a response that activity was deleted
     */
    @DELETE
    @Path("/{activityId}")
    public Response deleteActivity(@PathParam("eventId") int id) {
        ThingToDo thingToDo = (ThingToDo) dao.get(id);
        dao.delete(thingToDo);

        return Response.status(javax.ws.rs.core.Response.Status.OK).build();
    }

    /**
     * Method used to update activity from database
     *
     * @return a response that activity was updated
     */
    @PUT
    @Path("/{activityId}")
    @Consumes("application/json")
    public Response updateActivity(ThingToDo thingToDo) {
        dao.update(thingToDo);
        return Response.status((javax.ws.rs.core.Response.Status.OK)).build();
    }

}
