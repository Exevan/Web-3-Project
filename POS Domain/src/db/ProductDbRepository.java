package db;

import java.util.List;

import domain.product.*;

public class ProductDbRepository {

	private WebshopDB db;

	public ProductDbRepository(WebshopDB db) {
		this.db = db;
	}

	public Product get(String productName) {
		return null;
	}

	public List<Product> getAll() {
		return null;
	}

	public void add(Product product) {

	}

	public void update(Product product) {

	}
	
	public void delete(String productName) {
		
	}
}
