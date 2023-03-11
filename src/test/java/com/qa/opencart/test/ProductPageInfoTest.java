package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ExcelUtil;

public class ProductPageInfoTest extends BaseTest
{
	@BeforeClass
	public void productPageInfoSetup()
	{
		accPage	=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	/*@DataProvider
	public Object[][] getProductImagesTestData() {
		return  new Object[] [] {
			{"Macbook", "MacBook Air",4},
			{"Macbook","MacBook Pro",4},
			{"iMac", "iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	*/
	
	@DataProvider
	public Object[][] getProductData() {
		Object prodData[][] = ExcelUtil.getTestData(AppConstant.PRODUCT_SHEET_NAME);
		System.out.println(prodData[1][0]);
		System.out.println(prodData[1][1]);
		System.out.println(prodData[1][2]);
		return prodData;
	}
	
	@Test(dataProvider = "getProductData")
	public void productImageCountTest(String searchKey, String productName, String imgCount)
	{
		int imgCoun = Integer.parseInt(imgCount);
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
//		String actProductHeader = productInfoPage.getProductValue();
//		Assert.assertEquals(actProductHeader, productName);
		int actImgCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImgCount, imgCoun);
	}
	
}
