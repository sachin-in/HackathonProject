package RegressionTesting;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Executor.testExecutor;
import Tools.ElementContainer;
import Tools.Highlight;


public class hotelScenario extends testExecutor{
	
	ArrayList<String> adults;	
	
	@Test(priority=0)
	public void enterCity() {
		
        Highlight.flash(ElementContainer.hotelTab(driver), driver,"HotelButton");
        ElementContainer.hotelTab(driver).click();
        
        Highlight.flash( driver.findElement(By.xpath("//span[contains(text(),'City / Hotel / Area / Building')]")), driver, "EnterCityButton");
        driver.findElement(By.xpath("//span[contains(text(),'City / Hotel / Area / Building')]")).click();
        String buttonText=driver.findElement(By.xpath("//span[contains(text(),'City / Hotel / Area / Building')]")).getText();
        regReport.createTest("EnterHotelLocation");
		Assert.assertEquals(buttonText, "CITY / HOTEL / AREA / BUILDING", "Text is not correct");
		regReport.flush();
		
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']")).sendKeys(data[1][8]);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Actions keyPress = new Actions(driver);
        keyPress.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();

		
		
	}
	
	@Test(dependsOnMethods="enterCity",priority=1)
	public void enterCheckInCheckOutDate() {
		
		Highlight.flash(driver.findElement(By.xpath("//span[contains(text(),'CHECK-IN')]")),driver,"Calender");
		
			try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			
		WebElement checkInDate = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[5]/div[4]"));// day picker list
		checkInDate.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		WebElement checkOutDate = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[5]/div[5]"));// day picker list
		checkOutDate.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String buttonText=driver.findElement(By.xpath("//span[contains(text(),'CHECK-IN')]")).getText();
		regReport.createTest("EnterCheckInChekOutDate");
		Assert.assertEquals(buttonText, "CHECK-IN", "Text is not correct");
		regReport.flush();
	}
	
	@Test(priority=2)
	public void verifyRoomsAndGuestButton() {
		
		adults = new ArrayList<String>();
		ElementContainer.guestsButton(driver);// guest button
		Highlight.flash(driver.findElement(By.xpath("//span[contains(text(),'ROOMS & GUESTS')]")), driver, "RoomsAndGuestButton");
		String buttonText=driver.findElement(By.xpath("//span[contains(text(),'ROOMS & GUESTS')]")).getText();
		ElementContainer.guestsButton(driver).click();
		regReport.createTest("VerifyROoms&GuestButton");
		Assert.assertEquals(buttonText, "ROOMS & GUESTS", "Text doesn't match");
		regReport.flush();
	}

	@Test(priority=3,dependsOnMethods = "verifyRoomsAndGuestButton")
	public void getAdultsList() throws InterruptedException {
		
		List<WebElement> adultsCount = ElementContainer.adultsList(driver);// no. of adults list

		for (WebElement c : adultsCount) {
			adults.add(c.getText());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[contains(text(),'APPLY')]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ElementContainer.hotelsearch(driver).click();
		
		regReport.createTest("SelectNumberOfAdults");
		Assert.assertEquals(adults.size() + ".0", data[1][2], "No. of adults is not correct");
		regReport.flush();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=4,dependsOnMethods="getAdultsList")
	public void verifyFreeBreakfastCheckBox() throws InterruptedException {
		
		WebElement freeBreakfastButton=driver.findElement(By.xpath("//p[contains(text(),'Free Breakfast')]"));
		Highlight.flash(freeBreakfastButton, driver, "FreeBreakfastCheckBox");
		String buttonText=freeBreakfastButton.getText();
		freeBreakfastButton.click();
		regReport.createTest("SelectFreeBreakfast");
		Assert.assertEquals(buttonText, "Free Breakfast", "Text is not correct");
		regReport.flush();
		
		Thread.sleep(2000);
		driver.navigate().to(data[1][0]);
		Thread.sleep(2000);
	}
	
}
