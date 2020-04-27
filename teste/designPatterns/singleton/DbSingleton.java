package singleton;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbSingleton {
	// volatile makes thread safe
	private static volatile DbSingleton instance = null;
	private static volatile Connection conn = null;

	private DbSingleton(){

		// use derby db driver
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		if (conn != null) {
			throw new RuntimeException("Use getConnection() method to create");
		}

		// avoid reflection class creating an instance
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method to create");
		}
	}

	public static DbSingleton getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (instance == null) { // Lazily Looded
			synchronized (DbSingleton.class) {
				if (instance == null) {
					instance = new DbSingleton();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		if (conn == null) {
			synchronized (DbSingleton.class) {
				if (conn == null) {
					try {
						String dbUrl = "jdbc:derby:teste/resources;create=true";
						conn = DriverManager.getConnection(dbUrl);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return conn;
	}
}
