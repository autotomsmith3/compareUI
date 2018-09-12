package mongo;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class ExteriorColor {
	public void ExteriorColor() {
		try {
			// MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoClient mongoClient = new MongoClient("lnoc-dvcp-xmg1.autodatacorp.org", 27017);
			// MongoDatabase db = mongoClient.getDatabase("test");
			MongoDatabase db = mongoClient.getDatabase("ads_US");

			FindIterable<Document> iterable = db.getCollection("ExteriorColor").find(new Document("styleIds", 371914)); // OK, return multiple

			// FindIterable<Document> iterable = db.getCollection("ExteriorColor").find(new Document("styleIds", 371914).append("code", "GCN")); // Using AND operation and it returns only 1. OK

			// FindIterable<Document> iterable = db.getCollection("ExteriorColor").find(new Document("code", "GCN").append("styleIds", 371914)); //Same using AND operation and it returns only 1. OK

//			 FindIterable<Document> iterable = db.getCollection("ExteriorColor").find(and(eq("styleIds", 371914), eq("code", "GCN"))); //? not working on eq

			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					System.out.println(document);
				}
			});
			mongoClient.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.setOut(original);
		// System.out.println("The end!");
		// System.out.println("Done!");
	}
}
