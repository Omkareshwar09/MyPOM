package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsMamager
{
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsMamager(Properties prop)
	{
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOption()
	{
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("Running Chrome Browsre in headless mode............");
			co.addArguments("--headless");	
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			System.out.println("Running Chrome Browsre in incognito mode............");
			co.addArguments("--incognito");	
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOption()
	{
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("Running Chrome Browsre in headless mode............");
			fo.addArguments("--headless");	
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			System.out.println("Running Chrome Browsre in incognito mode............");
			fo.addArguments("--incognito");	
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOption()
	{
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("Running Chrome Browsre in headless mode............");
			eo.addArguments("--headless");	
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			System.out.println("Running Chrome Browsre in incognito mode............");
			eo.addArguments("--incognito");	
		}
		return eo;
	}
}
