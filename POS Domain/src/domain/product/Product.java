package domain.product;

public class Product {
	
	private String name, description;
	private double price;
	
	public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static boolean isValidName(String name) {
		if (name.isEmpty())
			throw new IllegalArgumentException("No name given");
		return true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static boolean isValidDescription(String description) {
		if (description.isEmpty())
			throw new IllegalArgumentException("No description given");
		return true;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static boolean isValidPrice(double price) {
		if (price <= 0)
			throw new IllegalArgumentException("Price must be greater than 0");
		return true;
	}
	
	
	
}
