package test.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.PageObject;

public class AddProductPage extends PageObject{

	public AddProductPage(WebDriver driver) {
		super(driver, "/Controller?action=addproduct_start");
	}
	
	public boolean isCurrentPage() {
		return driver.getTitle().equals("Add Product");
	}

	public void setId(String id) {
		WebElement firstNameField = driver.findElement(By.id("id"));
		firstNameField.clear();
		firstNameField.sendKeys(id);
	}

	public void setName(String name) {
		WebElement firstNameField = driver.findElement(By.id("name"));
		firstNameField.clear();
		firstNameField.sendKeys(name);
	}

	public void setDescription(String description) {
		WebElement firstNameField = driver.findElement(By.id("desc"));
		firstNameField.clear();
		firstNameField.sendKeys(description);
	}

	public void setPrice(String price) {
		WebElement firstNameField = driver.findElement(By.id("price"));
		firstNameField.clear();
		firstNameField.sendKeys(price);
	}

	public HomePage add(boolean redirect) {
		WebElement addButton = driver.findElement(By.id("add"));
		addButton.click();
		if (redirect)
			return new HomePage(driver);
		else
			return null;
	}

	public boolean hasError(String string) {
		WebElement errorMsg = driver.findElement(By
				.cssSelector("div.alert-danger ul li"));
		return string.equals(errorMsg.getText());
	}

	public boolean hasInIdField(String string) {
		WebElement idField = driver.findElement(By.id("id"));
		return string.equals(idField.getAttribute("value"));
	}

	public boolean hasInNameField(String string) {
		WebElement nameField = driver.findElement(By.id("name"));
		return string.equals(nameField.getAttribute("value"));
	}

	public boolean hasInDescField(String string) {
		WebElement descriptionField = driver.findElement(By.id("desc"));
		return string.equals(descriptionField.getAttribute("value"));
	}

	public boolean hasInPriceField(String string) {
		WebElement priceField = driver.findElement(By.id("price"));
		return string.equals(priceField.getAttribute("value"));
	}

}
