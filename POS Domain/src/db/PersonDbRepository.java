package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain.person.*;

public class PersonDbRepository {

	private Connection connection;

	public PersonDbRepository() throws SQLException {
		this.connection = WebshopDB.createConnection();
	}

	public Person get(String email) throws SQLException {
		String sql = "SELECT * FROM r0376333_r0296118.person WHERE email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		String password = result.getString("password");
		String firstname = result.getString("firstname");
		String lastname = result.getString("lastnaam");
		return new Person(email, password, firstname, lastname);
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
