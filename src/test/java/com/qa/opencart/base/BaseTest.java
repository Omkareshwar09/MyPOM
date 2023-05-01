package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPages;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest
{
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPages loginPage;
	protected AccountPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;

	@BeforeTest
	public void setup()
	{
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop); 
		loginPage = new LoginPages(driver);
	}

	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
