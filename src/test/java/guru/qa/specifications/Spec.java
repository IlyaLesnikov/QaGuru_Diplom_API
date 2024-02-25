package guru.qa.specifications;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static guru.qa.helpres.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class Spec {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .baseUri("https://restful-booker.herokuapp.com");

    public static ResponseSpecification responseSpec200OK = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(STATUS)
                .log(BODY)
                .build();
    public static ResponseSpecification responseSpecification400BadRequest = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .log(STATUS)
                .log(BODY)
                .build();
    public static ResponseSpecification responseSpecification201Created = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();
}
