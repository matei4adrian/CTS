package ro.ase.cts;

import java.util.Arrays;
import java.util.List;

import ro.ase.cts.entity.Employee;
import ro.ase.cts.general.Database;

public class Main {

	public static void main(String[] args) {
		Database database = null;

		List<Employee> employees = Arrays.asList(new Employee(1, "Popescu Ion", "Bucharest", 4000),
				new Employee(2, "Ionescu Vasile", "Brasov", 4500));
		String tableName = "employees";

		try {
//			database = (Database) Class.forName("ro.ase.cts.nosql.MongoDB").getDeclaredConstructor().newInstance();
			database = (Database) Class.forName("ro.ase.cts.sql.SqliteDB").getDeclaredConstructor().newInstance();
			var connection = database.connectToDB();
			database.createTable(connection, tableName);
			database.insertData(connection, tableName, employees);
			database.readData(connection, tableName);
			database.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
