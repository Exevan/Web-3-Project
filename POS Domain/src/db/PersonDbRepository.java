package db;

import java.util.List;

import domain.person.Person;

public interface PersonDbRepository {
	boolean establishConnection(String username, String password);
	Person get(String userId);
	List<Person> getAll();
	void add(Person person);
	void update(Person person);
	void delete(String userId);
}
