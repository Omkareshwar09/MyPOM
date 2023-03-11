package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPages
{
//	1. No one can access this login page locators
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBTN = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By rightColumn = By.id("column-right");
	private By topLogo = By.id("logo");
	private By footer = By.xpath("//p[contains(text(),'Powered By')]"); 
	private By registerLink = By.linkText("Register");
	
	public LoginPages(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting the login page title form the application.........")
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstant.DEFAULT_SHORT_TIME_OUT , AppConstant.LOGIN_PAGE_TITLE_VALUE);
//		String title = driver.getTitle();
		System.out.println("Login Page title is "+title);
		return title;
	}
	
	@Step("Getting the login page URL form the application.........")
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURLContainsAndFetch(AppConstant.DEFAULT_SHORT_TIME_OUT, AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE);
//		String url = driver.getCurrentUrl();
		System.out.println("Login Page title is "+url);
		return url;
	}
	
	@Step("Checking the forgot link exist in application.........")
	public boolean isForgotPasswordLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
//		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	public boolean isRightColumnExistTest()
	{
//		return driver.findElement(rightColumn).isDisplayed();
		return eleUtil.waitForElementVisible(rightColumn, AppConstant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public boolean isTopLogoExist()
	{
//		return driver.findElement(topLogo).isDisplayed();
		return eleUtil.waitForElementVisible(topLogo, AppConstant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public boolean isFooterExist()
	{
//		return driver.findElement(footer).isDisplayed();
		return eleUtil.waitForElementVisible(footer, AppConstant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	@Step("Login with userName : {0} and password : {1)")
	public AccountPage doLogin(String un, String pw)
	{
		eleUtil.waitForElementVisible(emailId, AppConstant.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKey(password, pw);
		eleUtil.doClick(loginBTN);
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pw);
//		driver.findElement(loginBTN).click();
		System.out.println("Loged in with UserName : "+ un +" and Password : " + pw);
		return new AccountPage(driver);
	}
	
	@Step("......Navigating into Register Page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
