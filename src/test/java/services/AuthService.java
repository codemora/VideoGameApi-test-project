package services;

import com.google.gson.JsonObject;
import configs.Configuration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private static final String BASE_URL = Configuration.getProperty("base.url");
    private static final String AUTH_ENDPOINT = Configuration.getProperty("auth.endpoint");
    private static final String USERNAME = Configuration.getProperty("username");
    private static final String PASSWORD = Configuration.getProperty("password");

    public static String getAuthToken() {
        logger.info("Authenticating with API to obtain JWT token...");

        Response response = getAuthToken(USERNAME,PASSWORD);

        if (response.getStatusCode() == 200) {
            String token = response.jsonPath().getString("token");
            logger.info("Successfully obtained JWT token.");
            return token;
        } else {
            throw new RuntimeException("Failed to authenticate. Status Code: " + response.getStatusCode());
        }
    }

    public static Response getAuthToken(String username, String password) {
        logger.info("Authenticating with user: {}", username);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", username);
        requestBody.addProperty("password", password);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post(AUTH_ENDPOINT)
                .then()
                .extract()
                .response();

        logger.info("Authentication Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());

        return response;
    }
}


