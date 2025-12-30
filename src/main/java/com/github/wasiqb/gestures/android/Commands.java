package com.github.wasiqb.gestures.android;

import static java.util.Objects.isNull;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Commands {
    private final AndroidDriver driver;

    public Commands (final AndroidDriver driver) {
        this.driver = driver;
    }

    public void dragDrop (final WebElement source, final WebElement target) {
        final var command = "mobile: dragGesture";
        final var sourceCenter = getElementCenter (source);
        final var targetCenter = getElementCenter (target);
        final var params = ImmutableMap.<String, Object>builder ()
            .put ("startX", sourceCenter.getX ())
            .put ("startY", sourceCenter.getY ())
            .put ("endX", targetCenter.getX ())
            .put ("endY", targetCenter.getY ())
            .build ();
        this.driver.executeScript (command, params);
    }

    public void swipe (final WebElement element, final String direction, final int percentage) {
        final var command = "mobile: swipeGesture";
        final var paramsBuilder = ImmutableMap.<String, Object>builder ()
            .put ("direction", direction)
            .put ("percent", percentage / 100.0);
        if (element != null) {
            final var id = ((RemoteWebElement) element).getId ();
            paramsBuilder.put ("elementId", id);
        } else {
            paramsBuilder.put ("top", 200)
                .put ("left", 100)
                .put ("width", getScreenSize ().getWidth () - 100)
                .put ("height", getScreenSize ().getHeight () / 2);
        }
        this.driver.executeScript (command, paramsBuilder.build ());
    }

    public void tap (final WebElement element) {
        final var command = "mobile: clickGesture";
        final var id = ((RemoteWebElement) element).getId ();
        this.driver.executeScript (command, ImmutableMap.of ("elementId", id));
    }

    public void zoomIn (final WebElement element, final int percentage) {
        final var command = "mobile: pinchOpenGesture";
        final var params = ImmutableMap.builder ()
            .put ("percent", percentage / 100.0);
        if (!isNull (element)) {
            final var id = ((RemoteWebElement) element).getId ();
            params.put ("elementId", id);
        } else {
            params.put ("top", 200)
                .put ("left", 100)
                .put ("width", getScreenSize ().getWidth () - 100)
                .put ("height", getScreenSize ().getHeight () / 2);
        }
        this.driver.executeScript (command, params);
    }

    public void zoomOut (final WebElement element, final int percentage) {
        final var command = "mobile: pinchCloseGesture";
        final var params = ImmutableMap.builder ()
            .put ("percent", percentage / 100.0);
        if (!isNull (element)) {
            final var id = ((RemoteWebElement) element).getId ();
            params.put ("elementId", id);
        } else {
            params.put ("top", 100)
                .put ("left", 100)
                .put ("width", getScreenSize ().getWidth () - 100)
                .put ("height", getScreenSize ().getHeight () - 100);
        }
        this.driver.executeScript (command, params);
    }

    private Point getElementCenter (final WebElement element) {
        final var location = element.getLocation ();
        final var size = element.getSize ();
        final var centerX = location.getX () + size.getWidth () / 2;
        final var centerY = location.getY () + size.getHeight () / 2;
        return new Point (centerX, centerY);
    }

    private Dimension getScreenSize () {
        return this.driver.manage ()
            .window ()
            .getSize ();
    }
}
