package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public abstract class WebshopDB {

	public static Connection createConnection() throws SQLException {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
		properties.setProperty("user", JOptionPane.showInputDialog("username"));
		properties.setProperty("password", JOptionPane.showInputDialog("password"));
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory",
				"org.postgresql.ssl.NonValidatingFactory");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = DriverManager.getConnection(url, properties);
		return connection;
	}
}
