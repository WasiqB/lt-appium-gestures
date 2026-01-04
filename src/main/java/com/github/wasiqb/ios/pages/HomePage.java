package com.github.wasiqb.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final By browserTab  = AppiumBy.accessibilityId ("Webview");
    private final By dragDropTab = AppiumBy.accessibilityId ("Drag");
    private final By swipeTab    = AppiumBy.accessibilityId ("Swipe");

    public HomePage (final IOSDriver driver) {
        super (driver);
    }

    public WebElement getBrowserTab () {
        return this.wait.until (ExpectedConditions.elementToBeClickable (this.browserTab));
    }

    public WebElement getDragDropTab () {
        return this.wait.until (ExpectedConditions.elementToBeClickable (this.dragDropTab));
    }

    public WebElement getSwipeTab () {
        return this.wait.until (ExpectedConditions.elementToBeClickable (this.swipeTab));
    }

    public void openBrowserPage () {
        this.w3CActions.tap (getBrowserTab ());
    }

    public void openDragDropPage () {
        this.w3CActions.tap (getDragDropTab ());
    }

    public void openSwipePage () {
        this.w3CActions.tap (getSwipeTab ());
    }
}
