package ui.base.conditions;

import com.codeborne.selenide.WebElementCondition;

public class CustomConditions {

    public static final WebElementCondition readyToUse = new ReadyToUse();

    public static WebElementCondition hasName(String expectedStatus) {
        return new HasName(expectedStatus);
    }

}
