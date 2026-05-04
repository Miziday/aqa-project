package utils;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class AssertSteps {

    public static void shouldShowErrorWithText(String text) {
        Allure.step("Отображение ошибки с текстом \"" + text+ "\"", () -> {
            String errorDivPath = "//*[@data-test='error-button']//ancestor::div[1]";
            $x(errorDivPath).shouldBe(Condition.visible);
            $x(errorDivPath).shouldHave(text(text));
        });
    }

    public static void shouldHaveMoreThenZeroGoods() {
        Allure.step("Проверить, что товары присутствуют", () -> {
            Assert.assertFalse($$x("//*[@data-test='inventory-item']").isEmpty());
        });
    }

    public static void shouldBeOpenedInventoryPage() {
        Allure.step("Проверить, что открыта страница Inventory", () -> {
            webdriver().shouldHave(urlContaining("inventory"));
        });
    }

    public static void loginButtonShouldBeClickable() {
        Allure.step("Проверить, что кнопка логина активна", () -> {
            $("#login-button").shouldBe(clickable);
        });
    }

    public static void allPointsSidebarMenuShouldBeVisible() {
        Allure.step("Проверить, что в сайд баре отображаются все его пункты", () -> {
            $("#inventory_sidebar_link").shouldBe(visible);
            $("#about_sidebar_link").shouldBe(visible);
            $("#logout_sidebar_link").shouldBe(visible);
            $("#reset_sidebar_link").shouldBe(visible);
        });
    }

}
