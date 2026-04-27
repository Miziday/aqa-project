package tests;

import api.base.BaseApiTest;
import api.models.users.UserRequest;
import api.models.users.UserResponse;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.base.BaseRequests.sendGetRequestWithPathParamsAndGetResponse;
import static io.restassured.RestAssured.given;
import static utils.RandomData.*;

public class FirstApiTest extends BaseApiTest {

    @Test
    public void createNewUserCheck() {

        // Arrange
        String userId = getRandUUID();
        UserRequest requestBody = UserRequest.builder().id(userId).name(getRandomString(10)).email(getRandEmail()).build();

        //Act
        UserResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post("/users").then().statusCode(201).extract().as(UserResponse.class);

        UserResponse getNewUser = sendGetRequestWithPathParamsAndGetResponse(UserResponse.class, userId);

        //Assert
        Assert.assertEquals(requestBody.getId(), getNewUser.getId());
        Assert.assertEquals(requestBody.getName(), getNewUser.getName());
        Assert.assertEquals(requestBody.getEmail(), getNewUser.getEmail());

        //Cleanup
        given().when().delete("/users/{user_id}", response.getId()).prettyPrint();

    }

    @Test
    public void updateUserCheck() {

        // Arrange
        String userId = getRandUUID();
        UserRequest requestBodyForCreateUser = UserRequest.builder().id(userId).name(getRandomString(10)).email(getRandEmail()).build();
        UserRequest requestBodyForUpdateUser = UserRequest.builder().id(userId).name(getRandomString(10)).email(getRandEmail()).build();

        // Создание нового пользователя
        given().contentType(ContentType.JSON).body(requestBodyForCreateUser).when().post("/users").then().statusCode(201);

        //Act
        UserResponse updateUser =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBodyForUpdateUser)
                        .when()
                        .put("/users/{id}", userId)
                        .then().log().all().statusCode(200).extract().as(UserResponse.class);

        //Assert
        UserResponse getUpdatedUser = sendGetRequestWithPathParamsAndGetResponse(UserResponse.class, userId);

        Assert.assertEquals(requestBodyForUpdateUser.getId(), getUpdatedUser.getId());
        Assert.assertEquals(requestBodyForUpdateUser.getName(), getUpdatedUser.getName());
        Assert.assertEquals(requestBodyForUpdateUser.getEmail(), getUpdatedUser.getEmail());

        //Cleanup
        given().when().delete("/users/{user_id}", userId);

    }

    @Test
    public void deleteUserCheck() {

        // Arrange
        String userId = getRandUUID();
        UserRequest requestBodyForCreateUser = UserRequest.builder().id(userId).name(getRandomString(10)).email(getRandEmail()).build();

        // Создание нового пользователя
        given().contentType(ContentType.JSON).body(requestBodyForCreateUser).when().post("/users").then().statusCode(201);

        //Act
        given().when().delete("/users/{user_id}", userId);

        //Assert
        given().when().get("/users/{user_id}", userId).then().statusCode(404);

    }
}
