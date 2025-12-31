package com.github.wasiqb.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final By browserTab  = AppiumBy.accessibilityId ("Webview");
    private final By dragDropTab = AppiumBy.accessibilityId ("Drag");
    private final By swipeTab    = AppiumBy.accessibilityId ("Swipe");

    public HomePage (final AndroidDriver driver) {
        super (driver);
    }

    public void openBrowserPage () {
        final var browserTabElement = this.wait.until (ExpectedConditions.elementToBeClickable (this.browserTab));
        this.w3CActions.tap (browserTabElement);
    }

    public void openDragDropPage () {
        final var dragDropTabElement = this.wait.until (ExpectedConditions.elementToBeClickable (this.dragDropTab));
        this.w3CActions.tap (dragDropTabElement);
    }

    public void openSwipePage () {
        final var swipeTabElement = this.wait.until (ExpectedConditions.elementToBeClickable (this.swipeTab));
        this.w3CActions.tap (swipeTabElement);
    }
}
