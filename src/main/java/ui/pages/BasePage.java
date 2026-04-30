package ui.pages;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.WebDriverConditions.title;

public class BasePage {

    public void waitForUrlContains(String urlPart) {
        webdriver().shouldHave(urlContaining(urlPart));
    }

    public void waitForTitle(String title) {
        webdriver().shouldHave(title(title));
    }
}