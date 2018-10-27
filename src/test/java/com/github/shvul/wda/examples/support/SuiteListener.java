package com.github.shvul.wda.examples.support;

import com.github.shvul.wda.client.driver.DriverCapabilities;
import com.github.shvul.wda.client.driver.DriverFactory;
import com.github.shvul.wda.client.driver.TVDriver;
import com.github.shvul.wda.client.support.LoggerManager;
import org.apache.http.util.Args;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.Map;
import java.util.Optional;

public class SuiteListener implements ISuiteListener {

    public void onStart(ISuite iSuite) {
        // enable console log from wda client
        LoggerManager.enableServerConsoleLogging();
        LoggerManager.enableClientConsoleLogging();

        // extract parameters
        Map<String, String> parameters = iSuite.getXmlSuite().getParameters();
        String deviceId = parameters.get("deviceId");
        String deviceIp = parameters.get("deviceIp");

        Args.check(!deviceId.isEmpty(),"Specify deviceId in TestNGSuiteConfig.xml");
        Args.check(!deviceIp.isEmpty(),"Specify deviceIp in TestNGSuiteConfig.xml");

        // setup driver
        DriverCapabilities capabilities = new DriverCapabilities();
        capabilities.setWdaPath("WebDriverAgent/WebDriverAgent.xcodeproj");
        capabilities.setPlatform("tvOS");
        capabilities.setDeviceName("Apple TV");
        capabilities.setBundleId("com.facebook.wda.integrationApp");
        capabilities.setDeviceIp(deviceId);
        capabilities.setDeviceId(deviceIp);
        capabilities.setAppPath(getClass().getClassLoader().getResource("IntegrationApp_tvOS.ipa").getPath());
        DriverManager.setDriver(DriverFactory.createDriver(capabilities));
    }

    public void onFinish(ISuite iSuite) {
        Optional.ofNullable(DriverManager.getDriver()).ifPresent(TVDriver::quit);
    }
}
