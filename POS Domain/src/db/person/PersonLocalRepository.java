package db.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.person.Person;
import domain.person.Role;

public class PersonLocalRepository implements PersonDbRepository{
	private Map<String, Person> persons;

	public PersonLocalRepository () {
		persons = new HashMap<String, Person>();
		add(new Person("admin@ucll.be", "t", "Ad", "Min", Role.ADMINISTRATOR));
		add(new Person("cashier@ucll.be", "t", "Cash", "Ier", Role.CASHIER));
	}

	public Person get(String personId){
		if(personId == null || personId.equals("")){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}

	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getUserId())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getUserId(), person);
	}

	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		persons.put(person.getUserId(), person);
	}

	public void delete(String userId){
		if(userId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(userId);
	}
}
