package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AuthService;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthApiTest {
    private static final Logger logger = LoggerFactory.getLogger(AuthApiTest.class);

    @Test
    @Order(1)
    @DisplayName("Test Authentication with Valid Credentials")
    public void testAuthWithValidCredentials() {
        logger.info("Starting test: Valid Authentication");

        Response response = AuthService.getAuthToken("admin", "admin");

        assertEquals(200, response.getStatusCode(), "Expected HTTP status 200");
        assertNotNull(response.jsonPath().getString("token"), "Token should not be null for valid credentials");
        logger.info("Test Passed: Received valid token.");
    }

    @Test
    @Order(2)
    @DisplayName("Test Authentication with Invalid Credentials")
    public void testAuthWithInvalidCredentials() {
        logger.info("Starting test: Invalid Authentication");

        Response response = AuthService.getAuthToken("wrongUser", "wrongPass");

        assertEquals(403, response.getStatusCode(), "Expected HTTP status 401 for invalid credentials");
        assertNull(response.jsonPath().getString("token"), "Token should be null for invalid credentials");
        logger.info("Test Passed: Authentication failed as expected.");
    }

    @Test
    @Order(3)
    @DisplayName("Test Authentication with Missing Username")
    public void testAuthWithMissingUsername() {
        logger.info("Starting test: Missing Username");

        Response response = AuthService.getAuthToken("", "admin");

        assertEquals(400, response.getStatusCode(), "Expected HTTP status 400 for missing username");
        assertNull(response.jsonPath().getString("token"), "Token should be null for missing username");
        logger.info("Test Passed: Authentication failed as expected.");
    }

    @Test
    @Order(4)
    @DisplayName("Test Authentication with Missing Password")
    public void testAuthWithMissingPassword() {
        logger.info("Starting test: Missing Password");

        Response response = AuthService.getAuthToken("admin", "");

        assertEquals(400, response.getStatusCode(), "Expected HTTP status 400 for missing password");
        assertNull(response.jsonPath().getString("token"), "Token should be null for missing password");
        logger.info("Test Passed: Authentication failed as expected.");
    }
}
