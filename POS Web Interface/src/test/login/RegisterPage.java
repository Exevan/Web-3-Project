package test.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.PageObject;

public class RegisterPage extends PageObject{

	public RegisterPage(WebDriver driver) {
		super(driver, "/Controller?action=addperson_start");
	}
	
	public void setFirstName(String firstName) {
		WebElement firstNameField = driver.findElement(By.id("firstName"));
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		WebElement lastNameField = driver.findElement(By.id("lastName"));
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
	}
	
	public void setUserId(String userId) {
		WebElement userIdField = driver.findElement(By.id("email"));
		userIdField.clear();
		userIdField.sendKeys(userId);
	}
	
	public void setPassword(String password) {
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	
	public UserOverviewPage register(boolean redirect) {
		WebElement loginButton = driver.findElement(By.id("signUp"));
		loginButton.click();
		if (redirect)
			return new UserOverviewPage(driver);
		else
			return null;
	}
}
