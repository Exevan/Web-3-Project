package db;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domain.person.*;

public class PersonDbRepository {

	private Statement statement;

	public PersonDbRepository() throws SQLException {
		this.statement = WebshopDB.createStatement();
	}

	public Person get(String productName) {
		// TODO implement
		return null;
	}

	public List<Person> getAll() {
		// TODO implement
		return null;
	}

	public void add(Person person) {
		// TODO implement
	}

	public void update(Person person) {
		// TODO implement
	}

	public void delete(String personName) {
		// TODO implement
	}
}
