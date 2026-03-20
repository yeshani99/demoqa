package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import base.BaseTest;
import pages.UploadDownloadPage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadDownloadTest extends BaseTest {

    private UploadDownloadPage uploadDownloadPage;

    @BeforeMethod
    @Override
    public void setup() {
        // Call parent setup first
        super.setup();
        
        // Navigate to Upload/Download page
        driver.get("https://demoqa.com/upload-download");
        uploadDownloadPage = new UploadDownloadPage(driver);
    }

    // ============================================
    // TEST CASE 1: Verify Download Button is Displayed
    // ============================================
    @Test(description = "Verify download button is displayed on the page")
    public void testDownloadButtonDisplayed() {
        Assert.assertTrue(uploadDownloadPage.isDownloadButtonDisplayed(), 
            "Download button should be displayed on the page");
    }

    // ============================================
    // TEST CASE 2: Verify Upload Input is Displayed
    // ============================================
    @Test(description = "Verify file upload input is displayed on the page")
    public void testUploadInputDisplayed() {
        Assert.assertTrue(uploadDownloadPage.isUploadInputDisplayed(), 
            "Upload input should be displayed on the page");
    }

    // ============================================
    // TEST CASE 3: Click Download Button
    // ============================================
    @Test(description = "Verify clicking download button works")
    public void testDownloadButtonClick() throws InterruptedException {
        uploadDownloadPage.clickDownloadButton();
        Thread.sleep(2000);
        
        Assert.assertTrue(uploadDownloadPage.isDownloadButtonDisplayed(), 
            "Page should remain on upload/download page after download");
    }

    // ============================================
    // TEST CASE 4: Upload a File Successfully
    // ============================================
    @Test(description = "Verify uploading a file successfully")
    public void testFileUpload() throws Exception {
        String testFileName = "test_upload.txt";
        String testFilePath = System.getProperty("java.io.tmpdir") + File.separator + testFileName;
        
        // Create the test file
        Files.write(Paths.get(testFilePath), "This is a test file for upload".getBytes());
        
        // Upload the file
        uploadDownloadPage.uploadFile(testFilePath);
        Thread.sleep(2000);
        
        // Verify file was uploaded
        Assert.assertTrue(uploadDownloadPage.isFileUploaded(), 
            "File should be uploaded successfully");
    }

    // ============================================
    // TEST CASE 5: Verify Uploaded File Path is Displayed
    // ============================================
    @Test(description = "Verify uploaded file path is displayed on the page")
    public void testUploadedFilePathDisplayed() throws Exception {
        String testFileName = "test_upload.txt";
        String testFilePath = System.getProperty("java.io.tmpdir") + File.separator + testFileName;
        
        // Create the test file
        Files.write(Paths.get(testFilePath), "This is a test file for upload".getBytes());
        
        // Upload the file
        uploadDownloadPage.uploadFile(testFilePath);
        uploadDownloadPage.waitForFileUploadCompletion();
        
        // Verify file path is displayed
        String uploadedPath = uploadDownloadPage.getUploadedFilePath();
        Assert.assertNotNull(uploadedPath, "Uploaded file path should be displayed");
        Assert.assertFalse(uploadedPath.isEmpty(), "Uploaded file path should not be empty");
    }

    // ============================================
    // TEST CASE 6: Verify Uploaded File Name Matches
    // ============================================
    @Test(description = "Verify uploaded file name matches the uploaded file")
    public void testUploadedFileNameMatches() throws Exception {
        String testFileName = "sample_file.txt";
        String testFilePath = System.getProperty("java.io.tmpdir") + File.separator + testFileName;
        
        // Create the test file
        Files.write(Paths.get(testFilePath), "Sample content for testing".getBytes());
        
        // Upload the file
        uploadDownloadPage.uploadFile(testFilePath);
        uploadDownloadPage.waitForFileUploadCompletion();
        
        // Verify file name matches
        boolean fileNameMatches = uploadDownloadPage.verifyUploadedFileName(testFileName);
        Assert.assertTrue(fileNameMatches, 
            "Uploaded file name should match the test file name");
    }
}
