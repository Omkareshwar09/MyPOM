package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProductResult = By.cssSelector("div#content div.product-layout"); 
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getCountSearchProductsCount()
	{
		int productCount =  eleUtil.waitForElementsVisible(searchProductResult, AppConstant.DEFAULT_SHORT_TIME_OUT).size();
		System.out.println("Product Count in the page is :"+ productCount);
		return productCount;
	}
	
	

	public ProductInfoPage selectProduct(String productName)
	{
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstant.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
}
