package test.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import test.LoginPage;

public class RegisterTest {
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

	private String generateRandomEmail(String component) {
		// generate random email address in order to run test more than once
		int random = (int) (Math.random() * 1000 + 1);
		return random + component;
	}

	private UserOverviewPage submitForm(RegisterPage registerPage,
			String firstName, String lastName, String email, String password, boolean redirect) {
		registerPage.setFirstName(firstName);
		registerPage.setLastName(lastName);
		registerPage.setUserId(email);
		registerPage.setPassword(password);

		return registerPage.register(redirect);
	}

	@Test
	public void testRegisterCorrect() {
		String randomEmail = generateRandomEmail("jan.janssens@hotmail.com");
		UserOverviewPage userOverviewPage = submitForm(
				new RegisterPage(driver), "Jan", "Janssens", randomEmail, "1234", true);

		assertTrue(userOverviewPage.isCurrentPage());

		assertTrue(userOverviewPage.hasRow(randomEmail + " Jan Janssens CUSTOMER"));
	}

	@Test
	public void testRegisterFirstNameEmpty() {
		RegisterPage registerPage = new RegisterPage(driver);
		submitForm(registerPage, "", "Janssens", "jan.janssens@hotmail.com", "1234", false);
		
		String title = driver.getTitle();
		assertEquals("Sign Up", title);

		WebElement errorMsg = driver.findElement(By
				.cssSelector("div.alert-danger ul li"));
		assertEquals("No first name given", errorMsg.getText());

		WebElement fieldFirstName = driver.findElement(By.id("firstName"));
		assertEquals("", fieldFirstName.getAttribute("value"));

		WebElement fieldLastName = driver.findElement(By.id("lastName"));
		assertEquals("Janssens", fieldLastName.getAttribute("value"));

		WebElement fieldEmail = driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
	}

	@Test
	public void testRegisterLastNameEmpty() {
		RegisterPage registerPage = new RegisterPage(driver);
		submitForm(registerPage, "Jan", "", "jan.janssens@hotmail.com", "1234", false);

		String title = driver.getTitle();
		assertEquals("Sign Up", title);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No last name given", errorMsg.getText());

		WebElement fieldFirstName = driver.findElement(By.id("firstName"));
		assertEquals("Jan", fieldFirstName.getAttribute("value"));

		WebElement fieldLastName = driver.findElement(By.id("lastName"));
		assertEquals("", fieldLastName.getAttribute("value"));

		WebElement fieldEmail = driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
	}

	@Test
	public void testRegisterEmailEmpty() {
		RegisterPage registerPage = new RegisterPage(driver);
		submitForm(registerPage, "Jan", "Janssens", "", "1234", false);

		String title = driver.getTitle();
		assertEquals("Sign Up", title);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No id given", errorMsg.getText());

		WebElement fieldFirstName = driver.findElement(By.id("firstName"));
		assertEquals("Jan", fieldFirstName.getAttribute("value"));

		WebElement fieldLastName = driver.findElement(By.id("lastName"));
		assertEquals("Janssens", fieldLastName.getAttribute("value"));

		WebElement fieldEmail = driver.findElement(By.id("email"));
		assertEquals("", fieldEmail.getAttribute("value"));
	}

	@Test
	public void testRegisterPasswordEmpty() {
		RegisterPage registerPage = new RegisterPage(driver);
		submitForm(registerPage, "Jan", "Janssens", "jan.janssens@hotmail.com", "", false);

		String title = driver.getTitle();
		assertEquals("Sign Up", title);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No password given", errorMsg.getText());

		WebElement fieldFirstName = driver.findElement(By.id("firstName"));
		assertEquals("Jan", fieldFirstName.getAttribute("value"));

		WebElement fieldLastName = driver.findElement(By.id("lastName"));
		assertEquals("Janssens", fieldLastName.getAttribute("value"));

		WebElement fieldEmail = driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com", fieldEmail.getAttribute("value"));
	}

	@Test
	public void testRegisterUserAlreadyExists() {
		String emailRandom = generateRandomEmail("pieter.pieters@hotmail.com");
		RegisterPage registerPage = new RegisterPage(driver);
		submitForm(registerPage, "Pieter", "Pieters", emailRandom, "1234", false);

		registerPage = new RegisterPage(driver);
		submitForm(registerPage, "Pieter", "Pieters", emailRandom, "1234", false);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("User already exists", errorMsg.getText());

		WebElement fieldFirstName = driver.findElement(By.id("firstName"));
		assertEquals("Pieter", fieldFirstName.getAttribute("value"));

		WebElement fieldLastName = driver.findElement(By.id("lastName"));
		assertEquals("Pieters", fieldLastName.getAttribute("value"));

		WebElement fieldEmail = driver.findElement(By.id("email"));
		assertEquals(emailRandom, fieldEmail.getAttribute("value"));
	}

}
