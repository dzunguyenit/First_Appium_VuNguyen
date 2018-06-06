package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractPage {
	private int timeouts = 20;

	// Appium
	protected WebDriver newServerAppium(DesiredCapabilities desireCapabilities, WebDriver driver, String url)
			throws MalformedURLException {
		return new RemoteWebDriver(new URL(url), desireCapabilities);
	}

	protected void setBrowserName(DesiredCapabilities desireCapabilities, String browserName, String platform) {
		desireCapabilities.setCapability(browserName, platform);
	}

	protected void setVersion(DesiredCapabilities desireCapabilities, String platformVersion, String version) {
		desireCapabilities.setCapability(platformVersion, version);
	}

	protected void setDevice(DesiredCapabilities desireCapabilities, String deviceName, String device) {
		desireCapabilities.setCapability(deviceName, device);
	}

	protected void setPlatform(DesiredCapabilities desireCapabilities, String platformName, String platform) {
		desireCapabilities.setCapability(platformName, platform);
	}

	protected void setPackage(DesiredCapabilities desireCapabilities, String appPackage, String pathAppPackage) {
		desireCapabilities.setCapability(appPackage, pathAppPackage);
	}

	protected void setActivity(DesiredCapabilities desireCapabilities, String appActivity, String pathAppActivity) {
		desireCapabilities.setCapability(appActivity, pathAppActivity);
	}

	// Web Browser
	protected void openUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	protected String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected void waitForIEBrowser(int Timeouts) {
		try {
			Thread.sleep(Timeouts);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Web Element
	protected void clickToElement(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.click();
		} catch (Exception e) {
			LogFactory.error("Element is not clickable " + e.getMessage());
		}

	}

	protected void clickToElement(WebDriver driver, String locator, String... value) {
		try {
			String dynamicLocator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(dynamicLocator));
			element.click();
		} catch (Exception e) {
			LogFactory.error("Element is not clickable " + e.getMessage());
		}
	}

	protected String getTextElement(WebDriver driver, String locator, String... value) {
		String text = null;
		try {
			String dynamicLocator = String.format(locator, (Object[]) value);
			return getTextElement(driver, dynamicLocator);
		} catch (Exception e) {
			LogFactory.error("Element is cann't get text " + e.getMessage());
		}
		return text;
	}

	// Clear and sendkey
	protected void sendKeyToElement(WebDriver driver, String locator, String value) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			LogFactory.error("Element don't sendkeys " + e.getMessage());
		}
	}

	protected void clearAndSendKeyToElementDynamicTextbox(WebDriver driver, String locator, String text,
			String... value) {
		try {
			locator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(locator));
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			LogFactory.error("Element don't clear and sendkeys " + e.getMessage());
		}
	}

	protected void clearAndSendKeyPressToElementDynamicTextbox(WebDriver driver, String locator, Keys key,
			String... value) {
		try {
			locator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(locator));
			element.clear();
			element.sendKeys(key);
		} catch (Exception e) {
			LogFactory.error("Element don't clear and sendkeys " + e.getMessage());
		}
	}

	protected void sendKeyToElementDynamicTextbox(WebDriver driver, String locator, String text, String... value) {
		try {
			locator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(locator));
			element.sendKeys(text);
		} catch (Exception e) {
			LogFactory.error("Element don't sendkeys to dynamic textbox " + e.getMessage());
		}
	}

	protected void sendKeyToElementDonotClear(WebDriver driver, String locator, String value) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.sendKeys(value);
		} catch (Exception e) {
			LogFactory.error("Element don't sendkeys textbox " + e.getMessage());
		}
	}

	protected void selectItemInDropdown(WebDriver driver, String locator, String value) {
		try {
			Select element = new Select(driver.findElement(By.xpath(locator)));
			element.selectByVisibleText(value);
		} catch (Exception e) {
			LogFactory.error("Element don't select combobox " + e.getMessage());
		}
	}

	protected void selectItemInDynamicDropdown(WebDriver driver, String locator, String valueDropdown,
			String... value) {
		try {
			locator = String.format(locator, (Object[]) value);
			Select element = new Select(driver.findElement(By.xpath(locator)));
			element.selectByVisibleText(valueDropdown);
		} catch (Exception e) {
			LogFactory.error("Element don't select combobox " + e.getMessage());
		}
	}

	protected void selectItemInDropdownSpecial(WebDriver driver, String locatorDropdown, String locator, String value) {
		try {
			WebElement dropdown = driver.findElement(By.xpath(locatorDropdown));
			dropdown.click();
			List<WebElement> list = driver.findElements(By.xpath(locator));
			for (WebElement element : list) {
				if (element.getText().equalsIgnoreCase(value)) {
					element.click();
					break;
				}
			}
		} catch (Exception e) {
			LogFactory.error("Element don't select combobox special " + e.getMessage());
		}

	}

	protected String getFirstItemSelected(WebDriver driver, String locator) {
		String text = null;
		try {
			Select element = new Select(driver.findElement(By.xpath(locator)));
			return element.getFirstSelectedOption().getText();
		} catch (Exception e) {
			LogFactory.error("Element cannot get First Item Selected " + e.getMessage());
		}
		return text;
	}

	protected String getAtribute(WebDriver driver, String locator, String attribute) {
		String text = null;
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.getAttribute(attribute);
		} catch (Exception e) {
			LogFactory.error("Element cannot get atribute " + e.getMessage());
		}
		return text;
	}

	protected String getTextElement(WebDriver driver, String locator) {
		String text = null;
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.getText();
		} catch (Exception e) {
			LogFactory.error("Element is cann't get text " + e.getMessage());
		}
		return text;
	}

	protected String getTextDynamicElement(WebDriver driver, String locator, String... value) {
		String text = null;
		try {
			locator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(locator));
			return element.getText();
		} catch (Exception e) {
			LogFactory.error("Element is cann't get text " + e.getMessage());
		}
		return text;
	}

	protected int getSize(WebDriver driver, String locator) {
		int size = 0;
		try {
			List<WebElement> element = driver.findElements(By.xpath(locator));
			return element.size();
		} catch (Exception e) {
			LogFactory.error("Element is cann't get size " + e.getMessage());
		}
		return size;
	}

	protected void uncheckTheCheckbox(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			if (element.isSelected())
				element.click();
		} catch (Exception e) {
			LogFactory.error("Element is cann't uncheck checkbox " + e.getMessage());
		}
	}

	protected boolean isControlDisplayed(WebDriver driver, String locator, String... value) {
		boolean result = false;
		try {
			String dynamicLocator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(dynamicLocator));
			return element.isDisplayed();
		} catch (Exception e) {
			LogFactory.error("Element cann't check element displayed " + e.getMessage());
		}
		return result;
	}

	protected boolean isControlDisplayed(WebDriver driver, String locator) {
		boolean result = false;
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			LogFactory.error("Element cann't check element displayed " + e.getMessage());
		}
		return result;
	}

	protected boolean isControlSelected(WebDriver driver, String locator) {
		boolean result = false;
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isSelected();
		} catch (Exception e) {
			LogFactory.error("Element cann't check element selected " + e.getMessage());
		}
		return result;
	}

	protected boolean isControlEnabled(WebDriver driver, String locator) {
		boolean result = false;
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isEnabled();
		} catch (Exception e) {
			LogFactory.error("Element cann't check element enabled " + e.getMessage());
		}
		return result;
	}
	// Alert

	protected void acceptAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			LogFactory.error("Cann't accept alert " + e.getMessage());
		}
	}

	protected void cancelAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			LogFactory.error("Cann't dismiss alert " + e.getMessage());
		}
	}

	protected String getTextAlert(WebDriver driver) {
		String text = null;
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (Exception e) {
			LogFactory.error("Cann't get text alert " + e.getMessage());
		}
		return text;
	}

	protected void sendKeyAlert(WebDriver driver, String value) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(value);
		} catch (Exception e) {
			LogFactory.error("Cann't get sendKeys alert " + e.getMessage());
		}
	}

	// Windows
	protected void switchWindowByID(WebDriver driver, String parent) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String childWindows : allWindows) {
				if (!childWindows.equals(parent)) {
					driver.switchTo().window(childWindows);
					break;
				}
			}
		} catch (Exception e) {
			LogFactory.error("Cann't switch window by ID " + e.getMessage());
		}
	}

	protected void switchWindowByTitle(WebDriver driver, String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String childWindows : allWindows) {
				driver.switchTo().window(childWindows);
				String childTitle = driver.getTitle();
				if (childTitle.equals(title)) {
					break;
				}
			}
		} catch (Exception e) {
			LogFactory.error("Cann't switch window by Title " + e.getMessage());
		}
	}

	// Bonus
	protected boolean closeAllWindowsWithoutParent(WebDriver driver, String parent) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String childWindows : allWindows) {
				if (!childWindows.equals(parent)) {
					driver.switchTo().window(childWindows);
					driver.close();
				}
			}
			driver.switchTo().window(parent);

			if (driver.getWindowHandles().size() == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			LogFactory.error("Cann't close all windows without parent " + e.getMessage());
			return false;
		}
	}

	protected String getWindowParentID(WebDriver driver) {
		String text = null;
		try {
			return driver.getWindowHandle();
		} catch (Exception e) {
			LogFactory.error("Cann't get window parentID " + e.getMessage());
		}
		return text;
	}

	// Iframe
	protected void switchToIframe(WebDriver driver, String locator) {
		try {
			WebElement iframe = driver.findElement(By.xpath(locator));
			driver.switchTo().frame(iframe);
		} catch (Exception e) {
			LogFactory.error("Cann't switch to iframe " + e.getMessage());
		}
	}

	protected void switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			LogFactory.error("Cann't switch to default content " + e.getMessage());
		}
	}

	// Actions
	protected void doubleClick(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.doubleClick(element).perform();
		} catch (Exception e) {
			LogFactory.error("Cann't double click " + e.getMessage());
		}
	}

	protected void hoverMouse(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
		} catch (Exception e) {
			LogFactory.error("Cann't hover mouse " + e.getMessage());
		}
	}

	protected void hoverMouse(WebDriver driver, String locator, String... value) {
		try {
			String dynamicLocator = String.format(locator, (Object[]) value);
			WebElement element = driver.findElement(By.xpath(dynamicLocator));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
		} catch (Exception e) {
			LogFactory.error("Cann't hover mouse " + e.getMessage());
		}
	}

	protected void rightClick(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.contextClick(element).perform();
		} catch (Exception e) {
			LogFactory.error("Cann't right click " + e.getMessage());
		}
	}

	protected void dragAndDrop(WebDriver driver, String locatorFrom, String locatorTarget) {
		try {
			WebElement dragFrom = driver.findElement(By.xpath(locatorFrom));
			WebElement target = driver.findElement(By.xpath(locatorTarget));
			Actions builder = new Actions(driver);
			Action dragAndDropAction = builder.clickAndHold(dragFrom).moveToElement(target).release(target).build();
			dragAndDropAction.perform();
		} catch (Exception e) {
			LogFactory.error("Cann't drag and drop " + e.getMessage());
		}
	}

	// Bonus
	protected void clickAndHold(WebDriver driver, int itemFrom, int itemTarget, String locator) {
		try {
			List<WebElement> listItems = driver.findElements(By.xpath(locator));
			Actions action = new Actions(driver);
			action.clickAndHold(listItems.get(itemFrom)).clickAndHold(listItems.get(itemTarget)).click().perform();
			action.release();
		} catch (Exception e) {
			LogFactory.error("Cann't click and hold " + e.getMessage());
		}
	}

	// Key Press
	protected void keyDownElement(WebDriver driver, String locator, Keys pressKeyDown) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.keyDown(element, pressKeyDown);
		} catch (Exception e) {
			LogFactory.error("Cann't click key Down " + e.getMessage());
		}
	}

	protected void keyUpElement(WebDriver driver, String locator, Keys pressKeyUp) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.keyUp(element, pressKeyUp);
		} catch (Exception e) {
			LogFactory.error("Cann't click key Up " + e.getMessage());
		}
	}

	protected void sendKeyPress(WebDriver driver, String locator, Keys key) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.sendKeys(key);
		} catch (Exception e) {
			LogFactory.error("Cann't sendKeys press " + e.getMessage());
		}
	}

	protected void clearAndSendKeyPress(WebDriver driver, String locator, Keys key) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.clear();
			element.sendKeys(key);
		} catch (Exception e) {
			LogFactory.error("Cann't clear and sendKeys press " + e.getMessage());
		}
	}

	// Upload
	protected void uploadFile(WebDriver driver, String locator, String firePath) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.sendKeys(firePath);
		} catch (Exception e) {
			LogFactory.error("Cann't upload File by sendkeys " + e.getMessage());
		}
	}

	// Javascript
	protected Object executeJavascriptToBrowser(WebDriver driver, String javaSript) {
		Object object = null;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			LogFactory.error("Cann't execute javascript to browser " + e.getMessage());
		}
		return object;
	}

	protected Object executeJavascriptToElement(WebDriver driver, String locator) {
		Object object = null;
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			LogFactory.error("Cann't click by javascript " + e.getMessage());
		}
		return object;
	}

	protected Object executeJavascriptToElement(WebDriver driver, String locator, String... value) {
		Object object = null;
		String dynamicLocator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			LogFactory.error("Cann't click by javascript " + e.getMessage());
		}
		return object;
	}

	protected Object scrollToBottomPage(WebDriver driver) {
		Object object = null;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			LogFactory.error("Cann't scroll to bottom page " + e.getMessage());
		}
		return object;
	}

	protected Object scrollToElement(WebDriver driver, String locator) {
		Object object = null;
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			LogFactory.error("Cann't scroll to element " + e.getMessage());
		}
		return object;
	}

	// Bonus
	protected void highlightElement(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='6px groove red'", element);
		} catch (Exception e) {
			LogFactory.error("Cann't highlight element " + e.getMessage());
		}
	}

	// Bonus
	protected Object removeAttributeInDOM(WebDriver driver, String locator, String attribute) {
		Object object = null;
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			LogFactory.error("Cann't remove attribute in DOM " + e.getMessage());
		}
		return object;
	}

	// Wait
	protected void waitForControlPresence(WebDriver driver, String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			By by = By.xpath(locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			LogFactory.error("Cann't wait for control presence " + e.getMessage());
		}
	}

	protected void waitForControlVisible(WebDriver driver, String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			By by = By.xpath(locator);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			LogFactory.error("Cann't wait for control visible " + e.getMessage());
		}
	}

	protected void waitForIframeVisible(WebDriver driver, String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			By by = By.xpath(locator);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
		} catch (Exception e) {
			LogFactory.error("Cann't wait for iframe visible " + e.getMessage());
		}
	}

	protected void waitForControlVisible(WebDriver driver, String locator, String... value) {
		try {
			String dynamicLocator = String.format(locator, (Object[]) value);
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			By by = By.xpath(dynamicLocator);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			LogFactory.error("Cann't wait for control visible " + e.getMessage());
		}
	}

	protected void waitForControlClickable(WebDriver driver, String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			By by = By.xpath(locator);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			LogFactory.error("Cann't wait for control clickable " + e.getMessage());
		}
	}

	protected void waitForControlInvisible(WebDriver driver, String locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			By by = By.xpath(locator);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			LogFactory.error("Cann't wait for control invisible " + e.getMessage());
		}
	}

	protected void waitForAlertPresence(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeouts);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			LogFactory.error("Cann't wait for alert presence " + e.getMessage());
		}
	}

	protected boolean sortElementAcsending(WebDriver driver, String locator) {
		try {
			List<WebElement> list = driver.findElements(By.xpath(locator));
			int length = list.size();
			for (int i = 0; i < length; i++) {
				if (Integer.parseInt(list.get(i).getText()) <= Integer.parseInt(list.get(i + 1).getText())) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			LogFactory.error("Cann't sort element acsending " + e.getMessage());
			return false;
		}
	}

	protected boolean sortElementDescending(WebDriver driver, String locator) {
		try {
			List<WebElement> list = driver.findElements(By.xpath(locator));
			int length = list.size();
			for (int i = 0; i < length; i++) {
				if (Integer.parseInt(list.get(i).getText()) >= Integer.parseInt(list.get(i + 1).getText())) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			LogFactory.error("Cann't sort element descending " + e.getMessage());
			return false;
		}
	}

	protected void swapElement(WebDriver driver, String locator) {
		try {
			List<WebElement> list = driver.findElements(By.xpath(locator));
			int temp;
			int position1 = Integer.parseInt(list.get(0).getText());
			int position2 = Integer.parseInt(list.get(1).getText());
			temp = position1;
			position1 = position2;
			position2 = temp;
		} catch (Exception e) {
			LogFactory.error("Cann't swap element " + e.getMessage());
		}
	}

	protected boolean verifyEquals(Object actual, Object expected, boolean flag) {
		boolean pass = true;
		if (flag == false) {
			try {
				Assert.assertEquals(actual, expected);
			} catch (Throwable e) {
				pass = false;
				Reporter.getCurrentTestResult().setThrowable(e);
			}
		} else {
			Assert.assertEquals(actual, expected);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return verifyEquals(actual, expected, false);
	}

}
