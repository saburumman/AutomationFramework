Tools and Dependencies Used
Selenium WebDriver: For automating browser actions.


TestNG: For structuring and running the tests.


Java: Programming language for writing the automation scripts.


ChromeDriver: WebDriver for interacting with Chrome browser.


JavaScript Executor: Used for executing scripts within the browser, especially for interactions with elements that are hard to click.


SimpleDateFormat: For comparing date and time.


WebDriverWait: For waiting for elements to become visible or clickable.



Assumptions Made During Development
The website's date and time format is consistent across all pages (though there may be differences in AM/PM representation).


The buttons with the same selector are expected to be handled via scroll and JavaScript execution to avoid interference from overlapping elements.


Screenshots and logs are captured for debugging purposes.


The browser's window is set to an appropriate size to avoid layout issues during the automation process.



Instructions for Executing the Tests
Set Up Environment:


Install Java: Ensure you have JDK 8 or higher installed.


Install Maven: To manage dependencies.


Install ChromeDriver: Ensure you have the correct version of ChromeDriver installed based on the Chrome version you're using.


Clone the Repository: Clone the repository to your local machine:


git clone <repository_url>

Install Dependencies: Navigate to the project folder and run:

mvn install
 This will download all the required dependencies.


Run the Tests: Run the automation scripts using Maven and TestNG:

mvn test
 Alternatively, you can run the tests directly from an IDE like IntelliJ IDEA or Eclipse with TestNG support.


Test Logs and Reports:


Logs and reports will be automatically generated for each test run.


Check the /target/test-output folder for generated reports and logs.







Automation Report
The automation framework generates detailed logs and screenshots to help diagnose any issues that occur during the execution of tests.


Upon failure, specific error messages are shown, such as element not found, click intercepts, or mismatched values.


Sample Test Result Output:


Auction Name: انوار المدينة
Screenshot saved: auction_details.png
Title verified: انوار المدينة
Auction Date and Time: 2025/04/17 01:00 م
Current System Time: 2025/04/18 12:56 AM
Time mismatch: Expected 2025/04/18 12:56 AM, got 2025/04/17 01:00 م
Countdown Timer - Days: 3, Hours: 18, Minutes: 3, Seconds: 50

