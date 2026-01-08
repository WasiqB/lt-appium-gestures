package com.github.wasiqb.android;

import java.net.MalformedURLException;

import com.github.wasiqb.android.gestures.ActionsClass;
import com.github.wasiqb.android.pages.DragDropPage;
import com.github.wasiqb.android.pages.HomePage;
import com.github.wasiqb.android.pages.SwipePage;
import com.github.wasiqb.manager.AndroidManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidActionsTest {
    private ActionsClass   androidActions;
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
        this.androidActions = this.homePage.getActionsClass ();
    }

    @AfterClass
    public void tearDownClass () {
        this.androidManager.quit ();
    }

    @Test
    public void testDragDrop () {
        this.androidActions.tap (this.homePage.getDragDropTab ());
        final var columnsChars = new String[] { "l", "c", "r" };
        for (var index = 1; index <= 3; index++) {
            for (final var columnChar : columnsChars) {
                this.dragDropPage.dragAndDropActionClass (columnChar, index);
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
}
