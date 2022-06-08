package tests;

import api.model.CreateData;
import api.model.NegativeRegistrationData;
import api.model.UserData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import network.EndpointsData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class HomeWorkApiTests extends TestBase {

    @Test
    public void listUsersTest() {
        List<UserData> users = given()
                .when()
                .get(EndpointsData.LIST_USER_POINT.title)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
        assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        assertTrue(users.stream().allMatch(x -> x.getAvatar().endsWith("image.jpg")));
    }

    @Test
    public void listUsersTestWithIteration() {
        List<UserData> users = given()
                .when()
                .get(EndpointsData.LIST_USER_POINT.title)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
        assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> emails = users.stream().map(UserData::getEmail).collect(Collectors.toList());
        for (String avatar : avatars) {
            assertTrue(avatar.endsWith("image.jpg"));
        }
        for (String email : emails) {
            assertTrue(email.endsWith("@reqres.in"));
        }
    }

    @Test
    public void createTest() {
        String name = "morpheus";
        String job = "leader";
        CreateData createData = new CreateData(name, job);
        given()
                .body(createData)
                .when()
                .post(EndpointsData.CREATE_POINT.title)
                .then().log().all()
                .extract().as(CreateData.class);
        assertEquals(name, createData.getName());
        assertEquals(job, createData.getJob());
    }

    @Test
    public void singleUserTest() {
        given()
                .when()
                .get(EndpointsData.SINGLE_USER.title)
                .then().log().all()
                .body("data.id", is(2));
    }

    @Test
    public void deleteUserTest() {
        given()
                .when()
                .delete(EndpointsData.SINGLE_USER.title)
                .then().log().status()
                .statusCode(204);
    }

    @Test
    public void unsuccessfulRegisterTest() {
        given()
                .body(new NegativeRegistrationData(EndpointsData.WRONG_EMAIL.title))
                .when()
                .post(EndpointsData.REGISTER_POINT.title)
                .then().log().all()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    public void unsuccessfulRegisterWithFilePathTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = classLoader.getResourceAsStream("jsons/unsuccessfulRegister.json")) {
            String json = (new String(is.readAllBytes(), UTF_8));
            JsonNode jsonNode = objectMapper.readTree(json);
            given()
                    .body(jsonNode)
                    .when()
                    .post(EndpointsData.REGISTER_POINT.title)
                    .then().log().all()
                    .statusCode(400)
                    .body("error", is("Missing email or username"));
        }
    }
}
