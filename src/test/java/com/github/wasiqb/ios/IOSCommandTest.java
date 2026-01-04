package com.github.wasiqb.ios;

import java.net.MalformedURLException;

import com.github.wasiqb.ios.gestures.Commands;
import com.github.wasiqb.ios.pages.DragDropPage;
import com.github.wasiqb.ios.pages.HomePage;
import com.github.wasiqb.ios.pages.SwipePage;
import com.github.wasiqb.manager.IOSManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IOSCommandTest {
    private DragDropPage dragDropPage;
    private HomePage     homePage;
    private Commands     iosCommands;
    private IOSManager   iosManager;
    private SwipePage    swipePage;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.iosManager = new IOSManager ();
        this.homePage = new HomePage (this.iosManager.getDriver ());
        this.dragDropPage = new DragDropPage (this.iosManager.getDriver ());
        this.swipePage = new SwipePage (this.iosManager.getDriver ());
        this.iosCommands = this.homePage.getCommands ();
    }

    @AfterClass
    public void tearDownClass () {
        this.iosManager.quit ();
    }

    @Test
    public void testDragDrop () {
        this.iosCommands.tap (this.homePage.getDragDropTab ());
        final var columnsChars = new String[] { "l", "c", "r" };
        for (var index = 1; index <= 3; index++) {
            for (final var columnChar : columnsChars) {
                this.dragDropPage.dragAndDropCommands (columnChar, index);
            }
        }
        Assert.assertEquals (this.dragDropPage.getSuccessMessageText (),
            "You made it, click retry if you want to try it again.");
    }

    @Test
    public void testSwipeLeftRight () {
        this.iosCommands.tap (this.homePage.getSwipeTab ());
        this.iosCommands.swipe (this.swipePage.getCarousal (), "left", 50);
        this.iosCommands.swipe (this.swipePage.getCarousal (), "right", 50);
    }

    @Test
    public void testSwipeUpDown () throws InterruptedException {
        this.iosCommands.tap (this.homePage.getBrowserTab ());
        Thread.sleep (5000);
        this.iosCommands.swipe (null, "up", 75);
        Thread.sleep (5000);
        this.iosCommands.swipe (null, "down", 75);
        Thread.sleep (5000);
    }

    @Test
    public void testZoomInOut () throws InterruptedException {
        this.iosCommands.tap (this.homePage.getBrowserTab ());
        Thread.sleep (5000);
        this.iosCommands.zoomIn (null);
        Thread.sleep (2000);
        this.iosCommands.zoomOut (null);
        Thread.sleep (2000);
    }
}
