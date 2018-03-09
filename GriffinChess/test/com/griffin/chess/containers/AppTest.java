package com.griffin.chess.containers;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class AppTest {
    @Test
    public void testInitialOptions() {
        App myApp = new App();
        HashMap<String, String> correctOptions = new HashMap<>();
        correctOptions.put("view", "main");
        correctOptions.put("theme", "day");
        correctOptions.put("opponent", "robot");
        correctOptions.put("difficulty", "normal");

        HashMap<String, String> initialOptions = myApp.getOptions();
        Assert.assertEquals("initial options set correctly", correctOptions, initialOptions);
    }

    @Test
    public void testChangeOptions() {
        App myApp = new App();
        HashMap<String, String> correctOptions = new HashMap<>();
        correctOptions.put("view", "main");
        correctOptions.put("theme", "night");
        correctOptions.put("opponent", "human");
        correctOptions.put("difficulty", "hard");

        HashMap<String, String> actualOptions = myApp.getOptions();
        Assert.assertNotEquals("new options change correctly", correctOptions, actualOptions);

        actualOptions.put("theme", "night");
        actualOptions.put("opponent", "human");
        actualOptions.put("difficulty", "hard");
        Assert.assertEquals("chosen options correctly reflected in game", correctOptions, actualOptions);
    }
}