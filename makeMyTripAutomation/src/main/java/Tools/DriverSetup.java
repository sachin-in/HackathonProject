package Tools;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {
	
		public static WebDriver driver;
		public static RemoteWebDriver remoteDriver;
		
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
		public static RemoteWebDriver createRemoteDriver() throws MalformedURLException {
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Chrome, Firefox");
			System.out.println("Choose any of the above broswers: ");
			String browser="Chrome";//sc.nextLine();
			sc.close();
			
			if (browser.equalsIgnoreCase("Chrome")) {
				DesiredCapabilities dc=DesiredCapabilities.chrome();
				remoteDriver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				DesiredCapabilities df=DesiredCapabilities.firefox();
				remoteDriver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), df);
			}
			return remoteDriver;
		}
}
