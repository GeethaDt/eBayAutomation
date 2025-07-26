package eBayTest.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import eBayTest.PageObjects.HomePage;

public class BaseTest {

	public WebDriver driver;
	public HomePage home;

	static Logger logger = Logger.getLogger(BaseTest.class);

	public WebDriver initilizeDriver() throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\eBayTest\\Resources\\GlobalData.properties");
		props.load(fis);

		String browserName = props.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			logger.info("Launching browser...");
			driver = new ChromeDriver();
			logger.info("Browser launched successfully");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			logger.info("Launching browser...");
			driver = new FirefoxDriver();
			logger.info("Browser launched successfully");
		} else if (browserName.equalsIgnoreCase("edge")) {
			logger.info("Launching browser...");
			driver = new EdgeDriver();
			logger.info("Browser launched successfully");
		}
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeSuite
	public void beforeSuiteSetup() {
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "/src/main/java/eBayTest/Resources/log4j.properties");
		new File("logs").mkdirs();
		new File(System.getProperty("user.dir") + "/target/Screenshots").mkdirs();
		logger.info("Folders for logs, screenshots, and reports created.");
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		driver = initilizeDriver();
		home = new HomePage(driver);
		home.goTo();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			logger.info("Closing browser");
			driver.quit();
		}
	}
}
