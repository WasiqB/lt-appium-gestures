package com.github.wasiqb;

import java.net.MalformedURLException;

import com.github.wasiqb.gestures.android.W3CActions;
import com.github.wasiqb.manager.AndroidManager;
import com.github.wasiqb.pages.DragDropPage;
import com.github.wasiqb.pages.HomePage;
import com.github.wasiqb.pages.SwipePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidW3CTest {
    private W3CActions     androidActions;
    private AndroidManager androidManager;
    private DragDropPage   dragDropPage;
    private HomePage       homePage;
    private SwipePage      swipePage;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.androidManager = new AndroidManager ();
        this.homePage = new HomePage (this.androidManager.getDriver ());
        this.dragDropPage = new DragDropPage (this.androidManager.getDriver ());
        this.swipePage = new SwipePage (this.androidManager.getDriver ());
        this.androidActions = this.homePage.getW3CActions ();
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
                this.dragDropPage.dragAndDropAction (columnChar, index);
            }
        }
        Assert.assertEquals (this.dragDropPage.getSuccessMessageText (),
            "You made it, click retry if you want to try it again.");
    }

    @Test
    public void testSwipeLeftRight () {
        this.homePage.openSwipePage ();
        this.androidActions.swipeLeft (this.swipePage.getCarousal (), 50);
        this.androidActions.swipeRight (this.swipePage.getCarousal (), 50);
    }

    @Test
    public void testSwipeUpDown () {
        this.homePage.openSwipePage ();
        this.androidActions.swipeUp (null, 75);
        this.androidActions.swipeDown (null, 75);
    }

    @Test
    public void testZoomInOut () throws InterruptedException {
        this.homePage.openBrowserPage ();
        Thread.sleep (5000);
        this.androidActions.zoomIn (null, 50);
        Thread.sleep (2000);
        this.androidActions.zoomOut (null, 50);
        Thread.sleep (2000);
    }
}
