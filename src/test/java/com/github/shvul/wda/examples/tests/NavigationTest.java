package com.github.shvul.wda.examples.tests;

import com.github.shvul.wda.client.driver.TVDriver.RemoteControl;
import com.github.shvul.wda.client.element.TVElement;
import com.github.shvul.wda.client.element.TVLocator;
import com.github.shvul.wda.examples.support.Group;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NavigationTest extends BaseTest {

    @Test(groups = { Group.FULL, Group.NAVIGATION })
    public void elementFocuseTest() {

        TVElement navigationBtn = driver.findElement(TVLocator.name("Navigation"));

        Assert.assertFalse(navigationBtn.isFocused(),"Navigation button is focused, but shouldn't be.");

        navigationBtn.focuse();
        Assert.assertTrue(navigationBtn.isFocused(),"Navigation button is not focused.");
    }

    @Test(groups = { Group.FULL, Group.NAVIGATION })
    public void remoteControlTest() {

        TVElement navigationBtn = driver.findElement(TVLocator.name("Navigation"));

        Assert.assertFalse(navigationBtn.isFocused(),"Navigation button is focused, but shouldn't be.");

        RemoteControl remoteControl = driver.getRemoteControl();
        remoteControl.down();
        remoteControl.down();
        Assert.assertTrue(navigationBtn.isFocused(),"Navigation button is not focused.");
    }

    @Test(groups = { Group.FULL, Group.NAVIGATION })
    public void elementSelectTest() {
        TVElement navigationBtn = driver.findElement(TVLocator.name("Navigation"));

        navigationBtn.select();
        List<TVElement> elements = driver.findElements(TVLocator.predicate("name=\"Templates\""));
        Assert.assertTrue(elements.size() > 0,"Navigation page is not opened.");
    }
}
