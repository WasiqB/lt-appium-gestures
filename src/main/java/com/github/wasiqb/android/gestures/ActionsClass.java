package com.github.wasiqb.android.gestures;

import static java.util.Objects.isNull;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class ActionsClass {
    private final AndroidTouchAction actions;
    private final AndroidDriver      driver;

    public ActionsClass (final AndroidDriver driver) {
        this.driver = driver;
        this.actions = new AndroidTouchAction (driver);
    }

    public void dragDrop (final WebElement source, final WebElement target) {
        this.actions.longPress (ElementOption.element (source))
            .moveTo (ElementOption.element (target))
            .release ()
            .perform ();
    }

    public void swipeDown (final WebElement element, final int distance) {
        final var direction = new Point (0, 1);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        this.actions.longPress (PointOption.point (start))
            .moveTo (PointOption.point (end))
            .release ()
            .perform ();
    }

    public void swipeLeft (final WebElement element, final int distance) {
        final var direction = new Point (-1, 0);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        this.actions.longPress (PointOption.point (start))
            .moveTo (PointOption.point (end))
            .release ()
            .perform ();
    }

    public void swipeRight (final WebElement element, final int distance) {
        final var direction = new Point (1, 0);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        this.actions.longPress (PointOption.point (start))
            .moveTo (PointOption.point (end))
            .release ()
            .perform ();
    }

    public void swipeUp (final WebElement element, final int distance) {
        final var direction = new Point (0, -1);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        this.actions.longPress (PointOption.point (start))
            .moveTo (PointOption.point (end))
            .release ()
            .perform ();
    }

    public void tap (final WebElement element) {
        this.actions.tap (TapOptions.tapOptions ()
                .withElement (ElementOption.element (element)))
            .perform ();
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

    private Point getSwipeEndPosition (final Point direction, final WebElement element, final int distance) {
        final var start = getSwipeStartPosition (element);
        final var x = start.getX () + ((start.getX () * direction.getX () * distance) / 100);
        final var y = start.getY () + ((start.getY () * direction.getY () * distance) / 100);
        return new Point (x, y);
    }

    private Point getSwipeStartPosition (final WebElement element) {
        final var screenSize = getScreenSize ();
        var x = screenSize.getWidth () / 2;
        var y = screenSize.getHeight () / 2;
        if (!isNull (element)) {
            final var point = getElementCenter (element);
            x = point.getX ();
            y = point.getY ();
        }
        return new Point (x, y);
    }
}
