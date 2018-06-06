package appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Calculator {

	WebDriver driver;
	DesiredCapabilities desireCapabilities = new DesiredCapabilities();

	@BeforeClass
	public void beforeClass() {
		desireCapabilities.setCapability("BROWSER_NAME", "Android");
		desireCapabilities.setCapability("VERSION", "6.0");
		desireCapabilities.setCapability("deviceName", "And-1");
		desireCapabilities.setCapability("platformName", "Android");
		desireCapabilities.setCapability("appPackage", "com.android.calculator2");
		desireCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
	}

	@Test
	public void TC_01_Calculator() throws MalformedURLException, Exception {

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), desireCapabilities);
		Thread.sleep(1000);
		driver.findElement(By.name("1")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("2")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("3")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("4")).click();
		driver.findElement(By.name("=")).click();
		String result = driver.findElement(By.name("7")).getText();
		Assert.assertEquals(result, "7");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
