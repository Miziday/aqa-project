package ui.base.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.WebElement;

public class HasName extends WebElementCondition {

    private final String expectedName;

    public HasName(String expectedName) {
        super("name: " + expectedName);
        this.expectedName = expectedName;
    }

    @Override
    public CheckResult check(Driver driver, WebElement element) {

        String actualName = element.getAttribute("name");

        boolean matched = expectedName.equals(actualName);

        return matched ? CheckResult.accepted() : CheckResult.rejected("wrong status", actualName);

    }
}
