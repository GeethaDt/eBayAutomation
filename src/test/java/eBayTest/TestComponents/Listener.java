package eBayTest.TestComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import eBayTest.Resources.ExtentReportNG;

public class Listener extends BaseTest implements ITestListener {

	Logger logger = Logger.getLogger(Listener.class);
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	ExtentReports reports = new ExtentReportNG().getReports();

	public String captureScreenshot(String testName, WebDriver driver) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/target/Screenshots/" + testName + "_" + timestamp
				+ ".png";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(screenshotPath);
		FileHandler.copy(sourceFile, destinationFile);
		logger.info("Screenshot saved: " + screenshotPath);
		return screenshotPath;
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = reports.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test is passed");
		String path = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			path = captureScreenshot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
			logger.info("Screenshot captured for passed test: " + path);
		} catch (Exception e) {
			logger.error("Error capturing screenshot on success", e);
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.error("Test Failed: " + result.getName());
		extentTest.get().fail(result.getThrowable());
		String failPath = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			logger.info("Screenshot captured at: " + failPath);
		} catch (Exception e) {
			logger.error("Error capturing screenshot", e);
			e.printStackTrace();
		}
		try {
			failPath = captureScreenshot(result.getMethod().getMethodName(), driver);
			logger.info("Screenshot captured at: " + failPath);
		} catch (Exception e) {
			logger.error("Error capturing screenshot", e);
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(failPath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
