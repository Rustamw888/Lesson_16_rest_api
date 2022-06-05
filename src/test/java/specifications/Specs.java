package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class Specs {

    public static RequestSpecification specRequest(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(JSON)
                .build();
    }

    public static void specInstall(RequestSpecification specRequest) {
        RestAssured.requestSpecification = specRequest;
    }
}
