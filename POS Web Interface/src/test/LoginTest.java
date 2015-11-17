package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import db.DBtypes;
import domain.person.Person;
import domain.person.PersonService;
import domain.person.Role;

public class LoginTest {
	private static PersonService persons = new PersonService(DBtypes.LOCALDB, null);

	private String generateRandomEmail(String component) {
		// generate random email adres in order to run test more than once
		int random = (int) (Math.random() * 1000 + 1);
		return random + component;
	}

	@Test
	public void personRetrievedFromDatabase() {
		// test if user can be retrieved from database
		String email = generateRandomEmail("klaas@klaas.be");
		Person klaas = new Person(email, "1234", "Klaas", "Claesens", Role.CUSTOMER);
		persons.addPerson(klaas);

		Person klaasRetrieved = persons.getPerson(email);

		assertTrue(klaasRetrieved.getFirstName() != null);
	}

	@Test
	public void personCorrectRetrievedFromDatabase() {
		// test if all properties of user can be retrieved correctly from database
		String email = generateRandomEmail("klaas@klaas.be");
		Person klaas = new Person(email, "1234", "Klaas", "Claesens", Role.CUSTOMER);
		persons.addPerson(klaas);

		Person klaasRetrieved = persons.getPerson(email);

		assertTrue(klaasRetrieved.getFirstName() != null);
		if (klaasRetrieved.getFirstName() != null) {
			assertTrue(klaasRetrieved.isCorrectPassword("1234"));
			assertTrue(klaasRetrieved.getFirstName().equals("Klaas"));
			assertTrue(klaasRetrieved.getLastName().equals("Claesens"));
		}

	}
	

}
