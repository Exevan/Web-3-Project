package domain.product;

import java.sql.SQLException;
import java.util.List;

import db.ProductDbRepository;
import db.WebshopDB;

public class ProductService {
	private ProductDbRepository productRepository;

	public ProductService(String username, String password) throws SQLException{
		productRepository = new ProductDbRepository(username, password);
	}
	
	public Product getProduct(String productName) throws SQLException {
		return getProductRepository().get(productName);
	}

	public List<Product> getProducts() throws SQLException {
		return getProductRepository().getAll();
	}

	public void addProduct(Product product) throws SQLException {
		getProductRepository().add(product);
	}

	public void updateProduct(Product product) throws SQLException {
		getProductRepository().update(product);
	}

	public void deleteProduct(String productName) throws SQLException {
		getProductRepository().delete(productName);
	}

	private ProductDbRepository getProductRepository() {
		return productRepository;
	}
}
