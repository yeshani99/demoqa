package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.io.File;

public class UploadDownloadPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private final By downloadButton = By.id("downloadButton");
    private final By uploadInput = By.id("uploadFile");
    private final By uploadedFilePath = By.id("uploadedFilePath");

    public UploadDownloadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Click the download button to download a file
     */
    public void clickDownloadButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        button.click();
    }

    /**
     * Upload a file using the file input element
     * @param filePath - Full path to the file to upload
     */
    public void uploadFile(String filePath) {
        WebElement uploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(uploadInput));
        uploadElement.sendKeys(filePath);
    }

    /**
     * Get the uploaded file path from the page
     * @return - The file path displayed on the page after upload
     */
    public String getUploadedFilePath() {
        WebElement filePathElement = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFilePath));
        return filePathElement.getText();
    }

    /**
     * Verify if the file has been uploaded successfully
     * @return - True if uploaded file path is displayed, false otherwise
     */
    public boolean isFileUploaded() {
        try {
            WebElement filePathElement = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFilePath));
            return !filePathElement.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if the download button is displayed
     * @return - True if download button is visible
     */
    public boolean isDownloadButtonDisplayed() {
        try {
            return driver.findElement(downloadButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if the upload input is displayed
     * @return - True if upload input is visible
     */
    public boolean isUploadInputDisplayed() {
        try {
            return driver.findElement(uploadInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the file name from the uploaded file path
     * @return - File name extracted from the path
     */
    public String getUploadedFileName() {
        String filePath = getUploadedFilePath();
        if (filePath != null && !filePath.isEmpty()) {
            return new File(filePath).getName();
        }
        return null;
    }

    /**
     * Wait for the file path to be populated after upload
     */
    public void waitForFileUploadCompletion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFilePath));
    }

    /**
     * Verify if the uploaded file path contains the expected file name
     * @param fileName - Expected file name
     * @return - True if the file path contains the expected file name
     */
    public boolean verifyUploadedFileName(String fileName) {
        String uploadedPath = getUploadedFilePath();
        return uploadedPath != null && uploadedPath.contains(fileName);
    }
}
