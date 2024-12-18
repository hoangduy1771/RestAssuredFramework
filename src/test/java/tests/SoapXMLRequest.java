package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

public class SoapXMLRequest {

    @Test
    public void validateSoapXML() throws Exception {

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
                body("//*:AddResult.text()", equalTo("5"));

//        Use https://www.freeformatter.com/xpath-tester.html or https://www.xrmtoolbox.com/ to validate xml response
//        on https://www.freeformatter.com/xpath-tester.html use //*:AddResult/text()

    }

}
