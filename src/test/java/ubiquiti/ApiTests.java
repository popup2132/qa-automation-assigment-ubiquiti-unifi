package ubiquiti;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ubiquiti.models.*;
import ubiquiti.utils.CookieManager;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests {

    private static final String BASE_URL = "https://127.0.0.1:8443/api/";
    private final CookieManager cookieManager = CookieManager.getInstance();

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createLocalAdmin() {
        AdminUser adminUser = new AdminUser(
                "add-default-admin",
                "admin",
                "network-admin@gmail.com",
                "password"
        );
        sendPostRequest("s/default/cmd/sitemgr", adminUser);
    }

    @Test
    public void setApplicationName() {
        ApplicationName applicationName = new ApplicationName("UniFi Network");
        sendPostRequest("set/setting/super_identity", applicationName);
    }

    @Test
    public void setCountryRegion() {
        CountryCode countryCode = new CountryCode("840");
        sendPostRequest("set/setting/country", countryCode);
    }

    @Test
    public void setLocaleTimezone() {
        LocaleTimezone localeTimezone = new LocaleTimezone("Europe/Riga");
        sendPostRequest("set/setting/locale", localeTimezone);
    }

    @Test
    public void setConfiguredState() {
        ConfiguredState configuredState = new ConfiguredState("set-installed");

        Response response = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json, text/plain, */*")
                .body(configuredState)
                .when()
                .post("cmd/system")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        cookieManager.setCsrfToken(response.getCookie("csrf_token"));
        cookieManager.setUnifises(response.getCookie("unifises"));
    }

    @Test
    public void getUserConfiguration() {
        AdminConfig adminConfig = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json, text/plain, */*")
                .cookie("csrf_token", cookieManager.getCsrfToken())
                .cookie("unifises", cookieManager.getUnifises())
                .when()
                .get("self")
                .then()
                .statusCode(200)
                .extract().body().as(AdminConfig.class);

        assertEquals("admin", adminConfig.data().get(0).name(), "The admin name should match the expected value.");
    }

    @Test
    public void getCountryConfig() {
        Country country = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json, text/plain, */*")
                .cookie("csrf_token", cookieManager.getCsrfToken())
                .cookie("unifises", cookieManager.getUnifises())
                .when()
                .get("s/default/get/setting/country")
                .then()
                .statusCode(200)
                .extract().body().as(Country.class);

        assertEquals("840", country.getData().get(0).getCode(), "The country dode should match the expected value.");    }

    private <T> void sendPostRequest(String endpoint, T requestBody) {
        given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json, text/plain, */*")
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .log().all();
    }
}
