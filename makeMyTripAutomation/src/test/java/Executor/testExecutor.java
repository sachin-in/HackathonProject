package Executor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.collections.Lists;

import com.aventstack.extentreports.ExtentReports;

import Tools.DriverSetup;
import Tools.ExcelData;
import Tools.ExtentReport;

public class testExecutor {

	//public static RemoteWebDriver driver;
	public static WebDriver driver;
	public static String[][] data;
	public static ExtentReports regReport;
	public static ExtentReports smokeReport;
	

	@BeforeTest
	public void getWebDriver() throws MalformedURLException {
		
		String browser="chrome";
		System.out.println("Chosen browser is: "+browser);
		
			//driver =  DriverSetup.createRemoteDriver(browser);
			driver =  DriverSetup.createWebDriver("Drivers", browser);
			String baseURL = data[1][0];
			driver.manage().window().maximize();
			driver.get(baseURL);
		
	}

	@BeforeSuite
	public String[][] getExcelData() throws IOException { // Reads data from the excel file

		/*
		 * Get Excel data from readExcelData class Pass the size of data array as int
		 * type, name of excel file and name of excel sheet as string type
		 */
		data = ExcelData.readData(12, "HackathonData.xlsx", "readData");
		return data;
	}

	@BeforeTest
	public void createExtentReport() {
		
		smokeReport = ExtentReport.createExtentReport("SmokeTest");
		regReport = ExtentReport.createExtentReport("RegressionTest");
	}

	

	@AfterTest
	public void closeBrowser() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();

	}

	public static void invokeTestNG() {

		// Invoke TestNG to run as java application
		TestNG test = new TestNG();
		List<String> suites = Lists.newArrayList();
		
		suites.add("smokeTest.xml");
		suites.add("testng.xml");
		
		test.setTestSuites(suites);
		test.run();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		// Call invokeTestNG method
		testExecutor.invokeTestNG();
	}

}
