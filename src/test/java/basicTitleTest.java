package Selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait; 
import static org.junit.Assert.*;
import org.openqa.selenium.remote.Augmenter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class basicTitleTest {
	
	//URL to visit
	public String QWE="https://www.facebook.com/";
	
	//Title to check
	public String QWE1="Facebook";
	public String nodeURL = "http://localhost:4444/wd/hub";

	public static void main(String[] args){
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { basicTitleTest.class });
		testng.addListener(tla);
		testng.run();
	}
	
	@Test
	public void testSelenium() throws IOException
	{
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capability.setBrowserName("firefox");
		//capabilities.setPlatform(Platform.Linux);

        WebDriver driver = new RemoteWebDriver(new URL(nodeURL), capability);
        System.out.println("navigating to: " + QWE);
        driver.get(QWE);
        System.out.println("succesfully navigated to: " + QWE);
        
        String actualTitle = driver.getTitle();
        String expectedTitle = QWE1;
	    assertTrue(actualTitle.contains(expectedTitle));
		driver = new Augmenter().augment( driver );
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot Successful");
        FileUtils.copyFile(screenshot, new File("Screenshot.png"));
        System.out.println("Screenshot Saved");
        
        driver.quit();

	}
	
}
