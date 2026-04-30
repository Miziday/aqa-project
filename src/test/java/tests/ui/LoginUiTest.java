package tests.ui;

import ui.base.BaseUiTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;
import utils.AssertSteps;

public class LoginUiTest extends BaseUiTest {

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][] {
                {"user1", "pass1", "Epic sadface: Username and password do not match any user in this service"},
                {"user2", "pass2", "Epic sadface: Username and password do not match any user in this service"},
                {"", "pass", "Epic sadface: Username is required"},
                {"user", "", "Epic sadface: Password is required"}
        };
    }

    @Test
    public void checkSuccessfulLogin() {

        InventoryPage inventoryPage = new LoginPage()
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
}