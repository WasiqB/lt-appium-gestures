package com.github.wasiqb.manager;

import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;

public class LTAndroidManager {
    private static final String        ACCESS_KEY = System.getenv ("LT_ACCESS_KEY");
    private static final String        SERVER_URL = "https://{0}:{1}@mobile-hub.lambdatest.com/wd/hub";
    private static final String        USERNAME   = System.getenv ("LT_USERNAME");
    private final        AndroidDriver driver;

    public LTAndroidManager () throws MalformedURLException {
        final var capabilities = buildCapabilities ();
        this.driver = new AndroidDriver (new URL (MessageFormat.format (SERVER_URL, USERNAME, ACCESS_KEY)),
            capabilities);
        this.driver.manage ()
            .timeouts ()
            .implicitlyWait (ofSeconds (3));
    }

    public AndroidDriver getDriver () {
        return this.driver;
    }

    public void quit () {
        this.driver.quit ();
    }

    private Capabilities buildCapabilities () {
        final Map<String, Object> ltOptions = new HashMap<> ();
        ltOptions.put ("w3c", true);
        ltOptions.put ("platformName", "android");
        ltOptions.put ("deviceName", "Pixel 9 Pro");
        ltOptions.put ("platformVersion", "15");
        ltOptions.put ("visual", true);
        ltOptions.put ("network", true);
        ltOptions.put ("timezone", "Kolkata");
        ltOptions.put ("video", true);
        ltOptions.put ("app", "AndroidApp");
        ltOptions.put ("build", "Test Build");
        ltOptions.put ("name", "Test Name");
        ltOptions.put ("project", "Project Name");
        ltOptions.put ("autoGrantPermissions", true);
        ltOptions.put ("autoAcceptAlerts", true);
        ltOptions.put ("isRealMobile", true);
        ltOptions.put ("console", true);

        final var options = new UiAutomator2Options ();
        options.setCapability ("lt:options", ltOptions);
        return options;
    }
}
