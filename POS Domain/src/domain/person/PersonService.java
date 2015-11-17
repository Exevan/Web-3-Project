package domain.person;

import java.util.List;
import java.util.Properties;

import db.DBtypes;
import db.person.PersonDbFactory;
import db.person.PersonDbRepository;

public class PersonService {
	private PersonDbRepository personDbRepository;

	public PersonService(DBtypes type, Properties properties) {
		PersonDbFactory factory = PersonDbFactory.getPersonDbFactory();
		personDbRepository = factory.createPersonDb(type, properties);
	}

	public Person getPerson(String personId) {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public boolean hasPerson(Person person) {
		return getPerson(person.getUserId()) != null;
	}

	public boolean canHaveAsPerson(Person person) {
		if(hasPerson(person))
			throw new IllegalArgumentException("User already exists");
		return true;
	}

	public void addPerson(Person person) {
		if(canHaveAsPerson(person))
			getPersonRepository().add(person);
	}

	public void updatePerson(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}

	public Person getAuthenticatedUser(String personId, String password) {		
		Person person;
		person = getPerson(personId);
		return (person != null && person.isCorrectPassword(password)) ? person : null;
	}

	private PersonDbRepository getPersonRepository() {
		return personDbRepository;
	}
}
