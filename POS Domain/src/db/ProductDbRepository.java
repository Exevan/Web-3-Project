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

	public ProductDbRepository() throws SQLException {
		this.connection = WebshopDB.createConnection();
	}

	public Product get(String name) throws SQLException {
		String sql = "SELECT * FROM r0376333_r0296118.product WHERE name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		ResultSet result = statement.executeQuery();
		String description = result.getString("description");
		Double price = result.getDouble("price");
		return new Product(name, description, price);
	}

	public List<Product> getAll() throws SQLException {
		ResultSet result = connection.createStatement().executeQuery("SELECT * FROM r0376333_r0296118.product");
		List<Product> list = new ArrayList<>();
		while (result.next()) {
			String name = result.getString("name");
			String description = result.getString("description");
			Double price = result.getDouble("price");
			list.add(new Product(name, description, price));
		}
		return list;
	}

	public void add(Product product) throws SQLException {
		String sql = "INSERT INTO r0376333_r0296118.product (name, description, price) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getName());
		statement.setString(2, product.getDescription());
		statement.setString(3, Double.toString(product.getPrice()));
		statement.execute();
	}

	public void update(Product product) {
		// TODO implement
	}

	public void delete(String productName) {
		// TODO implement
	}
}
