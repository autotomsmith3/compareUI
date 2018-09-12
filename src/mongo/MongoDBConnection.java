package mongo;

import java.net.UnknownHostException;
import java.util.Properties;
//import java.util.List;
import java.util.Set;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import cPP.JSONParser;
import cPP.com_libs;

public class MongoDBConnection {
	public static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}
	public static void main(String args[]) {

		System.out.println("Starting....\n");
		PrintStream original = System.out;
		
		
		Properties prop = new Properties();
		try {
			prop.load(JSONParser.class.getClassLoader().getResourceAsStream("mongo_data/vin_data.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = com_libs.fetchOneDemArrayFromPropFile("build_data_vins", prop);
	
		String[] modelcodes = com_libs.fetchOneDemArrayFromPropFile("build_data_models", prop);
	
		
		
		
		String brand="GM"; 
//		Nissan
//		
//		
//		
//		
//		21164
//		25714
//		27014
//		61114
//		31055
//		32315
//		65115
//		60215
//		34115
//		String modecode="21164";
		
		
		
		for (String modelcode: modelcodes){
		
		
		
		
		
		
		
		
		
		
		
		
		String ConsoleLogfile = "C:\\1\\Eclipse\\Test Results\\All_Vins_Fr_ModelCodes\\MongoDBTestConsoleLog_"+brand+"_"+modelcode+".txt";

		// // Comment below if you want console info show running status
		 try {
		 System.setOut(new PrintStream(new FileOutputStream(ConsoleLogfile)));
		 } catch (FileNotFoundException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		
		
		

////		*******ExteriorColor**************
//		ExteriorColor BD=new ExteriorColor();
//		BD.ExteriorColor();  // take longer than
//		System.setOut(original);
//		System.out.println("The end!");
//		System.out.println("Done!");
////		*******ExteriorColor**************		 
		 
		 
//		*******VINPattern,**************
		BuildData BD=new BuildData();
		BD.getBuildData(vinNums, modelcode);  // take longer than
		System.setOut(original);
		System.out.println("The end!");
		System.out.println("Done!");
////		*******VINPattern**************
//		VINPattern VP=new VINPattern();
//		VP.getVINPatern();
//		System.setOut(original);
//		System.out.println("The end!");
//		System.out.println("Done!");
////		*******VINEngine**************		
//		VINEngine VE=new VINEngine();
//		VE.getVINEngine();
//		System.setOut(original);
//		System.out.println("The end!");
//		System.out.println("Done!");
//		
		
		
		
		
//		try {
//			// MongoClient mongoClient = new MongoClient("localhost", 27017);
//			MongoClient mongoClient = new MongoClient("lnoc-dvcp-xmg1.autodatacorp.org", 27017);
//			// MongoDatabase db = mongoClient.getDatabase("test");
//			MongoDatabase db = mongoClient.getDatabase("ads_US");
//
//			// FindIterable<Document> iterable = db.getCollection("VINEngine").find(); //take 2 sec
//			// FindIterable<Document> iterable = db.getCollection("VINEngine").find();
//			// FindIterable<Document> iterable = db.getCollection("VINPattern").find(); //take about 2 mins
//			// FindIterable<Document> iterable = db.getCollection("VINPattern").find(new Document("pattern", "3GCPCREC*F*******")); //OK
//			FindIterable<Document> iterable = db.getCollection("VINPattern")
//					.find(new Document("pattern", "3GCPCREC*F*******")); // OK
//
//			iterable.forEach(new Block<Document>() {
//				@Override
//				public void apply(final Document document) {
//					System.out.println(document);
//				}
//			});
//
//			// DB db = mongoClient.getDB("database name");
//			// // boolean auth = db.authenticate("username", "password".toCharArray());
//			//
//			// List<String> databases = mongoClient.getDatabaseNames();//.getDatabaseNames();
//			//
//			//
//			// DBCollection table = db.getCollection("user");
//			//
//			// for (String dbName: databases) {
//			// System.out.println("- Database: " + dbName);
//			// DB db = mongoClient.getDB(dbName);
//			// Set<String> collections = db.getCollectionNames();
//			// for (String colName : collections) {
//			// System.out.println("\t + Collection: " + colName);
//			// }
//			// }
//			//
//
//			mongoClient.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		System.setOut(original);
		System.out.println("Brand="+brand+" - "+modelcode+" is complete!");
		System.out.println("Done!");
		
		
		}
		
		
		
	}
}
