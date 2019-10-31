package com.icrm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.icrm.qa.util.TestUtil;
import com.icrm.qa.util.WebEventListener;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static JavascriptExecutor jse;
	public static Actions action;
	public static Logger log;
	static String projectPath = System.getProperty("user.dir");
	
	public TestBase() {
		try {
			prop = new Properties();
			String config = projectPath +"\\src\\main\\java\\com\\icrm\\qa\\config\\config.properties";
			//FileInputStream fis = new FileInputStream("D:\\Ali\\Selenium\\STS\\STSProject\\ICRM\\src\\main\\java\\com\\icrm\\qa\\config\\config.properties");
			FileInputStream fis = new FileInputStream(config);
			prop.load(fis);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			String chromeDriver = projectPath +"\\Driver\\chromedriver.exe";
			//System.setProperty("webdriver.chrome.driver", "D:\\Ali\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			ChromeOptions options = new ChromeOptions();						
		    options.setExperimentalOption("useAutomationExtension", false);
		    options.addArguments("chrome.switches", "--disabsle-extensions");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("Firefox")) {
			//set firefox driver
		}
		
		else if(browserName.equals("ie")) {
			String ieDriver = projectPath +"\\Driver\\IEDriverServer.exe";
			//System.setProperty("webdriver.ie.driver","D:\\Ali\\Selenium\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver",ieDriver);
			driver = new InternetExplorerDriver();			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);	
		driver.get(prop.getProperty("url"));
	}
}
