import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VideoGameApiTest {
    
    private static final Logger logger = LoggerFactory.getLogger(VideoGameApiTest.class);
    private static int gameId = 1;

    @BeforeAll
    public static void setup(){
        VideoGameService.token = AuthService.getAuthToken();
    }

    @Test
    @Order(1)
    @DisplayName("Test Creating a Video Game without using token authentication")
    public void testCreateVideoGameWithoutToken() {
        logger.info("Starting test: Create Video Game without using token authentication");

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("id", gameId);
        requestBody.addProperty("name", "Resident Evil 4");
        requestBody.addProperty("releaseDate", "2005-10-01 23:59:59");
        requestBody.addProperty("reviewScore", 85);
        requestBody.addProperty("category", "Shooter");
        requestBody.addProperty("rating", "Universal");

        Response response = VideoGameService.createVideoGameWithoutToken(requestBody);

        assertEquals(403, response.getStatusCode(), "Expected status code 403 (Forbidden)");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Create Video Game without using token authentication");
    }

    @Test
    @Order(2)
    @DisplayName("Test Creating a Video Game using token authentication")
    public void testCreateVideoGameWithToken() {
        logger.info("Starting test: Create Video Game using token authentication");

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("id", gameId);
        requestBody.addProperty("name", "Resident Evil 4");
        requestBody.addProperty("releaseDate", "2005-10-01 23:59:59");
        requestBody.addProperty("reviewScore", 85);
        requestBody.addProperty("category", "Shooter");
        requestBody.addProperty("rating", "Universal");

        Response response = VideoGameService.createVideoGameWithToken(requestBody);

        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("Resident Evil 4", response.jsonPath().getString("name"), "Game name should match");

        logger.info("Test Completed: Create Video Game using token authentication");
    }

    @Test
    @Order(3)
    @DisplayName("Test Retrieving a Video Game without using token authentication")
    public void testGetVideoGameWithoutToken() {
        logger.info("Starting test: Get Video Game without using token authentication");

        Response response = VideoGameService.getVideoGameWithoutToken(gameId);

        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals("Resident Evil 4", response.jsonPath().getString("name"), "Game name should match");

        logger.info("Test Completed: Get Video Game without using token authentication");
    }

    @Test
    @Order(4)
    @DisplayName("Test Retrieving a Video Game using token authentication")
    public void testGetVideoGameWithToken() {
        logger.info("Starting test: Get Video Game using token authentication");

        Response response = VideoGameService.getVideoGameWithToken(gameId);

        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals("Resident Evil 4", response.jsonPath().getString("name"), "Game name should match");

        logger.info("Test Completed: Get Video Game using token authentication");
    }

    @Test
    @Order(5)
    @DisplayName("Test Deleting a Video Game without using token authentication")
    public void testDeleteVideoGameWithoutToken() {
        logger.info("Starting test: Delete Video Game without using token authentication");

        Response response = VideoGameService.deleteVideoGameWithoutToken(gameId);
        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");

        // Verify the game no longer exists
        Response getResponse = VideoGameService.getVideoGameWithoutToken(gameId);
        assertEquals(404, getResponse.getStatusCode(), "Game should no longer exist");

        logger.info("Test Completed: Delete Video Game without using token authentication");
    }

    @Test
    @Order(6)
    @DisplayName("Test Deleting a Video Game using token authentication")
    public void testDeleteVideoGameWithToken() {
        logger.info("Starting test: Delete Video Game using token authentication");

        Response response = VideoGameService.deleteVideoGameWithToken(gameId);
        assertEquals(200, response.getStatusCode(), "Expected status code 200 (OK)");

        // Verify the game no longer exists
        Response getResponse = VideoGameService.getVideoGameWithoutToken(gameId);
        assertEquals(404, getResponse.getStatusCode(), "Game should no longer exist");

        logger.info("Test Completed: Delete Video Game using token authentication");
    }
}
