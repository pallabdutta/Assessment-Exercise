package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfigRead;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait wait;
	public ConfigRead conf;

	public BaseTest() {
		// create an object of ConfigRead
		try {
			conf = new ConfigRead();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@BeforeMethod
	public void launchApp() throws Exception {
		System.out.println("launching....");
		// getting driver instance
		driver = WebDriverManager.chromedriver().create();
		// getting instance of WebDriverWait
		wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		// reading the url of the app
		String url = conf.get_ServiceNow_App();
		// navigate to service now instance
		driver.get(url);
	}

	@AfterMethod
	public void closeApp() throws Exception {
		Thread.sleep(5000);
		driver.close();
		System.out.println("closing....");
	}

}
