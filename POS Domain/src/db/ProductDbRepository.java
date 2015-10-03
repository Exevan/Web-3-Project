package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import domain.product.*;

public class ProductDbRepository {

	private Connection connection;

	public ProductDbRepository() throws SQLException {
		this.connection = WebshopDB.createConnection();
	}

	public Product get(String productName) {
		// TODO implement
		return null;
	}

	public List<Product> getAll() {
		// TODO implement
		return null;
	}

	public void add(Product product) {
		// TODO implement
	}

	public void update(Product product) {
		// TODO implement
	}

	public void delete(String productName) {
		// TODO implement
	}
}
