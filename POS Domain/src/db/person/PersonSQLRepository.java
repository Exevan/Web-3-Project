package db.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import db.SQLrepository;
import domain.DbException;
import domain.person.Person;
import domain.person.Role;

public class PersonSQLRepository extends SQLrepository implements PersonDbRepository {

	private static final String TABLE_NAME = "r0376333_r0296118.person";
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String SALT_FIELD = "salt";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LASTNAME_FIELD = "lastname";

	private static final String ROLE_FIELD = "role";

	public PersonSQLRepository(Properties properties) {
		super(properties);
	}

	public Person get(String email) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		Person person = null;
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String password = result.getString(PASSWORD_FIELD);
				byte[] salt = result.getBytes(SALT_FIELD);
				String firstname = result.getString(FIRSTNAME_FIELD);
				String lastname = result.getString(LASTNAME_FIELD);
				Role role = Role.valueOf(result.getString(ROLE_FIELD));
				person = new Person(email, password, salt, firstname, lastname,
						role);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		return person;
	}

	public List<Person> getAll() {
		Connection connection = createConnection();
		Statement statement = null;
		List<Person> list = null;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(
					"SELECT * FROM " + TABLE_NAME);
			list = new ArrayList<>();
			while (result.next()) {
				String email = result.getString(EMAIL_FIELD);
				String password = result.getString(PASSWORD_FIELD);
				byte[] salt = result.getBytes(SALT_FIELD);
				String firstname = result.getString(FIRSTNAME_FIELD);
				String lastname = result.getString(LASTNAME_FIELD);
				Role role = Role.valueOf(result.getString(ROLE_FIELD));
				list.add(new Person(email, password, salt, firstname, lastname,
						role));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		return list;
	}

	public void add(Person person) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO " + TABLE_NAME + " (" + EMAIL_FIELD
				+ ", " + PASSWORD_FIELD + ", " + SALT_FIELD + ", "
				+ FIRSTNAME_FIELD + ", " + LASTNAME_FIELD + ", "
				+ ROLE_FIELD + ") " + "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, person.getUserId());
			statement.setString(2, person.getHashedPassword());
			statement.setBytes(3, person.getSalt());
			statement.setString(4, person.getFirstName());
			statement.setString(5, person.getLastName());
			statement.setString(6, person.getRole().toString());
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
	}

	public void update(Person person) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "UPDATE " + TABLE_NAME + " SET " + PASSWORD_FIELD
				+ " = ?, " + SALT_FIELD + " = ?, " + FIRSTNAME_FIELD
				+ " = ?, " + LASTNAME_FIELD + " = ?, " + ROLE_FIELD
				+ " = ? WHERE " + EMAIL_FIELD + " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, person.getHashedPassword());
			statement.setBytes(2, person.getSalt());
			statement.setString(3, person.getFirstName());
			statement.setString(4, person.getLastName());
			statement.setString(5, person.getRole().toString());
			statement.setString(6, person.getUserId());
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
	}

	public void delete(String email) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + EMAIL_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
	}
}
