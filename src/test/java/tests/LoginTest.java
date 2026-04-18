package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][] {
                {"user1", "pass1"},
                {"user2", "pass2"},
                {"", "pass"},
                {"user", ""}
        };
    }

    @Test
    public void successfulLoginTest() {

        InventoryPage inventoryPage = new LoginPage()
                .openPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin()
                .shouldBeOpened();
    }

    @Test(dataProvider = "invalidDataProvider")
    public void invalidLoginTest(String login, String pass) {
        new LoginPage()
                .openPage()
                .enterUsername(login)
                .enterPassword(pass)
                .clickLogin();

        new LoginPage()
                .shouldShowError();
    }
}