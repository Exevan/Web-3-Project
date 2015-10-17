package domain.person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import db.*;

public class PersonService {
	private PersonDbRepository personDbRepository;

	public PersonService(String username, String password) throws SQLException{
		personDbRepository = new PersonDbRepository(username, password);
	}
	
	public Person getPerson(String personId) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) throws SQLException {
		getPersonRepository().add(person);
	}

	public void updatePerson(Person person) throws SQLException {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) throws SQLException {
		getPersonRepository().delete(id);
	}

	private PersonDbRepository getPersonRepository() {
		return personDbRepository;
	}
}
