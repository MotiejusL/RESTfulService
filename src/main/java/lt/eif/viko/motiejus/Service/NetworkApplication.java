/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

/**
 * Class used to register JAX-RS REST resource
 *
 * @author motsa
 */
@ApplicationPath("/rest")
public class NetworkApplication extends Application {

    private final Set<Object> singletons = new HashSet<>();
    private final Set<Class<?>> empty = new HashSet<>();

    /**
     * NetworkApplication constructor that adds KolegijaResource
     *
     */
    public NetworkApplication() throws SQLException, ClassNotFoundException {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        singletons.add(corsFilter);
        singletons.add(new DestinationsResource());
    }

    /**
     * Method for getting Classes
     *
     */
    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    /**
     * Method for getting Singletons
     *
     */
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
