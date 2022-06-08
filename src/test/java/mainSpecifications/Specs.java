package mainSpecifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;
@JsonIgnoreProperties
public class Specs {

    public static RequestSpecification specRequest(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(JSON)
                .build();
    }
}
