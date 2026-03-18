package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.TextBoxPage;

public class TextBoxTest extends BaseTest {

    // Test Case 1 – Valid Form Submission
    @Test
    public void validFormSubmission() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("Yeshani");
        Thread.sleep(1000);

        page.enterEmail("yeshani@gmail.com");
        Thread.sleep(1000);

        page.enterCurrentAddress("Colombo");
        Thread.sleep(1000);

        page.enterPermanentAddress("Kandy");
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000); // see output

        Assert.assertTrue(page.getNameOutput().contains("Yeshani"));
    }

    // Test Case 2 – Empty Form Submission
    @Test
    public void emptyFormSubmission() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.clickSubmit();
        Thread.sleep(2000); // wait to see result

        String pageSource = driver.getPageSource();
        Assert.assertFalse(pageSource.contains("Name:"));
    }

    // Test Case 3 – Invalid Email
    @Test
    public void invalidEmailTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("Yeshani");
        Thread.sleep(500);

        page.enterEmail("abc123"); // invalid email
        Thread.sleep(500);

        page.clickSubmit();
        Thread.sleep(2000); // see result

        String emailFieldClass = driver.findElement(
                org.openqa.selenium.By.id("userEmail")).getAttribute("class");

        Assert.assertTrue(emailFieldClass.contains("field-error"));
    }

    // Test Case 4 – Only Name Entered
    @Test
    public void onlyNameEntered() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("Yeshani");
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains("Yeshani"));
    }

    // Test Case 5 – Long Address Input
    @Test
    public void longAddressInput() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("Yeshani");
        Thread.sleep(500);

        page.enterEmail("yeshani@gmail.com");
        Thread.sleep(500);

        page.enterCurrentAddress(
                "This is a very long current address used for automation testing on DemoQA.");
        Thread.sleep(500);

        page.enterPermanentAddress(
                "This is a very long permanent address to verify text box functionality.");
        Thread.sleep(500);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains("Yeshani"));
    }
    @Test
    public void minInputTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("A");
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains("A"));
    }
    @Test
    public void maxInputTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        String longName = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
        page.enterFullName(longName);
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains(longName));
    }
    @Test
    public void specialCharacterTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("@#$%^&*");
        page.enterCurrentAddress("!@# Colombo");
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains("@#$%^&*"));
    }
    @Test
    public void numericNameTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("123456");
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains("123456"));
    }
    @Test
    public void invalidEmailFormats() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("Yeshani");
        page.enterEmail("test@.com"); // invalid
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        String emailClass = driver.findElement(By.id("userEmail"))
                .getAttribute("class");

        Assert.assertTrue(emailClass.contains("field-error"));
    }
    @Test
    public void spacesOnlyTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("     ");
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        String output = page.getNameOutput();

        Assert.assertTrue(output.contains("     "));
    }
    @Test
    public void uiValidationTest() {

        Assert.assertTrue(driver.findElement(By.id("userName")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("submit")).isEnabled());
    }
    @Test
    public void refreshPageTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        page.enterFullName("Yeshani");
        Thread.sleep(1000);

        driver.navigate().refresh();
        Thread.sleep(2000);

        String value = driver.findElement(By.id("userName")).getAttribute("value");

        Assert.assertTrue(value.isEmpty());
    }
    @Test
    public void copyPasteTest() throws InterruptedException {
        TextBoxPage page = new TextBoxPage(driver);

        String text = "Copied Text Example";

        page.enterFullName(text);
        Thread.sleep(1000);

        page.clickSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(page.getNameOutput().contains(text));
    }



}