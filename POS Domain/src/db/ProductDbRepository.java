package db;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domain.product.*;

public class ProductDbRepository {

	private Statement statement;

	public ProductDbRepository() throws SQLException {
		this.statement = WebshopDB.createStatement();
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
