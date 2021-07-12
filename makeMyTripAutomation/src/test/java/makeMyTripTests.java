import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		driver = DriverSetup.createWebDriver("Drivers");
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
		data = readExcelData.getData(12, "HackathonData.xlsx", "readData");
		return data;
	}

	@BeforeTest
	public void createExtentReport() {
		report = ExtentReport.createExtentReport(data[1][3]);
	}

	@Test(priority = 0)
	public void loginWithGmail() {
		try {
			ElementContainer.loginButton(driver).click();// login button
			Thread.sleep(500);
			ElementContainer.googleLogin(driver).click();// login by google

			Set<String> handle = driver.getWindowHandles();
			Iterator<String> it = handle.iterator();
			String parentwindowid = it.next();
			String childwindowid = it.next();
			driver.switchTo().window(childwindowid);
			Thread.sleep(1000);

			ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
			ElementContainer.nextButton(driver).click();// click next
			Thread.sleep(1000);
			ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
			ElementContainer.nextButton(driver).click();// click next
			driver.switchTo().window(parentwindowid);
			Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void loginFromPopUp() {
		try {
			String popUp = ElementContainer.popupTitle(driver).getText();// popup title
			if (popUp.equalsIgnoreCase(data[1][6])) {
				ElementContainer.googleClick(driver).click();// login by google
				Set<String> handle = driver.getWindowHandles();
				Iterator<String> it = handle.iterator();
				String parentwindowid = it.next();
				String childwindowid = it.next();
				driver.switchTo().window(childwindowid);
				Thread.sleep(1000);

				ElementContainer.emailInput(driver).sendKeys(data[1][4]);// enter email
				ElementContainer.nextButton(driver).click();// click next
				Thread.sleep(1000);
				ElementContainer.passwordInput(driver).sendKeys(data[1][5]);// enter password
				ElementContainer.nextButton(driver).click();// click next
				driver.switchTo().window(parentwindowid);
				Thread.sleep(10000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority=2)
	public void getHomePageSignature() {
		String title = ElementContainer.homepageSignature(driver).getText();// Web Page signature
		Assert.assertEquals(title, data[1][1], "Title is not correct");
		Screenshot.captureScreenshot("Homepage", driver);
	}

	@Test(priority = 3)
	public void searchCab() {
		ElementContainer.cabButton(driver).click();// Cab button
		WebElement radio = ElementContainer.radioButton(driver);
		if (radio.isSelected() == false) {
			radio.click();

		}
	}
@Test(dependsOnMethods="searchCab")
	public void departure() {
		ElementContainer.fromCity(driver).click();// from option
		ElementContainer.selectDeparture(driver).sendKeys(data[1][7]);// departure city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
	}

	@Test(priority=4)
	public void arrival() {
		ElementContainer.selectArrival(driver).sendKeys(data[1][8]);// arrival city
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		Actions keyPress = new Actions(driver);
		keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
	}

	@Test(priority=5)
	public void calender() {
		ElementContainer.calender(driver).click(); // calender
		List<WebElement> dates = ElementContainer.daySelect(driver);// day picker list

		for (int i = 0; i < dates.size(); i++) {
			String test = dates.get(i).getText();

			if (test.equalsIgnoreCase(data[1][9])) {
				dates.get(i).click();
				break;
			}
		}
	}

	@Test(priority=6)
	public void time() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'PICKUP-TIME')]")).click();// pickup time
		List<WebElement> optionList = driver.findElements(By.xpath("//ul[@class = 'timeDropDown blackText']/li"));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", optionList.get(14));
		driver.findElement(By.xpath("//ul[@class = 'timeDropDown blackText']/li[14]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[normalize-space()='Search']")).click();
		}

	@Test(priority=7)
	public void getCabDetails() {

	}

	@Test
	public void giftCards() throws InterruptedException {
		Actions moveCursor = new Actions(driver);
		moveCursor.moveToElement(ElementContainer.moreDropDown(driver)).perform();
		ElementContainer.giftCardTab(driver).click();
	}
	@Test(dependsOnMethods="giftCards")
	public void buyGiftCard() throws InterruptedException {
		Actions click=new Actions(driver);
		click.moveToElement(ElementContainer.giftCard(driver)).click().perform();
		Thread.sleep(500);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		
<<<<<<< HEAD
		driver.findElement(By.name("senderName")).sendKeys(data[3][0]);
		driver.findElement(By.name("senderMobileNo")).sendKeys(data[3][1]);
		driver.findElement(By.name("senderEmailId")).sendKeys(data[3][2]);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/button")).click();
=======
		ElementContainer.sendersName(driver).sendKeys("Bughunters");
		ElementContainer.sendersMobile(driver).sendKeys("9999999562");
		ElementContainer.sendersEmail(driver).sendKeys("test@gmail");
		ElementContainer.buyNowButton(driver).click();
>>>>>>> 852c2055a133e9da0bc360c855149b54dbecf3c3
	}

	@Test
	public void getAdultsList() {

		WebElement hotels = ElementContainer.hotelTab(driver);// hotels tab
		Highlight.flash(hotels, driver);
		hotels.click();
		ArrayList<String> adults = new ArrayList<String>();
		WebElement guest = ElementContainer.guestsButton(driver);// guest button
		Highlight.flash(guest, driver);
		guest.click();
		List<WebElement> adultsCount = ElementContainer.adultsList(driver);// no. of adults list
		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
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

		// Invoke TestNG to run as java application
		TestNG test = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("testng.xml");
		test.setTestSuites(suites);
		test.run();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		// Call invokeTestNG method
		// Project obj=new Project();
		makeMyTripTests.invokeTestNG();
	}

}
