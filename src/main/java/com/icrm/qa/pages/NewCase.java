package com.icrm.qa.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.icrm.qa.base.BasePage;
import com.icrm.qa.util.TestUtil;

public class NewCase extends BasePage {

	private By txtCustomer = By.xpath("//input[@id='customerid_ledit']");
	private By txtPolicy = By.xpath("//input[@id='mli_policy_ledit']");
	private By txtTaskSubject = By.xpath("//input[@id='mli_tasksubjectid_ledit']");
	private By btnSearchCustomer = By.xpath("//img[@id='customerid_i']");
	private By btnSearchPolicy = By.xpath("//img[@id='mli_policy_i']");
	private By btnSearchTaskSubject = By.xpath("//img[@id='mli_tasksubjectid_i']");
	private By customer = By.xpath("//ul[@id='customerid_IMenu']//li[1]");
	private By policy = By.xpath("//ul[@id='mli_policy_IMenu']//li[1]");
	private By taskSubject = By.xpath("//ul[@id='mli_tasksubjectid_IMenu']//li[1]");
	private By lblVerificationResult = By.xpath("//label[@id='Verification Result_label']");
	private By lblTouchPointResult = By.xpath("//label[@id='Touchpoint_label']");
	private By lblCaseCategory = By.xpath("//label[@id='Case Category_label']");
	private By btnSave = By.xpath("//li[@id='incident|NoRelationship|Form|Mscrm.Form.incident.Save']//span//span");
	private By btnSaveAndRoute = By.xpath("//li[@id='incident|NoRelationship|Form|mli.incident.SaveAndSoute.Button']//span//span");
	private By btnDashboard = By.xpath("//span[@id='navTabLogoTextId']//img");
	private By btnMore = By.xpath("//li[@id='moreCommands']//span");
	private By statusElement = By.xpath("//label[contains(@id,'Status_label')]");
	private By btnCancelNotif = By.xpath("//button[@id='butBegin']");

	public NewCase(WebDriver driver) {
		super(driver);
	}

	// getter
	public WebElement getTxtPolicy() {
		return getElement(txtPolicy);
	}

	public WebElement getTxtCustomer() {
		return getElement(txtCustomer);
	}

	public WebElement getSearchPolicy() {
		return getElement(btnSearchPolicy);
	}

	public WebElement getSearchCustomer() {
		return getElement(btnSearchCustomer);
	}

	public WebElement getPolicy() {
		return getElement(policy);
	}

	public WebElement getCustomer() {
		return getElement(customer);
	}

	public WebElement getLblTouchPointResult() {
		return getElement(lblTouchPointResult);
	}

	public WebElement getLblVerificationResult() {
		return getElement(lblVerificationResult);
	}

	public WebElement getLblCaseCategory() {
		return getElement(lblCaseCategory);
	}

	public WebElement getTxtTaskSubject() {
		return getElement(txtTaskSubject);
	}

	public WebElement getBtnSearchTaskSubject() {
		return getElement(btnSearchTaskSubject);
	}

	public WebElement getTaskSubject() {
		return getElement(taskSubject);
	}

	public WebElement getBtnSave() {
		return getElement(btnSave);
	}

	public WebElement getBtnSaveAndRoute() {
		return getElement(btnSaveAndRoute);
	}

	public WebElement getBtnCancelNotif() {
		return getElement(btnCancelNotif);
	}

	public String getStatusElement() {
		return getValue(statusElement);
	}

	public WebElement getBtnMore() {
		return getElement(btnMore);
	}
	
	public WebElement getBtnDashboard() {
		return getElement(btnDashboard);
	}

