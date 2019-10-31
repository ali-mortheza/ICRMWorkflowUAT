package com.icrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.icrm.qa.base.TestBase;
import com.icrm.qa.pages.CasePage;
import com.icrm.qa.pages.Dashboard;
import com.icrm.qa.pages.NewCase;
import com.icrm.qa.util.TestUtil;

public class CreateCase extends TestBase {
	
	Dashboard dashboard;
	CasePage casePage;
	NewCase newCasePage;
	int i = 0;
	
	@BeforeClass
	public void setUp() {
		initialization();
		dashboard = new Dashboard(driver);
	}
	
	@DataProvider
	public Object[][] getDataLevel1() {
		Object data[][] = TestUtil.getTestData("level1");
		return data;
	}
	
	@Test(dataProvider = "getDataLevel1")
	public void createCase(String nopol, String ts, String type) {
		if(i==0) {
			dashboard.handleWarningEmail();
		}	
		casePage = dashboard.goToCasePage();
		newCasePage = casePage.goToNewCase();
		if(!newCasePage.doSetFieldCase(nopol, ts, type,i)) {
			Assert.assertFalse(false, "Failed for save and route from status Open to In Progress");
		}
		newCasePage.gotoDashboard();
		i++;
	}

}
