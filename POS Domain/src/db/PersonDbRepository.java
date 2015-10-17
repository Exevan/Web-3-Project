package db;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.person.*;

public class PersonDbRepository {

	private Connection connection;

	private static final String TABLE_NAME = "r0376333_r0296118.person";
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LASTNAME_FIELD = "lastnaam"; // great job Wouter
	private static final String SALT_FIELD = "salt";

	public PersonDbRepository(String username, String password)
			throws SQLException {
		this.connection = WebshopDB.createConnection(username, password);
	}

	public Person get(String email) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		// We could ask for email and pass here. It would be more secure, but
		// it's not as flexible.
		// I will think about this
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD
				+ " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			String password = result.getString(PASSWORD_FIELD);
			String firstname = result.getString(FIRSTNAME_FIELD);
			String lastname = result.getString(LASTNAME_FIELD);
			String salt = result.getString(SALT_FIELD);
			boolean isHashed = true;
			return new Person(email, password, firstname, lastname, salt.getBytes(), isHashed);
		}
		return null;
	}

	public List<Person> getAll() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		ResultSet result = connection.createStatement().executeQuery(
				"SELECT * FROM " + TABLE_NAME);
		List<Person> list = new ArrayList<>();
		while (result.next()) {
			String email = result.getString(EMAIL_FIELD);
			String password = result.getString(PASSWORD_FIELD);
			String firstname = result.getString(FIRSTNAME_FIELD);
			String lastname = result.getString(LASTNAME_FIELD);
			String salt = result.getString(SALT_FIELD);
			boolean isHashed = true;
			list.add(new Person(email, password, firstname, lastname, salt.getBytes(), isHashed));
		}
		return list;
	}

	public void add(Person person) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (" + EMAIL_FIELD + ", "
				+ PASSWORD_FIELD + ", " + FIRSTNAME_FIELD + ", "
				+ LASTNAME_FIELD + ", " + SALT_FIELD + ") VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, person.getUserId());
		statement.setString(2, person.getPassword());
		statement.setString(3, person.getFirstName());
		statement.setString(4, person.getLastName());
		statement.setString(5, new String(person.getSalt()));
		statement.execute();
	}

	public void update(Person person) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " SET " + PASSWORD_FIELD
				+ " = ?, " + FIRSTNAME_FIELD + " = ?, " + LASTNAME_FIELD
				+ " = ?, " + SALT_FIELD + " = ? WHERE " + EMAIL_FIELD + " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, person.getPassword());
		statement.setString(2, person.getFirstName());
		statement.setString(3, person.getLastName());
		statement.setString(4, person.getUserId());
		statement.setString(5, new String(person.getSalt()));
		statement.execute();
	}

	public void delete(String email) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD
				+ " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.execute();
	}
}
