package services;

import com.google.gson.JsonObject;
import configs.Configuration;
import configs.RestAssuredConfig;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private static final String AUTH_ENDPOINT = Configuration.getProperty("auth.endpoint");
    private static final String USERNAME = Configuration.getProperty("username");
    private static final String PASSWORD = Configuration.getProperty("password");

    public static String getAuthToken() {
        logger.info("Authenticating with API to obtain JWT token...");

        User user = new User(USERNAME, PASSWORD);

        Response response = getAuthToken(user.toJson());

        if (response.getStatusCode() == 200) {
            String token = response.jsonPath().getString("token");
            logger.info("Successfully obtained JWT token.");
            return token;
        } else {
            throw new RuntimeException("Failed to authenticate. Status Code: " + response.getStatusCode());
        }
    }

    public static Response getAuthToken(JsonObject requestBody) {
        logger.info("Authenticating with user: {}", requestBody.get("username"));

        Response response = RestAssured.given()
                .spec(RestAssuredConfig.getRequestSpec())
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


