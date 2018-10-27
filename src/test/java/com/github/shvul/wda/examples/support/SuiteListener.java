package com.github.shvul.wda.examples.support;

import com.github.shvul.wda.client.driver.DriverCapabilities;
import com.github.shvul.wda.client.driver.DriverFactory;
import com.github.shvul.wda.client.driver.TVDriver;
import com.github.shvul.wda.client.support.LoggerManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.Map;
import java.util.Optional;

public class SuiteListener implements ISuiteListener {

    public void onStart(ISuite iSuite) {
        // enable console log from wda client
        LoggerManager.enableServerConsoleLogging();
        LoggerManager.enableClientConsoleLogging();

        // setup driver
        Map<String, String> parameters = iSuite.getXmlSuite().getParameters();
        DriverCapabilities capabilities = new DriverCapabilities();
        capabilities.setWdaPath("WebDriverAgent/WebDriverAgent.xcodeproj");
        capabilities.setPlatform("tvOS");
        capabilities.setDeviceName("Apple TV");
        capabilities.setBundleId("com.facebook.wda.integrationApp");
        capabilities.setDeviceIp(parameters.get("deviceIp"));
        capabilities.setDeviceId(parameters.get("deviceId"));
        capabilities.setAppPath(getClass().getClassLoader().getResource("IntegrationApp_tvOS.ipa").getPath());
        DriverManager.setDriver(DriverFactory.createDriver(capabilities));
    }

    public void onFinish(ISuite iSuite) {
        Optional.ofNullable(DriverManager.getDriver()).ifPresent(TVDriver::quit);
    }
}
