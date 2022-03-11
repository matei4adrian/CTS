package ro.ase.cts.nosql;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.cts.entity.Employee;
import ro.ase.cts.general.Database;

public class MongoDB implements Database<MongoDatabase> {
	MongoClient mongoClient = new MongoClient("localhost", 27017);

	@Override
	public MongoDatabase connectToDB() {
		MongoDatabase mongoDb = mongoClient.getDatabase("test");
		return mongoDb;
	}

	@Override
	public void createTable(MongoDatabase connection, String tableName) {
		if (connection.getCollection(tableName) != null) {
			connection.getCollection(tableName).drop();
		}
		connection.createCollection(tableName);
	}

	@Override
	public void insertData(MongoDatabase connection, String tableName, List<Employee> employees) {
		MongoCollection<Document> collection = connection.getCollection(tableName);
		for (Employee employee : employees) {
			Document employeeToInsert = new Document().append("name", employee.getName())
					.append("address", employee.getAddress()).append("salary", employee.getSalary());
			collection.insertOne(employeeToInsert);
		}
	}

	@Override
	public void readData(MongoDatabase connection, String tableName) {
		MongoCollection<Document> collection = connection.getCollection(tableName);
		FindIterable<Document> result = collection.find();
		for (Document doc : result) {
			System.out.println(doc);
		}
	}

	@Override
	public void closeConnection(MongoDatabase connection) {
		mongoClient.close();
	}

}
