package org.ra.test.countries;

import io.restassured.RestAssured;
import io.restassured.internal.http.Status;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.ra.test.Context;


/**
 * Created by mburunducova on 7/17/2018.
 */
public class SmokeTest {

    @Test
    public void smoke() throws JSONException {
        String path = Context.context.getEndPointAll();
        Response response = RestAssured.given().get(path);
        //I found strange that RestAssured does not contain enum with http codes
        Assert.assertEquals(Status.find(response.getStatusCode()), Status.SUCCESS);
        RestAssured.get(path).then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Context.context
                .getSchema()));
    }

}
