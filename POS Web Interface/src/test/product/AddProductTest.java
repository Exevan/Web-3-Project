package test.product;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import test.LoginPage;

public class AddProductTest {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		login();
	}

	@After
	public void clean() {
		driver.quit();
	}

	private void login() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserId("admin@ucll.be");
		loginPage.setPassword("t");
		loginPage.login();
	}

	private HomePage submitForm(AddProductPage addProductPage, String id,
			String name, String description, String price, boolean redirect) {
		addProductPage.setId(id);
		addProductPage.setName(name);
		addProductPage.setDescription(description);
		addProductPage.setPrice(price);

		return addProductPage.add(redirect);
	}

	@Test
	public void testAddProductIdEmpty() {
		AddProductPage addProductPage = new AddProductPage(driver);
		submitForm(addProductPage, "", "Thing", "This is a thing", "9000", false);

		assertTrue(addProductPage.isCurrentPage());
		assertTrue(addProductPage.hasError("No id given"));

		assertTrue(addProductPage.hasInIdField(""));
		assertTrue(addProductPage.hasInNameField("Thing"));
		assertTrue(addProductPage.hasInDescField("This is a thing"));
		assertTrue(addProductPage.hasInPriceField("9000.0"));
	}

	@Test
	public void testAddProductNameEmpty() {
		AddProductPage addProductPage = new AddProductPage(driver);
		submitForm(addProductPage, "1", "", "This is a thing", "9000", false);

		assertTrue(addProductPage.isCurrentPage());
		assertTrue(addProductPage.hasError("No name given"));

		assertTrue(addProductPage.hasInIdField("1"));
		assertTrue(addProductPage.hasInNameField(""));
		assertTrue(addProductPage.hasInDescField("This is a thing"));
		assertTrue(addProductPage.hasInPriceField("9000.0"));
	}

	@Test
	public void testAddProductDescriptionEmpty() {
		AddProductPage addProductPage = new AddProductPage(driver);
		submitForm(addProductPage, "1", "Thing", "", "9000", false);

		assertTrue(addProductPage.isCurrentPage());
		assertTrue(addProductPage.hasError("No description given"));

		assertTrue(addProductPage.hasInIdField("1"));
		assertTrue(addProductPage.hasInNameField("Thing"));
		assertTrue(addProductPage.hasInDescField(""));
		assertTrue(addProductPage.hasInPriceField("9000.0"));
	}

	@Test
	public void testAddProductPriceEmpty() {
		AddProductPage addProductPage = new AddProductPage(driver);
		submitForm(addProductPage, "1", "Thing", "This is a thing", "", false);

		assertTrue(addProductPage.isCurrentPage());
		assertTrue(addProductPage.hasError("No price given"));

		assertTrue(addProductPage.hasInIdField("1"));
		assertTrue(addProductPage.hasInNameField("Thing"));
		assertTrue(addProductPage.hasInDescField("This is a thing"));
		assertTrue(addProductPage.hasInPriceField(""));
	}

	@Test
	public void testAddProductWithProperFieldsAddsProduct() {
		AddProductPage addProductPage = new AddProductPage(driver);
		HomePage homepage = submitForm(addProductPage, "3", "Thing",
				"This is a thing", "9000", true);

		assertTrue(homepage.isCurrentPage());

		ProductOverviewPage overviewPage = new ProductOverviewPage(driver);
		assertTrue(overviewPage.hasRow("3 Thing This is a thing 9000.0"));
	}
}
