package pages;

import static com.codeborne.selenide.Selenide.webdriver;

public class BasePage {

    public void waitForUrlContains(String urlPart) {
        webdriver().shouldHave(com.codeborne.selenide.WebDriverConditions.urlContaining(urlPart));
    }

    public void waitForTitle(String title) {
        webdriver().shouldHave(com.codeborne.selenide.WebDriverConditions.title(title));
    }
}