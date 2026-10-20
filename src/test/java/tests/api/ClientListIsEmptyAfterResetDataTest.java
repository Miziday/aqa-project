package tests.api;

import api.base.BaseApiTest;
import api.schemes.client.ClientResponseSchema;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ClientListIsEmptyAfterResetDataTest extends BaseApiTest {

    @Test
    public void clientListIsEmptyAfterResetDataTest() {

        // очистка всех данных
        given().when().post("/api/test/reset").then().statusCode(200);

        List<ClientResponseSchema> clientList = given()
                .when()
                .get("/api/clients").jsonPath().getList(".", ClientResponseSchema.class);

        Assert.assertTrue(clientList.isEmpty());
    }
}
