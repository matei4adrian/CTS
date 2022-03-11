package ro.ase.cts.nosql;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ro.ase.cts.general.Database;

public class MongoDB implements Database<MongoDatabase>{
	MongoClient mongoClient = new MongoClient("localhost", 27017);

	@Override
	public MongoDatabase connectToDB() {
		MongoDatabase mongoDb = mongoClient.getDatabase("test");
		return mongoDb;
	}

	@Override
	public void crateTable(MongoDatabase connection) {
		if(connection.getCollection("employees") != null) {
			connection.getCollection("employees").drop();
		}
		connection.createCollection("employees");
	}

	@Override
	public void insertData(MongoDatabase connection) {
		Document employee1 = new Document().append("name", "Popescu Ion").
				append("address", "Bucharest").append("salary", 4000);
		
		MongoCollection<Document> collection = connection.getCollection("employees");
		collection.insertOne(employee1);
		
		Document employee2 = new Document().append("name", "Ionescu Vasile").
				append("salary", 4500);
		collection.insertOne(employee2);
	}

	@Override
	public void readData(MongoDatabase connection) {
		MongoCollection<Document> collection = connection.getCollection("employees");
		FindIterable<Document> result = collection.find();
		for(Document doc : result) {
			System.out.println(doc);
		}
	}

	@Override
	public void closeConnection(MongoDatabase connection) {
		mongoClient.close();
	}

}
