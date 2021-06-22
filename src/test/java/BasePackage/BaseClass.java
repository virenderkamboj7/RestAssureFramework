package BasePackage;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import unilities.Report;
import unilities.TxtFileConfig;

public class BaseClass {

	public static RequestSpecification httpRequest;
	public static Response response;

	public static Logger logger;

	public static DateTimeFormatter dtf; // Date time formater
	public static LocalDateTime now; // Get local time

	static String baseURI;
//	static String endPoint;
//	Map<String, String> formParams;
	
	
	
	////////////Extend Report onjects//////////
//	public ExtentHtmlReporter htmlReporter;
//	public ExtentReports extent;
//	public ExtentTest test;
	

	@BeforeClass
	public void setup() {

		logger = logger.getLogger(getClass()); ////
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

	// POST Method
	public static void PostRequest(String endPoint, Map<String, Object> Params) {

		baseURI = Apis.baseURI();

		// Base URL
		RestAssured.baseURI = baseURI;
		httpRequest = RestAssured.given();

		logger.info("URL is ======> " + baseURI + endPoint);

		Gson gson = new Gson();
		JsonObject payload = gson.toJsonTree(Params).getAsJsonObject();
		logger.info("Payload is ======> " + payload);

		// Adding headers
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Accept", "application/json");

		String txtFilePath = "./src\\test\\java\\TestData\\token.txt";

		httpRequest.header("authorization", TxtFileConfig.Read(txtFilePath));// Passing parameters in request
		httpRequest.body(Params);

		// Passing URI to the request
		response = httpRequest.post(endPoint);
//		response = httpRequest.request(Method.POST, endPoint);

		String responseBody = response.getBody().asString();

		logger.info("Response is: ======> " + responseBody.toString());
		logger.info("Responce time of " + endPoint + " : " + response.time() + " ms");

	}
	
	//POST without bearer token
	public static void PostRequestWithoutBearer(String endPoint, Map<String, Object> Params) {

		baseURI = Apis.baseURI();

		// Base URL
		RestAssured.baseURI = baseURI;
		httpRequest = RestAssured.given();

		logger.info("URL is ======> " + baseURI + endPoint);

		Gson gson = new Gson();
		JsonObject payload = gson.toJsonTree(Params).getAsJsonObject();
		logger.info("Payload is ======> " + payload);

		// Adding headers
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Accept", "application/json");

//		String txtFilePath = "./src\\test\\java\\TestData\\token.txt";
//
//		httpRequest.header("authorization", TxtFileConfig.Read(txtFilePath));// Passing parameters in request
		httpRequest.body(Params);

		// Passing URI to the request
		response = httpRequest.post(endPoint);
//		response = httpRequest.request(Method.POST, endPoint);

		String responseBody = response.getBody().asString();

		logger.info("Response is: ======> " + responseBody.toString());
		logger.info("Responce time of " + endPoint + " : " + response.time() + " ms");

	}

	// GET Method
	public static void GetRequest(String endURL) {

		String baseURI = Apis.baseURI();
		RestAssured.baseURI = baseURI;
		httpRequest = RestAssured.given();

		String txtFilePath = "./src\\test\\java\\TestData\\token.txt";
		httpRequest.header("authorization", TxtFileConfig.Read(txtFilePath));
		response = httpRequest.request(Method.GET, endURL);
		logger.info("URL is =====>  " + baseURI + endURL);
		logger.info("Response is ======> " + response.asString());
		logger.info("Responce time of " + endURL + " : " + response.time() + " ms");
	}
	
	
	public static void GetRequestWithoutBearer(String endURL) {

		String baseURI = Apis.baseURI();
		RestAssured.baseURI = baseURI;
		httpRequest = RestAssured.given();

//		String txtFilePath = "./src\\test\\java\\TestData\\token.txt";
//		TxtFileConfig.Read(txtFilePath);
//		httpRequest.header("authorization", TxtFileConfig.Read(txtFilePath));
		
		response = httpRequest.request(Method.GET, endURL);
		logger.info("URL is =====>  " + baseURI + endURL);
		logger.info("Response is ======> " + response.asString());
		logger.info("Responce time of " + endURL + " : " + response.time() + " ms");
	}

	public void params(String key, String value) {
		Map<String, String> params = new HashMap<>();

	}

//	@AfterClass
	public void failMethod(ITestResult result) throws NoSuchMethodException, SecurityException {


		if (ITestResult.FAILURE == result.getStatus()) {
			System.out.println("Test Case Failed");
			try {
				dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				now = LocalDateTime.now();
//				System.out.println("Test case failed by:: "+dtf.format(now));
			} catch (Exception e) {
				System.out.println("Exception:: " + e.getMessage());
			}

			String nameOfCurrentClass = super.getClass().getSimpleName();
			logger.info("Failed Class Name:: " + nameOfCurrentClass + ", Failed Method Name:: "
					+ result.getMethod().getMethodName() + ", by Time:: " + dtf.format(now));
//			HelperClass.email("Test Case Failed", "Failed Class Name:: "+nameOfCurrentClass+ ", Failed Method Name:: "+result.getMethod().getMethodName()+", by Time:: "+dtf.format(now));
		}
	}


	
	@BeforeSuite
	public void afterTestMethod() {
		Report.ExtentCall();
	}
	
	@AfterSuite
	public void endReport() {
		Report.Extentflush();
		
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE && Report.test!=null) {
			Report.test.log(Status.FAIL, "Test Case Failed, Name : " +result.getName()); // to add name is extetnt report
			Report.test.log(Status.FAIL, "Throw exception is : " +result.getThrowable()); // to add error/exception in extent report
		}else if(result.getStatus() == ITestResult.SKIP && Report.test!=null) {
			Report.test.log(Status.SKIP, "Test case skipped is : " +result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS && Report.test!=null) {
			Report.test.log(Status.PASS, "Test case Passed is : " +result.getName());
		}
	}

	
	

}
