package appium;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class Contacts extends AbstractPage {

	WebDriver driver;
	DesiredCapabilities desireCapabilities = new DesiredCapabilities();

	@BeforeClass
	public void beforeClass() {
		setBrowserName(desireCapabilities, "BROWSER_NAME", "Android");
		setVersion(desireCapabilities, "VERSION", "6.0");
		setDevice(desireCapabilities, "deviceName", "And-1");
		setPlatform(desireCapabilities, "platformName", "Android");
		setPackage(desireCapabilities, "appPackage", "com.android.contacts");
		setActivity(desireCapabilities, "appActivity", "com.android.contacts.activities.PeopleActivity");
	}

	@Test
	public void TC_01_Contacts() throws MalformedURLException, Exception {

		driver = newServerAppium(desireCapabilities, driver, "http://127.0.0.1:4723/wd/hub");

		waitForControlVisible(driver, "//*[@text='Create a new contact']");
		clickToElement(driver, "//*[@text='Create a new contact']");

		waitForControlVisible(driver, "//*[@resource-id='com.android.contacts:id/expansion_view']");
		clickToElement(driver, "//*[@resource-id='com.android.contacts:id/expansion_view']");

		waitForControlVisible(driver, "//*[@text='Name prefix']");
		sendKeyToElement(driver, "//*[@text='Name prefix']", "Name prefix");

		waitForControlVisible(driver, "//*[@text='First name']");
		sendKeyToElement(driver, "//*[@text='First name']", "First name");

		waitForControlVisible(driver, "//*[@text='Middle name']");
		sendKeyToElement(driver, "//*[@text='Middle name']", "Middle name");

		waitForControlVisible(driver, "//*[@text='Last name']");
		sendKeyToElement(driver, "//*[@text='Last name']", "Last name");

		waitForControlVisible(driver, "//*[@text='Name suffix']");
		sendKeyToElement(driver, "//*[@text='Name suffix']", "Name suffix");

		waitForControlVisible(driver, "//*[@text='Phone']");
		sendKeyToElement(driver, "//*[@text='Phone']", "1234567890");

		// selectItemInDropdown(driver,
		// "//android.view.View[@class='android.widget.Spinner']", "Car");

		// Select select = new Select(
		//
		// driver.findElement(By.xpath("//android.view.View[@class='android.widget.Spinner']"));
		// select.selectByVisibleText("Car");
		// driver.findElement(By.name("Email")).sendKeys("12345@gmail.com");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
