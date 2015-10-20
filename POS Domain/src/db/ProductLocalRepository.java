package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.product.Product;

public class ProductLocalRepository implements productDbRepository {

	private Map<String, Product> products;

	public ProductLocalRepository() {
		products = new HashMap<String, Product>();
	}

	public Product get(String productName) {
		if (productName == null || productName.equals("")) {
			throw new IllegalArgumentException("No id given");
		}
		return products.get(productName);
	}

	public List<Product> getAll() {
		return new ArrayList<Product>(products.values());
	}

	public void add(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("No product given");
		}
		if (products.containsKey(product.getName())) {
			throw new IllegalArgumentException("Product already exists");
		}
		products.put(product.getName(), product);
	}

	public void update(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("No product given");
		}
		products.put(product.getName(), product);
	}

	public void delete(String productName) {
		if (productName == null) {
			throw new IllegalArgumentException("No name given");
		}
		products.remove(productName);
	}

	@Override
	public boolean establishConnection(String username, String password) {
		return true;
	}

}
