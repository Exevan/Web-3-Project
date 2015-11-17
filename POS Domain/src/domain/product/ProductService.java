package domain.product;

import java.util.List;
import java.util.Properties;

import db.DBtypes;
import db.product.ProductDbFactory;
import db.product.ProductDbRepository;

public class ProductService {
	private ProductDbRepository productRepository;

	public ProductService(DBtypes type, Properties properties) {
		ProductDbFactory factory = ProductDbFactory.getProductDbFactory();
		productRepository = factory.createProductDb(type, properties);
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
