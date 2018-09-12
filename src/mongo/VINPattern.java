package mongo;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class VINPattern {
	public void getVINPatern(){
	try {
		// MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoClient mongoClient = new MongoClient("lnoc-dvcp-xmg1.autodatacorp.org", 27017);
		// MongoDatabase db = mongoClient.getDatabase("test");
		MongoDatabase db = mongoClient.getDatabase("ads_US");

		// FindIterable<Document> iterable = db.getCollection("VINEngine").find(); //take 2 sec
		// FindIterable<Document> iterable = db.getCollection("VINEngine").find();
		// FindIterable<Document> iterable = db.getCollection("VINPattern").find(); //take about 2 mins
		// FindIterable<Document> iterable = db.getCollection("VINPattern").find(new Document("pattern", "3GCPCREC*F*******")); //OK
		FindIterable<Document> iterable = db.getCollection("VINPattern")
				.find(new Document("pattern", "3GCPCREC*F*******")); // OK

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
//	System.setOut(original);
//	System.out.println("The end!");
//	System.out.println("Done!");
}
}
