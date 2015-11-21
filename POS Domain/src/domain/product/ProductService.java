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
	
	public Product getProduct(int id) {
		return getProductRepository().get(id);
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

	public void deleteProduct(int id) {
		getProductRepository().delete(id);
	}

	private ProductDbRepository getProductRepository() {
		return productRepository;
	}
}
