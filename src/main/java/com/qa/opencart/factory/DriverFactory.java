package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory
{
	public WebDriver driver;
	public Properties prop;
	public OptionsMamager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is for to get the driver on the basic of browser name
	 * @param browser
	 * @return this will retuen driver
	 */
	public WebDriver initDriver(Properties prop)
	{ 
		optionsManager = new OptionsMamager(prop);
		highlight = prop.getProperty("highlight").trim();
		
		String browserName = prop.getProperty("browser").trim().toLowerCase();
		
//		String browserName = System.getProperty("browser").toLowerCase().trim();
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
//			driver = new ChromeDriver(optionsManager.getChromeOption());	
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOption()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
//			driver = new FirefoxDriver(optionsManager.getFirefoxOption());	
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
//			driver = new EdgeDriver(optionsManager.getEdgeOption());	
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOption()));
		}
		else
		{
			System.out.println("Please pass the correct browser name................... "+ browserName);
		}
		
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));
//		return driver;
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/*
	 *  Get the local thread copy of the driver
	 */
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	/**
	 * this method is reading the properties form the config.properties file
	 * @return
	 */
	public Properties initProperties() {
		
//		mvn clean install -Denvironment="qa"
//		mvn clean install -Denvironment="stage"
//		mvn clean install-------------------------------- it is showing environment is NULL. We have to check this type of value where env is NULL
		
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		System.out.println("Running the test cases on the "+envName);
		
		try
		{
			if (envName == null)
			{
				System.out.println("..... No environment is passed so Running on the default environment which is QA environment");
				ip = new FileInputStream(AppConstant.PROPERTY_FILE_PATH_QA);
	
			}
			else {
				switch (envName.toLowerCase().trim())
				{
				case "qa":
					ip = new FileInputStream(AppConstant.PROPERTY_FILE_PATH_QA);
					break;
				case "dev":
					ip = new FileInputStream(AppConstant.PROPERTY_FILE_PATH_DEV);
					break;
				case "stage":
					ip = new FileInputStream(AppConstant.PROPERTY_FILE_PATH_STAGE);
					break;
				case "prod":
						ip = new FileInputStream(AppConstant.PROPERTY_FILE_PATH);
						break;
				default:
					System.out.println("....Please pass the correct environment to run test cases.........");
					throw new FrameworkException("WRONG ENVIRONMENT IS PASSED......");
//					break;
				}
			}
		}
		catch (IOException e)
		{
		e.printStackTrace();
	}
		
		try
		{
			prop.load(ip);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		
//		try
//		{
//			FileInputStream ip = new FileInputStream(AppConstant.PROPERTY_FILE_PATH);
//																			
//			prop.load(ip);
//		} catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}
		return prop;
	}
	
	/*
	 * Get Screenshot logic
	 */
	
	public static String getScreenshot()
	{
		File soruceFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try
		{
			FileUtil.copyFile(soruceFile, destination);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	
	
	

}
