package com.github.wasiqb.ios.gestures;

import static java.util.Objects.isNull;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Commands {
    private final IOSDriver driver;

    public Commands (final IOSDriver driver) {
        this.driver = driver;
    }

    public void dragDrop (final WebElement source, final WebElement target) {
        final var command = "mobile: dragFromToWithVelocity";
        final var sourceId = ((RemoteWebElement) source).getId ();
        final var targetId = ((RemoteWebElement) target).getId ();
        final var params = ImmutableMap.<String, Object>builder ()
            .put ("fromElementId", sourceId)
            .put ("toElementId", targetId)
            .put ("pressDuration", 0.5)
            .put ("holdDuration", 0.5)
            .put ("velocity", 500)
            .build ();
        this.driver.executeScript (command, params);
    }

    public void swipe (final WebElement element, final String direction, final int speed) {
        final var command = "mobile: swipe";
        final var paramsBuilder = ImmutableMap.<String, Object>builder ()
            .put ("direction", direction)
            .put ("velocity", speed);
        if (!isNull (element)) {
            final var id = ((RemoteWebElement) element).getId ();
            paramsBuilder.put ("elementId", id);
        }
        this.driver.executeScript (command, paramsBuilder.build ());
    }

    public void tap (final WebElement element) {
        final var command = "mobile: tap";
        //        final var id = ((RemoteWebElement) element).getId ();
        final var coordinates = getElementCenter (element);
        final var params = ImmutableMap.builder ()
            .put ("x", coordinates.getX ())
            .put ("y", coordinates.getY ())
            .build ();
        this.driver.executeScript (command, params);
    }

    public void zoomIn (final WebElement element) {
        final var command = "mobile: pinch";
        final var params = ImmutableMap.builder ()
            .put ("scale", 2.0)
            .put ("velocity", 1.0);
        if (!isNull (element)) {
            final var id = ((RemoteWebElement) element).getId ();
            params.put ("elementId", id);
        }
        this.driver.executeScript (command, params.build ());
    }

    public void zoomOut (final WebElement element) {
        final var command = "mobile: pinch";
        final var params = ImmutableMap.builder ()
            .put ("scale", 0.5)
            .put ("velocity", -1.0);
        if (!isNull (element)) {
            final var id = ((RemoteWebElement) element).getId ();
            params.put ("elementId", id);
        }
        this.driver.executeScript (command, params.build ());
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
