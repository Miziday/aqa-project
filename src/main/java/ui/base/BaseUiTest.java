package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;

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
//        if (ConfigReader.get("remote.connection.use").equals("true")) {
//            Configuration.remote = "http://selenium-chrome:4444/wd/hub";
//        }

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}