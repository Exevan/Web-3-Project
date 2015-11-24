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
		addProduct(product, 1);
	}

	public void addProduct(Product product, int quantity) {
		if (product == null)
			throw new IllegalArgumentException("The product may not be null");

		if (products.containsKey(product)) {
			int old_value = products.get(product);
			int new_value = old_value + quantity;
			System.out.println("old_value: " + old_value);
			System.out.println("new_value: " + new_value);
			products.put(product,  new_value);
		} else {
			products.put(product, quantity);
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

	public int getOrderAmount() {
		int sum = 0;
		for (int quantity : products.values())
			sum += quantity;
		return sum;
	}
}
