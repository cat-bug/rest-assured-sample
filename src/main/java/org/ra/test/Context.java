package org.ra.test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mburunducova on 7/17/2018.
 */
public class Context {
    private static String baseurl;
    private static String path;
    private static String endPointAll;
    private static String endPointSearchName;
    private static String endPointSearchRegion;
    private static InputStream schema;

    public static Context context = new Context();

    private Context() {
        Properties prop = new Properties();

        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("countries.properties");
            prop.load(stream);
            baseurl = prop.getProperty("base.url");
            path = prop.getProperty("path");

            stream = this.getClass().getClassLoader().getResourceAsStream("countries-paths.properties");
            prop.load(stream);

            endPointAll = prop.getProperty("all");
            endPointSearchName = prop.getProperty("search.by.name");
            endPointSearchRegion = prop.getProperty("search.by.regional.block");

            schema = this.getClass().getClassLoader().getResourceAsStream("countries-schema.json");

        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseurl)
                .setBasePath(path)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();


    }

    public String getEndPointAll() {
        return endPointAll;
    }

    public String getEndPointSearchName(String name) {
        return endPointSearchName.replace("{name}", name);
    }

    public String getEndPointSearchRegion(String region) {
        return endPointSearchRegion.replace("{regionalbloc}", region);
    }

    public InputStream getSchema() {
        return schema;
    }

    public Context setToiletSpec() {
        String toiletBaseURI = null;
        String toiletPath = null;
        Properties prop = new Properties();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("toilet.properties");
        try {
            prop.load(stream);
            toiletBaseURI = prop.getProperty("base.url");
            toiletPath = prop.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(prop.getProperty("username"));
        authScheme.setPassword(prop.getProperty("password"));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(toiletBaseURI)
                .setBasePath(toiletPath)
                .setContentType("application/json")
                .setAccept("application/json")
                .setAuth(authScheme)
                .build();
        return this;
    }

}
