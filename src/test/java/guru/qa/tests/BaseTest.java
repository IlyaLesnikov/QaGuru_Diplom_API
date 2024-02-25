package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUP() {
        RestAssured.baseURI = System.getProperty("baseURI", "https://restful-booker.herokuapp.com");
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
}
