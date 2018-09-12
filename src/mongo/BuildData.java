package mongo;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import cPP.JSONParser;
import cPP.com_libs;

public class BuildData {
	public void getBuildData(String[] vins, String mdcode) {
		try {
			// MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoClient mongoClient = new MongoClient("lnoc-dvcp-xmg1.autodatacorp.org", 27017);
			// MongoDatabase db = mongoClient.getDatabase("test");
			MongoDatabase db = mongoClient.getDatabase("ads_BuildData");

//			 String vin = "3GCPCREC3FG408056";
			for (String vin : vins) {

				 FindIterable<Document> iterable = db.getCollection("BuildData").find(new Document ("modelCode", mdcode)); //OK, but take long time to finish all
//				FindIterable<Document> iterable = db.getCollection("BuildData").find(new Document("_id", vin)); // OK, secs.

				// FindIterable<Document> iterable = db.getCollection("VINEngine").find();
				// FindIterable<Document> iterable = db.getCollection("VINPattern").find(); //take about 2 mins
				// FindIterable<Document> iterable = db.getCollection("VINPattern").find(new Document("pattern", "3GCPCREC*F*******")); //OK
				// FindIterable<Document> iterable = db.getCollection("VINPattern").find(new Document("pattern", "3GCPCREC*F*******")); // OK

				iterable.forEach(new Block<Document>() {
					@Override
					public void apply(final Document document) {
						System.out.println(document);
					}
				});
			}
			mongoClient.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.setOut(original);
		// System.out.println("The end!");
		// System.out.println("Done!");
	}
	
}
