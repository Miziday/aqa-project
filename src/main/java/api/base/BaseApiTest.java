package api.base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = ConfigReader.get("baseApiUrl");
    }
}
