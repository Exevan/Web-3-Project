package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class WebshopDB {

	public static Connection createConnection() throws SQLException {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/webontwerp";
		properties.setProperty("user", "");
		properties.setProperty("password", "");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory",
				"org.postgresql.ssl.NonValidatingFactory");

		Connection connection = DriverManager.getConnection(url, properties);
		return connection;
	}
}
