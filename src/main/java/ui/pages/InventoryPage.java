package ui.pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class InventoryPage extends BasePage {

    public InventoryPage shouldBeOpened() {
         webdriver().shouldHave(urlContaining("inventory"));
         return this;
    }
}