package com.github.wasiqb.manager;

import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidManager {
    private static final String SERVER_URL = "http://localhost:4723";

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public AndroidManager () throws MalformedURLException {
        final var capabilities = buildCapabilities ();
        this.driver = new AndroidDriver (new URL (SERVER_URL), capabilities);
        this.driver.manage ()
            .timeouts ()
            .implicitlyWait (ofSeconds (3));

        this.wait = new WebDriverWait (this.driver, ofSeconds (10));
    }

    public AndroidDriver getDriver () {
        return this.driver;
    }

    public WebDriverWait getWait () {
        return this.wait;
    }

    public void quit () {
        this.driver.quit ();
    }

    private Capabilities buildCapabilities () {
        final var options = new UiAutomator2Options ();
        options.setDeviceName ("Pixel 9 Pro")
            .setPlatformVersion ("15")
            .setAvd ("Pixel_9_Pro")
            .setApp (Path.of (System.getProperty ("user.dir"), "src/test/resources", "wdio-demo.apk")
                .toString ())
            .setCapability ("appium:settings[ignoreUnimportantViews]", true);
        return options;
    }
}
