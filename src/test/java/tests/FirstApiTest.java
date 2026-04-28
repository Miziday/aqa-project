package tests;

import api.actions.UserApi;
import api.base.BaseApiTest;
import api.models.user.UserRequest;
import api.models.user.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;

import static utils.RandomData.getRandUUID;

public class FirstApiTest extends BaseApiTest {

    @Test
    public void createNewUserCheck() {

        // Arrange
        String userId = getRandUUID();
        UserRequest requestBody = UserData.getRandomUserBodyRequestWithId(userId);

        //Act
        UserApi.createUser(requestBody);

        //Assert
        UserResponse getNewUser = UserApi.getUserById(userId).then().extract().as(UserResponse.class);
        Assert.assertEquals(requestBody.getId(), getNewUser.getId());
        Assert.assertEquals(requestBody.getName(), getNewUser.getName());
        Assert.assertEquals(requestBody.getEmail(), getNewUser.getEmail());

        //Cleanup
        UserApi.deleteUserById(userId);

    }

    @Test
    public void updateUserCheck() {

        // Arrange
        String userId = getRandUUID();
        UserRequest requestBodyForCreateUser = UserData.getRandomUserBodyRequestWithId(userId);
        UserRequest requestBodyForUpdateUser = UserData.getRandomUserBodyRequestWithId(userId);

        // Создание нового пользователя
        UserApi.createUser(requestBodyForCreateUser);

        //Act
        UserApi.updateUser(userId, requestBodyForUpdateUser);

        //Assert
        UserResponse getUpdatedUser = UserApi.getUserById(userId).then().extract().as(UserResponse.class);

        Assert.assertEquals(requestBodyForUpdateUser.getId(), getUpdatedUser.getId());
        Assert.assertEquals(requestBodyForUpdateUser.getName(), getUpdatedUser.getName());
        Assert.assertEquals(requestBodyForUpdateUser.getEmail(), getUpdatedUser.getEmail());

        //Cleanup
        UserApi.deleteUserById(userId);

    }

    @Test
    public void deleteUserCheck() {

        // Arrange
        String userId = getRandUUID();
        UserRequest requestBodyForCreateUser = UserData.getRandomUserBodyRequestWithId(userId);

        // Создание нового пользователя
        UserApi.createUser(requestBodyForCreateUser);

        //Act
        UserApi.deleteUserById(userId);

        //Assert
        // ОСТАНОВОЧКА - ВОЗМОЖНО НАДО БУДЕТ ПЕРЕОСМЫСЛИТЬ BaseApiMethods
        UserApi.getUserById(userId).then().statusCode(404);

    }

}
