package test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserOverviewPage {
	private WebDriver driver;
	private static final String URL = "http://localhost:8080/POS_Web_Interface";

	public UserOverviewPage(WebDriver driver) {
		this.driver = driver;
		driver.get(URL + "/Controller?action=personoverview");
	}
	
	public boolean isCurrentPage() {
		String title = driver.getTitle();
		return title.equals("Overview");
	}

	public boolean hasRow(String string) {
		ArrayList<WebElement> tableRows=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
		boolean found=false;
		for (WebElement tableRow:tableRows) {
			String email = tableRow.getText();
			if (email.equals(string)) {
				return true;
			}
		}
		return false;
	}
	
}
