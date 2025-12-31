package com.github.wasiqb.pages;

import java.text.MessageFormat;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DragDropPage extends BasePage {
    private final By successMessage = AppiumBy.androidUIAutomator ("new UiSelector().textStartsWith(\"You made it\")");

    public DragDropPage (final AndroidDriver driver) {
        super (driver);
    }

    public void dragAndDropAction (final String columnChar, final int index) {
        final var dragElement = this.wait.until (
            ExpectedConditions.elementToBeClickable (getDragElement (columnChar, index)));
        final var dropElement = this.wait.until (
            ExpectedConditions.elementToBeClickable (getDropElement (columnChar, index)));
        this.w3CActions.dragDrop (dragElement, dropElement);
    }

    public void dragAndDropCommands (final String columnChar, final int index) {
        final var dragElement = this.wait.until (
            ExpectedConditions.elementToBeClickable (getDragElement (columnChar, index)));
        final var dropElement = this.wait.until (
            ExpectedConditions.elementToBeClickable (getDropElement (columnChar, index)));
        this.commands.dragDrop (dragElement, dropElement);
    }

    public void dragAndDropGestureCommands (final String columnChar, final int index) {
        final var dragElement = this.wait.until (
            ExpectedConditions.elementToBeClickable (getDragElement (columnChar, index)));
        final var dropElement = this.wait.until (
            ExpectedConditions.elementToBeClickable (getDropElement (columnChar, index)));
        this.gestureCommands.dragDrop (dragElement, dropElement);
    }

    public String getSuccessMessageText () {
        final var messageElement = this.wait.until (
            ExpectedConditions.visibilityOfElementLocated (this.successMessage));
        return messageElement.getText ();
    }

    private By getDragElement (final String columnChar, final int index) {
        return AppiumBy.accessibilityId (MessageFormat.format ("drag-{0}{1}", columnChar, index));
    }

    private By getDropElement (final String columnChar, final int index) {
        return AppiumBy.accessibilityId (MessageFormat.format ("drop-{0}{1}", columnChar, index));
    }
}
