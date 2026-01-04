package com.github.wasiqb.ios;

import java.net.MalformedURLException;

import com.github.wasiqb.ios.gestures.GesturePluginCommands;
import com.github.wasiqb.ios.pages.DragDropPage;
import com.github.wasiqb.ios.pages.HomePage;
import com.github.wasiqb.ios.pages.SwipePage;
import com.github.wasiqb.manager.IOSManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IOSGestureCommandTest {
    private DragDropPage          dragDropPage;
    private HomePage              homePage;
    private GesturePluginCommands iosCommands;
    private IOSManager            iosManager;
    private SwipePage             swipePage;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.iosManager = new IOSManager ();
        this.homePage = new HomePage (this.iosManager.getDriver ());
        this.dragDropPage = new DragDropPage (this.iosManager.getDriver ());
        this.swipePage = new SwipePage (this.iosManager.getDriver ());
        this.iosCommands = this.homePage.getGestureCommands ();
    }

    @AfterClass
    public void tearDownClass () {
        this.iosManager.quit ();
    }

    @Test
    public void testDragDrop () {
        this.homePage.openDragDropPage ();
        final var columnsChars = new String[] { "l", "c", "r" };
        for (var index = 1; index <= 3; index++) {
            for (final var columnChar : columnsChars) {
                this.dragDropPage.dragAndDropGestureCommands (columnChar, index);
            }
        }
        Assert.assertEquals (this.dragDropPage.getSuccessMessageText (),
            "You made it, click retry if you want to try it again.");
    }

    @Test
    public void testSwipeLeftRight () {
        this.homePage.openSwipePage ();
        this.iosCommands.swipe (this.swipePage.getCarousal (), "left", 25);
        this.iosCommands.swipe (this.swipePage.getCarousal (), "right", 25);
    }

    @Test
    public void testSwipeUpDown () throws InterruptedException {
        this.homePage.openSwipePage ();
        Thread.sleep (5000);
        this.iosCommands.swipe (this.swipePage.getSwipeArea (), "up", 50);
        Thread.sleep (5000);
        this.iosCommands.swipe (this.swipePage.getSwipeArea (), "down", 50);
        Thread.sleep (5000);
    }
}
