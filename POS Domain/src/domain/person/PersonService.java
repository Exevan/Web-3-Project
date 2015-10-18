package domain.person;

import java.util.List;

import db.PersonDbRepository;

public class PersonService {
	private PersonDbRepository personDbRepository;

	public PersonService() {
		personDbRepository = new PersonDbRepository();
	}

	public boolean establishConnection(String username, String password) {
		return personDbRepository.establishConnection(username, password);
	}

	public Person getPerson(String personId) {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
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
