package ui.pages;

import config.ConfigReader;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    public LoginPage openPage() {
        return Allure.step("Открытие страницы", () -> {
            open(ConfigReader.get("baseUiUrl"));
            return this;
        });

    }

    public LoginPage enterUsername(String username) {
        return Allure.step("Ввод username " + username, () -> {
            $("#user-name").setValue(username);
            return this;
        });
    }

    public LoginPage enterPassword(String password) {
        return Allure.step("Ввод password " + password, () -> {
            $("#password").setValue(password);
            return this;
        }) ;
    }

    public InventoryPage clickLogin() {
        return Allure.step("Нажание на кнопку логина", () -> {
            $("#login-button").click();
            return new InventoryPage();
        });
    }

}