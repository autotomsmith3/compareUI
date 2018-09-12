package mongo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class BuildDataByModelCode {
	public void getBuildDataByModelCode(String[] vins, String mdcode) {
		try {
			// MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoClient mongoClient = new MongoClient("lnoc-dvcp-xmg1.autodatacorp.org", 27017);
			// MongoDatabase db = mongoClient.getDatabase("test");
			MongoDatabase db = mongoClient.getDatabase("ads_BuildData");

			// String vin = "3GCPCREC3FG408056";
			for (String vin : vins) {

				FindIterable<Document> iterable = db.getCollection("BuildData").find(new Document("modelCode", mdcode)); // OK, but take long time to finish all
				// FindIterable<Document> iterable = db.getCollection("BuildData").find(new Document("_id", vin)); // OK, secs.

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
//****************Add ModelCode in build_data_vins=modelcode in mongo_data/vin_data.properties***********
//*************** Update below brand = "GM" or "Chrysler US" or ...
		String brand = "TEST";
		// Nissan
		//
		//
		//
		//
		// 21164
		// 25714
		// 27014
		// 61114
		// 31055
		// 32315
		// 65115
		// 60215
		// 34115
		// String modecode="21164";

		for (String modelcode : modelcodes) {

			String ConsoleLogfile = "C:\\1\\Eclipse\\Test Results\\All_Vins_Fr_ModelCodes\\MongoDBTestConsoleLog_"
					+ brand + "_" + modelcode + ".txt";

//			// //*********************** Comment below if you want console info show running status*******************
//			try {
//				System.setOut(new PrintStream(new FileOutputStream(ConsoleLogfile)));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			// //*********************** Comment above if you want console info show running status*******************


			// *******VINPattern,**************
			BuildDataByModelCode BD = new BuildDataByModelCode();
			BD.getBuildDataByModelCode(vinNums, modelcode); // take longer than
			System.setOut(original);
			System.out.println("The end!");
			System.out.println("Done!");
			//// *******VINPattern**************


			System.setOut(original);
			System.out.println("Brand=" + brand + " - " + modelcode + " is complete!");
			System.out.println("Done!");

		}

	}
}
