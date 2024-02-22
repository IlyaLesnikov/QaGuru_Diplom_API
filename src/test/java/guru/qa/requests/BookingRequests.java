package guru.qa.requests;

import guru.qa.endpoints.EndPoints;
import guru.qa.models.*;

import static guru.qa.specifications.Spec.requestSpec;
import static guru.qa.specifications.Spec.responseSpec200OK;
import static io.restassured.RestAssured.given;

public class BookingRequests {
    EndPoints endPoint = new EndPoints();
    public String getBookId() {
        CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2020-02-23", "2024-02-23");
        CreateBookingRequest createBookingRequest = new CreateBookingRequest("Ilya", "Lesnikov", 1550, true, bookingDates, "QA");
        return given()
                .spec(requestSpec)
                .body(createBookingRequest)
                .when()
                .post(endPoint.getBOOK_PATH())
                .then()
                .spec(responseSpec200OK)
                .extract().body().jsonPath().getString("bookingid");

    }
    public String getCookie() {
        CreateTokenRequest dataAuth = new CreateTokenRequest("admin", "password123");
        return given()
                .spec(requestSpec)
                .body(dataAuth)
                .when()
                .post(endPoint.getAUTH_PATH())
                .then()
                .spec(responseSpec200OK)
                .extract().body().jsonPath().getString("token");
    }
}
