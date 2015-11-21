package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;

import domain.product.Product;

public class ShoppingCart extends Observable {

	private final String userId; // can be null
	private Map<Product, Integer> products; // maps the product-id to the quantity

	public ShoppingCart(String userId) {
		this.userId = userId;
		products = new HashMap<>();
	}

	public String getUserId() {
		return this.userId;
	}

	public void addProduct(Product product) {
		if (product == null)
			throw new IllegalArgumentException("The product may not be null");

		if (products.containsKey(product)) {
			products.put(product, products.get(product) + 1);
		} else {
			products.put(product, 1);
		}

		setChanged();
		notifyObservers();
	}

	public double getTotalPrice() {
		double sum = 0.0;
		for (Entry<Product, Integer> pair : products.entrySet()) {
			sum += pair.getKey().getPrice() * pair.getValue();
		}
		return sum;
	}
}
