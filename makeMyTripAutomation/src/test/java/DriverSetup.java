import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	
		public static WebDriver driver;
		
		public static WebDriver createWebDriver(String folder) {//creating driver for Firefox and Chrome drivers respectively
			
			//Getting input from user as to choose from chrome or firefox driver
			Scanner sc=new Scanner(System.in);
			System.out.println("Chrome, Firefox");
			System.out.println("Choose any of the above broswers: ");
			String browser="Chrome";//sc.nextLine();
			sc.close();
			    
				String firefoxPath= System.getProperty("user.dir")+"\\"+folder+"\\geckodriver.exe";
				String chromePath= System.getProperty("user.dir")+"\\"+folder+"\\chromedriver.exe";
				//get absolute path of geckodriver and chromedriver
				String firefox = new File(firefoxPath).getAbsolutePath();
				String chrome = new File(chromePath).getAbsolutePath();
				
				//selecting either of the driver based on user input
				if (browser.equalsIgnoreCase("firefox")) {
					
					System.setProperty("webdriver.gecko.driver", firefox);
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
				}
				else if (browser.equalsIgnoreCase("chrome")) {
					
					System.setProperty("webdriver.chrome.driver", chrome);
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}
				
				return driver;
				
		}
}
