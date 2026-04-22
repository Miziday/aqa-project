package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;

public class BaseUiTest {

    @BeforeMethod
    public void setup() {

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));

        Configuration.browser = ConfigReader.get("browser");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = Long.parseLong(ConfigReader.get("timeout"));
    }
}