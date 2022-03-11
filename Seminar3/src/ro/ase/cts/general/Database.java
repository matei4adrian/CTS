package ro.ase.cts.general;

import java.sql.SQLException;
import java.util.List;

import ro.ase.cts.entity.Employee;

public interface Database<T> {
	T connectToDB() throws Exception;

	void createTable(T connection, String tableName) throws SQLException;

	void insertData(T connection, String tableName, List<Employee> employees) throws SQLException;

	void readData(T connection, String tableName) throws SQLException;

	void closeConnection(T connection) throws SQLException;
}
