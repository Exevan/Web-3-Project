package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.person.Person;

public class PersonDbRepository {

	private Connection connection;

	private static final String TABLE_NAME = "r0376333_r0296118.person";
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String SALT_FIELD = "salt";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LASTNAME_FIELD = "lastnaam"; // great job Wouter
															// suck it Milan
	
	public boolean establishConnection(String username, String password) {		
		return ((this.connection = WebshopDB.createConnection(username, password)) == null) ? false : true;
		// hele vieze oneliner
	}

	public Person get(String email) {
		// We could ask for email and pass here. It would be more secure, but it's not as flexible.
		// I will think about this
		try {
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD
					+ " = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			result.next();
			String password = result.getString(PASSWORD_FIELD);
			byte[] salt = result.getBytes(SALT_FIELD);
			String firstname = result.getString(FIRSTNAME_FIELD);
			String lastname = result.getString(LASTNAME_FIELD);
			return new Person(email, password, salt, firstname, lastname);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Person> getAll() {
		try {
			ResultSet result = connection.createStatement().executeQuery(
					"SELECT * FROM " + TABLE_NAME);
			List<Person> list = new ArrayList<>();
			while (result.next()) {
				String email = result.getString(EMAIL_FIELD);
				String password = result.getString(PASSWORD_FIELD);
				byte[] salt = result.getBytes(SALT_FIELD);
				String firstname = result.getString(FIRSTNAME_FIELD);
				String lastname = result.getString(LASTNAME_FIELD);
				list.add(new Person(email, password, salt, firstname, lastname));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void add(Person person) {
		try {
			String sql = "INSERT INTO " + TABLE_NAME + " (" 
						+ EMAIL_FIELD + ", "
						+ PASSWORD_FIELD + ", " 
						+ SALT_FIELD + ", " 
						+ FIRSTNAME_FIELD + ", "
						+ LASTNAME_FIELD + ") "
						+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, person.getUserId());
			statement.setString(2, person.getHashedPassword());
			statement.setString(3, person.getSalt().toString());
			statement.setString(4, person.getFirstName());
			statement.setString(5, person.getLastName());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Person person) {
		try {
			String sql = "UPDATE " + TABLE_NAME + " SET " 
					+ PASSWORD_FIELD+ " = ?, "
					+ SALT_FIELD + " = ?, "  
					+ FIRSTNAME_FIELD + " = ?, " 
					+ LASTNAME_FIELD+ " = ? WHERE " 
					+ EMAIL_FIELD + " = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, person.getHashedPassword());
			statement.setString(2, person.getSalt().toString());
			statement.setString(3, person.getFirstName());
			statement.setString(4, person.getLastName());
			statement.setString(5, person.getUserId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String email) {
		try {
			String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD
					+ " = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
