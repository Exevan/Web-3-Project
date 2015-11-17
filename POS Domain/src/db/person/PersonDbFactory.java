package db.person;

import java.util.Properties;

import db.DBtypes;

public class PersonDbFactory {
	private static PersonDbFactory factory = new PersonDbFactory();
	
	private PersonDbFactory() {}
	
	public static PersonDbFactory getPersonDbFactory() {
		return factory;
	}
	
	public PersonDbRepository createPersonDb(DBtypes type, Properties properties) {
		PersonDbRepository db = null;
		switch (type) {
		case SQLDB:
			db = new PersonSQLRepository(properties);
			break;
		case LOCALDB:
			db = new PersonLocalRepository();
			break;
		}
		return db;
	}
}
