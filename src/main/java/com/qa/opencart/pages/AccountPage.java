package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");
	
	public AccountPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle()
	{
		String title = eleUtil.waitForTitleIsAndFetch(AppConstant.DEFAULT_SHORT_TIME_OUT, AppConstant.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Account page Title is :"+title);
		return title;
	}
	
	public String getAccPageURL()
	{
		String url = eleUtil.waitForURLContainsAndFetch(AppConstant.DEFAULT_SHORT_TIME_OUT, AppConstant.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account page url is :"+url);
		return url;
	}
	
	public boolean isLogOutLinkExist()
	{
		return eleUtil.waitForElementVisible(logoutLink, AppConstant.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchExist()
	{
		return eleUtil.waitForElementVisible(search, AppConstant.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccPageHeadersList()
	{
		List<WebElement> accHeadersList = eleUtil.waitForElementsVisible(accHeaders, AppConstant.DEFAULT_SHORT_TIME_OUT);
		List<String> accHeadersValue = new ArrayList<String>();
		for (WebElement e : accHeadersList)
		{
			String text = e.getText();
			accHeadersValue.add(text);
		}
		return accHeadersValue;
	}

	
	public SearchPage performSearch(String searchKey)
	{
		if (isSearchExist())
		{
			eleUtil.doSendKey(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver); 
		}else
		{
			System.out.println("Search field not present on the page...............");
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
