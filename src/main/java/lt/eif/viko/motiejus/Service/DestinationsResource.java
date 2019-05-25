/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lt.eif.viko.motiejus.DAO.DAO;
import lt.eif.viko.motiejus.DAO.DAOCountryDb;
import lt.eif.viko.motiejus.entities.Country;
import lt.eif.viko.motiejus.entities.Destinations;

/**
 *
 * @author motsa
 */
@Path("/destinations")
@Produces(MediaType.APPLICATION_JSON)
public class DestinationsResource {
    
    DAOCountryDb dao;
    
    public DestinationsResource() throws SQLException, ClassNotFoundException {
        dao = new DAOCountryDb();
    }
    
    @Context
    UriInfo uriInfo;
    
    @GET
    public Destinations getDestinations(
                        @QueryParam("language") String language,
                        @QueryParam("currency") String currency) {
        Destinations destinations = new Destinations();
        if (language == null && currency == null)
            destinations.setCountries(dao.load());
        else if (language != null && currency == null)
            destinations.setCountries(dao.loadByLanguage(language));
        else if (language == null && currency != null)
            destinations.setCountries(dao.loadByCurrency(currency));
        Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
        destinations.setLink(link);
        
        for (Country country : destinations.getCountries()) {
            Link lnk = Link.fromUri(uriInfo.getPath() + "/" + country.getName()).rel("self").build();
            country.setLink(lnk);
        }
        
        return destinations;
    }
    
    @POST
    @Consumes("application/json")
    public Response createCountry(Country country) {
        dao.insert(country);
        Link lnk = Link.fromUri(uriInfo.getPath() + "/" + country.getId()).rel("self").build();
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).location(lnk.getUri()).build();
    }
    
    @Path("/{countryName}")
    public CountriesResource getCountriesResource(@PathParam("countryName") String name) throws SQLException, ClassNotFoundException {
        CountriesResource countriesResource = new CountriesResource(name);
        
        return countriesResource;
    }
}
