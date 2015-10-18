package domain.product;

import java.util.List;

import db.ProductDbRepository;

public class ProductService {
	private ProductDbRepository productRepository;

	public ProductService() {
		productRepository = new ProductDbRepository();
	}
	
	public boolean establishConnection(String username, String password) {
		return productRepository.establishConnection(username, password);
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
