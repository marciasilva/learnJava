package structural.facade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import singleton.DbSingleton;

public class JdbcFacade {

	DbSingleton instance = null;

	public JdbcFacade() {			
		try {
			this.instance = DbSingleton.getInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int createTable() {
		int count = 0;
		try {
			Connection conn = instance.getConnection();
			Statement sta = conn.createStatement();
			sta = conn.createStatement();
			count = sta.executeUpdate("CREATE TABLE Address (ID INT, StreetName VARCHAR(20), City VARCHAR(20))");
			System.out.println("Table created");
			sta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	public int insertIntoTable() {
		int count = 0;
		try {
			Connection conn = instance.getConnection();
			Statement sta = conn.createStatement();
			sta = conn.createStatement();
			count = sta.executeUpdate(
					"INSERT INTO Address (ID, StreetName, City) values (1, '167 Jose Alexandre da Mata Street', 'Oliveira'))");
			System.out.println("Table created");
			sta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Address> getAddresses() {

		List<Address> addresses = new ArrayList<>();

		try {
			Connection conn = instance.getConnection();
			Statement sta = conn.createStatement();
			ResultSet resultSet = sta.executeQuery("SELECT * FROM Address");
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
				Address address = new Address();
				address.setId(resultSet.getString(1));
				address.setStreetName(resultSet.getString(2));
				address.setCity(resultSet.getString(3));
				addresses.add(address);
			}
			
			resultSet.close();
			sta.close();
			conn.close();						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addresses;

	}

}
