package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages shopping carts that belong to actual people. Anonymous carts are
 * volatile and should be kept by the object that asks for their creation
 */
public class ShoppingCartService {
	
	private Map<String, ShoppingCart> carts; // maps userIds to carts

	public ShoppingCartService() {
		carts = new HashMap<>();
	}

	public ShoppingCart createCart(String userId) {
		ShoppingCart cart = new ShoppingCart(userId);
		if (userId != null) {
			if (carts.containsKey(userId))
				throw new IllegalArgumentException(
						"This customer already has a cart");
			carts.put(userId, cart);
		}
		return cart;
	}

	public void removeCart(String userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId may not be null");
		if (!carts.containsKey(userId))
			throw new IllegalArgumentException(
					"This customer doesn't have a cart to clear");
		carts.remove(userId);
	}

	public ShoppingCart getCart(String userId) {
		return carts.get(userId);
	}
}
