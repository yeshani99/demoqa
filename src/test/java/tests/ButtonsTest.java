package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ButtonsPage;

public class ButtonsTest extends BaseTest {

    private ButtonsPage buttonsPage;

    @BeforeMethod
    public void navigateToPage() {
        buttonsPage = new ButtonsPage(driver);
        buttonsPage.open();
    }

    @Test
    public void verifyDoubleClickButton() {
        buttonsPage.doubleClickButton();
        String msg = buttonsPage.getDoubleClickMessage();
        Assert.assertEquals(msg, "You have done a double click");
    }

    @Test
    public void verifyRightClickButton() {
        buttonsPage.rightClickButton();
        String msg = buttonsPage.getRightClickMessage();
        Assert.assertEquals(msg, "You have done a right click");
    }

    @Test
    public void verifyDynamicClickButton() {
        buttonsPage.dynamicClickButton();
        String msg = buttonsPage.getDynamicClickMessage();
        Assert.assertEquals(msg, "You have done a dynamic click");
    }
}