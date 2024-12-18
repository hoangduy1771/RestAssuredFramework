package tests;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.*;

public class XMLSchemaValidator {

    @Test
    public void schemaValidation() throws Exception {

        baseURI = "http://www.dneonline.com";

//        Using SOAP api here: http://www.dneonline.com/calculator.asmx
//        using chrome extension: Wizdler

        File file = new File("src/test/resources/soap-data/add.xml");

        if(file.exists()) {
            System.out.println(">> File exist");
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");

        given().
                contentType("text/xml").
                accept(ContentType.XML).
                body(requestBody).
                when().
                post("/calculator.asmx").
                then().
                statusCode(200).log().all().
                and().
                body("//*:AddResult.text()", equalTo("5")).
                and().
                assertThat().body(matchesXsdInClasspath("calculator-schema.xsd"));

    }

}
