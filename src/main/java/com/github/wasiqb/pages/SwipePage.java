package com.github.wasiqb.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SwipePage extends BasePage {
    private final By carousal     = AppiumBy.accessibilityId ("Carousel");
    private final By scrolledLogo = AppiumBy.androidUIAutomator (
        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"WebdriverIO logo\"))");

    public SwipePage (final AndroidDriver driver) {
        super (driver);
    }

    public WebElement getCarousal () {
        return this.wait.until (ExpectedConditions.visibilityOfElementLocated (this.carousal));
    }

    public WebElement getScrolledLogo () {
        return this.wait.until (ExpectedConditions.visibilityOfElementLocated (this.scrolledLogo));
    }
}
