package services;

import com.google.gson.JsonObject;
import configs.Configuration;
import configs.RestAssuredConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class VideoGameService {
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);
    public static String token; // Fetch JWT Token

    private static final String VIDEO_GAME_ENDPOINT = Configuration.getProperty("videogame.endpoint");

    private static Response getVideoGames(boolean useAuth) {
        RequestSpecification requestSpec = RestAssuredConfig.getRequestSpec();
        String logMessage = "Fetching all video games";

        if (useAuth) {
            requestSpec = RestAssuredConfig.getRequestSpecWithAuth(token);
            logMessage = "Fetching all video games using token authentication";
        }

        logger.info(logMessage);

        Response response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(VIDEO_GAME_ENDPOINT)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Total Games Retrieved = {}", response.getStatusCode(), response.as(ArrayList.class).size());
        return response;
    }

    public static Response getVideoGamesWithoutAuth() {
        return getVideoGames( false);
    }

    public static Response getVideoGamesWithAuth() {
        return getVideoGames( true);
    }

    private static Response getVideoGame(int gameId, boolean useAuth) {
        RequestSpecification requestSpec = RestAssuredConfig.getRequestSpec();
        String logMessage = "Fetching video game with ID: {}";

        if (useAuth) {
            requestSpec = RestAssuredConfig.getRequestSpecWithAuth(token);
            logMessage = "Fetching video game using token authentication with ID: {}";
        }

        logger.info(logMessage, gameId);

        Response response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response getVideoGameWithoutAuth(int gameId) {
        return getVideoGame(gameId, false);
    }

    public static Response getVideoGameWithAuth(int gameId) {
        return getVideoGame(gameId, true);
    }

    private static Response createVideoGame(JsonObject requestBody, boolean useAuth) {
        RequestSpecification requestSpec = RestAssuredConfig.getRequestSpec();
        String logMessage = "Creating video game with details: {}";

        if (useAuth) {
            requestSpec = RestAssuredConfig.getRequestSpecWithAuth(token);
            logMessage = "Creating video game using token authentication with details: {}";
        }

        logger.info(logMessage, requestBody.toString());

        Response response = RestAssured.given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post(VIDEO_GAME_ENDPOINT)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response createVideoGameWithoutAuth(JsonObject requestBody) {
        return createVideoGame(requestBody, false);
    }

    public static Response createVideoGameWithAuth(JsonObject requestBody) {
        return createVideoGame(requestBody, true);
    }

    private static Response updateVideoGame(int gameId, JsonObject requestBody, boolean useAuth) {
        RequestSpecification requestSpec = RestAssuredConfig.getRequestSpec();
        String logMessage = "Updating video game with ID: {} using details: {}";

        if (useAuth) {
            requestSpec = RestAssuredConfig.getRequestSpecWithAuth(token);
            logMessage = "Updating video game with ID: {} using token authentication and details: {}";
        }

        logger.info(logMessage, gameId, requestBody.toString());

        Response response = RestAssured.given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .put(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.getBody().asString());

        return response;
    }

    public static Response updateVideoGameWithoutAuth(int gameId, JsonObject requestBody) {
        return updateVideoGame(gameId, requestBody, false);
    }

    public static Response updateVideoGameWithAuth(int gameId, JsonObject requestBody) {
        return updateVideoGame(gameId, requestBody, true);
    }

    private static Response deleteVideoGame(int gameId, boolean useAuth) {
        RequestSpecification requestSpec = RestAssuredConfig.getRequestSpec();
        String logMessage = "Deleting video game with ID: {}";

        if (useAuth) {
            requestSpec = RestAssuredConfig.getRequestSpecWithAuth(token);
            logMessage = "Deleting video game using token authentication with ID: {}";
        }

        logger.info(logMessage, gameId);

        Response response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .delete(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response deleteVideoGameWithoutAuth(int gameId) {
        return deleteVideoGame(gameId, false);
    }

    public static Response deleteVideoGameWithAuth(int gameId) {
        return deleteVideoGame(gameId, true);
    }
}
