package guru.qa.tests;

import guru.qa.endpoints.EndPoints;
import guru.qa.models.CreateBooking.CreateBookingDatesRequest;
import guru.qa.models.CreateBooking.CreateBookingRequest;
import guru.qa.models.CreateBooking.CreateBookingResponse;
import guru.qa.models.GetBooking.GetBookingResponse;
import guru.qa.requests.BookingRequests;
import guru.qa.specifications.Spec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.endpoints.EndPoints.BOOK_PATH;
import static guru.qa.helpres.CustomAllureListener.withCustomTemplates;
import static guru.qa.specifications.Spec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@DisplayName("API тесты к сервису \"Книги\"")
public class ApiTests {
    EndPoints endPoints = new EndPoints();
    BookingRequests bookingRequests = new BookingRequests();

    @Test
    @Tag("API")
    @DisplayName("Создание книги")
    protected void bookCreationTest() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        CreateBookingDatesRequest createBookingDates = new CreateBookingDatesRequest("2020-01-01", "2023-01-01");
        CreateBookingRequest createBooking = new CreateBookingRequest("Jim", "Brown", 111, true, createBookingDates, "Breakfast");

        CreateBookingResponse createBookingResponse = step("Создание книги", () ->
                given()
                        .spec(requestSpec)
                        .body(createBooking)
                        .when()
                        .post(BOOK_PATH)
                        .then()
                        .extract().as(CreateBookingResponse.class));

        step("Проверка создания книги", () -> {
            Assertions.assertEquals(createBooking.getFirstname(), createBookingResponse.getBooking().getFirstname());
            Assertions.assertEquals(createBooking.getLastname(), createBookingResponse.getBooking().getLastname());
            Assertions.assertEquals(createBooking.getTotalprice(), createBookingResponse.getBooking().getTotalprice());
            Assertions.assertTrue(createBookingResponse.getBooking().getDepositpaid());
            Assertions.assertEquals(createBookingDates.getCheckin(), createBookingResponse.getBooking().getBookingdates().getCheckin());
            Assertions.assertEquals(createBookingDates.getCheckout(), createBookingResponse.getBooking().getBookingdates().getCheckout());
            Assertions.assertEquals(createBooking.getAdditionalneeds(), createBookingResponse.getBooking().getAdditionalneeds());
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Получение определенной книги")
    protected void gettingACertainBookTest() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        GetBookingResponse getBookingRequest = step("Получение определенной книги", () -> given()
                .spec(requestSpec)
                .when()
                .get(BOOK_PATH + "/" + bookingRequests.getBookId())
                .then()
                .log().body()
                .extract().as(GetBookingResponse.class));

        step("Проверка полученной книги", () -> {
            Assertions.assertEquals("Ilya", getBookingRequest.getFirstname());
            Assertions.assertEquals("Lesnikov", getBookingRequest.getLastname());
            Assertions.assertEquals(1550, getBookingRequest.getTotalprice());
            Assertions.assertTrue(getBookingRequest.getDepositpaid());
            Assertions.assertEquals("2020-02-23", getBookingRequest.getBookingdates().getCheckin());
            Assertions.assertEquals("2024-02-23", getBookingRequest.getBookingdates().getCheckout());
            Assertions.assertEquals("QA", getBookingRequest.getAdditionalneeds());
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Частичное обновление информации книги")
    protected void partialUpdateOfBookInformationTest() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        CreateBookingDatesRequest createBookingDates = new CreateBookingDatesRequest(null, null);
        CreateBookingRequest updateBooking = new CreateBookingRequest("Jim", "Brown", null, null, null, null);

        GetBookingResponse updateBookingResponse = step("Частичное обновление информации о книги", () ->
                given()
                        .spec(requestSpec)
                        .body(updateBooking)
                        .header("Cookie", "token=" + bookingRequests.getCookie())
                        .when()
                        .patch(BOOK_PATH + "/" + bookingRequests.getBookId())
                        .then()
                        .extract().as(GetBookingResponse.class));

        step("Проверка обновленной информации о книги", () -> {
            Assertions.assertEquals(updateBooking.getFirstname(), updateBookingResponse.getFirstname());
            Assertions.assertEquals(updateBooking.getLastname(), updateBookingResponse.getLastname());
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Обновление информации о книге")
    protected void updatingBookInformationTest() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        CreateBookingDatesRequest createBookingDates = new CreateBookingDatesRequest("2019-01-01", "2020-01-01");
        CreateBookingRequest updateBooking = new CreateBookingRequest("Jim", "Brown", 10000, false, createBookingDates, "QC");

        GetBookingResponse updateBookingResponse = step("Обновление всей информации о книге", () ->
                given()
                        .filter(withCustomTemplates())
                        .spec(requestSpec)
                        .body(updateBooking)
                        .header("Cookie", "token=" + bookingRequests.getCookie())
                        .when()
                        .put(BOOK_PATH + "/" + bookingRequests.getBookId())
                        .then()
                        .extract().as(GetBookingResponse.class));

        step("Проверка обновленной информации о книге", () -> {
            Assertions.assertEquals(updateBooking.getFirstname(), updateBookingResponse.getFirstname());
            Assertions.assertEquals(updateBooking.getLastname(), updateBookingResponse.getLastname());
            Assertions.assertEquals(updateBooking.getTotalprice(), updateBookingResponse.getTotalprice());
            Assertions.assertFalse(updateBookingResponse.getDepositpaid());
            Assertions.assertEquals(createBookingDates.getCheckin(), updateBookingResponse.getBookingdates().getCheckin());
            Assertions.assertEquals(createBookingDates.getCheckout(), updateBookingResponse.getBookingdates().getCheckout());
            Assertions.assertEquals(updateBooking.getAdditionalneeds(), updateBookingResponse.getAdditionalneeds());
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Удаление книги")
    protected void deletingABook() {
        Spec.initResponseSpec(Spec.responseSpec(201));
        step("Удаление книги", () ->
        given()
                .spec(requestSpec)
                .header("Cookie", "token=" + bookingRequests.getCookie())
                .when()
                .delete(BOOK_PATH + "/" + bookingRequests.getBookId())
                .then());
    }
}
