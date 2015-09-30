package domain.product;

import java.util.List;

public class ProductService {
	private ProductRepository productRepository = new ProductRepository();

	public ProductService(){
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

	private ProductRepository getProductRepository() {
		return productRepository;
	}
}
