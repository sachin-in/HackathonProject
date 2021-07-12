package RegressionTesting;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;

public class cabScenario extends testExecutor {
	
	
	
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

}