	public boolean doSetFieldCase(String nopol, String ts, String type, int i) {
		if(i==0) {
			waitForAvailableFrameAndSwitchToIt("contentIFrame1");
		}else {
			waitForAvailableFrameAndSwitchToIt("contentIFrame0");		
		}
		// Policy
		waitForElementPresent(txtPolicy);
		javaScriptExecutorSetLocator("arguments[0].click();", getTxtPolicy());
		javaScriptExecutorSetValue("document.getElementById('mli_policy_ledit').value= arguments[0];", nopol);// nopol
		waitForElementPresent(btnSearchPolicy);
		javaScriptExecutorSetLocator("arguments[0].click();", getSearchPolicy());
		waitForJavaScriptPageReady();
		waitForJQueryPageReady();
		waitForElementPresent(policy);
		getPolicy().sendKeys(Keys.ENTER);

		// Customer
		waitForElementPresent(txtCustomer);
		javaScriptExecutorSetLocator("arguments[0].click();", getTxtCustomer());
		javaScriptExecutorSetLocator("arguments[0].click();", getSearchCustomer());
		waitForJavaScriptPageReady();
		waitForJQueryPageReady();
		waitForElementPresent(customer);
		//action.moveToElement(getCustomer()).build().perform();
		getCustomer().sendKeys(Keys.ENTER);

		// TouchPoint
		waitForElementPresent(lblTouchPointResult);
		javaScriptExecutorSetLocator("arguments[0].click();", getLblTouchPointResult());
		Select drpTch = new Select(driver.findElement(By.id("mli_touchpoint_i")));
		drpTch.selectByIndex(0);

		// VerificationResult
		waitForElementPresent(lblVerificationResult);
		javaScriptExecutorSetLocator("arguments[0].click();", getLblVerificationResult());
		Select drpVR = new Select(driver.findElement(By.id("mli_verificationresult_i")));
		drpVR.selectByIndex(1);

		// CaseCategory
		waitForElementPresent(lblCaseCategory);
		javaScriptExecutorSetLocator("arguments[0].click();", getLblCaseCategory());
		Select drpCategory = new Select(driver.findElement(By.id("mli_casecategory_i")));
		if (type.equals("Inquiry")) {
			drpCategory.selectByIndex(0);
		} else if (type.equals("Request")) {
			drpCategory.selectByIndex(1);
		} else if (type.equals("Complaint")) {
			drpCategory.selectByIndex(2);
		}

		// TaskSubject
		waitForElementPresent(txtTaskSubject);
		javaScriptExecutorSetValue("document.getElementById('mli_tasksubjectid_ledit').value=arguments[0];",ts);
		waitForElementPresent(btnSearchTaskSubject);
		javaScriptExecutorSetLocator("arguments[0].click();", getBtnSearchTaskSubject());
		waitForJavaScriptPageReady();
		waitForJQueryPageReady();
		waitForElementToBeClickable(taskSubject);
		//action.moveToElement(getTaskSubject()).build().perform();
		getTaskSubject().sendKeys(Keys.ENTER);

		// Save
		switchToDefaultContent();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		waitForElementVisibility(btnSave);
		waitForJavaScriptPageReady();
		waitForJQueryPageReady();
		waitForElementPresent(btnSave);
		waitForElementToBeClickable(btnSave);	
		getBtnSave().click();
		waitForElementPresent(btnSaveAndRoute);

		// screenshoot
		try {
			TestUtil.takeScreenshotAtEndOfTestSuccess(ts);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// SaveAndRoute
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		waitForJavaScriptPageReady();
		waitForJQueryPageReady();
		waitForElementVisibility(btnSave);		
		waitForElementPresent(btnSave);
		waitForElementToBeClickable(btnSave);	
		getBtnSaveAndRoute().click();
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		waitForAvailableFrameAndSwitchToIt("InlineDialog_Iframe");
		waitForElementToBeClickable(btnCancelNotif);
		getBtnCancelNotif().click();
		switchToDefaultContent();
		waitForAvailableFrameAndSwitchToIt("contentIFrame0");
		String status = getStatusElement();

		if (status.equals("Open")) {
			int loop = 0;
			do {
				if (loop < 4) {
					driver.navigate().refresh();
					waitForAvailableFrameAndSwitchToIt("InlineDialog_Iframe");
					waitForElementToBeClickable(btnCancelNotif);
					getBtnCancelNotif().click();
					driver.switchTo().defaultContent();
					waitForAvailableFrameAndSwitchToIt("contentIFrame0");
					status = getStatusElement();
					loop++;
				}
			} while (status.equals("Open") && (loop < 4));
		}

		// screenshoot
		status = getStatusElement();
		if (status.equals("In Progress")) {
			try {
				switchToDefaultContent();
				waitForElementPresent(btnMore);
				TestUtil.takeScreenshotAtEndOfTestSuccess(ts);// ts
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}else {
			System.out.println("Save and Route Failed");
			return false;
		}
	}
	
	public void gotoDashboard() {
		switchToDefaultContent();
		waitForElementPresent(btnDashboard);
		waitForElementToBeClickable(btnDashboard);
		getBtnDashboard().click();
	}

}
