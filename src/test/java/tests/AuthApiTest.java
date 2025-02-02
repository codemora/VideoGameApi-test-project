package tests;

import configs.Configuration;
import entities.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AuthService;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthApiTest {
    private static final Logger logger = LoggerFactory.getLogger(AuthApiTest.class);
    private static final int STATUS_OK = 200;
    private static final int STATUS_UNAUTHORIZED = 401;
    private static final int STATUS_BAD_REQUEST = 400;
    private static final String USERNAME = Configuration.getProperty("username");
    private static final String PASSWORD = Configuration.getProperty("password");

    @Test
    @Order(1)
    @DisplayName("Test Authentication with Valid Credentials")
    public void testAuthWithValidCredentials() {
        logger.info("Starting test: Valid Authentication");

        User user = new User(USERNAME, PASSWORD);

        Response response = AuthService.getAuthToken(user.toJson());

        assertEquals(STATUS_OK, response.getStatusCode(), "Expected HTTP status 200");
        assertNotNull(response.jsonPath().getString("token"), "Token should not be null for valid credentials");

        logger.info("Test completed: Valid Authentication");
    }

    @Test
    @Order(2)
    @DisplayName("Test Authentication with Invalid Credentials")
    public void testAuthWithInvalidCredentials() {
        logger.info("Starting test: Invalid Authentication");

        User user = new User("wrongUser", "wrongUser");

        Response response = AuthService.getAuthToken(user.toJson());

        assertEquals(STATUS_UNAUTHORIZED, response.getStatusCode(), "Expected HTTP status 401 for invalid credentials");
        assertNull(response.jsonPath().getString("token"), "Token should be null for invalid credentials");

        logger.info("Test completed: Invalid Authentication");
    }

    @Test
    @Order(3)
    @DisplayName("Test Authentication with Missing Username")
    public void testAuthWithMissingUsername() {
        logger.info("Starting test: Missing Username");

        User user = new User("", "admin");

        Response response = AuthService.getAuthToken(user.toJson());

        assertEquals(STATUS_BAD_REQUEST, response.getStatusCode(), "Expected HTTP status 400 for missing username");
        assertNull(response.jsonPath().getString("token"), "Token should be null for missing username");

        logger.info("Test completed: Missing Username");
    }

    @Test
    @Order(4)
    @DisplayName("Test Authentication with Missing Password")
    public void testAuthWithMissingPassword() {
        logger.info("Starting test: Missing Password");

        User user = new User("admin", "");

        Response response = AuthService.getAuthToken(user.toJson());

        assertEquals(STATUS_BAD_REQUEST, response.getStatusCode(), "Expected HTTP status 400 for missing password");
        assertNull(response.jsonPath().getString("token"), "Token should be null for missing password");

        logger.info("Test completed: Missing Password");
    }
}
