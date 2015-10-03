package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.person.*;

public class PersonDbRepository {

	private Connection connection;

	public PersonDbRepository() throws SQLException {
		this.connection = WebshopDB.createConnection();
	}

	public Person get(String email) throws SQLException {
		// We could ask for email and pass here. It would be more secure, but it's not as flexible.
		// I will think about this
		String sql = "SELECT * FROM r0376333_r0296118.person WHERE email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		String password = result.getString("password");
		String firstname = result.getString("firstname");
		String lastname = result.getString("lastnaam"); // great job Wouter
		return new Person(email, password, firstname, lastname);
	}

	public List<Person> getAll() throws SQLException {
		ResultSet result = connection.createStatement().executeQuery("SELECT * FROM r0376333_r0296118.person");
		List<Person> list = new ArrayList<>();
		while (result.next()) {
			String email = result.getString("email");
			String password = result.getString("password");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastnaam");
			list.add(new Person(email, password, firstname, lastname));
		}
		return list;
	}

	public void add(Person person) throws SQLException {
		String sql = "INSERT INTO r0376333_r0296118.person (email, password, firstname, lastnaam) VALUES (?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, person.getUserId());
		statement.setString(2, person.getPassword());
		statement.setString(3, person.getFirstName());
		statement.setString(4, person.getLastName());
		statement.execute();
	}

	public void update(Person person) {
		// TODO implement
	}

	public void delete(String personName) {
		// TODO implement
	}
}
