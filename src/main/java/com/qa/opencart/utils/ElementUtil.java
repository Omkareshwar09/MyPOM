package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil
{
	private WebDriver driver;
	private JSUtil jsUtil;

	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		jsUtil = new JSUtil(driver);
	}

	public WebElement getElement(By locator)
	{
		WebElement element = driver.findElement(locator);
		driver.manage().window().maximize();
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(element);
		}
		
		return element;
	}

	public List<WebElement> getElements(By locator)
	{
		driver.manage().window().maximize();
		return driver.findElements(locator);
	}

	public void doSendKey(By locator, String value)
	{
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public String doElementGetText(By locator)
	{
		return getElement(locator).getText();
	}

//	 If element not found in the page then it will gives you "NO SUCH ELEMENT EXCEPTION". it will not gives you element not found and 
//	 IsDisplayed() will also not gives you "false"
	public boolean doElementIsDisplay(By locator)
	{
		return getElement(locator).isDisplayed();
	}

	public String getElementAttribute(By locator, String attributeName)
	{
		String attriValue = getElement(locator).getAttribute(attributeName);
		System.out.println("The arrribute value " + attriValue);
		return attriValue;
	}

	public void getElementAttributes(By locator, String tagName)
	{
		List<WebElement> element = getElements(locator);
		System.out.println("Total elements in page:- " + element.size());
		for (WebElement e : element)
		{
			String eleAttrValue = e.getAttribute(tagName);
			System.out.println(eleAttrValue);
		}
	}

	public int getTotalElementsCount(By locator)
	{
		int eleCount = getElements(locator).size();
		System.out.println("Total Elements " + locator + "------->" + eleCount);
		return eleCount;
	}

	public List<String> getElementTextList(By locator)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList)
		{
			String text = e.getText();
			eleTextList.add(text);
		}
		System.out.println("Total element count " + eleList.size());
		System.out.println("Total element list " + eleTextList);
		return eleTextList;
	}

//******** Select based drop down utilities ******************************

	public void deSelectDropdownByIndex(By locator, int index)
	{
		Select select = new Select(getElement(locator));
		System.out.println("Before select " + select.getAllSelectedOptions().get(0).getText());
		select.selectByIndex(index);
		System.out.println("After select " + select.getAllSelectedOptions().get(0).getText());
	}

	public void deSelectDropdownByValue(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		System.out.println("Before select " + select.getAllSelectedOptions().get(0).getText());
		select.selectByValue(value);
		System.out.println("After select " + select.getAllSelectedOptions().get(0).getText());
	}

	public void deSelectDropdownByVisibleText(By locator, String visibleText)
	{
		Select select = new Select(getElement(locator));
		System.out.println("Before select " + select.getAllSelectedOptions().get(0).getText());
		select.selectByVisibleText(visibleText);
		System.out.println("After select " + select.getAllSelectedOptions().get(0).getText());
	}

	public List<WebElement> getTotalDropDownOptionList(By locator)
	{
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}

	public List<String> getDropDownOptionTextList(By locator)
	{
		List<WebElement> dropDownList = getTotalDropDownOptionList(locator);
		List<String> textList = new ArrayList<String>();
		for (WebElement e : dropDownList)
		{
			String textValue = e.getText();
			textList.add(textValue);
		}
		return textList;
	}

	public void selectDropDownValue(By locator, String countruName)
	{
		List<WebElement> eleList = getTotalDropDownOptionList(locator);
		for (WebElement e : eleList)
		{
			System.out.println(e.getText());
			if (e.getText().equals(countruName))
			{
				e.click();
				break;
			}
		}
	}

	public int getTotalDropDownCount(By locator)
	{
		System.out.println("Total count of country is " + getTotalDropDownOptionList(locator).size());
		return getTotalDropDownOptionList(locator).size();
	}

//	Value selected without Using Select Class

	public void DropDownValueWithoutSelect(By locator, String countryName)
	{
		List<WebElement> elementList = driver.findElements(locator);
		for (WebElement e : elementList)
		{
			System.out.println(e.getText());
			if (e.getText().equals(countryName))
			{
				e.click();
				System.out.println(e.getText());
				break;
			}
		}
	}

//	Get all text from suggestion box
	public void doSearch(By locator, String suggestionName)
	{
		List<WebElement> suggestion = getElements(locator);
		System.out.println(suggestion.size());

		for (WebElement e : suggestion)
		{
			if (e.getText().length() > 0)
			{
				System.out.println(e.getText());
			}
			if (e.getText().contains(suggestionName))
			{
				e.click();
				break;
			}
		}

	}

	// ************************Wait Utils **********************//
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * 
	 * @param timeOut
	 * @return
	 */
	public Alert waitForAlertPresence(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut)
	{
		return waitForAlertPresence(timeOut).getText();
	}

	public void acceptAlert(int timeOut)
	{
		waitForAlertPresence(timeOut).accept();
	}

	public void dismissAlert(int timeOut)
	{
		waitForAlertPresence(timeOut).dismiss();
	}

	public void alertSendKeys(int timeOut, String value)
	{
		waitForAlertPresence(timeOut).sendKeys(value);
	}

	public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(titleFractionValue));
		return driver.getTitle();
	}

	public String waitForTitleIsAndFetch(int timeOut, String titleValue)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}

	public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(urlFractionValue));
		return driver.getCurrentUrl();
	}

	public String waitForURLIsAndFetch(int timeOut, String urlValue)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlToBe(urlValue));
		return driver.getCurrentUrl();
	}

	public boolean waitForURLContains(int timeOut, String urlFractionValue)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFractionValue));

	}

	public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrameAndSwitchToItByFrameLoctor(int timeOut, By frameLocator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param timeOut
	 * @param locator
	 */
	public void clickWhenReady(int timeOut, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementToBeClickable(int timeOut, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doClickWithActionsAndWait(int timeOut, By locator)
	{
		WebElement ele = waitForElementToBeClickable(timeOut, locator);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}

	public WebElement waitForElementPresenceWithFluentWait(int timeOut, int pollingTime, By locator)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime)).withMessage("...element is not found on the page....");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void waitForAlertWithFluentWait(int timeOut, int pollingTime)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoAlertPresentException.class).pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("...Alert is not found on the page....");

		wait.until(ExpectedConditions.alertIsPresent());

	}
}
