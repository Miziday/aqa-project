package tests.ui;

import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;
import utils.AssertSteps;

public class ProductsTest extends BaseUiTest {

    @Test
    public void checkGoodsMoreThenZero() {

        InventoryPage inventoryPage = new LoginPage()
                .openPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        AssertSteps.shouldHaveMoreThenZeroGoods();
    }

}
