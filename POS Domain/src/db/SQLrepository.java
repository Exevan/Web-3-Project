package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import domain.DbException;

public abstract class SQLrepository {
	
	private Properties properties;
	
	public SQLrepository(Properties properties) {
		this.properties = properties;
	}
	
	protected Connection createConnection() {
		try {
			Class.forName(properties.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage(), e);
		}
		try {
			return DriverManager.getConnection(properties.getProperty("url"), properties);
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
}
