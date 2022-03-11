package ro.ase.cts.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ro.ase.cts.entity.Employee;
import ro.ase.cts.general.Database;

public class SqliteDB implements Database<Connection> {

	@Override
	public Connection connectToDB() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		connection.setAutoCommit(false);
		return connection;
	}

	@Override
	public void createTable(Connection connection, String tableName) throws SQLException {
		String sqlDrop = "DROP TABLE IF EXISTS " + tableName;
		String sqlCreate = "CREATE TABLE " + tableName + "(id INTEGER PRIMARY KEY,"
				+ "name TEXT, address TEXT, salary REAL)";

		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlDrop);
		statement.executeUpdate(sqlCreate);
		statement.close();
		connection.commit();
	}

	@Override
	public void insertData(Connection connection, String tableName, List<Employee> employees) throws SQLException {
		for (Employee employee : employees) {
			String sqlInsertEmployeeWithParams = "INSERT INTO " + tableName + " VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertEmployeeWithParams);
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getAddress());
			preparedStatement.setDouble(4, employee.getSalary());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		connection.commit();
	}

	@Override
	public void readData(Connection connection, String tableName) throws SQLException {
		String sqlSelect = "SELECT * FROM " + tableName;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlSelect);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString(2);
			String address = rs.getString("address");
			double salary = rs.getDouble("salary");
			Employee employee = new Employee(id, name, address, salary);
			System.out.println(employee);
		}
		rs.close();
		statement.close();
	}

	@Override
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

}
