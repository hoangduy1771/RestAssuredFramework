package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PutPatchDeleteExamples {


    @Test
    public void testPut() {

        JSONObject request = new JSONObject();
        request.put("name", "Duy");
        request.put("job", "Student");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
        contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testPatch() {

        JSONObject request = new JSONObject();
        request.put("name", "Duy");
        request.put("job", "Student");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testDelete() {

        JSONObject request = new JSONObject();
        request.put("name", "Duy");
        request.put("job", "Student");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                delete("/users/2").
                then().
                statusCode(204).
                log().all();
    }

//    Create a fake api endpoint
//    https://github.com/typicode/json-server
//    https://www.npmjs.com/package/json-server/v/0.17.4




}
