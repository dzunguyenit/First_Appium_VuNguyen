package appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Dialer {

	WebDriver driver;
	DesiredCapabilities desireCapabilities = new DesiredCapabilities();

	@Test
	public void TC_01_Calculator() throws MalformedURLException {
		desireCapabilities.setCapability("BROWSER_NAME", "Android");
		desireCapabilities.setCapability("VERSION", "6.0");
		desireCapabilities.setCapability("deviceName", "And-1");
		desireCapabilities.setCapability("platformName", "Android");
		desireCapabilities.setCapability("appPackage", "com.android.dialer");
		desireCapabilities.setCapability("appActivity", "com.android.dialer.DialtactsActivity");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), desireCapabilities);
	}

}
