package tests;

import base.BaseApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstApiTest extends BaseApiTest {

    @Test
    public void firstApiTest() {
        given()
                .when()
                    .get("/posts/1")
                .then()
                    .statusCode(200)
                    .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }
}
