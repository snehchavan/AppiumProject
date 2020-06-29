package appiumPackage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class Appium1 {

	public static void main(String[] args) throws MalformedURLException, Exception {
		
		File f= new File("src/main/java");
		File fs=new File(f, "Amazon_shopping.apk");

		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "VirtualDevice1");
		cap.setCapability(MobileCapabilityType.APP,fs.getAbsolutePath());
		
	
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
	
		//Click on Preferences
		
		driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		
		//Select WiFi Checkbox
		driver.findElementById("android:id/checkbox").click();
		
		//Click on WiFi Settings
		driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
		//Send Keys Hello to WIFI settings
		 driver.findElementById("android:id/edit").sendKeys("Hello");
		 
		// driver.findElementById("android:id/edit").getAttribute("text").contains("Hello");
		 
		if(driver.findElementById("android:id/edit").getAttribute("text").equalsIgnoreCase("Hello"))
		{
			System.out.println("OK");
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		}
		else
		{
			System.out.println("Cancel");
			driver.findElementByXPath("//android.widget.Button[@text='CANCEL']").click();
		}
		
	
		
	}

}
