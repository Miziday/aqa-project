package base;

import com.codeborne.selenide.Configuration;
import config.ConfigReader;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        Configuration.browser = ConfigReader.get("browser");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = Long.parseLong(ConfigReader.get("timeout"));
    }
}