package Tools;

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
	// country 
	public static WebElement countryVerify(WebDriver driver) {
		element = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/ul[1]/li[4]/div[1]/span[2]")) ;
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

	// 4 To locate the cab button
	public static WebElement cabButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[@class='chNavText darkGreyText'][normalize-space()='Cabs']"));
		return element;

	}

	// radio button
	public static List<WebElement> radioButton(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li"));
		return elements;
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

	// pickuptime
	public static WebElement pickupTimeDropdown(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[contains(text(),'PICKUP-TIME')]"));
		return element;
	}

	// listDropdown
	public static List<WebElement> pickupTimeLists(WebDriver driver) {
		elements = driver.findElements(By.xpath("//ul[@class = 'timeDropDown blackText']/li"));
		return elements;
	}

	// get the time
	public static WebElement timeSet(WebDriver driver) {
		element = driver.findElement(By.xpath("//ul[@class = 'timeDropDown blackText']/li[14]"));
		return element;
	}

	// search button
	public static WebElement searchButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[normalize-space()='Search']"));
		return element;
	}

	// Suv Select
	public static WebElement suvSelect(WebDriver driver) {
		element = driver.findElement(By.xpath("//label[normalize-space()='SUV']"));
		return element;
	}

	// Select carName
	public static WebElement carName(WebDriver driver) {
		element = driver.findElement(By.xpath(
				"//body/div[@id='root']/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]"));
		return element;
	}

	// get the charges
	public static WebElement charges(WebDriver driver) {
		element = driver.findElement(By.xpath(
				"//body/div[@id='root']/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/p[1]"));
		return element;
	}

	// get the rating
	public static WebElement rating(WebDriver driver) {
		element = driver.findElement(By.xpath(
				"//body/div[@id='root']/div[1]/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/span[1]"));
		return element;
	}
	// Gift Card Starts

	// more dropdown
	public static WebElement moreDropDown(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='SW']/div[1]/div[2]/div/div/nav/ul/li[9]/a/span[1]"));
		return element;
	}

	// giftcard tab
	public static WebElement giftCardTab(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='SW']/div[1]/div[2]/div/div/nav/ul/li[9]/div/a[4]"));
		return element;
	}

	// select gift card
	public static WebElement giftCard(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@class='card__data']"));
		return element;
	}
	// senders name

	public static WebElement sendersName(WebDriver driver) {
		element = driver.findElement(By.name("senderName"));
		return element;
	}

	// senders mobile no.
	public static WebElement sendersMobile(WebDriver driver) {
		element = driver.findElement(By.name("senderMobileNo"));
		return element;
	}

	// senders email
	public static WebElement sendersEmail(WebDriver driver) {
		element = driver.findElement(By.name("senderEmailId"));
		return element;
	}

	// error
	public static WebElement errorMessage(WebDriver driver) {
		element = driver.findElement(By.xpath("//p[contains(text(),'Please enter a valid Email id.')]"));
		return element;
	}

	// buy now button
	public static WebElement buyNowButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/button"));
		return element;
	}

	// ADULT List
	// TO locate the hotel tab

	public static WebElement hotelTab(WebDriver driver) {
		element = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/nav[1]/ul[1]/li[2]/a[1]/span[2]"));
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
