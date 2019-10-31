/**
 * 
 */
package com.icrm.qa.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * @author aliridh
 *
 */
public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
		action = new Actions(driver);
		log = Logger.getLogger(BasePage.class);
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	@Override
	public String getValue(By locator) {
		// TODO Auto-generated method stub
		return getElement(locator).getText();
	}

	@Override
	public WebElement getElement(By locator) {
		// TODO Auto-generated method stub
		WebElement element = null;
		try {
			waitForElementPresent(locator);
			element = driver.findElement(locator);
			return element;
		}
		catch(Exception e) {
			System.out.println("Some error while creating element " +locator.toString());
			//e.printStackTrace();
			//log.error("Some error while creating element "+locator.toString());
		}
		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {	
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch(Exception e) {
			System.out.println("Some exception error while waiting for the element "+locator.toString());
			//log.error("Some exception error while waiting for the element "+locator.toString());
		}
	}

	@Override
	public void waitForPageTitle(String title) {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.titleContains(title));
		}
		catch(Exception e) {
			System.out.println("Some exception error while waiting for title "+title);
			//log.error("Some exception error while waiting for title "+title);
		}
	}

	@Override
	public void waitForAvailableFrameAndSwitchToIt(String id) {
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));
		}
		catch(Exception e) {
			System.out.println("Some exception error while waiting for the frame "+id);
			//log.error("Some exception error while waiting for the frame "+id);
		}
	}

	@Override
	public void switchToDefaultContent() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().defaultContent();
		}
		catch(Exception e) {
			System.out.println("Error when switch to default content "+e.getMessage());
			//log.error("Error when switch to default content "+e.getMessage());
		}
	}

	@Override
	public void waitForElementToBeClickable(By locator) {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		catch(Exception e) {
			System.out.println("Some exception error while clicking the element "+locator.toString());
			//log.error("Some exception error while clicking the element "+locator.toString());
		}
		
	}

	@Override
	public void javaScriptExecutorSetLocator(String script, WebElement element) {
		// TODO Auto-generated method stub	
		try {
			jse.executeScript(script, element);					
		}
		catch(Exception e) {
			System.out.println("JavaScript executor locator error");
			//log.error("JavaScript executor locator error "+e.getMessage());
		}
	}

	@Override
	public void javaScriptExecutorSetValue(String script, String value) {
		// TODO Auto-generated method stub
		try {
			jse.executeScript(script, value);
		}
		catch(Exception e) {
			System.out.println("Some exception error while clicking the element");
			//log.error("JavaScript executor value error "+e.getMessage());
		}
	}

	@Override
	public void waitForJavaScriptPageReady() {
		try {
			wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")); 
		}
		catch(Exception e) {
			System.out.println("Some exception error while clicking the element");
			//log.error("JavaScript executor value error "+e.getMessage());
		}			
	}

	@Override
	public void waitForJQueryPageReady() {
		try {
			wait.until((ExpectedCondition<Boolean>) wd -> (Long)((JavascriptExecutor) wd).executeScript("return jQuery.active") == 0); 
		}
		catch(Exception e) {
			System.out.println("Some exception error while clicking the element");
			//log.error("JavaScript executor value error "+e.getMessage());
		}		
	}
	
	@Override
	public void waitForElementVisibility(By locator) {		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		catch(Exception e) {
			System.out.println("Some exception error while waiting for the element "+locator.toString());
			//log.error("Some exception error while waiting visibility for the element "+locator.toString());
		}
	}
}
