package tests;

import entities.VideoGame;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AuthService;
import services.VideoGameService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VideoGameApiTest {
    
    private static final Logger logger = LoggerFactory.getLogger(VideoGameApiTest.class);
    private static final int gameId = 1;
    private static final int STATUS_OK = 200;
    private static final int STATUS_FORBIDDEN = 403;
    private static final int STATUS_NOT_FOUND = 404;
    private final VideoGame game = new VideoGame("Mario", "2012-05-04", 85, "Platform", "Mature");

    @BeforeAll
    public static void setup(){
        VideoGameService.token = AuthService.getAuthToken();
    }

    @Test
    @Order(1)
    @DisplayName("Test Creating a Video Game without using token authentication")
    public void testCreateVideoGameWithoutAuth() {
        logger.info("Starting test: Create Video Game without using token authentication");

        Response response = VideoGameService.createVideoGameWithoutAuth(game.toJson());

        assertEquals(STATUS_FORBIDDEN, response.getStatusCode(), "Expected status code 403 (Forbidden)");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Create Video Game without using token authentication");
    }

    @Test
    @Order(2)
    @DisplayName("Test Creating a Video Game using token authentication")
    public void testCreateVideoGameWithAuth() {
        logger.info("Starting test: Create Video Game using token authentication");

        Response response = VideoGameService.createVideoGameWithAuth(game.toJson());

        VideoGame actualGame = response.as(VideoGame.class);

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(0, actualGame.getId(), "Game ID should match");
        assertEquals(game.getName(), actualGame.getName(), "Game name should be match");
        assertEquals(game.getReleaseDate(), actualGame.getReleaseDate(), "Release date should match");
        assertEquals(game.getReviewScore(), actualGame.getReviewScore(), "Review score should match");
        assertEquals(game.getCategory(), actualGame.getCategory(), "Category should match");
        assertEquals(game.getRating(), actualGame.getRating(), "Rating should match");

        logger.info("Test Completed: Create Video Game using token authentication");
    }

    @Test
    @Order(3)
    @DisplayName("Test Retrieving a Video Game without using token authentication")
    public void testGetVideoGamesWithoutAuth() {
        logger.info("Starting test: Get all Video Game without using token authentication");

        VideoGame game = new VideoGame("Resident Evil 4", "2005-10-01 23:59:59", 85, "Shooter", "Universal");

        Response response = VideoGameService.getVideoGamesWithoutAuth();

        List<VideoGame> actualGames = response.jsonPath().getList(".", VideoGame.class);
        VideoGame actualGame = actualGames.getFirst();

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals(10, actualGames.size(), "Expected total games should be 10");
        assertEquals(gameId, actualGame.getId(), "Game ID should match");
        assertEquals(game.getName(), actualGame.getName(), "Game name should be match");
        assertEquals(game.getReleaseDate(), actualGame.getReleaseDate(), "Release date should match");
        assertEquals(game.getReviewScore(), actualGame.getReviewScore(), "Review score should match");
        assertEquals(game.getCategory(), actualGame.getCategory(), "Category should match");
        assertEquals(game.getRating(), actualGame.getRating(), "Rating should match");

        logger.info("Test Completed: Get all Video Game without using token authentication");
    }

    @Test
    @Order(4)
    @DisplayName("Test Retrieving a Video Game using token authentication")
    public void testGetVideoGamesWithAuth() {
        logger.info("Starting test: Get all Video Game using token authentication");

        VideoGame game = new VideoGame("Resident Evil 4", "2005-10-01 23:59:59", 85, "Shooter", "Universal");

        Response response = VideoGameService.getVideoGamesWithAuth();

        List<VideoGame> actualGames = response.jsonPath().getList(".", VideoGame.class);
        VideoGame actualGame = actualGames.getFirst();

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals(10, actualGames.size(), "Expected total games should be 10");
        assertEquals(gameId, actualGame.getId(), "Game ID should match");
        assertEquals(game.getName(), actualGame.getName(), "Game name should be match");
        assertEquals(game.getReleaseDate(), actualGame.getReleaseDate(), "Release date should match");
        assertEquals(game.getReviewScore(), actualGame.getReviewScore(), "Review score should match");
        assertEquals(game.getCategory(), actualGame.getCategory(), "Category should match");
        assertEquals(game.getRating(), actualGame.getRating(), "Rating should match");

        logger.info("Test Completed: Get all Video Game using token authentication");
    }

    @Test
    @Order(5)
    @DisplayName("Test Retrieving a Video Game without using token authentication")
    public void testGetVideoGameWithoutAuth() {
        logger.info("Starting test: Get Video Game without using token authentication");

        VideoGame game = new VideoGame("Resident Evil 4", "2005-10-01 23:59:59", 85, "Shooter", "Universal");

        Response response = VideoGameService.getVideoGameWithoutAuth(gameId);

        VideoGame actualGame = response.as(VideoGame.class);

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals(gameId, actualGame.getId(), "Game ID should match");
        assertEquals(game.getName(), actualGame.getName(), "Game name should be match");
        assertEquals(game.getReleaseDate(), actualGame.getReleaseDate(), "Release date should match");
        assertEquals(game.getReviewScore(), actualGame.getReviewScore(), "Review score should match");
        assertEquals(game.getCategory(), actualGame.getCategory(), "Category should match");
        assertEquals(game.getRating(), actualGame.getRating(), "Rating should match");

        logger.info("Test Completed: Get Video Game without using token authentication");
    }

    @Test
    @Order(6)
    @DisplayName("Test Retrieving a Video Game using token authentication")
    public void testGetVideoGameWithAuth() {
        logger.info("Starting test: Get Video Game using token authentication");

        VideoGame game = new VideoGame("Resident Evil 4", "2005-10-01 23:59:59", 85, "Shooter", "Universal");

        Response response = VideoGameService.getVideoGameWithAuth(gameId);

        VideoGame actualGame = response.as(VideoGame.class);

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals(gameId, actualGame.getId(), "Game ID should match");
        assertEquals(game.getName(), actualGame.getName(), "Game name should be match");
        assertEquals(game.getReleaseDate(), actualGame.getReleaseDate(), "Release date should match");
        assertEquals(game.getReviewScore(), actualGame.getReviewScore(), "Review score should match");
        assertEquals(game.getCategory(), actualGame.getCategory(), "Category should match");
        assertEquals(game.getRating(), actualGame.getRating(), "Rating should match");

        logger.info("Test Completed: Get Video Game using token authentication");
    }

    @Test
    @Order(7)
    @DisplayName("Test Get Non-Existent VideoGame")
    public void testGetNonExistentVideoGame() {
        logger.info("Starting test: Get Non-Existent VideoGame");

        Response response = VideoGameService.getVideoGameWithAuth(9999);

        assertEquals(STATUS_NOT_FOUND, response.getStatusCode(), "Expected HTTP status 404 for non-existent videogame");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Get Non-Existent VideoGame");
    }

    @Test
    @Order(8)
    @DisplayName("Test Update VideoGame using token authentication")
    public void testUpdateVideoGameWithValidAuth() {
        logger.info("Starting test: Update Video Game using token authentication");

        Response response = VideoGameService.updateVideoGameWithAuth(gameId, game.toJson());

        VideoGame actualGame = response.as(VideoGame.class);

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(gameId, actualGame.getId(), "Game ID should match");
        assertEquals(game.getName(), actualGame.getName(), "Game name should be match");
        assertEquals(game.getReleaseDate(), actualGame.getReleaseDate(), "Release date should match");
        assertEquals(game.getReviewScore(), actualGame.getReviewScore(), "Review score should match");
        assertEquals(game.getCategory(), actualGame.getCategory(), "Category should match");
        assertEquals(game.getRating(), actualGame.getRating(), "Rating should match");

        logger.info("Test Completed: Update VideoGame using token authentication");
    }

    @Test
    @Order(9)
    @DisplayName("Test Update VideoGame without using token authentication")
    public void testUpdateVideoGameWithoutAuth() {
        logger.info("Starting test: Update Video Game without using token authentication");

        Response response = VideoGameService.updateVideoGameWithoutAuth(gameId, game.toJson());

        assertEquals(STATUS_FORBIDDEN, response.getStatusCode(), "Expected status code 403 (Forbidden)");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Update VideoGame without using token authentication");
    }

    @Test
    @Order(10)
    @DisplayName("Test Delete Non-Existent VideoGame")
    public void testUpdateNonExistentVideoGame() {
        logger.info("Starting test: Update Non-Existent VideoGame");

        Response response = VideoGameService.updateVideoGameWithAuth(9999, game.toJson());

        assertEquals(STATUS_NOT_FOUND, response.getStatusCode(), "Expected HTTP status 404 for non-existent videogame");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Update Non-Existent VideoGame");
    }

    @Test
    @Order(11)
    @DisplayName("Test Deleting a Video Game without using token authentication")
    public void testDeleteVideoGameWithoutAuth() {
        logger.info("Starting test: Delete Video Game without using token authentication");

        Response response = VideoGameService.deleteVideoGameWithoutAuth(gameId);

        assertEquals(STATUS_FORBIDDEN, response.getStatusCode(), "Expected status code 403 (Forbidden)");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Delete Video Game without using token authentication");
    }

    @Test
    @Order(12)
    @DisplayName("Test Deleting a Video Game using token authentication")
    public void testDeleteVideoGameWithAuth() {
        logger.info("Starting test: Delete Video Game using token authentication");

        Response response = VideoGameService.deleteVideoGameWithAuth(gameId);
        assertEquals(STATUS_OK, response.getStatusCode(), "Expected status code 200 (OK)");
        assertEquals("Video game deleted", response.asString(),  "Deletion message should match");

        logger.info("Test Completed: Delete Video Game using token authentication");
    }

    @Test
    @Order(13)
    @DisplayName("Test Delete Non-Existent VideoGame")
    public void testDeleteNonExistentVideoGame() {
        logger.info("Starting test: Delete Non-Existent VideoGame");

        Response response = VideoGameService.deleteVideoGameWithAuth(9999);

        assertEquals(STATUS_NOT_FOUND, response.getStatusCode(), "Expected HTTP status 404 for non-existent videogame");
        assertNotNull(response.getBody(), "Response body should not be null");

        logger.info("Test Completed: Delete Non-Existent VideoGame");
    }
}
