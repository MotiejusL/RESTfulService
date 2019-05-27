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
import javax.ws.rs.core.Response;
import lt.eif.viko.motiejus.entities.Country;
import lt.eif.viko.motiejus.entities.Destinations;
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
public class DestinationsResourceTest {

    public DestinationsResourceTest() {
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
     * Test of getDestinations method, of class DestinationsResource.
     */
    @Test
    public void testGetDestinations() {
        when().
                get("/destinations").
                then().
                statusCode(200);
    }

    /**
     * Test of createCountry method, of class DestinationsResource.
     */
    @Test
    public void testCreateCountry() {
        given()
                .contentType("application/json")
                .body("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"name\": \"Test\",\n"
                        + "    \"language\": \"German\",\n"
                        + "    \"currency\": \"EUR\",\n"
                        + "    \"capitalCity\": \"Berlin\",\n"
                        + "    \"generalInformation\": \"Germany has a diverse landscape of rich culture and history. In the heart of Europe, fairytale castles rest high on craggy mountaintops, and tiny romantic villages enchant with chocolate box charm and the typical German Gemütlichkeit.\",\n"
                        + "    \"climateSummerAvg\": 18,\n"
                        + "    \"climateWinterAvg\": -4\n"
                        + "}").
                when()
                .post("/destinations").
                then()
                .statusCode(201);
    }

}
