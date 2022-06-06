package mainSpecifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.http.ContentType.JSON;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
        new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(JSON)
                .build();
    }
}
