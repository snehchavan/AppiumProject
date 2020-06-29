package appiumPackage;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class EcommerceProject {

	public class LauchAmazon {

		private AndroidDriver<MobileElement> driver;


		@Test
		public void setup() throws MalformedURLException, Exception {

			//Path to AmazonShoppink apk

			File app = new File("C:\\Users\\sbjagtap\\eclipse-workspace\\Pyramid_ECommerceProject\\src\\main\\java", "Amazon_shopping.apk");

				
			//Object creation of desired capability
			DesiredCapabilities cap= new DesiredCapabilities();
			
			
			cap.setCapability("device", "Android");
			cap.setCapability(CapabilityType.VERSION,"10");
			cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
			
			//Approach1: Using real Device
			//Mobile Device: One Plus 7t and Andorid Version 10
			cap.setCapability("deviceName","OnePlus 7T");
			cap.setCapability("platformName","Android");
			
			//Approach 2 : Option to run using Virtual Device
			//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "VirtualDevice1");

			cap.setCapability("app-package", "com.amazon.mShop.android.shopping");
			//set the Launcher activity name of the app
			cap.setCapability ("appActivity","com.amazon.mShop.android.home.HomeActivity");

			cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
			//cap.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 200000);

		
			
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			
			//For validation purpose
			System.out.println("Amazon App is Launched");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
			
			//Login the application with test User
			//Test Data : Mobile Number-8999316678 and Password- snehaljagtap
			try {
			driver.findElement(By.id("com.amazon.mShop.android.shopping:id/sign_in_button")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//android.widget.EditText[@index='1']")).sendKeys("9511756183");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			

			driver.findElement(By.xpath("//android.widget.Button[@index='0']")).click();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//android.widget.EditText[@bounds='[36,418][1044,534]']")).sendKeys("snehaljagtap");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath ("//android.widget.Button[@bounds='[36,746][1044,870]']")).click();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//Navigate to Home page
			System.out.println("You are on home page");
			
			//Search keyword: 65-inch TV

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")).sendKeys("65-inch TV");

			
			String expectedTitle = "Amazon";
			String actualTitle= driver.getTitle();
			
			System.out.println("Title is "+actualTitle);
			
			assertEquals(actualTitle, expectedTitle);
			
			//Validate expected and Actual result
			
			if (expectedTitle.contains("Amazon"))
			{
				System.out.println("Amazon is the title the test Case passed");
			}
			else
			{
				System.out.println("Test Case Failed");
			}
					
			

			Thread.sleep(3000);

			
			}
			catch (StaleElementReferenceException e) {
				
				driver.navigate().refresh();
			}

			// driver.quit();
		}
		
		@AfterTest
		private void closeApp() {

			driver.quit();
	}
} 
	
}


