package testcases;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseFile;

public class SiteNavigation extends BaseFile{

	
	
	  public void verifyClickNavigatesTo(By bySelector, String expectedUrl) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bySelector));
	        element.click();

	        wait.until(ExpectedConditions.urlContains(expectedUrl));

	        String actualUrl = driver.getCurrentUrl();
	        if (!actualUrl.contains(expectedUrl)) {
	            throw new AssertionError("Expected URL to contain: " + expectedUrl + ", but was: " + actualUrl);
	        }

	        System.out.println("Navigation verified. Reached: " + actualUrl);
	    }
	  
	  
	     //Clicks an element and verifies it navigates to the expected URL.
	  
	  @Test
	  public void testButtonNavigation_Motors() {

	        // Click and verify navigation
	        verifyClickNavigatesTo(By.cssSelector("[src='/assets/img/car-auction.png']"), "https://re.mobasher.sa/?cat=motors");
	    }
	  
	  @Test
	  public void testButtonNavigation_Estate() {

	        verifyClickNavigatesTo(By.cssSelector("[src='/assets/img/real-estate-auction.png']"), "https://re.mobasher.sa/");
	    }
	  
	  
	  @Test
	  public void testButtonNavigation_Map() {

	        verifyClickNavigatesTo(By.cssSelector("[href='/map']"), "https://re.mobasher.sa/map");
	    }
	  
	  @Test
	  public void testButtonNavigation_Login() {

	        verifyClickNavigatesTo(By.cssSelector("[href='/user/login']"), "https://re.mobasher.sa/user/login");
	    }
	  
	 
	  
	
	}

