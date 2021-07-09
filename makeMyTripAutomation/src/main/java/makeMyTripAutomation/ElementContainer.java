package makeMyTripAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementContainer {

	public static WebDriver driver;
	public static WebElement element;

	// TO LOCATE "Cabs" tab
	public static WebElement loginButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[1]"));
		return element;

	}

	// TO LOCATE "Out station One Way" Radio button
	public static WebElement outstationOneWay(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[1]/ul/li[1]"));
		return element;

	}

	// TO LOCATE "From" Drop down ListBox
	public static WebElement fromCity(WebDriver driver) {
		element = driver.findElement(By.id("fromCity"));
		return element;
	}

	// TO SELECT THE CITY FROM THE SUGESSTIONS LIST BOX
	public static WebElement selectDelhi(WebDriver driver, String city) {
		element = driver.findElement(By.xpath("//span[@class=\"sr_city blackText\"and contains(text(),'"+city+"')][1]"));
		return element;
	}

	// TO LOCATE "To" Drop down ListBox
	public static WebElement toCity(WebDriver driver) {
		element = driver.findElement(
				By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div/div/div/input"));
		return element;
	}

	// TO SELECT THE CITY FROM THE SUGESSTIONS LIST BOX
	public static WebElement selectManali(WebDriver driver, String city) {
		element = driver
				.findElement(By.xpath("//span[@class=\"sr_city blackText\"and contains(text(),'"+city+"')][1]"));
		return element;
	}

	// TO LOCATE "Search" Button
	public static WebElement searchCabs(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/p/a"));
		return element;
	}

	// TO LOCATE "Departure" Drop down ListBox
	public static WebElement pickDay(WebDriver driver,String date) {
		element = driver.findElement(By.xpath("(//div[text()='"+date+"'])[1]"));
		return element;
	}

	// TO LOCATE "SUV" CheckBox
	public static WebElement suvCheckBox(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[contains(text(),'SUV')]"));
		return element;
	}

	// TO FIND THE ELEMENTS OF ALL THE PRICES
	public static List<WebElement> searchResults(WebDriver driver) {
		List<WebElement> element = driver.findElements(By.xpath("//*[@class='font28 latoBlack blackText ']"));
		return element;
	}

	// TO LOCATE "Pickup Time" Drop down ListBox
	public static WebElement pickUpTimeSelect(WebDriver driver, String time) {
		element = driver.findElement(By.xpath("(//li[text()='"+time+"'])[1]"));
		return element;

	}

	// TO FIND THE ELEMENT OF THE POP-UP
	public static WebElement loginPopUp(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='SW']/div[1]/div[1]/ul/li[5]/div[3]"));
		return element;
	}

	// TO FIND THE ELEMENT OF THE LOGIN BUTTON
	public static WebElement loginBtn(WebDriver driver) {

		element = driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[1]/ul/li[5]"));
		return element;
	}

}
