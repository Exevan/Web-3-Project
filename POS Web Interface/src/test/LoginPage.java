package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private WebDriver driver;
	private static final String URL = "http://localhost:8080/POS_Web_Interface";
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get(URL + "/Controller?action=login_start");
	}
	
	public void setUserId(String userId) {
		WebElement userIdField = driver.findElement(By.id("username"));
		userIdField.clear();
		userIdField.sendKeys(userId);
	}
	
	public void setPassword(String password) {
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	
	public void login() {
		WebElement loginButton = driver.findElement(By.id("login"));
		loginButton.click();
	}
}
