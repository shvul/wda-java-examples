package com.github.shvul.wda.examples.tests;

import com.github.shvul.wda.client.element.TVElement;
import com.github.shvul.wda.client.element.TVLocator;
import com.github.shvul.wda.examples.support.Group;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeyboardTest extends BaseTest {

    @Test(groups = { Group.FULL, Group.KEYBOARD })
    public void keyboardTypingTest() {
        openAttributesPage();
        TVElement textField = driver.findElement(TVLocator.predicate("type=\"XCUIElementTypeTextField\""));
        Assert.assertEquals(textField.getText(), "Value");

        textField.select();

        String textToType = "Test";
        driver.sendKeys(textToType);
        Assert.assertEquals(textField.getText(), textToType);
    }
}
