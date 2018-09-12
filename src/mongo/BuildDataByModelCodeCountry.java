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

public class BuildDataByModelCodeCountry {
	public void getBuildDataByModelCodeCountry(String country, String mdcode) {
		try {
			String mongoDBbuildDataPathFile = "C:\\1\\Eclipse\\Test Results\\All_Vins_Fr_ModelCodes\\ChryslerCA\\BuildDataFromCanda.xls";
			String[] titleStringMongoDBbuildData = { "S/N", "_id:", "modelCode:", "options:", "buildDate:", "source:",
					"country:", "builtMSRP:", "latestFileDate" };
			com_libs.writeTitle(mongoDBbuildDataPathFile, titleStringMongoDBbuildData);
			int countNum = 0;

			// MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoClient mongoClient = new MongoClient("lnoc-dvcp-xmg1.autodatacorp.org", 27017);
			// MongoDatabase db = mongoClient.getDatabase("test");
			MongoDatabase db = mongoClient.getDatabase("ads_BuildData");

			// **********************************************************************************
			// FindIterable<Document> iterable = db.getCollection("BuildData").find(new Document("country", country).append("modelCode", "WKJT74")).limit(20000); // It's ready by 2016-10-07

			FindIterable<Document> iterable = db.getCollection("BuildData").find(new Document("country", country)); // It's ready by 2016-10-07
			// **********************************************************************************

			iterable.forEach(new Block<Document>() {
				@Override
				public void apply(final Document document) {
					// System.out.println(document.toJson());

					String VIN = (String) document.get("_id"); // This is cast (String)
					String modelcode = (String) document.get("modelCode");
					String buildDate = (String) document.get("buildDate");
					String options = (String) document.get("options").toString();// object to String
					String source = (String) document.get("source");
					String country = (String) document.get("country");
					String builtMSRP = (String) document.get("builtMSRP");
					String latestFileDate = (String) document.get("latestFileDate");

					System.out.println("VIN=" + VIN + "   ModelCode=" + modelcode + "     buildDate=" + buildDate
							+ "      options=" + options);
					int wSize = titleStringMongoDBbuildData.length;
					String[] jsonValue = new String[wSize];

					jsonValue[0] = "";
					jsonValue[1] = VIN;
					jsonValue[2] = modelcode;
					jsonValue[3] = options;
					jsonValue[4] = buildDate;
					jsonValue[5] = source;
					jsonValue[6] = country;
					jsonValue[7] = builtMSRP;
					jsonValue[8] = latestFileDate;

					try {
						com_libs.writeToSheet(mongoDBbuildDataPathFile, jsonValue);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Write to Sheet failure!!!!");
					}

				}
			});
			// }
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

		// String[] vinNums = com_libs.fetchOneDemArrayFromPropFile("build_data_vins", prop);

		String countryCode = prop.getProperty
				("countryCode");
		String[] modelcodes = com_libs.fetchOneDemArrayFromPropFile("build_data_models", prop);
		// ****************Add ModelCode in build_data_vins=modelcode in mongo_data/vin_data.properties***********
		// *************** Update below brand = "GM" or "Chrysler US" or ...
		String brand = "TEST";

		for (String modelcode : modelcodes) {

			String ConsoleLogfile = "C:\\1\\Eclipse\\Test Results\\All_Vins_Fr_ModelCodes\\MongoDBTestConsoleLog_"
					+ brand + "_" + modelcode + ".txt";

			// // //*********************** Comment below if you want console info show running status*******************
			// try {
			// System.setOut(new PrintStream(new FileOutputStream(ConsoleLogfile)));
			// } catch (FileNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// // //*********************** Comment above if you want console info show running status*******************

			// *******VINPattern,**************
			BuildDataByModelCodeCountry BD = new BuildDataByModelCodeCountry();
			BD.getBuildDataByModelCodeCountry(countryCode, modelcode); // take longer than
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
