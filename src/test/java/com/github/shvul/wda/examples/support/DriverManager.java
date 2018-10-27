package com.github.shvul.wda.examples.support;

import com.github.shvul.wda.client.driver.TVDriver;

public class DriverManager {

    private static volatile DriverManager instance;
    private TVDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                DriverManager manager = instance;
                if (manager == null) {
                    manager = new DriverManager();
                    instance = manager;
                }
            }
        }
        return instance;
    }

    public static TVDriver getDriver() {
        return DriverManager.getInstance().driver;
    }

    public static void setDriver(TVDriver driver) {
        DriverManager.getInstance().driver = driver;
    }
}
