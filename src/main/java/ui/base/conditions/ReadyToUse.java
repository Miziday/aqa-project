package ui.base.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.WebElement;

public class ReadyToUse extends WebElementCondition {

    public ReadyToUse() {
        super("ready to use");
    }

    @Override
    public CheckResult check(Driver driver, WebElement element) {

        boolean ready = element.isDisplayed() && element.isEnabled();

        return ready
                ? CheckResult.accepted()
                : CheckResult.rejected("actual data-status: ", false);
    }
}
