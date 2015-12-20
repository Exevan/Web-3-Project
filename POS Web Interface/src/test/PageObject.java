package test;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {
	protected WebDriver driver;
	private static final String URL = "http://localhost:8080/POS_Web_Interface";

	public PageObject(WebDriver driver, String path) {
		this.driver = driver;
		this.driver.get(URL + path);
	}

}
