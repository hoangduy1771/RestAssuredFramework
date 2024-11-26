package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestOnLocalAPI {

    @BeforeClass
    public static void setUp() {
        baseURI = "http://localhost:3000/";
    }

    @Test
    public void get() {

        given().
                get("/users").
                then().statusCode(200).log().all();

    }

    @Test
    public void post() {

        JSONObject request = new JSONObject();
        request.put("firstName", "Eugene");
        request.put("lastName", "Krabs");
        request.put("subjectId", 3);

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/users").
                then().statusCode(201).log().all();
    }

    @Test
    public void put() {

        JSONObject request = new JSONObject();
        request.put("firstName", "Eugene Harold");
        request.put("lastName", "Krabs");
        request.put("subjectId", 2);

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/58f3").
                then().statusCode(200).log().all();
    }

    @Test
    public void patch() {

        JSONObject request = new JSONObject();
        request.put("firstName", "Eugene");

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/users/58f3").
                then().statusCode(200).log().all();
    }

    @Test
    public void delete() {

        JSONObject request = new JSONObject();
        request.put("firstName", "Eugene");

        when().delete("users/58f3").then().statusCode(200);
    }

}
