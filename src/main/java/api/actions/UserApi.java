package api.actions;

import api.models.user.UserRequest;
import api.models.user.UserResponse;
import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static api.base.BaseApiMethods.sendGetRequestWithPathParams;
import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response getUserById(String id) {
        return Allure.step("Получение пользователя по id = " + id, () -> {
            return sendGetRequestWithPathParams("/users/{user_id}", id);
        });
    }

    public static void deleteUserById(String id) {
        Allure.step("Удаление пользователя по id = " + id, () -> {
            given().when().delete("/users/{user_id}",id).then().statusCode(200);
        });
    }

    public static UserResponse createUser(UserRequest body) {
        return Allure.step("Создание нового пользователя", () -> {
            return given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post("/users").then().statusCode(201).extract().as(UserResponse.class);
        });
    }

    public static ValidatableResponse updateUser(String userId, UserRequest requestBody) {
        return Allure.step("Обновление пользователя по id = " + userId, () -> {
            return given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .put("/users/{id}", userId)
                    .then().statusCode(200);
        });

    }
}
