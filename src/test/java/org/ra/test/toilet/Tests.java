package org.ra.test.toilet;
import io.restassured.RestAssured;
import org.junit.Test;
import org.ra.test.Context;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by mburunducova on 7/18/2018.
 */
public class Tests {

    //you can see history of requests on http://ptsv2.com/t/testtoilet

    private final String bodyResponse = "Thank you for this dump. I hope you have a lovely day!";

    @Test
    public void testPost(){
        Context.context.setToiletSpec();
        RestAssured.given()
                .param("color", "brown")
                .expect()
                .statusCode(200)
                .when()
                .post()
                .then()
                .assertThat()
                .body(equalTo(bodyResponse));
    }

    @Test
    public void testGet(){
        Context.context.setToiletSpec();
        RestAssured.given()
                .param("id", "1")
                .expect()
                .statusCode(200)
                .when()
                .get()
                .then()
                .assertThat()
                .body(equalTo(bodyResponse));
    }

    @Test
    public void unauthorized(){
        Context.context.setToiletSpec();
        RestAssured.given()
                .auth().basic("invalid", "invalid")
                .expect()
                .statusCode(401)
                .when()
                .post();
    }
}
