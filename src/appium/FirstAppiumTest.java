package appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirstAppiumTest {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("BROWSER_NAME", "Android");
		dc.setCapability("VERSION","6.0");
		dc.setCapability("deviceName","And-1");
		dc.setCapability("platformName", "Android");

		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		System.out.println(driver.toString());
		driver.findElement(By.name("4")).click();
		driver.findElement(By.name("+")).click();
		driver.findElement(By.name("6")).click();
		driver.findElement(By.name("=")).click();
		driver.quit();
	}

}
