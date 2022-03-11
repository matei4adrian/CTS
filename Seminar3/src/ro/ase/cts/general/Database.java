package ro.ase.cts.general;

import java.sql.SQLException;

public interface Database<T> {
	T connectToDB() throws Exception;

	void crateTable(T connection) throws SQLException;

	void insertData(T connection) throws SQLException;

	void readData(T connection) throws SQLException;

	void closeConnection(T connection) throws SQLException;
}
