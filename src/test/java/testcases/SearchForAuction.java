package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.TakeFullScreenshot;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import base.BaseFile;

public class SearchForAuction extends BaseFile {

    @Test
    public void testSearchForAuction() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2.listing-title.w-100 a")));

        List<WebElement> listingTitlesElements = driver.findElements(By.cssSelector("h2.listing-title.w-100 a"));
        List<String> listingTitles = new ArrayList<>();

        for (WebElement el : listingTitlesElements) {
            listingTitles.add(el.getText());
        }

        for (String searchItem : listingTitles) {

            WebElement searchBox = driver.findElement(By.cssSelector("input[placeholder='بحث']"));
            searchBox.clear();
            searchBox.sendKeys(searchItem);
            searchBox.sendKeys(Keys.RETURN);

            // Wait for results to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2.listing-title.w-100 a")));
            Thread.sleep(2000); // Small pause for Search

            List<WebElement> results = driver.findElements(By.cssSelector("h2.listing-title.w-100 a"));
            boolean found = false;

            for (WebElement result : results) {
                String resultText = result.getText().trim();
                System.out.println("Comparing: '" + resultText + "' with searched: '" + searchItem );
                if (resultText.equalsIgnoreCase(searchItem)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Exact match found for: " + searchItem);
                TakeFullScreenshot.captureFullPageScreenshot(); // Screenshot only on success
            } else {
                System.out.println("No exact match for: " + searchItem);
            }

            break; 
        }
    }
}
