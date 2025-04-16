package utilities;

import base.BaseFile;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.page.Page;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.time.Duration;

public class TakeFullScreenshot extends BaseFile {

    // Capture full page screenshot
    public static void captureFullPageScreenshot() {
        try {
            driver.manage().window().maximize();  // Ensure the window is maximized

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));  // Ensure the body is visible

            DevTools devTools = ((ChromiumDriver) driver).getDevTools();
            devTools.createSession();

            // Capture full-page screenshot
            String screenshotBase64 = devTools.send(
                Page.captureScreenshot(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(true),  // Capture full page
                    Optional.of(true),  // Capture background
                    Optional.of(false)  // Do not capture the viewport
                )
            );

            byte[] screenshotBytes = Base64.decodeBase64(screenshotBase64);

            // Specify the path where you want to save the screenshot
            String path = "\\screenshots\\full_page_screenshot.png";
            
            File outputFile = new File(System.getProperty("user.dir") + path);

            Files.write(outputFile.toPath(), screenshotBytes);

            System.out.println("Full page screenshot saved at: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
