#eBay Automation Exercise

This project is an automated test suite developed using Java, Selenium WebDriver, TestNG, and Log4j for verifying eBay website functionality such as item search and cart validation.

##Project Structure

- eBayTest.CommonComponents - Having reusable components
- eBayTest.PageObjects – Page Object classes (e.g., HomePage, ItemListingPage, OrdersPage, SearchResultsPage)
- eBayTest.Resources - Property files and Log4j config
- eBayTest.TestComponents -  Base test setup, logging, screenshot, and reporting
- eBayTest.Tests – Actual TestNG test cases
- target/Screenshots – Stores captured screenshots
- reports/ExtentReports – Stores ExtentReports HTML files
- logs - Log4j log file

##Features

- Opens eBay website
- Searches for an item (e.g., "Book")
- Verifies cart behavior
- Takes screenshot after test pass/fail
- Generates logs and ExtentReports
- Uses Page Object Model (POM) for maintainability

##Tools & Technologies

- Java
- Selenium WebDriver
- TestNG
- Maven
- Log4j
- ExtentReports
- Git

##How to Run the Tests

1. Clone the repo
2. Run `mvn clean test`
3. Check reports and screenshots under the mentioned folder

4. **View Reports**
   - Screenshots: target/Screenshots
   - Extent Report:  reports/ExtentReports

##Author

**Geetha**  
Senior Software Test Engineer  
7+ Years of Experience | Selenium | Java | TestNG | POM | Jenkins | GitHub  
Domain Experience: Healthcare, Wealth Management, Banking

##GitHub Repository Link

https://github.com/your-username/eBayAutomation

##Note:
   * Although the requirement screenshot shows '2' in the cart icon,
   * this test adds only one item as per the written steps.
   * Hence, cart is expected to show '1' — and our assertion is based on that.
