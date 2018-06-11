package webserver.util;

import java.sql.*;

public class SQLiteConnector {
	static Connection databaseConnection = null;
	static final String SQL_DATABASE_NAME="system.sqlite3";
	
	static {
		try {
            Class.forName("org.sqlite.JDBC");
            databaseConnection = DriverManager.getConnection("jdbc:sqlite:"+SQL_DATABASE_NAME);
            System.out.println("Database successfully opened");
        } catch ( ClassNotFoundException e ) {
        	System.out.println("SQLite driver not found");
            e.printStackTrace();
            System.exit(0);
        } catch (SQLException e) {
        	System.out.println("Database '"+SQL_DATABASE_NAME+"' not found");
            e.printStackTrace();
            System.exit(0);
		} 
	}
	
	static public void close() throws SQLException {
		databaseConnection.close();
		System.out.println("Database successfully closed");
	}
	
	public static void getAverageWindmillProductions() throws SQLException {
		Statement statement = databaseConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(""); // TO BE COMPLETED
		while ( resultSet.next() ) {
            String  name = resultSet.getString(1);
            float energy = resultSet.getFloat(2);
            System.out.println(name+": "+energy+"MWh");
        }
        resultSet.close();
        statement.close();
	}
	
	public static void getAverageConsumerConsumptions(int monthInYear) throws SQLException {
		Statement statement = databaseConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(""); // TO BE COMPLETED
		while ( resultSet.next() ) {
            String  name = resultSet.getString(1);
            float energy = resultSet.getFloat(2);
            System.out.println(name+": "+energy+"MWh");
        }
        resultSet.close();
        statement.close();
	}
	
    public static void main( String args[] ) {
    	try {
    		System.out.println("Windmills");
			getAverageWindmillProductions();
			System.out.println("Consumers");
			getAverageConsumerConsumptions(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
