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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assume;

/**
 *
 * @author motsa
 */
public class TopThingsToDoResourceTest {

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

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost/RESTfulService/rest";
        RestAssured.port = 8080;
    }

    /**
     * Test of loadActivities method, of class TopThingsToDoResource.
     */
    @Test
    public void testLoadActivities() {
        when().
                get("/destinations/Germany/activities").
                then().
                statusCode(200);
    }

    /**
     * Test of getEvent method, of class TopThingsToDoResource.
     */
    @Test
    public void testGetActivity() {
        when().
                get("/destinations/Germany/activities/1").
                then().
                statusCode(200);
    }

    /**
     * Test of createActivity method, of class TopThingsToDoResource.
     */
    @Test
    public void testCreateActivity() {
        given()
                .contentType("application/json")
                .body("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"name\": \"TestActivity\",\n"
                        + "    \"countryName\": \"Germany\",\n"
                        + "    \"description\": \"Stroll through art &amp; culture in our museums and experience a fascinating world with breathtaking exponents and exciting exhibits from every century\"\n"
                        + "}").
                when()
                .post("/destinations/Germany/activities").
                then()
                .statusCode(201);
    }

    /**
     * Test of updateCountry method, of class TopThingsToDoResource.
     */
    @Test
    public void testUpdateActivity() {
        given()
                .contentType("application/json")
                .body("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"name\": \"TestActivity\",\n"
                        + "    \"countryName\": \"Germany\",\n"
                        + "    \"description\": \"Stroll through art &amp; culture in our museums and experience a fascinating world with breathtaking exponents and exciting exhibits from every century\"\n"
                        + "}").
                when()
                .put("/destinations/Germany/activities/0").
                then()
                .statusCode(200);
    }

    /**
     * Test of deleteEvent method, of class TopThingsToDoResource.
     */
    @Test
    public void testDeleteActivity() {
        when().
                delete("/destinations/Germany/activities/{id}", 0).
                then().
                statusCode(500);
    }

}
