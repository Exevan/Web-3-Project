package db;

import java.util.List;

import domain.product.Product;

public interface productDbRepository {
	boolean establishConnection(String username, String password);
	Product get(String name);
	List<Product> getAll();
	void add(Product product);
	void update(Product product);
	void delete(String name);
}
