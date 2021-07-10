import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.aventstack.extentreports.ExtentReports;

import makeMyTripAutomation.ElementContainer;

public class makeMyTripTests {
	
	public static WebDriver driver;
	public static String[][] data;
	public static ExtentReports report;
	
	@BeforeTest
	public void getWebDriver() {
		driver=DriverSetup.createWebDriver("Drivers");
		String baseURL=data[1][0];
		driver.manage().window().maximize();
		
		driver.get(baseURL);
		
	}
	@BeforeSuite
	public String[][] getExcelData () throws IOException { //Reads data from the excel file
		
		/*Get Excel data from readExcelData class
		Pass the size of data array as int type, name of excel file and name of excel sheet as string type*/
		data=readExcelData.getData(7, "HackathonData.xlsx", "readData");
		return data;	
	}
	@BeforeTest
	public void createExtentReport() {
		report=ExtentReport.createExtentReport("HackathonProject");	
	}

	@Test(priority=0)
	public void loginWithGmail() {
		try {
			ElementContainer.loginButton(driver).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[2]/section[1]/div[4]")).click();//login by google
			
			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid  =  it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);
			
			driver.findElement(By.id("identifierId")).sendKeys("bughunterss01@gmail.com");//enter email
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();//click next
			Thread.sleep(1000);
			driver.findElement(By.name("password")).sendKeys("Bughunter$6");//enter password
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();//click next
			driver.switchTo().window(parentwindowid);
			Thread.sleep(5000);
			System.out.println("Logged in by google account");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void loginFromPopUp() {
		try {
		String popUp=driver.findElement(By.xpath("//p[contains(text(),'Login/Signup for Best Prices')]")).getText();//popup title
		if(popUp.equalsIgnoreCase("Login/Signup for Best Prices")) {
			driver.findElement(By.xpath("//span[contains(text(),'Google')]")).click();//login by google
			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid  =  it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);
			
			driver.findElement(By.id("identifierId")).sendKeys("bughunterss01@gmail.com");//enter email
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();//click next
			Thread.sleep(1000);
			driver.findElement(By.name("password")).sendKeys("Bughunter$6");//enter password
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();//click next
			driver.switchTo().window(parentwindowid);
			Thread.sleep(5000);
			System.out.println("Logged in with Google account");
			
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test()
	public void getHomePageSignature() {
		String title=driver.findElement(By.xpath("//p[contains(text(),'© 2021 MAKEMYTRIP PVT. LTD.')]")).getText();//Web Page signature
		Assert.assertEquals(title, data[1][1], "Title is not correct");
		Screenshot.captureScreenshot("Homepage", driver);
	}
	
	@Test(priority=2)
	public void searchCab() {
		driver.findElement(By.xpath("//span[@class='chNavText darkGreyText'][normalize-space()='Cabs']")).click();// Cab button
        driver.findElement(By.xpath("//span[normalize-space()='From']")).click();//from option
        driver.findElement(By.xpath("//input[contains(@placeholder,'From')]")).sendKeys("Delhi");//departure city
        try {
			Thread.sleep(500);
		} catch(InterruptedException e) {
		}
        Actions keyPress=new Actions(driver);
        keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER)).perform();
        driver.findElement(By.xpath("//input[contains(@placeholder,'To')]")).sendKeys("Manali");// arrival city
        try {
			Thread.sleep(500);
		} catch(InterruptedException e) {
		}
        keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER)).perform();
        
        driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]")).click();// calender
        List<WebElement> dates=driver.findElements(By.className("DayPicker-Day"));//day picker list
      
        for(int i=0;i<dates.size();i++) {
            String test=dates.get(i).getText();
        
            if(test.equalsIgnoreCase("20")) {
                dates.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath("//span[contains(text(),'PICKUP-TIME')]")).click();// pickup time
        List<WebElement> time=driver.findElements(By.className("timeDropDown blackText"));
        for(int j=0;j<time.size();j++) {
        	String test2=time.get(j).getText();
        	if(test2.equalsIgnoreCase("6:30")) {
        		time.get(j).click();
        	}
        }
	}
	
	@Test(dependsOnMethods="searchCab")
	public void getCabDetails() {
		
	}
	
	@Test
	public void buyGiftCard() throws InterruptedException {
		Actions moveCursor=new Actions(driver);
		moveCursor.moveToElement(driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[9]/a/span[1]"))).perform();
		driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[9]/div/a[4]")).click();	
		
	}
	
	@Test
	public void getAdultsList() {
			
			WebElement hotels=driver.findElement(By.xpath("//li[@class='menu_Hotels']//span[2]"));// hotels tab
			Highlight.flash(hotels, driver);
			hotels.click();
			ArrayList<String> adults = new ArrayList<String>();
	        WebElement guest=driver.findElement(By.id("guest"));// guest button
	        Highlight.flash(guest, driver);
	        guest.click();
	        List <WebElement> adultsCount = driver.findElements(By.xpath("//ul[@data-cy='adultCount']/li"));//no. of adults list
	        for (WebElement c : adultsCount)
	        {
	             adults.add(c.getText());
	        }
	        Assert.assertEquals(adults.size()+".0", data[1][2], "No. of adults is not correct");
	}
	
	@AfterTest
	public void closeBrowser() {
		
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		driver.quit();
		
	}
	public static void invokeTestNG() {
			
			//Invoke TestNG to run as java application
			TestNG test=new TestNG();
			List<String> suites=Lists.newArrayList();
			suites.add("testng.xml");
			test.setTestSuites(suites);
			test.run();	
		}
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Call invokeTestNG method
		//Project obj=new Project();
  		makeMyTripTests.invokeTestNG();	
}
	
}
