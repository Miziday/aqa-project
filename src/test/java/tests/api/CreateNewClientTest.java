package tests.api;

import api.base.BaseApiTest;
import api.schemes.client.ClientRequestSchema;
import api.schemes.client.ClientResponseSchema;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ClientsData;

import static io.restassured.RestAssured.given;

public class CreateNewClientTest extends BaseApiTest {

    @Test
    public void createNewClientTest() {

        ClientRequestSchema client = ClientsData.getRandomClintRequestBody();

        given()
                .contentType(ContentType.JSON)
                .body(client)
                .when()
                .post("/api/clients").then().statusCode(201);

        ClientResponseSchema response = given()
                .when()
                .get("/api/clients/{id}", client.getId()).as(ClientResponseSchema.class);

        Assert.assertEquals(response.getFullName(), client.getFullName());
        Assert.assertEquals(response.getEmail(), client.getEmail());
        Assert.assertEquals(response.getPhone(), client.getPhone());
    }
}
