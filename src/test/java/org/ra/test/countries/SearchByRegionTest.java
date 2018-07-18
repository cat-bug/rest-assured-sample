package org.ra.test.countries;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.ra.test.Context;

import static org.hamcrest.Matchers.is;

/**
 * Created by mburunducova on 7/18/2018.
 */
public class SearchByRegionTest {

    @Test
    public void searchByRegionalBlock(){
        String path = Context.context.getEndPointSearchRegion("NAFTA");
        Response response = RestAssured.get(path);
        JSONArray jsonResponse = new JSONArray(response.asString());
        Assert.assertThat(jsonResponse.length(), is(3));
    }

}
