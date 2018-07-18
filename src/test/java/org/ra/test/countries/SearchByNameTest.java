package org.ra.test.countries;

import io.restassured.RestAssured;
import org.junit.Test;
import org.ra.test.Context;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

/**
 * Created by mburunducova on 7/18/2018.
 */
public class SearchByNameTest {

    @Test
    public void checkSubRegion() {
        String path = Context.context.getEndPointSearchName("Moldova");

//            Line fails, produces output:
//    >JSON path subregion doesn't match.
//    >Expected: equalToIgnoringCase("[Eastern Europe]")
//    >Actual: [Eastern Europe]
//    RestAssured does not handle all matchers from hamcrest?
//        RestAssured.get(path).then().body("subregion",equalToIgnoringCase("[Eastern Europe]"));

        //here I need to assert as hasItem, because an array of strings is returned
        RestAssured.get(path).then().body("subregion",hasItem("Eastern Europe"));
        //because of it, cannot assert nested values like:
//        RestAssured.get(path).then().body("currencies.code", hasItem("[[MDL]]"));
        //It is because of structure of json file, it contains arrays in array
//        (so you can use your array while you are using your array :)
        //but I dont really understand how to get nested array (and assert its content)
    }

    @Test
    public void checkISOCode(){
        String path = Context.context.getEndPointSearchName("Moldova");
        //    ISO 3166-1 alpha-3
        RestAssured.get(path).then().body("cioc", hasItem("MDA"));
    }


}
