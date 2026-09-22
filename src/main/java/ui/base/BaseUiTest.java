package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import java.lang.reflect.Method;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseUiTest {

    private static final Logger log = LoggerFactory.getLogger(BaseUiTest.class);

    @BeforeSuite
    public void configureSelenide() {
        // Выполняется один раз до старта параллельных потоков, поэтому безопасно
        // писать в статический Configuration без риска гонки между потоками.
        Configuration.browser = ConfigReader.get("browser");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = Long.parseLong(ConfigReader.get("timeout"));
        if (ConfigReader.get("remote.connection.use").equals("true")) {
            Configuration.remote = "http://selenium-chrome:4444/wd/hub";
        }

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
    }

    @BeforeMethod
    public void setup(Method method) {
        log.info("START TEST: {}.{}",
                method.getDeclaringClass().getSimpleName(),
                method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        log.info("FINISH TEST: {}.{} | STATUS: {}",
                result.getTestClass().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(),
                getStatus(result));

        closeWebDriver();
    }

    private String getStatus(ITestResult result) {
        return switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASSED";
            case ITestResult.FAILURE -> "FAILED";
            case ITestResult.SKIP -> "SKIPPED";
            default -> "UNKNOWN";
        };
    }
}
