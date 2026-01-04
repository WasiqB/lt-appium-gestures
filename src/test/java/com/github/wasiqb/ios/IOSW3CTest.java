package com.github.wasiqb.ios;

import java.net.MalformedURLException;

import com.github.wasiqb.ios.gestures.W3CActions;
import com.github.wasiqb.ios.pages.DragDropPage;
import com.github.wasiqb.ios.pages.HomePage;
import com.github.wasiqb.ios.pages.SwipePage;
import com.github.wasiqb.manager.IOSManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IOSW3CTest {
    private DragDropPage dragDropPage;
    private HomePage     homePage;
    private W3CActions   iosActions;
    private IOSManager   iosManager;
    private SwipePage    swipePage;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.iosManager = new IOSManager ();
        this.homePage = new HomePage (this.iosManager.getDriver ());
        this.dragDropPage = new DragDropPage (this.iosManager.getDriver ());
        this.swipePage = new SwipePage (this.iosManager.getDriver ());
        this.iosActions = this.homePage.getW3CActions ();
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
                this.dragDropPage.dragAndDropAction (columnChar, index);
            }
        }
        Assert.assertEquals (this.dragDropPage.getSuccessMessageText (),
            "You made it, click retry if you want to try it again.");
    }

    @Test
    public void testSwipeLeftRight () {
        this.homePage.openSwipePage ();
        this.iosActions.swipeLeft (this.swipePage.getCarousal (), 50);
        this.iosActions.swipeRight (this.swipePage.getCarousal (), 50);
    }

    @Test
    public void testSwipeUpDown () throws InterruptedException {
        this.homePage.openBrowserPage ();
        Thread.sleep (5000);
        this.iosActions.swipeUp (null, 75);
        this.iosActions.swipeDown (null, 75);
    }

    @Test
    public void testZoomInOut () throws InterruptedException {
        this.homePage.openBrowserPage ();
        Thread.sleep (5000);
        this.iosActions.zoomIn (null, 50);
        Thread.sleep (2000);
        this.iosActions.zoomOut (null, 50);
        Thread.sleep (2000);
    }
}
