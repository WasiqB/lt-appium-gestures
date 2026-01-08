package com.github.wasiqb.android;

import java.net.MalformedURLException;

import com.github.wasiqb.android.pages.HomePage;
import com.github.wasiqb.android.pages.SwipePage;
import com.github.wasiqb.manager.AndroidManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidSelectorTest {
    private AndroidManager androidManager;
    private HomePage       homePage;
    private SwipePage      swipePage;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.androidManager = new AndroidManager ();
        this.homePage = new HomePage (this.androidManager.getDriver ());
        this.swipePage = new SwipePage (this.androidManager.getDriver ());
    }

    @AfterClass
    public void tearDownClass () {
        this.androidManager.quit ();
    }

    @Test
    public void testSwipeUpDown () {
        this.homePage.openSwipePage ();
        final var isLogoDisplayed = this.swipePage.getScrolledSelectorLogo ()
            .isDisplayed ();
        Assert.assertTrue (isLogoDisplayed);
    }
}
