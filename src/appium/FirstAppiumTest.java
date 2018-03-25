package appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstAppiumTest {

	WebDriver driver;
	DesiredCapabilities dc = new DesiredCapabilities();

	@BeforeClass
	public void beforeClass() {
		dc.setCapability("BROWSER_NAME", "Android");
		dc.setCapability("VERSION", "6.0");
		dc.setCapability("deviceName", "And-1");
		dc.setCapability("platformName", "Android");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
	}

	@Test
	public void TC_01_Calculator() throws MalformedURLException {

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.findElement(By.name("1")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("2")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("3")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("4")).click();
		driver.findElement(By.name("=")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
