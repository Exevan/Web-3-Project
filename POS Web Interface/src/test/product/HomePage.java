package test.product;

import org.openqa.selenium.WebDriver;

import test.PageObject;

public class HomePage extends PageObject{

	public HomePage(WebDriver driver) {
		super(driver, "/Controller?action=home");
	}
	
	public boolean isCurrentPage() {
		String title = driver.getTitle();
		return title.equals("Home");
	}
}
