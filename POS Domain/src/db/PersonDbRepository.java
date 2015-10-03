package db;

import java.util.List;

import domain.person.*;

public class PersonDbRepository {
	
	private WebshopDB db;

	public PersonDbRepository(WebshopDB db) {
		this.db = db;
	}

	public Person get(String productName) {
		return null;
	}

	public List<Person> getAll() {
		return null;
	}

	public void add(Person person) {

	}

	public void update(Person person) {

	}
	
	public void delete(String personName) {
		
	}
}
