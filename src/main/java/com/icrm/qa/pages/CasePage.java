package com.icrm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.icrm.qa.base.BasePage;

public class CasePage extends BasePage {

	private By btnNewCase = By.xpath("//li[@id='incident|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.incident.NewRecord']//span//span");
	
	public CasePage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getBtnNewCase() {
		return getElement(btnNewCase);
	}

	public NewCase goToNewCase(){
		//waitForJavaScriptPageReady();
		//waitForJQueruPageReady();
		switchToDefaultContent();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitForElementPresent(btnNewCase);
		waitForElementToBeClickable(btnNewCase);
		javaScriptExecutorSetLocator("arguments[0].click();", getBtnNewCase());
		return getInstance(NewCase.class);
	}
}
