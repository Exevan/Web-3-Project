package domain.person;

import java.sql.SQLException;
import java.util.List;

import db.*;

public class PersonService {
	private PersonDbRepository personDbRepository;

	public PersonService() throws SQLException{
		personDbRepository = new PersonDbRepository();
	}
	
	public Person getPerson(String personId) throws SQLException {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() throws SQLException {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) throws SQLException {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}

	private PersonDbRepository getPersonRepository() {
		return personDbRepository;
	}
}
