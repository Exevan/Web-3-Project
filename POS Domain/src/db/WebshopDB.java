package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class WebshopDB {

	public static Connection createConnection(String username, String password) {
		try {
			Properties properties = new Properties();
			String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
			properties.setProperty("user", username);
			properties.setProperty("password", password);
			properties.setProperty("ssl", "true");
			properties.setProperty("sslfactory",
					"org.postgresql.ssl.NonValidatingFactory");
			Class.forName("org.postgresql.Driver");
			Connection connection;
			connection = DriverManager.getConnection(url, properties);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
