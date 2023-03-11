package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 1:- Login for opencart application")
@Story("Login:- Steps are designed for the login into oprcart application")
public class LoginPageTest extends BaseTest
{
	@Severity(SeverityLevel.TRIVIAL)
	@Description("Getting the applicatio title from the method designed by :-Omkareshwar Singh")
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
			String actTitle = loginPage.getLoginPageTitle();
			Assert.assertEquals(actTitle,AppConstant.LOGIN_PAGE_TITLE_VALUE);
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("Getting the applicatio URL from the method Tested by :-Omkareshwar Singh")
	@Test(priority = 2)
	public void loginPageURLTest()
	{
			String actURL = loginPage.getLoginPageURL();
			Assert.assertTrue(actURL.contains(AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Forgot password is exist on webpage Tested by :-Omkareshwar Singh")
	@Test(priority = 3)
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test(priority = 4)
	public void isRightColumnExistTest()
	{
		Assert.assertTrue(loginPage.isRightColumnExistTest());
	}
	
	
	@Test(priority = 5)
	public void logoTest()
	{
		Assert.assertTrue(loginPage.isTopLogoExist());
	}
	
	@Test(priority = 6)
	public void footerLinkTest()
	{
		Assert.assertTrue(loginPage.isFooterExist());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("User should be albe to login application with correct user name and password")
	@Test(priority = 7)
	public void loginTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
//		accPage =  loginPage.doLogin(System.getProperty("userName").toLowerCase().trim(), System.getProperty("password").trim());
		
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
}
