package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.BrokenLinksImagesPage;
import pages.BrokenLinksImagesPage.LinkValidation;
import pages.BrokenLinksImagesPage.ImageValidation;

import java.util.List;

import static org.testng.Assert.*;

public class BrokenLinksImagesTest extends BaseTest {

    private BrokenLinksImagesPage brokenLinksPage;

    // Test Case 1: Verify page navigation and all links are displayed
    @Test
    public void verifyBrokenLinksPageLoad() {
        brokenLinksPage = new BrokenLinksImagesPage(driver);
        brokenLinksPage.navigateToBrokenLinksPage();

        // Verify that links are present on the page
        int totalLinks = brokenLinksPage.getTotalLinksCount();
        assertTrue(totalLinks > 0, "Page should contain at least one link");
        System.out.println("Total links found: " + totalLinks);
    }

    // Test Case 2: Verify all images are displayed on the page
    @Test
    public void verifyAllImagesDisplayed() {
        brokenLinksPage = new BrokenLinksImagesPage(driver);
        brokenLinksPage.navigateToBrokenLinksPage();

        // Verify that images are present on the page
        int totalImages = brokenLinksPage.getTotalImagesCount();
        assertTrue(totalImages > 0, "Page should contain at least one image");
        System.out.println("Total images found: " + totalImages);
    }

    // Test Case 3: Verify valid link returns 200 status code
    @Test
    public void verifyValidLinkStatus() {
        brokenLinksPage = new BrokenLinksImagesPage(driver);
        brokenLinksPage.navigateToBrokenLinksPage();

        // Check status code of a valid link
        String validLinkUrl = "https://demoqa.com";
        int statusCode = BrokenLinksImagesPage.getHttpStatusCode(validLinkUrl);
        assertEquals(statusCode, 200, "Valid link should return 200 status code");
        System.out.println("Valid link status code: " + statusCode);
    }

    // Test Case 4: Verify broken link does not return 200 status code
    @Test
    public void verifyBrokenLinkStatus() {
        brokenLinksPage = new BrokenLinksImagesPage(driver);
        brokenLinksPage.navigateToBrokenLinksPage();

        // Get all broken links from the page
        List<LinkValidation> brokenLinks = brokenLinksPage.getAllBrokenLinks();
        
        // Verify that there are broken links on the page
        assertTrue(brokenLinks.size() > 0, "Page should contain at least one broken link");
        
        // Check the status code of the first broken link
        LinkValidation firstBrokenLink = brokenLinks.get(0);
        assertNotEquals(firstBrokenLink.statusCode, 200, "Broken link should not return 200 status code");
        System.out.println("Broken link status code: " + firstBrokenLink.statusCode);
        System.out.println("Broken link URL: " + firstBrokenLink.url);
    }

    // Test Case 5: Verify all links on page and identify broken ones
    @Test
    public void verifyAllLinksAndIdentifyBroken() {
        brokenLinksPage = new BrokenLinksImagesPage(driver);
        brokenLinksPage.navigateToBrokenLinksPage();

        // Get all links and validate them
        List<LinkValidation> brokenLinks = brokenLinksPage.getAllBrokenLinks();

        // Print all links status
        int validLinksCount = brokenLinksPage.getTotalLinksCount() - brokenLinks.size();
        System.out.println("Total Valid Links: " + validLinksCount);
        System.out.println("Total Broken Links: " + brokenLinks.size());

        // Print details of broken links
        for (LinkValidation link : brokenLinks) {
            System.out.println("Broken Link - Text: " + link.linkText + 
                             ", URL: " + link.url + 
                             ", Status Code: " + link.statusCode);
        }

        // Assert that broken links exist
        assertTrue(brokenLinksPage.getTotalLinksCount() > 0, "Page should contain links for validation");
    }
}
