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
public class CountriesResourceTest {

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
     * Test of getCountry method, of class CountriesResource.
     */
    @Test
    public void testGetCountry() {
        when().
                get("/destinations/Germany").
                then().
                statusCode(200);
    }

    /**
     * Test of updateCountry method, of class CountriesResource.
     */
    @Test
    public void testUpdateCountry() {
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
                .put("/destinations/Test").
                then()
                .statusCode(200);
    }

    /**
     * Test of deleteCountry method, of class CountriesResource.
     */
    @Test
    public void testDeleteCountry() {
        when().
                delete("/destinations/{name}", "Test").
                then().
                statusCode(500);
    }

}
