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

public class ProductSQLRepository extends SQLrepository implements
		ProductDbRepository {

	private static final String TABLE_NAME = "r0376333_r0296118.product";
	private static final String NAME_FIELD = "name";
	private static final String DESCRIPTION_FIELD = "description";
	private static final String PRICE_FIELD = "price";
	private static final String ID_FIELD = "id";

	public ProductSQLRepository(Properties properties) {
		super(properties);
	}

	public Product get(int id) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		Product product = null;
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String description = result.getString(DESCRIPTION_FIELD);
				Double price = result.getDouble(PRICE_FIELD);
				String name = result.getString(NAME_FIELD);
				product = new Product(id, name, description, price);
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
			ResultSet result = statement.executeQuery("SELECT * FROM "
					+ TABLE_NAME);
			list = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt(ID_FIELD);
				String name = result.getString(NAME_FIELD);
				String description = result.getString(DESCRIPTION_FIELD);
				Double price = result.getDouble(PRICE_FIELD);
				list.add(new Product(id, name, description, price));
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
		String sql = "INSERT INTO " + TABLE_NAME + " (" + ID_FIELD + ", "
				+ NAME_FIELD + ", " + DESCRIPTION_FIELD + ", " + PRICE_FIELD
				+ ") VALUES (?, ?, ?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, product.getId());
			statement.setString(1, product.getName());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
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
		String sql = "UPDATE " + TABLE_NAME + " SET " + NAME_FIELD + " = ?, "
				+ DESCRIPTION_FIELD + " = ?, " + PRICE_FIELD + " = ? WHERE "
				+ ID_FIELD + " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setInt(4, product.getId());
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

	public void delete(int id) {
		Connection connection = createConnection();
		PreparedStatement statement = null;
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_FIELD
				+ " = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
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
