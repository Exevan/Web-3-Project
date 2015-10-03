package domain.person;

import java.util.List;

import db.*;

public class PersonService {
	private PersonDbRepository personDbRepository;

	public PersonService(WebshopDB db){
		personDbRepository = new PersonDbRepository(db);
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
