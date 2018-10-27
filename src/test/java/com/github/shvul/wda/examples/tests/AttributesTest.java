package com.github.shvul.wda.examples.tests;

import com.github.shvul.wda.client.element.TVElement;
import com.github.shvul.wda.client.element.TVLocator;
import com.github.shvul.wda.examples.support.Group;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.Rectangle;

public class AttributesTest extends BaseTest {

    @Test(groups = { Group.FULL, Group.ATTRIBUTES })
    public void elementAttributesTest() {
        openAttributesPage();

        TVElement progress = driver.findElement(TVLocator.predicate("type=\"XCUIElementTypeProgressIndicator\""));

        Assert.assertTrue(progress.isDisplayed());
        Assert.assertTrue(progress.isEnabled());
        Assert.assertFalse(progress.isFocused());
        Assert.assertEquals(progress.getAttribute("value"), "69%");

        Rectangle elementRect = progress.getRect();
        Assert.assertTrue(elementRect.x > 0);
        Assert.assertTrue(elementRect.y > 0);
        Assert.assertTrue(elementRect.width > 0);
        Assert.assertTrue(elementRect.height > 0);
    }
}
