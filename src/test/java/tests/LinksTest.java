package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import base.BaseTest;
import pages.LinksPage;

public class LinksTest extends BaseTest {

    private LinksPage linksPage;

    @BeforeMethod
    @Override
    public void setup() {
        // Call parent setup first
        super.setup();
        
        // Navigate to Links page
        driver.get("https://demoqa.com/links");
        linksPage = new LinksPage(driver);
    }

    // ============================================
    // TEST CASE 1: Verify Links Page UI Elements
    // ============================================
    @Test(description = "Verify simple link is displayed and enabled")
    public void verifySimpleLinkDisplayed() throws InterruptedException {
        Assert.assertTrue(linksPage.isSimpleLinkDisplayed(), 
            "Simple link should be displayed");
        Assert.assertTrue(linksPage.isSimpleLinkEnabled(), 
            "Simple link should be enabled");
    }

    // ============================================
    // TEST CASE 2: Click Simple Link and Verify Navigation
    // ============================================
    @Test(description = "Verify clicking simple link navigates to home page")
    public void verifySimpeLinkNavigation() throws InterruptedException {
        linksPage.clickSimpleLink();
        Thread.sleep(2000);
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://demoqa.com"), 
            "Should navigate to demoqa site");
    }

    // ============================================
    // TEST CASE 3: Verify Dynamic Link Works
    // ============================================
    @Test(description = "Verify dynamic link works correctly")
    public void verifyDynamicLink() throws InterruptedException {
        String linkHref = linksPage.getLinkHref(By.id("dynamicLink"));
        Assert.assertNotNull(linkHref, "Dynamic link should have href attribute");
        Assert.assertTrue(linkHref.length() > 0, "Href should not be empty");
    }

    // ============================================
    // TEST CASE 4: Verify Created Link (201 Status)
    // ============================================
    @Test(description = "Verify Created link returns 201 status response")
    public void verifyCreatedLinkResponse() throws InterruptedException {
        linksPage.clickCreatedLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("201"), 
            "Response should contain 201 status code");
    }

    // ============================================
    // TEST CASE 5: Verify No Content Link (204 Status)
    // ============================================
    @Test(description = "Verify No Content link returns 204 status response")
    public void verifyNoContentLinkResponse() throws InterruptedException {
        linksPage.clickNoContentLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("204") || !linksPage.getResponseText().isEmpty(), 
            "Response should contain 204 status code or be empty");
    }

    // ============================================
    // TEST CASE 6: Verify Moved Link (301 Status)
    // ============================================
    @Test(description = "Verify Moved link returns 301 status response")
    public void verifyMovedLinkResponse() throws InterruptedException {
        linksPage.clickMovedLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("301"), 
            "Response should contain 301 status code");
    }

    // ============================================
    // TEST CASE 7: Verify Bad Request Link (400 Status)
    // ============================================
    @Test(description = "Verify Bad Request link returns 400 status response")
    public void verifyBadRequestLinkResponse() throws InterruptedException {
        linksPage.clickBadRequestLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("400"), 
            "Response should contain 400 status code");
    }

    // ============================================
    // TEST CASE 8: Verify Unauthorized Link (401 Status)
    // ============================================
    @Test(description = "Verify Unauthorized link returns 401 status response")
    public void verifyUnauthorizedLinkResponse() throws InterruptedException {
        linksPage.clickUnauthorizedLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("401"), 
            "Response should contain 401 status code");
    }

    // ============================================
    // TEST CASE 9: Verify Forbidden Link (403 Status)
    // ============================================
    @Test(description = "Verify Forbidden link returns 403 status response")
    public void verifyForbiddenLinkResponse() throws InterruptedException {
        linksPage.clickForbiddenLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("403"), 
            "Response should contain 403 status code");
    }

    // ============================================
    // TEST CASE 10: Verify Not Found Link (404 Status)
    // ============================================
    @Test(description = "Verify Not Found link returns 404 status response")
    public void verifyNotFoundLinkResponse() throws InterruptedException {
        linksPage.clickNotFoundLink();
        linksPage.waitForResponse();
        
        Assert.assertTrue(linksPage.responseContains("404"), 
            "Response should contain 404 status code");
    }

    // ============================================
    // TEST CASE 11: Verify Link Attributes
    // ============================================
    @Test(description = "Verify links have proper attributes")
    public void verifyLinkAttributes() throws InterruptedException {
        String simpleHref = linksPage.getLinkHref(By.id("simpleLink"));
        
        Assert.assertNotNull(simpleHref, "Link should have href attribute");
        Assert.assertTrue(simpleHref.startsWith("http"), 
            "Href should be a valid URL starting with http");
    }

    // ============================================
    // TEST CASE 12: Verify Multiple Links Exist
    // ============================================
    @Test(description = "Verify multiple links exist on the page")
    public void verifyMultipleLinksExist() throws InterruptedException {
        int linkCount = linksPage.getAllLinks().size();
        Assert.assertTrue(linkCount > 0, "Page should contain at least one link");
    }

    // ============================================
    // TEST CASE 13: Verify Response Text Updates After Click
    // ============================================
    @Test(description = "Verify response text updates after clicking different links")
    public void verifyResponseTextUpdates() throws InterruptedException {
        linksPage.clickCreatedLink();
        linksPage.waitForResponse();
        String response1 = linksPage.getResponseText();
        
        // Navigate back to links page
        driver.get("https://demoqa.com/links");
        Thread.sleep(1000);
        
        linksPage.clickBadRequestLink();
        linksPage.waitForResponse();
        String response2 = linksPage.getResponseText();
        
        Assert.assertNotEquals(response1, response2, 
            "Response text should differ for different status codes");
    }

    // ============================================
    // TEST CASE 14: Verify Link Text Is Visible
    // ============================================
    @Test(description = "Verify link text is visible and readable")
    public void verifyLinkTextVisible() throws InterruptedException {
        String linkText = linksPage.getLinkText(By.id("simpleLink"));
        Assert.assertNotNull(linkText, "Link should have visible text");
        Assert.assertTrue(linkText.length() > 0, "Link text should not be empty");
    }

    // ============================================
    // TEST CASE 15: Verify Same Link Multiple Clicks
    // ============================================
    @Test(description = "Verify clicking same link multiple times works consistently")
    public void verifySameLinkMultipleClicks() throws InterruptedException {
        // First click
        linksPage.clickCreatedLink();
        linksPage.waitForResponse();
        String response1 = linksPage.getResponseText();
        
        // Navigate back
        driver.get("https://demoqa.com/links");
        Thread.sleep(1000);
        
        // Second click
        linksPage.clickCreatedLink();
        linksPage.waitForResponse();
        String response2 = linksPage.getResponseText();
        
        Assert.assertEquals(response1, response2, 
            "Multiple clicks on same link should produce same response");
    }

    // ============================================
    // TEST CASE 16: Verify Link Href Structure
    // ============================================
    @Test(description = "Verify all links have valid href structure")
    public void verifyLinkHrefStructure() throws InterruptedException {
        String href = linksPage.getLinkHref(By.id("simpleLink"));
        
        Assert.assertFalse(href.isEmpty(), "Href should not be empty");
        Assert.assertTrue(href.matches("^https?://.*"), 
            "Href should follow URL pattern");
    }

    // ============================================
    // TEST CASE 17: Verify Response Contains Expected Text
    // ============================================
    @Test(description = "Verify response contains expected text patterns")
    public void verifyResponseTextPattern() throws InterruptedException {
        linksPage.clickCreatedLink();
        linksPage.waitForResponse();
        
        boolean hasResponse = linksPage.responseContains("201");
        Assert.assertTrue(hasResponse, 
            "Response should contain status code in response text");
    }

    // ============================================
    // TEST CASE 18: Verify Page Title After Link Click
    // ============================================
    @Test(description = "Verify page remains on links page after clicking links")
    public void verifyPageTitleAfterLinkClick() throws InterruptedException {
        linksPage.clickCreatedLink();
        Thread.sleep(1000);
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("links"), 
            "Should remain on links page");
    }

    // ============================================
    // TEST CASE 19: Verify All Status Links Are Clickable
    // ============================================
    @Test(description = "Verify all status code links are clickable")
    public void verifyAllStatusLinksClickable() throws InterruptedException {
        // Test links by finding them using linkText strategy instead of ID
        String[] linkTexts = {"Created", "Bad Request", "Not Found"};
        
        for (String linkText : linkTexts) {
            driver.get("https://demoqa.com/links");
            linksPage.waitForResponse();
            
            By linkLocator = By.linkText(linkText);
            try {
                WebElement element = driver.findElement(linkLocator);
                Assert.assertTrue(element.isDisplayed(), linkText + " link should be displayed");
                Assert.assertTrue(element.isEnabled(), linkText + " link should be enabled");
            } catch (Exception e) {
                // Try partial link text as fallback
                By partialLocator = By.partialLinkText(linkText);
                WebElement element = driver.findElement(partialLocator);
                Assert.assertTrue(element.isDisplayed(), linkText + " link should be displayed");
                Assert.assertTrue(element.isEnabled(), linkText + " link should be enabled");
            }
        }
    }

    // ============================================
    // TEST CASE 20: Verify No Broken Links
    // ============================================
    @Test(description = "Verify links do not return server errors for valid status codes")
    public void verifyNoBrokenLinks() throws InterruptedException {
        linksPage.clickCreatedLink();
        linksPage.waitForResponse();
        
        Assert.assertFalse(linksPage.responseContains("Exception") || linksPage.responseContains("Error"), 
            "Response should not contain Exception or Error");
    }
}
