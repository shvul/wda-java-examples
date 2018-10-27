package com.github.shvul.wda.examples.tests;

import com.github.shvul.wda.client.driver.TVDriver;
import com.github.shvul.wda.client.element.TVLocator;
import com.github.shvul.wda.examples.support.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected TVDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = DriverManager.getDriver();
        driver.activate();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.terminate();
    }

    protected void openAttributesPage() {
        driver.findElement(TVLocator.name("Attributes")).select();
    }
}
