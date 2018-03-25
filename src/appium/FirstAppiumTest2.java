package appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class FirstAppiumTest2 {

	WebDriver driver;
	DesiredCapabilities dc = new DesiredCapabilities();

	@Test
	public void TC_01_Calculator() throws MalformedURLException {
		dc.setCapability("BROWSER_NAME", "Android");
		dc.setCapability("VERSION", "6.0");
		dc.setCapability("deviceName", "And-1");
		dc.setCapability("platformName", "Android");
		dc.setCapability("appPackage", "com.android.calendar");
		dc.setCapability("appActivity", "com.android.calendar.AllInOneActivity");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
	}

}
