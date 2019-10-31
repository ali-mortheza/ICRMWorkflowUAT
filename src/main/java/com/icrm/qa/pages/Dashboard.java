package com.icrm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.icrm.qa.base.BasePage;

public class Dashboard extends BasePage {

	private By btnCancelNotif = By.xpath("//button[@id='butBegin']");
	private By ddService = By.xpath("//span[@id='TabCS']//a[@class='navTabButtonLink']");
	private By LinkCases = By.xpath("//a[@id='nav_cases']//span[@class='nav-rowLabel']");
	
	public Dashboard(WebDriver driver) {
		super(driver);
	}
	
	//getter:
	public WebElement getBtnCancelNotif() {
		return getElement(btnCancelNotif);
	}
	
	public WebElement getBtnDDService() {
		return getElement(ddService);
	}
	
	public WebElement getLinkCases() {
		return getElement(LinkCases);
	}
	
	//action
	public void handleWarningEmail() {
		waitForAvailableFrameAndSwitchToIt("InlineDialog_Iframe");
		getBtnCancelNotif().click();
	}
	
	public CasePage goToCasePage() {
		switchToDefaultContent();
		waitForElementToBeClickable(ddService);
		getBtnDDService().click();
		getLinkCases().click();	
		return getInstance(CasePage.class);
	}

}
