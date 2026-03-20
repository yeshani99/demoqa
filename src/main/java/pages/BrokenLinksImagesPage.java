package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksImagesPage {

    private WebDriver driver;
    private By allLinks = By.tagName("a");
    private By allImages = By.tagName("img");
    private By validLink = By.partialLinkText("Valid");
    private By brokenLink = By.partialLinkText("Broken");

    public BrokenLinksImagesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to the Broken Links page
    public void navigateToBrokenLinksPage() {
        driver.get("https://demoqa.com/broken");
    }

    // Get all links on the page
    public List<WebElement> getAllLinks() {
        return driver.findElements(allLinks);
    }

    // Get all images on the page
    public List<WebElement> getAllImages() {
        return driver.findElements(allImages);
    }

    // Click on valid link
    public void clickValidLink() {
        driver.findElement(validLink).click();
    }

    // Click on broken link
    public void clickBrokenLink() {
        driver.findElement(brokenLink).click();
    }

    // Get total number of links
    public int getTotalLinksCount() {
        return getAllLinks().size();
    }

    // Get total number of images
    public int getTotalImagesCount() {
        return getAllImages().size();
    }

    // Get link text
    public String getLinkText(WebElement link) {
        return link.getText();
    }

    // Get link URL
    public String getLinkUrl(WebElement link) {
        return link.getAttribute("href");
    }

    // Get image source
    public String getImageSource(WebElement image) {
        return image.getAttribute("src");
    }

    // Get image alt text
    public String getImageAltText(WebElement image) {
        return image.getAttribute("alt");
    }

    // Validate HTTP status code of a link
    public static int getHttpStatusCode(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            return connection.getResponseCode();
        } catch (Exception e) {
            return -1;
        }
    }

    // Check if link is valid (200 status code)
    public static boolean isLinkValid(String urlString) {
        return getHttpStatusCode(urlString) == 200;
    }

    // Get all broken links
    public List<LinkValidation> getAllBrokenLinks() {
        List<LinkValidation> brokenLinks = new ArrayList<>();
        List<WebElement> links = getAllLinks();

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                int statusCode = getHttpStatusCode(url);
                if (statusCode != 200) {
                    brokenLinks.add(new LinkValidation(link.getText(), url, statusCode));
                }
            }
        }
        return brokenLinks;
    }

    // Get all broken images
    public List<ImageValidation> getAllBrokenImages() {
        List<ImageValidation> brokenImages = new ArrayList<>();
        List<WebElement> images = getAllImages();

        for (WebElement image : images) {
            String src = image.getAttribute("src");
            if (src != null && !src.isEmpty()) {
                int statusCode = getHttpStatusCode(src);
                if (statusCode != 200) {
                    brokenImages.add(new ImageValidation(src, image.getAttribute("alt"), statusCode));
                }
            }
        }
        return brokenImages;
    }

    // Inner class for Link Validation
    public static class LinkValidation {
        public String linkText;
        public String url;
        public int statusCode;

        public LinkValidation(String linkText, String url, int statusCode) {
            this.linkText = linkText;
            this.url = url;
            this.statusCode = statusCode;
        }
    }

    // Inner class for Image Validation
    public static class ImageValidation {
        public String imageSrc;
        public String altText;
        public int statusCode;

        public ImageValidation(String imageSrc, String altText, int statusCode) {
            this.imageSrc = imageSrc;
            this.altText = altText;
            this.statusCode = statusCode;
        }
    }
}
