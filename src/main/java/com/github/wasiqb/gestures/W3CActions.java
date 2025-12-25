package com.github.wasiqb.gestures;

import static java.util.Objects.isNull;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class W3CActions {
    private final AndroidDriver driver;

    public W3CActions (final AndroidDriver driver) {
        this.driver = driver;
    }

    public void dragDrop (final WebElement source, final WebElement target) {
        final var sourceCenter = getElementCenter (source);
        final var targetCenter = getElementCenter (target);

        final var finger = new PointerInput (PointerInput.Kind.TOUCH, "Finger 1");
        final var sequence = new Sequence (finger, 0);

        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), sourceCenter.getX (),
                sourceCenter.getY ()));
        sequence.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        sequence.addAction (new Pause (finger, Duration.ofMillis (500)));
        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), targetCenter.getX (),
                targetCenter.getY ()));
        sequence.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Collections.singletonList (sequence));
    }

    public void swipeDown (final WebElement element, final int distance) {
        final var direction = new Point (0, 1);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        final var finger = new PointerInput (PointerInput.Kind.TOUCH, "Finger 1");
        final var sequence = new Sequence (finger, 0);

        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), start.getX (),
                start.getY ()));
        sequence.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        sequence.addAction (new Pause (finger, Duration.ofMillis (500)));
        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), end.getX (),
                end.getY ()));
        sequence.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Collections.singletonList (sequence));
    }

    public void swipeLeft (final WebElement element, final int distance) {
        final var direction = new Point (-1, 0);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        final var finger = new PointerInput (PointerInput.Kind.TOUCH, "Finger 1");
        final var sequence = new Sequence (finger, 0);

        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), start.getX (),
                start.getY ()));
        sequence.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        sequence.addAction (new Pause (finger, Duration.ofMillis (500)));
        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), end.getX (),
                end.getY ()));
        sequence.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Collections.singletonList (sequence));
    }

    public void swipeRight (final WebElement element, final int distance) {
        final var direction = new Point (1, 0);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        final var finger = new PointerInput (PointerInput.Kind.TOUCH, "Finger 1");
        final var sequence = new Sequence (finger, 0);

        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), start.getX (),
                start.getY ()));
        sequence.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        sequence.addAction (new Pause (finger, Duration.ofMillis (500)));
        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), end.getX (),
                end.getY ()));
        sequence.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Collections.singletonList (sequence));
    }

    public void swipeUp (final WebElement element, final int distance) {
        final var direction = new Point (0, -1);
        final var start = getSwipeStartPosition (element);
        final var end = getSwipeEndPosition (direction, element, distance);

        final var finger = new PointerInput (PointerInput.Kind.TOUCH, "Finger 1");
        final var sequence = new Sequence (finger, 0);

        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), start.getX (),
                start.getY ()));
        sequence.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        sequence.addAction (new Pause (finger, Duration.ofMillis (500)));
        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), end.getX (),
                end.getY ()));
        sequence.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Collections.singletonList (sequence));
    }

    public void tap (final WebElement element) {
        final var center = getElementCenter (element);

        final var finger = new PointerInput (PointerInput.Kind.TOUCH, "Finger 1");
        final var sequence = new Sequence (finger, 0);

        sequence.addAction (
            finger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), center.getX (),
                center.getY ()));
        sequence.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        sequence.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Collections.singletonList (sequence));
    }

    public void zoomIn (final WebElement element, final int distance) {
        final var thumbDirection = new Point (0, 1);
        final var thumbStart = getSwipeStartPosition (element);
        final var thumbEnd = getSwipeEndPosition (thumbDirection, element, distance);

        final var thumbFinger = new PointerInput (PointerInput.Kind.TOUCH, "Thumb Finger");
        final var thumbSequence = new Sequence (thumbFinger, 0);

        thumbSequence.addAction (
            thumbFinger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), thumbStart.getX (),
                thumbStart.getY () + 5));
        thumbSequence.addAction (thumbFinger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        thumbSequence.addAction (new Pause (thumbFinger, Duration.ofMillis (500)));
        thumbSequence.addAction (
            thumbFinger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), thumbEnd.getX (),
                thumbEnd.getY ()));
        thumbSequence.addAction (thumbFinger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        final var indexDirection = new Point (0, -1);
        final var indexStart = getSwipeStartPosition (element);
        final var indexEnd = getSwipeEndPosition (indexDirection, element, distance);

        final var indexFinger = new PointerInput (PointerInput.Kind.TOUCH, "Index Finger");
        final var indexSequence = new Sequence (indexFinger, 0);

        indexSequence.addAction (
            indexFinger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), indexStart.getX (),
                indexStart.getY () - 5));
        indexSequence.addAction (indexFinger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        indexSequence.addAction (new Pause (indexFinger, Duration.ofMillis (500)));
        indexSequence.addAction (
            indexFinger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), indexEnd.getX (),
                indexEnd.getY ()));
        indexSequence.addAction (indexFinger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Arrays.asList (thumbSequence, indexSequence));
    }

    public void zoomOut (final WebElement element, final int distance) {
        final var thumbDirection = new Point (0, -1);
        final var thumbEnd = getSwipeStartPosition (element);
        final var thumbStart = getSwipeEndPosition (thumbDirection, element, distance);

        final var thumbFinger = new PointerInput (PointerInput.Kind.TOUCH, "Thumb Finger");
        final var thumbSequence = new Sequence (thumbFinger, 0);

        thumbSequence.addAction (
            thumbFinger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), thumbStart.getX (),
                thumbStart.getY () + 5));
        thumbSequence.addAction (thumbFinger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        thumbSequence.addAction (new Pause (thumbFinger, Duration.ofMillis (500)));
        thumbSequence.addAction (
            thumbFinger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), thumbEnd.getX (),
                thumbEnd.getY ()));
        thumbSequence.addAction (thumbFinger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        final var indexDirection = new Point (0, 1);
        final var indexEnd = getSwipeStartPosition (element);
        final var indexStart = getSwipeEndPosition (indexDirection, element, distance);

        final var indexFinger = new PointerInput (PointerInput.Kind.TOUCH, "Index Finger");
        final var indexSequence = new Sequence (indexFinger, 0);

        indexSequence.addAction (
            indexFinger.createPointerMove (Duration.ofMillis (500), PointerInput.Origin.viewport (), indexStart.getX (),
                indexStart.getY () - 5));
        indexSequence.addAction (indexFinger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
        indexSequence.addAction (new Pause (indexFinger, Duration.ofMillis (500)));
        indexSequence.addAction (
            indexFinger.createPointerMove (Duration.ofMillis (300), PointerInput.Origin.viewport (), indexEnd.getX (),
                indexEnd.getY ()));
        indexSequence.addAction (indexFinger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));

        this.driver.perform (Arrays.asList (thumbSequence, indexSequence));
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
