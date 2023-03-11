
package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;

public class AccountPageTest extends BaseTest
{
	@BeforeClass
	public void accPageSetup()
	{
		accPage	=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void AccPageTitleTest()
	{
			String actTitle = accPage.getAccPageTitle();
			Assert.assertEquals(actTitle,AppConstant.ACCOUNT_PAGE_TITLE_VALUE);
	}

	@Test
	public void AccPageURLTest()
	{
			String actURL = accPage.getAccPageURL();
			Assert.assertTrue(actURL.contains(AppConstant.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void isSearchFieldExistTest()
	{
			Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test
	public void isLogoutExistTest()
	{
			Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	
	@Test
	public void accPageHeaderCountTest()
	{
			List<String> actAccHeaderList = accPage.getAccPageHeadersList();
			System.out.println("Account Page Headers "+ actAccHeaderList);
			Assert.assertEquals(actAccHeaderList.size(), AppConstant.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeaderValueTest()
	{
			List<String> actAccHeaderList = accPage.getAccPageHeadersList();
			System.out.println("Account Page Headers "+ actAccHeaderList);
			System.out.println("EXPECTED Account Page Headers "+ AppConstant.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
			Assert.assertEquals(actAccHeaderList, AppConstant.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getData() {
		return  new Object[] [] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider = "getData")
	public void searchProductCountTest(String searchKey)
	{
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getCountSearchProductsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return  new Object[] [] {
			{"Macbook", "MacBook Air"},
			{"Macbook","MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName)
	{
		searchPage = accPage.performSearch(searchKey);
		if (searchPage.getCountSearchProductsCount()>0)
		{
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductValue();
			Assert.assertEquals(actProductHeader, productName);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
