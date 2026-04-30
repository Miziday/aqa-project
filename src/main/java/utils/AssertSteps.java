package utils;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
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
            Assert.assertTrue(!$$x("//*[@data-test='inventory-item']").isEmpty());
        });
    }

    public static void shouldBeOpenedInventoryPage() {
        Allure.step("Проверить, что открыта страница Inventory", () -> {
            webdriver().shouldHave(urlContaining("inventory"));
        });
    }

}
