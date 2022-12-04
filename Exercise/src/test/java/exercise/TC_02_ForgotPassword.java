package exercise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseTest;

public class TC_02_ForgotPassword extends BaseTest {

	// expected values
	String[] expected_listOf_tabs = { "Identify", "Verify", "Reset" };
	final int number_of_tabs = 3;
	final String username = "admin";

	// page element locators
	By xpath_forgotPassword_link = By.xpath("//div[contains(@class,'login-card-footer')]/a");
	By xpath_forgotPassword_tabs = By.xpath("//ol[@class='login-forgot-password-steps']/li");
	By xpath_username_txtField = By.xpath("//input[contains(@aria-label,'Enter your User name')]");
	By xpath_next_button = By.xpath("//button[@id='sysverb_pwd_reset']");
	By xpath_error_messsage = By.xpath("//span[@id='retype_captcha']");
	
	@Test
	public void verify_forgotPassword_feature() {

		// user should see the forgot password
		Reporter.log("user should see the forgot password");
		WebElement link_forgotPassword = driver.findElement(xpath_forgotPassword_link);
		boolean forgotPassword_link_visibility = link_forgotPassword.isDisplayed();
		Assert.assertTrue(forgotPassword_link_visibility);

		// click the forgot password
		Reporter.log("click the forgot password");
		wait.until(ExpectedConditions.elementToBeClickable(link_forgotPassword));
		link_forgotPassword.click();

		// user should see three tabs as Identify, Verify & Reset
		Reporter.log("user should see three tabs as Identify, Verify & Reset");
		List<WebElement> tabs_forgotPassword = driver.findElements(xpath_forgotPassword_tabs);
		wait.until(ExpectedConditions.visibilityOfAllElements(tabs_forgotPassword));

		for (int index = 0; index < number_of_tabs; index++) {
			String actual_tab_name = tabs_forgotPassword.get(index).getText();
			String expected_tab_name = expected_listOf_tabs[index];
			Assert.assertEquals(actual_tab_name, expected_tab_name);
		}

		// user should see the username label and text field
		Reporter.log("user should see the username label and text field");
		WebElement textField_username = driver.findElement(xpath_username_txtField);
		boolean username_textField_visibility = textField_username.isDisplayed();
		Assert.assertTrue(username_textField_visibility);

		// user should see the next button
		Reporter.log("user should see the next button");
		WebElement button_next = driver.findElement(xpath_next_button);
		boolean next_button_visibility = button_next.isDisplayed();
		Assert.assertTrue(next_button_visibility);

		// enter username
		Reporter.log("enter username");
		driver.findElement(xpath_username_txtField).sendKeys(username);
		
		// click on the next button
		Reporter.log("click on the next button");
		wait.until(ExpectedConditions.elementToBeClickable(xpath_next_button));
		WebElement btn_next = driver.findElement(xpath_next_button);
		btn_next.click();
		
		// verify error message Characters do not match, try again
		Reporter.log("verify error message Characters do not match, try again");
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath_error_messsage));
		WebElement message_error = driver.findElement(xpath_error_messsage);
		boolean error_message_visibility = message_error.isDisplayed();
		Assert.assertTrue(error_message_visibility);

	}

}
