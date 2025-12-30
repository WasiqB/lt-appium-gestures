package com.github.wasiqb;

import java.net.MalformedURLException;

import com.github.wasiqb.manager.AndroidManager;
import com.github.wasiqb.pages.HomePage;
import com.github.wasiqb.pages.SwipePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidUiSelectorTest {
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
    public void testSwipeTillElement () {
        this.homePage.openSwipePage ();
        final var logoDisplayed = this.swipePage.getScrolledLogo ()
            .isDisplayed ();
        Assert.assertTrue (logoDisplayed, "Logo should be displayed after scrolling");
    }
}
