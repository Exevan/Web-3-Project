package test.login;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.PageObject;

public class UserOverviewPage extends PageObject{

	public UserOverviewPage(WebDriver driver) {
		super(driver, "/Controller?action=personoverview");
	}
	
	public boolean isCurrentPage() {
		String title = driver.getTitle();
		return title.equals("Overview");
	}

	public boolean hasRow(String string) {
		ArrayList<WebElement> tableRows=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
		for (WebElement tableRow:tableRows) {
			String row = tableRow.getText();
			if (row.equals(string)) {
				return true;
			}
		}
		return false;
	}
	
}
