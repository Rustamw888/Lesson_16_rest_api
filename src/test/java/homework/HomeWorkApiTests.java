package homework;

import api.CreateData;
import api.UnsuccessfulRegisterData;
import api.UserData;
import org.junit.jupiter.api.Test;
import specifications.Specs;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class HomeWorkApiTests {

    private static final String
            URL = "https://reqres.in/",
            LIST_USER_POINT = "api/users?page=2",
            CREATE_POINT = "api/users",
            SINGLE_USER = "api/users/2",
            WRONG_EMAIL = "sydney@fife",
            REGISTER_POINT = "api/register";
    UnsuccessfulRegisterData unsuccessfulRegisterData = new UnsuccessfulRegisterData(WRONG_EMAIL);

    @Test
    public void listUsersTest() {
        Specs.specInstall(Specs.specRequest(URL));
        List<UserData> users = given()
                .when()
                .get(LIST_USER_POINT)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x-> assertTrue(x.getAvatar().contains(x.getId().toString())));
        assertTrue(users.stream().allMatch(x-> x.getEmail().endsWith("@reqres.in")));
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            assertTrue(avatars.get(i).endsWith("image.jpg"));
        }
    }

    @Test
    public void createTest() {
        Specs.specInstall(Specs.specRequest(URL));
        String name = "morpheus";
        String job = "leader";
        CreateData createData = new CreateData(name, job);
        given()
                .body(createData)
                .when()
                .post(CREATE_POINT)
                .then().log().all()
                .extract().as(CreateData.class);
        assertEquals(name, createData.getName());
        assertEquals(job, createData.getJob());
    }

    @Test
    public void singleUserTest() {
        Specs.specInstall(Specs.specRequest(URL));
        given()
                .when()
                .get(SINGLE_USER)
                .then().log().all()
                .body("data.id", is(2));
    }

    @Test
    public void deleteUserTest() {
        Specs.specInstall(Specs.specRequest(URL));
        given()
                .when()
                .delete(SINGLE_USER)
                .then().log().status()
                .statusCode(204);
    }

    @Test
    public void unsuccessfulRegisterTest() {
        Specs.specInstall(Specs.specRequest(URL));
        given()
                .body(unsuccessfulRegisterData)
                .when()
                .post(REGISTER_POINT)
                .then().log().all()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    public void unsuccessfulRegisterWithFilePathTest() {
        Specs.specInstall(Specs.specRequest(URL));
        File file = new File("src/test/java/jsons/unsuccessfulRegister.json");
        given()
                .body(file)
                .when()
                .post(REGISTER_POINT)
                .then().log().all()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
