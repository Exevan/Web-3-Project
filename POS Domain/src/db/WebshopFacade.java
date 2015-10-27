package db;

import java.util.List;
import java.util.Properties;

import domain.person.Person;
import domain.person.PersonService;
import domain.product.Product;
import domain.product.ProductService;

public class WebshopFacade {
	private final PersonService personService;
	private final ProductService productService;
	
	public WebshopFacade (Properties properties) {
		personService = new PersonService(DBtypes.LOCALDB, properties);
		productService = new ProductService(DBtypes.LOCALDB, properties);
	}
	
	// product things
	
	public Product getProduct(String productName) {
		return productService.getProduct(productName);
	}

	public List<Product> getProducts() {
		return productService.getProducts();
	}

	public void addProduct(Product product) {
		productService.addProduct(product);
	}

	public void updateProduct(Product product) {
		productService.updateProduct(product);
	}

	public void deleteProduct(String productName) {
		productService.deleteProduct(productName);
	}
	
	// person things
	
	public Person getPerson(String personId) {
		return personService.getPerson(personId);
	}

	public List<Person> getPersons() {
		return personService.getPersons();
	}

	public boolean hasPerson(Person person) {
		return personService.hasPerson(person);
	}

	public boolean canHaveAsPerson(Person person) {
		return personService.canHaveAsPerson(person);
	}

	public void addPerson(Person person) {
		if(canHaveAsPerson(person))
			personService.addPerson(person);
	}

	public void updatePerson(Person person) {
		personService.updatePerson(person);
	}

	public void deletePerson(String id) {
		personService.deletePerson(id);
	}

	public Person getAuthenticatedUser(String personId, String password) {		
		return personService.getAuthenticatedUser(personId, password);
	}
	
}
