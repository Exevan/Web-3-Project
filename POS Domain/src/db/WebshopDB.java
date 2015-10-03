package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class WebshopDB {
	
	Statement statement;
	
	public WebshopDB() throws SQLException {
		String password = ""; //TODO get password
		String userid = ""; //TODO get ID
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/webontwerp";
		properties.setProperty("user", userid);
		properties.setProperty("password", password);
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		
		Connection connection = DriverManager.getConnection(url,properties);
		statement = connection.createStatement();
	}
}
