package tests.ui;

import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;
import utils.AssertSteps;

public class ProductsTest extends BaseUiTest {

//    @Test(retryAnalyzer = Retryer.class)
    @Test
    public void checkGoodsMoreThenZero() {

        // Имитация флакования (верный username = standard_user)
//        List<String> flackData = List.of("standard_user", "standard_user1", "standard_user2");

        InventoryPage inventoryPage = new LoginPage()
                .openPage()
//                .enterUsername(flackData.get(ThreadLocalRandom.current().nextInt(flackData.size() - 1)))
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        AssertSteps.shouldHaveMoreThenZeroGoods();

    }

    @Test
    public void checkPointsOfSidebarMenu() {

        InventoryPage inventoryPage = new LoginPage()
                .openPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin()
                .sideBarMenuClick();

        AssertSteps.allPointsSidebarMenuShouldBeVisible();

    }

}
