package com.github.wasiqb.android.pages;

import java.time.Duration;

import com.github.wasiqb.android.gestures.ActionsClass;
import com.github.wasiqb.android.gestures.Commands;
import com.github.wasiqb.android.gestures.GesturePluginCommands;
import com.github.wasiqb.android.gestures.W3CActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected ActionsClass          actionsClass;
    protected Commands              commands;
    protected AndroidDriver         driver;
    protected GesturePluginCommands gestureCommands;
    protected W3CActions            w3CActions;
    protected WebDriverWait         wait;

    protected BasePage (final AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait (driver, Duration.ofSeconds (10));
        this.w3CActions = new W3CActions (driver);
        this.commands = new Commands (this.driver);
        this.gestureCommands = new GesturePluginCommands (this.driver);
        this.actionsClass = new ActionsClass (driver);
    }

    public ActionsClass getActionsClass () {
        return this.actionsClass;
    }

    public Commands getCommands () {
        return this.commands;
    }

    public GesturePluginCommands getGestureCommands () {
        return this.gestureCommands;
    }

    public W3CActions getW3CActions () {
        return this.w3CActions;
    }
}
