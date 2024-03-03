package guru.qa.tests;

import guru.qa.models.createbooking.CreateBookingDatesRequest;
import guru.qa.models.createbooking.CreateBookingRequest;
import guru.qa.models.createbooking.CreateBookingResponse;
import guru.qa.models.getbooking.GetBookingResponse;
import guru.qa.requests.BookingRequests;
import guru.qa.specifications.Spec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static guru.qa.endpoints.EndPoints.BOOK_PATH;
import static guru.qa.helpres.CustomAllureListener.withCustomTemplates;
import static guru.qa.specifications.Spec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("API тесты к сервису \"Книги\"")
public class ApiTests {
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
            assertEquals(createBooking.getFirstname(), createBookingResponse.getBooking().getFirstname());
            assertEquals(createBooking.getLastname(), createBookingResponse.getBooking().getLastname());
            assertEquals(createBooking.getTotalPrice(), createBookingResponse.getBooking().getTotalPrice());
            assertTrue(createBookingResponse.getBooking().getDepositpaid());
            assertEquals(createBookingDates.getCheckIn(), createBookingResponse.getBooking().getBookingDates().getCheckin());
            assertEquals(createBookingDates.getCheckout(), createBookingResponse.getBooking().getBookingDates().getCheckout());
            assertEquals(createBooking.getAdditionalneeds(), createBookingResponse.getBooking().getAdditionalneeds());
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
            assertEquals("Ilya", getBookingRequest.getFirstname());
            assertEquals("Lesnikov", getBookingRequest.getLastname());
            assertEquals(1550, getBookingRequest.getTotalPrice());
            assertTrue(getBookingRequest.getDepositpaid());
            assertEquals("2020-02-23", getBookingRequest.getBookingDates().getCheckIn());
            assertEquals("2024-02-23", getBookingRequest.getBookingDates().getCheckout());
            assertEquals("QA", getBookingRequest.getAdditionalneeds());
        });
    }

    @Test
    @Tag("API")
    @DisplayName("Частичное обновление информации книги")
    protected void partialUpdateOfBookInformationTest() {
        Spec.initResponseSpec(Spec.responseSpec(200));
        CreateBookingDatesRequest createBookingDates = new CreateBookingDatesRequest(null, null);
        CreateBookingRequest updateBooking = new CreateBookingRequest("Jim", "Brown", null, null, createBookingDates, null);

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
            assertEquals(updateBooking.getFirstname(), updateBookingResponse.getFirstname());
            assertEquals(updateBooking.getLastname(), updateBookingResponse.getLastname());
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
            assertEquals(updateBooking.getFirstname(), updateBookingResponse.getFirstname());
            assertEquals(updateBooking.getLastname(), updateBookingResponse.getLastname());
            assertEquals(updateBooking.getTotalPrice(), updateBookingResponse.getTotalPrice());
            assertFalse(updateBookingResponse.getDepositpaid());
            assertEquals(createBookingDates.getCheckIn(), updateBookingResponse.getBookingDates().getCheckIn());
            assertEquals(createBookingDates.getCheckout(), updateBookingResponse.getBookingDates().getCheckout());
            assertEquals(updateBooking.getAdditionalneeds(), updateBookingResponse.getAdditionalneeds());
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
