package ro.ase.cts;

import ro.ase.cts.general.Database;

public class Main {

	public static void main(String[] args) {
		Database database = null;

		try {
//			database = (Database) Class.forName("ro.ase.cts.nosql.MongoDB").getDeclaredConstructor().newInstance();
			database = (Database) Class.forName("ro.ase.cts.sql.SqliteDB").getDeclaredConstructor().newInstance();
			var connection = database.connectToDB();
			database.crateTable(connection);
			database.insertData(connection);
			database.readData(connection);
			database.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
