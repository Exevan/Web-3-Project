package domain.product;

public class Product {
	
	private String name, description;
	private double price;
	private final int id;
	
	public Product(int id, String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static boolean isValidId(int id) {
		if (id <= 0)
			throw new IllegalArgumentException("Id must be greater than 0");
		return true;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static boolean isValidQuantity(int quantity) {
		if (quantity <= 0)
			throw new IllegalArgumentException("quantity must be greater than 0");
		return true;
	}
	
	
	
}
