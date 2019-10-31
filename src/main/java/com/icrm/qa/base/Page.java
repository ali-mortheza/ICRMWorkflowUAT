/**
 * 
 */
package com.icrm.qa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.icrm.qa.base.BasePage;

/**
 * @author aliridh
 *
 */
public abstract class Page extends TestBase {
	//WebDriver driver;
	WebDriverWait wait;

	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);
	}

	// abstract methods:
	public abstract String getPageTitle();
	public abstract String getValue(By locator);
	public abstract WebElement getElement(By locator);
	public abstract void waitForElementPresent(By locator);
	public abstract void waitForElementToBeClickable(By locator);
	public abstract void waitForPageTitle(String title);
	public abstract void waitForAvailableFrameAndSwitchToIt(String id);
	public abstract void waitForJavaScriptPageReady();
	public abstract void waitForJQueryPageReady();
	public abstract void waitForElementVisibility(By locator);
	public abstract void switchToDefaultContent();
	public abstract void javaScriptExecutorSetLocator(String script, WebElement element);
	public abstract void javaScriptExecutorSetValue(String script, String value);
	
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
