package api.base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = ConfigReader.get("baseApiUrl");
    }
}
