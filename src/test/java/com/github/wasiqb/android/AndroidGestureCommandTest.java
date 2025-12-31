package com.github.wasiqb;

import java.net.MalformedURLException;

import com.github.wasiqb.gestures.android.GesturePluginCommands;
import com.github.wasiqb.manager.AndroidManager;
import com.github.wasiqb.pages.DragDropPage;
import com.github.wasiqb.pages.HomePage;
import com.github.wasiqb.pages.SwipePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidGestureCommandTest {
    private GesturePluginCommands androidCommands;
    private AndroidManager        androidManager;
    private DragDropPage          dragDropPage;
    private HomePage              homePage;
    private SwipePage             swipePage;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.androidManager = new AndroidManager ();
        this.homePage = new HomePage (this.androidManager.getDriver ());
        this.dragDropPage = new DragDropPage (this.androidManager.getDriver ());
        this.swipePage = new SwipePage (this.androidManager.getDriver ());
        this.androidCommands = this.homePage.getGestureCommands ();
    }

    @AfterClass
    public void tearDownClass () {
        this.androidManager.quit ();
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
        this.androidCommands.swipe (this.swipePage.getCarousal (), "left", 25);
        this.androidCommands.swipe (this.swipePage.getCarousal (), "right", 25);
    }

    @Test
    public void testSwipeUpDown () throws InterruptedException {
        this.homePage.openSwipePage ();
        this.androidCommands.swipe (this.swipePage.getSwipeArea (), "up", 50);
        Thread.sleep (5000);
        this.androidCommands.swipe (this.swipePage.getSwipeArea (), "down", 50);
        Thread.sleep (5000);
    }
}
