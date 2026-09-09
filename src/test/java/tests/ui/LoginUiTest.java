package tests.ui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.LoginPage;
import utils.AssertSteps;

public class LoginUiTest extends BaseUiTest {

    @DataProvider(parallel = true)
    public Object[][] invalidDataProvider() {
        return new Object[][] {
                {"user1", "pass1", "Epic sadface: Username and password do not match any user in this service"},
                {"user2", "pass2", "Epic sadface: Username and password do not match any user in this service"},
                {"", "pass", "Epic sadface: Username is required"},
                {"user", "", "Epic sadface: Password is required"}
        };
    }

    // Ретраер на случай, если не нашелся элемент по локатору (например, помешал какой то Loader)
    @Test // @Test(retryAnalyzer = ElementNotFountRetryer.class)
    public void checkSuccessfulLogin() {

        new LoginPage()
                .openPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        AssertSteps.shouldBeOpenedInventoryPage();

    }

    @Test(dataProvider = "invalidDataProvider")
    public void checkInvalidLogin(String login, String pass, String errorMessage) {

        new LoginPage()
                .openPage()
                .enterUsername(login)
                .enterPassword(pass)
                .clickLogin();

        AssertSteps.shouldShowErrorWithText(errorMessage);

    }

    @Test
    public void checkClickableLoginButton() {

        new LoginPage().openPage();

        AssertSteps.loginButtonShouldBeClickable();

    }
}
