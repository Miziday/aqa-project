package tests;

import api.base.BaseApiTest;
import api.models.users.UserRequest;
import api.models.users.UserResponse;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utils.RandomData.*;

public class FirstApiTest extends BaseApiTest {

    @Test
    public void createNewUserCheck() {

        // Arrange
        UserRequest requestBody = UserRequest.builder().id(getRandUUID()).name(getRandomString(10)).email(getRandEmail()).build();

        //Act
        UserResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/users").then().statusCode(201).extract().as(UserResponse.class);

        UserResponse getNewUser = given().when().get("/users/{user_id}", response.getId()).then().statusCode(200).extract().as(UserResponse.class);

        //Assert
        Assert.assertEquals(requestBody.getId(), getNewUser.getId());
        Assert.assertEquals(requestBody.getName(), getNewUser.getName());
        Assert.assertEquals(requestBody.getEmail(), getNewUser.getEmail());

        //Cleanup
        given().when().delete("/users/{user_id}", response.getId()).prettyPrint();

    }

}
