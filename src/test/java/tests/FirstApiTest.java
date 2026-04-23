package tests;

import api.base.BaseApiTest;
import api.models.users.UserRequest;
import api.models.users.UserResponse;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstApiTest extends BaseApiTest {

    @Test
    public void getUserById() {

        UserResponse response = given().when().get("/users/1").then().extract().as(UserResponse.class);

        Assert.assertEquals(response.getId(), 1);
        Assert.assertEquals(response.getName(), "Fixic");
    }


    @Test
    public void createNewUserCheck() {

        UserRequest requestBody = UserRequest.builder().id(1).name("Fixic").email("1@mail.ru").build();

         UserResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/users").then().statusCode(201).extract().as(UserResponse.class);

        Assert.assertEquals(response.getId(), requestBody.getId());
        Assert.assertEquals(response.getName(), requestBody.getName());
        Assert.assertEquals(response.getEmail(), requestBody.getEmail());

    }

}
