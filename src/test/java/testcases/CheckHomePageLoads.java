package testcases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.codeborne.selenide.ex.TimeoutException;

import base.BaseFile;

public class CheckHomePageLoads extends BaseFile {
	
		 @Test
			 public void verifyBanner() {
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				    try {
				        WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".carousel-root")));
				        
				        System.out.println("Banner is visible.");
				        Assert.assertTrue(banner.isDisplayed(), "Banner (carousel) is not visible.");
				        
				    } catch (TimeoutException e) {
				        System.out.println("Banner did not appear within the timeout.");
				        Assert.fail("Banner did not become visible within timeout.");
				    }
				}
		
	
		 @Test
		    public void verifyNavBar() {
		    	try {
		    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	    WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".navbar")));
	
		    	    System.out.println("Navbar is visible.");
		    	    Assert.assertTrue(navbar.isDisplayed(), "Navbar is not visible.");
	
		    	} catch (TimeoutException e) {
		    	    System.out.println("Navbar did not appear within the timeout.");
		    	    Assert.fail("Navbar did not become visible within timeout.");
		    	}
		    }
	
		 @Test
		    public void verifyFooter() {
		    	try {
		    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	    WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".footer-top")));
	
		    	    System.out.println("Footer is visible.");
		    	    Assert.assertTrue(footer.isDisplayed(), "Footer is not visible.");
	
		    	} catch (TimeoutException e) {
		    	    System.out.println("Footer did not appear within the timeout.");
		    	    Assert.fail("Footer did not become visible within timeout.");
		    	}
		    }
		
	
	}
