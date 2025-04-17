package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class AuctionVerificationTest extends CheckResponsiveness {

    @Test
    public void testAuctionVerification() throws InterruptedException {
        try {
            WebElement auctionLink = driver.findElement(By.cssSelector("div.flex-grow-1 h2.listing-title a"));
            String auctionName = auctionLink.getText();
            System.out.println("Auction Name: " + auctionName);

            auctionLink.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.section-head span")));

            takeScreenshot("auction_details");

            String auctionTitleDetailsPage = driver.findElement(By.cssSelector("h2.section-head span")).getText();
            if (!auctionName.equals(auctionTitleDetailsPage)) {
                System.out.println("Title mismatch: Expected " + auctionName + ", got " + auctionTitleDetailsPage);
            } else {
                System.out.println("Title verified: " + auctionTitleDetailsPage);
            }

            String auctionDate = driver.findElement(By.cssSelector("div.listing-time-date.text-center")).getText();
            String auctionTime = driver.findElement(By.cssSelector("div.listing-time.text-center")).getText();
            String auctionDatetime = auctionDate + " " + auctionTime;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
            String currentTime = sdf.format(new Date());
            System.out.println("Auction Date and Time: " + auctionDatetime);
            System.out.println("Current System Time: " + currentTime);

            if (!auctionDatetime.equals(currentTime)) {
                System.out.println("Time mismatch: Expected " + currentTime + ", got " + auctionDatetime);
            }

            int daysRemaining = Integer.parseInt(driver.findElement(By.cssSelector("span.timer-column p.lead.text-center")).getText());
            int hoursRemaining = Integer.parseInt(driver.findElements(By.cssSelector("span.timer-column p.lead.text-center")).get(1).getText());
            int minutesRemaining = Integer.parseInt(driver.findElements(By.cssSelector("span.timer-column p.lead.text-center")).get(2).getText());
            int secondsRemaining = Integer.parseInt(driver.findElements(By.cssSelector("span.timer-column p.lead.text-center")).get(3).getText());

            System.out.println("Countdown Timer - Days: " + daysRemaining + ", Hours: " + hoursRemaining + ", Minutes: " + minutesRemaining + ", Seconds: " + secondsRemaining);

            WebElement priceButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.text-zero button")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", priceButton);

            WebElement clickableButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(priceButton));

            js.executeScript("arguments[0].click();", clickableButton);

            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://re.mobasher.sa/user/login"));

            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals("https://re.mobasher.sa/user/login")) {
                System.out.println("Redirection failed: Expected https://re.mobasher.sa/user/login, got " + currentUrl);
            } else {
            	System.out.println("Redirection Passed: Expected https://re.mobasher.sa/user/login, got " + currentUrl);
                takeScreenshot("auction_details_redirection_to_login");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
