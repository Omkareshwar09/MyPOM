package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password, String subscribe)
	{
		eleUtil.waitForElementVisible(this.firstName, AppConstant.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKey(this.lastName, lastName);
		eleUtil.doSendKey(this.email, email);
		eleUtil.doSendKey(this.telephone, telephone);
		eleUtil.doSendKey(this.password, password);
		eleUtil.doSendKey(this.confirmpassword, password);
		
			if(subscribe.equalsIgnoreCase("yes")) {
				eleUtil.doClick(subscribeYes);
			}
			else {
				eleUtil.doClick(subscribeNo);
			}
		eleUtil.doActionsClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstant.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("user reg success messg : " + successMesg);
		System.out.println("Email id has been used for this account :"+email);
		
		if(successMesg.contains(AppConstant.USER_REG_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
