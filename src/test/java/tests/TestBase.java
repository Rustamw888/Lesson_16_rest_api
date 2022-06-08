package tests;

import io.restassured.RestAssured;
import mainSpecifications.Specs;
import org.junit.jupiter.api.BeforeAll;

public abstract class TestBase extends Specs {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
        specRequest(RestAssured.baseURI);
    }
}
