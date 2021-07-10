package makeMyTripAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementContainer {
	public static WebDriver driver;
	public static WebElement element;
	public static List<WebElement> elements;

	// login Button
	public static WebElement loginButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[1]"));
		return element;

	}

	// gooogle login
	public static WebElement googleLogin(WebDriver driver) {
		element = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[2]/section[1]/div[4]"));
		return element;
	}

	// enter email
	public static WebElement emailInput(WebDriver driver) {
		element = driver.findElement(By.id("identifierId"));
		return element;

	}

	// NextButton
	public static WebElement nextButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		return element;

	}

	// enter password
	public static WebElement passwordInput(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		return element;

	}

	// 2 popup title
	public static WebElement popupTitle(WebDriver driver) {
		element = driver.findElement(By.xpath("//p[contains(text(),'Login/Signup for Best Prices')]"));
		return element;

	}

	// login by google
	public static WebElement googleClick(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[contains(text(),'Google')]"));
		return element;

	}

	// 3 Webpage Signature
	public static WebElement homepageSignature(WebDriver driver) {
		element = driver.findElement(By.xpath("//p[contains(text(),'© 2021 MAKEMYTRIP PVT. LTD.')]"));
		return element;

	}

	// To locate the cab button
	public static WebElement cabButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[@class='chNavText darkGreyText'][normalize-space()='Cabs']"));
		return element;

	}

	// TO LOCATE "From" Drop down ListBox
	public static WebElement fromCity(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[normalize-space()='From']"));
		return element;
	}

	// TO SELECT THE CITY FROM THE SUGESSTIONS LIST BOX
	public static WebElement selectDeparture(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[contains(@placeholder,'From')]"));
		return element;
	}

	// TO SELECT THE CITY FROM THE SUGESSTIONS LIST BOX
	public static WebElement selectArrival(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[contains(@placeholder,'To')]"));
		return element;
	}

	// TO LOCATE "calender" Drop down ListBox
	public static WebElement calender(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[contains(text(),'DEPARTURE')]"));
		return element;
	}

	// TO LOCATE day
	public static List<WebElement> daySelect(WebDriver driver) {
		elements = driver.findElements(By.className("DayPicker-Day"));
		return elements;
	}

	// ADULT List
	// TO locate the hotel tab

	public static WebElement hotelTab(WebDriver driver) {
		element = driver.findElement(By.xpath("//li[@class='menu_Hotels']//span[2]"));
		return element;
	}

	// to locate the guests button
	public static WebElement guestsButton(WebDriver driver) {
		element = driver.findElement(By.id("guest"));
		return element;
	}
	// the numbers available for adults

	public static List<WebElement> adultsList(WebDriver driver) {
		elements = driver.findElements(By.xpath("//ul[@data-cy='adultCount']/li"));
		return elements;
	}

}
