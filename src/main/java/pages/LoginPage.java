package pages;

import com.codeborne.selenide.Condition;
import config.ConfigReader;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    public LoginPage openPage() {
        open(ConfigReader.get("baseUrl"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        $("#user-name").setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        $("#password").setValue(password);
        return this;
    }

    public InventoryPage clickLogin() {
        $("#login-button").click();
        return new InventoryPage();
    }

    public LoginPage shouldShowError() {
        $x("//*[@data-test='error-button']//ancestor::div[1]").shouldBe(Condition.visible);
        return this;
    }


}