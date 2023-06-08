package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static final String url = "jdbc:mysql://localhost:3306/coursejdbc";
	
	private static Connection connection;
	
	public static Connection getConnection() {
		
		try {	
			if(connection == null) {
				connection = DriverManager.getConnection(url, loadProperties());
				return connection;
			}
			return connection;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fileInputStream = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fileInputStream);
			return props;
		} 
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		if(connection != null) {
			try{
				connection.close();				
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
