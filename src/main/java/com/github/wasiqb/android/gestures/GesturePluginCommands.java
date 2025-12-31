package com.github.wasiqb.gestures.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class GesturePluginCommands {
    private final AndroidDriver driver;

    public GesturePluginCommands (final AndroidDriver driver) {
        this.driver = driver;
    }

    public void dragDrop (final WebElement source, final WebElement target) {
        final var command = "gesture: dragAndDrop";
        final var sourceId = ((RemoteWebElement) source).getId ();
        final var targetId = ((RemoteWebElement) target).getId ();
        final var params = ImmutableMap.<String, Object>builder ()
            .put ("sourceId", sourceId)
            .put ("destinationId", targetId)
            .build ();
        this.driver.executeScript (command, params);
    }

    public void swipe (final WebElement element, final String direction, final int percentage) {
        final var command = "gesture: swipe";
        final var paramsBuilder = ImmutableMap.<String, Object>builder ()
            .put ("direction", direction)
            .put ("percentage", percentage);
        final var id = ((RemoteWebElement) element).getId ();
        paramsBuilder.put ("elementId", id);
        this.driver.executeScript (command, paramsBuilder.build ());
    }
}
