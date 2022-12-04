package exercise;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseTest;

public class TC_01_ServiceNow_Login_Positive_Negative extends BaseTest {

	// constant texts to verify or enter
	final String expected_title = "Log in | ServiceNow";
	final String expected_username_label = "User name";
	final String expected_password_label = "Password";
	final String link_forgotPassword = "Forgot Password ?";
	final String expected_homePage_title = "ServiceNow";
	final String expected_errMsg_invalidUsername = "Invalid input in user name!";
	final String expected_errMsg_invalidPassword = "User name or password invalid";
	final String username = conf.getUsername();
	final String password = conf.getPassword();

	// page element locators
	By xpath_username_label = By.xpath("//label[@for='user_name']");
	By xpath_username_field = By.xpath("//input[@id='user_name']");
	By xpath_password_label = By.xpath("//label[@for='user_password']");
	By xpath_password_field = By.xpath("//input[@id='user_password']");
	By linkText_forgotPassword_link = By.linkText(link_forgotPassword);
	By xpath_login_button = By.xpath("//button[@id='sysverb_login']");
	By xpath_txt_AppEngineStudio = By.xpath("//div[text()='App Engine Studio']");
	By xpath_errMsg_invalidUsername = By.xpath("//div[contains(@class,'outputmsg_error')]/div");
	By xpath_invalidErrMsg_close = By.xpath("//button[contains(@class,'close')]");
	By xpath_errMsg_invalidPassword = By.xpath("//div[contains(@class,'dp-invalid-login')]/span");

	@Test(priority = 0)
	public void verify_login_positive_feature() throws Exception {
		// verify title is ServiceNow
		Reporter.log("verify title is ServiceNow");
		String app_title = driver.getTitle();
		Assert.assertEquals(app_title, expected_title);

		// verify username text field has label of User name
		Reporter.log("verify username text field has label of User name");
		String username_label = driver.findElement(xpath_username_label).getText();
		Assert.assertEquals(username_label, expected_username_label);

		// enter username
		Reporter.log("enter username");
		driver.findElement(xpath_username_field).sendKeys(username);

		// verify password text field has label of Password
		Reporter.log("verify password text field has label of Password");
		String password_label = driver.findElement(xpath_password_label).getText();
		Assert.assertEquals(password_label, expected_password_label);

		// enter password (instance password)
		Reporter.log("enter password");
		driver.findElement(xpath_password_field).sendKeys(password);

		// verify forgot password is visible to the user
		Reporter.log("verify forgot password is visible to the user");
		WebElement link_forgotPassword = driver.findElement(linkText_forgotPassword_link);
		Assert.assertTrue(link_forgotPassword.isDisplayed());

		// verify user can see the login button
		Reporter.log("verify user can see the login button");
		WebElement button_login = driver.findElement(xpath_login_button);
		Assert.assertTrue(button_login.isDisplayed());

		// click on login button
		wait.until(ExpectedConditions.elementToBeClickable(button_login));
		button_login.click();
		Thread.sleep(15000);

		// verify the title as ServiceNow
		String homePage_title = driver.getTitle();
		Reporter.log("ServiceNow HomePage Title >>> "+ homePage_title);
		Assert.assertEquals(homePage_title, expected_homePage_title);
	}

	@Test(priority = 1)
	public void verify_login_negative_feature() throws Exception {
		// verify title is ServiceNow
		Reporter.log("verify title is ServiceNow");
		String app_title = driver.getTitle();
		Assert.assertEquals(app_title, expected_title);

		// click on login button
		Reporter.log("click on login button");
		wait.until(ExpectedConditions.presenceOfElementLocated(xpath_login_button));
		WebElement button_login = driver.findElement(xpath_login_button);
		button_login.click();

		// verify the error message as Invalid input in user name!
		wait.until(ExpectedConditions.presenceOfElementLocated(xpath_errMsg_invalidUsername));
		String errMsg_invalid_username = driver.findElement(xpath_errMsg_invalidUsername).getText();
		Assert.assertEquals(errMsg_invalid_username, expected_errMsg_invalidUsername);

		// close the error message
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(xpath_invalidErrMsg_close)));
		driver.findElement(xpath_invalidErrMsg_close).click();

		// enter username
		Reporter.log("enter username");
		wait.until(ExpectedConditions.presenceOfElementLocated(xpath_username_field));
		driver.findElement(xpath_username_field).sendKeys(username);

		// click on login button
		Reporter.log("click on login button");
		WebElement btn_login = driver.findElement(xpath_login_button);
		btn_login.click();

		// Verify the error message contains User name or password invalid
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(xpath_errMsg_invalidPassword)));
		String errMsg_invalid_password = driver.findElement(xpath_errMsg_invalidPassword).getText();
		Assert.assertTrue(errMsg_invalid_password.contains(expected_errMsg_invalidPassword));
	}

}
