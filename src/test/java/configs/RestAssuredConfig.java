package configs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfig {
    private static final String BASE_URL = Configuration.getProperty("base.url");

    // ThreadLocal ensures each thread gets its own RequestSpecification instance (useful for parallel execution)
    private static final ThreadLocal<RequestSpecification> requestSpec = ThreadLocal.withInitial(() ->
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .addHeader("Content-Type", "application/json")
                    .build()
    );

    public static RequestSpecification getRequestSpec() {
        return requestSpec.get();
    }

    public static RequestSpecification getRequestSpecWithAuth(String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(getRequestSpec())
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}
