package ui.pages;

import com.codeborne.selenide.Condition;
import config.ConfigReader;
import io.qameta.allure.Allure;


import static com.codeborne.selenide.Selenide.*;

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

    public LoginPage shouldShowError() {
        return Allure.step("Отображение ошибки", () -> {
            $x("//*[@data-test='error-button']//ancestor::div[1]").shouldBe(Condition.visible);
            return this;
        });
    }

}