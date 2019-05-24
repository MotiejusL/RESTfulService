/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
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
    
    DAO dao;
    
    public DestinationsResource() throws SQLException, ClassNotFoundException {
        dao = new DAOCountryDb();
    }
    
    @Context
    UriInfo uriInfo;
    
    @GET
    public Destinations getDestinations() {
        Destinations destinations = new Destinations();
        destinations.setCountries(dao.load());
        Link link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
        destinations.setLink(link);
        
        for (Country country : destinations.getCountries()) {
            Link lnk = Link.fromUri(uriInfo.getPath() + "/" + country.getName()).rel("self").build();
            country.setLink(lnk);
        }
        
        return destinations;
    }
}
