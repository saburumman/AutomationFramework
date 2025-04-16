package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseFile {
    
    public static WebDriver driver;
    public static Properties prop = new Properties ();
    public static FileReader fr;
    
    @BeforeTest
    public void setUP() throws IOException {
        
        // I want to check if the browser I want to open is defined and call it from the properties file
        if (driver == null) {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\config.properties");
            prop.load(fr);
        }
        
        // Now I want to check if it is Chrome browser to open Chrome
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(); // No SelfHealingDriver, just the regular driver
            driver.manage().window().maximize();
            driver.get(prop.getProperty("URL"));
        }
        
        // Now I want to check if it is Firefox browser to open Firefox
        if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(); // No SelfHealingDriver, just the regular driver
            driver.manage().window().maximize();
            driver.get(prop.getProperty("URL"));
        }
        
        // Now I want to check if it is Safari browser to open Safari
        if (prop.getProperty("browser").equalsIgnoreCase("safari")) {
            
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver(); // No SelfHealingDriver, just the regular driver
            driver.manage().window().maximize();
            driver.get(prop.getProperty("URL"));
        }
        
    }
    
    @AfterTest
    public void tearDown() {
        
        driver.close();
        System.out.println("TearDown Done Successfully!!");
    }
}
