package guru.qa.requests;

import guru.qa.endpoints.EndPoints;
import guru.qa.models.CreateBooking.CreateBookingDatesRequest;
import guru.qa.models.CreateBooking.CreateBookingRequest;
import guru.qa.models.CreateToken.CreateTokenRequest;
import guru.qa.specifications.Spec;
import static guru.qa.endpoints.EndPoints.AUTH_PATH;
import static guru.qa.endpoints.EndPoints.BOOK_PATH;
import static guru.qa.specifications.Spec.requestSpec;
import static io.restassured.RestAssured.given;

public class BookingRequests {
    EndPoints endPoint = new EndPoints();
    public String getBookId() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2020-02-23", "2024-02-23");
        CreateBookingRequest createBookingRequest = new CreateBookingRequest("Ilya", "Lesnikov", 1550, true, bookingDates, "QA");
        return given()
                .spec(requestSpec)
                .body(createBookingRequest)
                .when()
                .post(BOOK_PATH)
                .then()
                .extract().body().jsonPath().getString("bookingid");

    }
    public String getCookie() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        CreateTokenRequest dataAuth = new CreateTokenRequest("admin", "password123");
        return given()
                .spec(requestSpec)
                .body(dataAuth)
                .when()
                .post(AUTH_PATH)
                .then()
                .extract().body().jsonPath().getString("token");
    }
}
