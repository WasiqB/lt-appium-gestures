package com.github.wasiqb.pages;

import java.time.Duration;

import com.github.wasiqb.gestures.android.Commands;
import com.github.wasiqb.gestures.android.W3CActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected Commands      commands;
    protected AndroidDriver driver;
    protected W3CActions    w3CActions;
    protected WebDriverWait wait;

    protected BasePage (final AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait (driver, Duration.ofSeconds (10));
        this.w3CActions = new W3CActions (driver);
        this.commands = new Commands (this.driver);
    }

    public Commands getCommands () {
        return this.commands;
    }

    public W3CActions getW3CActions () {
        return this.w3CActions;
    }
}
