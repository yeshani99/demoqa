package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.RadioButtonPage;

public class RadioButtonTest {

    private BasePage basePage;
    private RadioButtonPage radioPage;

    @BeforeClass
    public void setup() {
        basePage = new BasePage();
        basePage.initializeDriver();
        basePage.openUrl("https://demoqa.com/radio-button");
        radioPage = new RadioButtonPage(basePage.getDriver());
    }

    @Test
    public void testYesRadioButton() {
        radioPage.clickYes();
        String result = radioPage.getResultText();
        Assert.assertEquals(result, "Yes", "Yes radio button selection failed!");
    }

    @Test
    public void testImpressiveRadioButton() {
        radioPage.clickImpressive();
        String result = radioPage.getResultText();
        Assert.assertEquals(result, "Impressive", "Impressive radio button selection failed!");
    }

    @Test
    public void testNoRadioButtonDisabled() {
        // No radio button is disabled; verify it is not clickable
        try {
            radioPage.clickNo();
            Assert.fail("No radio button should not be clickable!");
        } catch (Exception e) {
            Assert.assertTrue(true, "No radio button is correctly disabled");
        }
    }

    @AfterClass
    public void tearDown() {
        basePage.closeDriver();
    }
}