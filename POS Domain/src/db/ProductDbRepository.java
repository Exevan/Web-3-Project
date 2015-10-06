package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.person.Person;
import domain.product.*;

public class ProductDbRepository {

	private Connection connection;

	private static final String TABLE_NAME = "r0376333_r0296118.product";
	private static final String NAME_FIELD = "name";
	private static final String DESCRIPTION_FIELD = "description";
	private static final String PRICE_FIELD = "price";

	public ProductDbRepository() throws SQLException {
		this.connection = WebshopDB.createConnection();
	}

	public Product get(String name) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_FIELD
				+ " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		ResultSet result = statement.executeQuery();
		String description = result.getString(DESCRIPTION_FIELD);
		Double price = result.getDouble(PRICE_FIELD);
		return new Product(name, description, price);
	}

	public List<Product> getAll() throws SQLException {
		ResultSet result = connection.createStatement().executeQuery(
				"SELECT * FROM " + TABLE_NAME);
		List<Product> list = new ArrayList<>();
		while (result.next()) {
			String name = result.getString(NAME_FIELD);
			String description = result.getString(DESCRIPTION_FIELD);
			Double price = result.getDouble(PRICE_FIELD);
			list.add(new Product(name, description, price));
		}
		return list;
	}

	public void add(Product product) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME_FIELD + ", "
				+ DESCRIPTION_FIELD + ", " + PRICE_FIELD + ") VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getName());
		statement.setString(2, product.getDescription());
		statement.setDouble(3, product.getPrice());
		statement.execute();
	}

	public void update(Product product) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " SET " + DESCRIPTION_FIELD
				+ " = ?, " + PRICE_FIELD + " = ? WHERE " + NAME_FIELD + " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getDescription());
		statement.setString(2, Double.toString(product.getPrice()));
		statement.setString(3, product.getName());
		statement.execute();
	}

	public void delete(String productName) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + NAME_FIELD
				+ " = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productName);
		statement.execute();
	}
}
