package domain.product;

import java.sql.SQLException;
import java.util.List;

import db.ProductDbRepository;
import db.WebshopDB;

public class ProductService {
	private ProductDbRepository productRepository;

	public ProductService() throws SQLException{
		productRepository = new ProductDbRepository();
	}
	
	public Product getProduct(String productName) {
		return getProductRepository().get(productName);
	}

	public List<Product> getProducts() {
		return getProductRepository().getAll();
	}

	public void addProduct(Product product) {
		getProductRepository().add(product);
	}

	public void updateProduct(Product product) {
		getProductRepository().update(product);
	}

	public void deleteProduct(String productName) {
		getProductRepository().delete(productName);
	}

	private ProductDbRepository getProductRepository() {
		return productRepository;
	}
}
