/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.Service;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import javax.ws.rs.core.Response;
import lt.eif.viko.motiejus.entities.Event;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assume;

/**
 *
 * @author motsa
 */
public class EventsResourceTest {

    public EventsResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("http://localhost:8080/RESTfulService/rest/destinations");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        if (code == 404) {
            Assume.assumeTrue(false);
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost/RESTfulService/rest";
        RestAssured.port = 8080;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadEvents method, of class EventsResource.
     */
    @Test
    public void testLoadEvents() {
        when().
                get("/destinations/Germany").
                then().
                statusCode(200);
    }

    /**
     * Test of createEvent method, of class EventsResource.
     */
    @Test
    public void testCreateEvent() {
        given()
                .contentType("application/json")
                .body("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"name\": \"Test\",\n"
                        + "    \"date\": \"2017-05-16\",\n"
                        + "    \"time\": \"22:15\",\n"
                        + "    \"city\": \"city\",\n"
                        + "    \"description\": \"des\",\n"
                        + "    \"countryName\": \"Germany\"\n"
                        + "}").
                when()
                .post("/destinations/Germany/events").
                then()
                .statusCode(201);
    }

    /**
     * Test of getEvent method, of class EventsResource.
     */
    @Test
    public void testGetEvent() {
        when().
                get("/destinations/Germany/events/3").
                then().
                statusCode(200);
    }

    /**
     * Test of updateCountry method, of class EventsResource.
     */
    @Test
    public void testUpdateCountry() {
        given()
                .contentType("application/json")
                .body("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"name\": \"Test\",\n"
                        + "    \"date\": \"2017-05-16\",\n"
                        + "    \"time\": \"22:15\",\n"
                        + "    \"city\": \"city\",\n"
                        + "    \"description\": \"des\",\n"
                        + "    \"countryName\": \"Germany\"\n"
                        + "}").
                when()
                .put("/destinations/Test/events/0").
                then()
                .statusCode(200);
    }
    
    /**
     * Test of deleteEvent method, of class EventsResource.
     */
    @Test
    public void testDeleteEvent() {
        when().
                delete("/destinations/Germany/events/{id}", 0).
                then().
                statusCode(500);
    }

}
