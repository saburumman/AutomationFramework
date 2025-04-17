package testcases;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class FormValidationTest extends CheckResponsiveness {

	@Test
	public void testFormValidation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement joinPartnerButton = driver.findElement(By.xpath("//button[contains(text(),'انضم لشركاء مباشر')]"));
		joinPartnerButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-body")));

		WebElement submitButton = driver
				.findElement(By.cssSelector(".modal-footer button.btn.btn-primary[type='button']"));
		submitButton.click();

		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.invalid-feedback"), 5));
		List<WebElement> errorMessages = driver.findElements(By.cssSelector("div.invalid-feedback"));
		System.out.println("Number of error messages: " + errorMessages.size());

		for (WebElement error : errorMessages) {
			String actualText = error.getText().trim();
			if (!actualText.isEmpty()) {
				System.out.println("Actual error text: [" + actualText + "]");
				Assert.assertEquals(actualText, "هذا الحقل مطلوب", "Validation message mismatch");
			} else {
				System.out.println("Warning: Found empty error message element.");
			}
		}
		takeScreenshot("validation_after_empty_submit");

		// Fill the form
		WebElement GOVERNMENTdropdown = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[contains(@class,'react-select__value-container') and .//div[text()='اختر نوع الجهة']]")));
		GOVERNMENTdropdown.click();

		WebElement firstOptionGOVERNMENT = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'react-select__menu')]")));
		firstOptionGOVERNMENT.click();

		takeScreenshot("GOVERNMENT_dropdown_opened");

		WebElement dropdowninterestedIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='react-select__placeholder css-1wa3eu0-placeholder' and text()='اختر نوع الاصول المراد عرضها']")));
		dropdowninterestedIn.click();

		WebElement firstOptioninterestedIn = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'react-select__menu')]")));
		firstOptioninterestedIn.click();
		takeScreenshot("interestedIn_dropdown_opened");

		System.out.println("Validation tests passed.");
	}
}
