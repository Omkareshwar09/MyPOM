package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductValue() 
	{
		String productHaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header Value is : "+productHaderValue);
		return productHaderValue;
	}
	
	public int getProductImagesCount()
	{
		int imageCount = eleUtil.waitForElementsVisible(productImages, AppConstant.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Total images count is "+imageCount);
		return imageCount;
	}
}
