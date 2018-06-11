package webserver.util;

import java.sql.*;
import java.util.ArrayList;

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
	public static double getWindmillData(int name, int month, int day,int hour) throws SQLException {
		Statement statement = databaseConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT hourInDAY,dayInMonth,"
                        + "monthInYear,energy FROM WindmillData,Windmill WHERE name='"+ name +"' "
                        + "AND monthInYear ='"+month+"' AND dayInMonth= '"+ day +"' "
                        + "AND Windmill.id=WindmillData.windmillIDREF ORDER BY hourInDAY ASC;");
		ArrayList<Float> energyArrayList = new ArrayList<Float>(); 
                ArrayList<Float> hourArrayList = new ArrayList<Float>(); 
		while ( resultSet.next() ) {
			energyArrayList.add(resultSet.getFloat(4));
                        hourArrayList.add(resultSet.getFloat(1));
        }
        resultSet.close();
        statement.close();
            double energies;
            energies = energyArrayList.get(hour);
            return energies;
	}
        
	public static double getConstumerData(int name, int month, int day,int hour) throws SQLException {
		Statement statement = databaseConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT hourInDAY,dayInMonth,monthInYear"
                        + ",energy FROM ConsumptionData,consumer WHERE name='"+ name +"' AND"
                        + " monthInYear = '"+month+"' AND dayInMonth= '"+ day +"' AND consumer.id"
                        + " ==ConsumptionData.consumerIDREF ORDER BY hourInDAY ASC;");
		ArrayList<Float> energyArrayList = new ArrayList<Float>(); 
                ArrayList<Float> hourArrayList = new ArrayList<Float>(); 
		while ( resultSet.next() ) {
			energyArrayList.add(resultSet.getFloat(4));
                        hourArrayList.add(resultSet.getFloat(1));
        }
        resultSet.close();
        statement.close();
            double energies;
            energies =energyArrayList.get(hour);
            return energies;
	}
	public static void getAverageWindmillProductions(String name1) throws SQLException {
		Statement statement = databaseConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT name, energy FROM Windmill , WindmillData WHERE id =windmillIDREF AND name= '"+name1+"' AND monthInYear = '1' AND dayInMonth='1';"); // TO BE COMPLETED
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
		ResultSet resultSet = statement.executeQuery("SELECT name, consumerIDREF , monthInYear , dayInMonth , hourInDay , energy FROM Consumer, ConsumptionData WHERE id =consumerIDREF AND name= '2000900' AND monthInYear = '"+ Integer.toString(monthInYear) +"' AND dayInMonth='1';"); 
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
			getAverageWindmillProductions("237");
                        System.out.println("Windmills");
			getAverageWindmillProductions("143");
			System.out.println("Consumers");
//                        System.out.println(webserver.WebClient.requestText("/helloworld/ask/nico"));
//			getAverageConsumerConsumptions(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
