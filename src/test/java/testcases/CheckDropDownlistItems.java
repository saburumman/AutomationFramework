package testcases;

import base.BaseFile;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CheckDropDownlistItems extends BaseFile {

    // Method to check dropdown visibility and click an item
    public static boolean checkDropdownVisibility(WebDriver driver, String buttonSelector, String dropdownSelector, String menuItemSelector) {
        try {
            WebElement dropdownButton = driver.findElement(By.xpath(buttonSelector));

            dropdownButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(dropdownSelector)));

            if (dropdownMenu.isDisplayed()) {
                System.out.println("Dropdown menu is visible");

//                if (menuItemSelector != null && !menuItemSelector.isEmpty()) {
//                    WebElement menuItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menuItemSelector)));
//                    menuItem.click();
//                    System.out.println("Clicked on menu item: " + menuItem.getText());
//                }

                return true;
            } else {
                System.out.println("Dropdown menu is not visible");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    //call checkDropdownVisibility
    @Test
    public void testDropdownVisibility() {
        checkDropdownVisibility(driver, "//button[text()='جميع الانواع']", ".dropdown-menu", "//button[text()='مزاد الكتروني (زمني)']");
    }
}
