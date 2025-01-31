import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoGameService {
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);
    public static String token; // Fetch JWT Token

    private static final String BASE_URL = Configuration.getProperty("base.url");
    private static final String VIDEO_GAME_ENDPOINT = Configuration.getProperty("videogame.endpoint");

    public static Response getVideoGameWithoutToken(int gameId) {
        logger.info("Fetching video game with ID: {}", gameId);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response getVideoGameWithToken(int gameId) {
        logger.info("Fetching video game using token authentication with ID: {}", gameId);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token) // Pass token
                .when()
                .get(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response createVideoGameWithoutToken(JsonObject requestBody) {
        logger.info("Creating video game with details: {}", requestBody.toString());

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post(VIDEO_GAME_ENDPOINT)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response createVideoGameWithToken(JsonObject requestBody) {

        logger.info("Creating video game using token authentication with details: {}", requestBody.toString());

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token) // Pass token
                .body(requestBody.toString())
                .when()
                .post(VIDEO_GAME_ENDPOINT)
                .then()
                .extract()
                .response();

        logger.info("Create Video Game Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response deleteVideoGameWithoutToken(int gameId) {
        logger.info("Deleting video game with ID: {}", gameId);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .delete(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }

    public static Response deleteVideoGameWithToken(int gameId) {
        logger.info("Deleting video game using token authentication with ID: {}", gameId);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(VIDEO_GAME_ENDPOINT + "/" + gameId)
                .then()
                .extract()
                .response();

        logger.info("Response: Status Code = {}, Body = {}", response.getStatusCode(), response.asString());
        return response;
    }
}
