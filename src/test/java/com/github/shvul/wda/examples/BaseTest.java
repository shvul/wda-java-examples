package com.github.shvul.wda.examples;

import com.github.shvul.wda.client.driver.DriverCapabilities;
import com.github.shvul.wda.client.driver.DriverFactory;
import com.github.shvul.wda.client.driver.TVDriver;
import com.github.shvul.wda.client.support.LoggerManager;
import org.apache.http.util.Args;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected TVDriver driver;

    @BeforeSuite(alwaysRun = true)
    @Parameters( {"deviceIp", "deviceId"} )
    public void createDriver(String deviceIp, String deviceId) {

        Args.check(!deviceId.isEmpty(),"Specify deviceId in TestNGSuiteConfig.xml");
        Args.check(!deviceIp.isEmpty(),"Specify deviceIp in TestNGSuiteConfig.xml");

        // enable console log from wda client
        LoggerManager.enableServerConsoleLogging();
        LoggerManager.enableClientConsoleLogging();

        // setup driver
        DriverCapabilities capabilities = new DriverCapabilities();
        capabilities.setWdaPath("WebDriverAgent/WebDriverAgent.xcodeproj");
        capabilities.setPlatform("tvOS");
        capabilities.setDeviceName("Apple TV");
        capabilities.setBundleId("com.facebook.wda.integrationApp");
        capabilities.setDeviceIp(deviceIp);
        capabilities.setDeviceId(deviceId);
        capabilities.setAppPath(getClass().getClassLoader().getResource("IntegrationApp_tvOS.ipa").getPath());
        driver = DriverFactory.createDriver(capabilities);
    }

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver.activate();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.terminate();
    }
}
