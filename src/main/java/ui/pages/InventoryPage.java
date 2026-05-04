package ui.pages;

import static com.codeborne.selenide.Selenide.$;

public class InventoryPage extends BasePage {

    public InventoryPage sideBarMenuClick() {
        $("#react-burger-menu-btn").click();
        return this;
    }

}