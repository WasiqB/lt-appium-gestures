package com.github.wasiqb.manager;

import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;

public class IOSManager {
    private static final String SERVER_URL = "http://localhost:4723";

    private final IOSDriver driver;

    public IOSManager () throws MalformedURLException {
        final var capabilities = buildCapabilities ();
        this.driver = new IOSDriver (new URL (SERVER_URL), capabilities);
        this.driver.manage ()
            .timeouts ()
            .implicitlyWait (ofSeconds (3));
    }

    public IOSDriver getDriver () {
        return this.driver;
    }

    public void quit () {
        this.driver.quit ();
    }

    private Capabilities buildCapabilities () {
        final var options = new XCUITestOptions ();
        options.setDeviceName ("iPhone 17 Pro")
            .setPlatformVersion ("26.2")
            .simulatorTracePointer ()
            .setApp (Path.of (System.getProperty ("user.dir"), "src/test/resources", "wdio-demo.zip")
                .toString ());
        return options;
    }
}
