package api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://127.0.0.1:8000";
    }
}
