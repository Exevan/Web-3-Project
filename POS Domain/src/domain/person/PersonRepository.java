package domain.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();

	public PersonRepository () {
		//Person administrator = new Person("admin@ucll.be", "t", "Ad", "Ministrator");
		//add(administrator);
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

	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}
}
