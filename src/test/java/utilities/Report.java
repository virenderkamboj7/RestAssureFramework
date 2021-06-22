package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {

////////////Extend Report objects//////////
	public static ExtentHtmlReporter htmlReporter;
//	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

//	Report.test =
	public static void endReport(String testName) {
		Report.test = Report.extent.createTest(testName);
	}

	public static void ExtentCall() {
		Report.Extent();
	}
	
	public static void Extentflush() {
		Report.extent.flush();
	}
	
	////////////////////////////////

	public static void Extent() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\report.html"); // "./Reports\\result.html"
		htmlReporter.config().setDocumentTitle("Automation Report"); // Title of the report
		htmlReporter.config().setReportName("Funtional Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		//
//		ExtentKlovReporter klov = new ExtentKlovReporter("MyProject")
//		  .initWithDefaultSettings();
		//
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("QA Name", "Virender");
	}



}
