package eBayTest.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public ExtentReports getReports() {
		String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setReportName("eBay Test Results using ExtentReports");
		sparkReporter.config().setDocumentTitle("eBay Test Results");

		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Tester", "Geeta D");
		return extentReport;
	}

}
