package tests;

import api.base.BaseApiTest;
import api.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstApiTest extends BaseApiTest {

    @Test
    public void firstApiTest() {

        User response = given().when().get("/users/1").then().extract().as(User.class);

        Assert.assertEquals(response.getId(), 1);
        Assert.assertEquals(response.getName(), "Leanne Graham");
    }
}
