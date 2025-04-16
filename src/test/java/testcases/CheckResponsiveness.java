package testcases;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;

import base.BaseFile;

public class CheckResponsiveness extends BaseFile {
    
	public void takeScreenshot(String filename) {
	    // Ensure the driver is of type TakesScreenshot before casting it
	    if (driver instanceof TakesScreenshot) {
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(src, new File("screenshots/" + filename + ".png"));
	            System.out.println("Screenshot saved: " + filename + ".png");
	        } catch (IOException e) {
	            System.out.println("Error while saving screenshot: " + e.getMessage());
	        }
	    } else {
	        System.out.println("The current driver does not support screenshots.");
	    }
	}

    // Method to test responsiveness at different screen resolutions
    public void testResponsiveness(int width, int height, String screenType) {
        // Set the browser window to the given resolution
        driver.manage().window().setSize(new Dimension(width, height));
        System.out.println("Testing " + screenType + " resolution: " + width + "x" + height);

        // Take a screenshot of the current screen size
        takeScreenshot(screenType + "_" + width + "x" + height);
    }

    // Sample test case to test various screen resolutions
    @Test
    public void runResponsivenessTests() {
        // Test for different resolutions
        testResponsiveness(1920, 1080, "Desktop");
        testResponsiveness(1366, 768, "Laptop");
        testResponsiveness(375, 667, "Mobile"); // Example for mobile resolution (e.g., iPhone 6)
        testResponsiveness(768, 1024, "Tablet"); // Example for tablet resolution
    }
}
