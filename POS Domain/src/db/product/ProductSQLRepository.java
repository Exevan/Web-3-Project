package db.product;

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
import domain.product.Product;

public class ProductSQLRepository extends SQLrepository implements ProductDbRepository {

	private static final String TABLE_NAME = "r0376333_r0296118.product";
	private static final String NAME_FIELD = "name";
	private static final String DESCRIPTION_FIELD = "description";
	private static final String PRICE_FIELD = "price";
	
	public ProductSQLRepository(Properties properties) {
		super(properties);
	}

	public Product get(String name) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		Product product = null;
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String description = result.getString(DESCRIPTION_FIELD);
				Double price = result.getDouble(PRICE_FIELD);
				product = new Product(name, description, price);
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
		return product;
	}

	public List<Product> getAll() {
		Connection connection = createConnection();
		Statement statement = null;
		List<Product> list = null;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(
					"SELECT * FROM " + TABLE_NAME);
			list = new ArrayList<>();
			while (result.next()) {
				String name = result.getString(NAME_FIELD);
				String description = result.getString(DESCRIPTION_FIELD);
				Double price = result.getDouble(PRICE_FIELD);
				list.add(new Product(name, description, price));
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

	public void add(Product product) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME_FIELD + ", "
				+ DESCRIPTION_FIELD + ", " + PRICE_FIELD
				+ ") VALUES (?, ?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
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

	public void update(Product product) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "UPDATE " + TABLE_NAME + " SET " + DESCRIPTION_FIELD
				+ " = ?, " + PRICE_FIELD + " = ? WHERE " + NAME_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getDescription());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getName());
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

	public void delete(String productName) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + NAME_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, productName);
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
