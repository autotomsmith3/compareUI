package DealerPortal;

//package cPP;

import java.io.IOException;

import org.json.JSONArray;

public class getUnityInventory {

	public static void jSonArry_UnityInventory() throws IOException {
		String[] titleString = { "S/N", "vehicleId:", "vin:", "stockNumber:", "used:", "certified:", "dateReceived:",
				"make:", "model:", "year:", "mileage:", "trim:", "bodyStyle:", "engine:", "transmission:",
				"exteriorColor:", "interiorColor:", "listPrice:", "picture:", "ctx:", "unityWorkID" };
		String savePathFile = "C:\\1\\Eclipse\\Test Results\\UnityInventory\\UnityInventory_JSON_Return.xls";

		//
		// String inventoryJSONText = "[ { \"vehicleId\": 67793090, \"vin\": \"1G6AN1RY9G0101247\", \"stockNumber\": \"G0101247\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS-V Coupe\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"xxx\", \"bodyStyle\": \"2dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Gray\", \"interiorColor\": \"Black\", \"listPrice\": 76057, \"picture\": \"http://inventory-dmg.assets-cdk.com/4/4/3/13352307344.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002238, \"vin\": \"1G6AB5SS4G0109293\", \"stockNumber\": \"G0109293\",
		// \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"Luxury Collection RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 47555, \"picture\": \"http://inventory-dmg.assets-cdk.com/8/4/6/13352307648.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002241, \"vin\": \"1G6AV5S82G0113631\", \"stockNumber\": \"G0113631\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\":
		// \"Cadillac\", \"model\": \"CTS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"V-Sport Premium RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 73735, \"picture\": \"http://inventory-dmg.assets-cdk.com/5/4/9/13352307945.jpg\", \"ctx\": \"BVJR64Q\" } ]";
		// getUnityWorkInventoryFrJSONParser(savePathFile,titleString, inventoryJSONText, "testID");// Array OK - start with [ characters (square brackets - array)

		// String unityWorkURL = "https://data.dealervideos.com/v0.9/vehicles/";
		String unityWorkURL = "https://data.dealervideos.com/v1.0/vehicles/"; // see VDVIIMG-759
		// From Prod on January 31, 2018 total 44. Please see details on AUTOpx.xlsx on Prod_UnityWorkID table
		// String[] unityWorkDealerShipID = {"27OE966","DP9B0OU","112Y2JUB","2FHFIYI"};
//		String[] unityWorkDealerShipID = { "2OLET45", "10DWC56S", "1125QZAN", "112Y2JUB", "11BW9H2I", "11D0Y1R8",
//				"11FDEWDR", "11FK6G5J", "11FYPTW0", "11GFTTMR", "11GLU8DF", "11J18GNF", "11JQM19D", "1QWGIJS",
//				"1V0BJMB", "246GNLU", "275D2EQ", "27OE966", "2BDCDUA", "2D962HH", "2FHFIYI", "2JH38WM", "2MH3Q9P",
//				"2QO23PC", "2ZLGTS2", "2ZN8AC1", "31G3JLA", "33M1H95", "3AOCXEK", "3QXAM0S", "4B5CDSU", "8EU6HMR",
//				"9L83MUH", "ARQFM74", "BX3EWMP", "BX72MDM", "C4QWU9Y", "C6C1MBD", "CGPB0MT", "COA94NS", "CSF1R1T",
//				"D29DGRX", "D2E8HW2", "D2ZDQQW", "DP9B0OU", "DVAESPR", "EEY9NYG", "EH1UY6J", "EPY41RX", "ESW5ME9",
//				"EVV73HI", "F2NF3UN", "F72BCJN", "FB7CIB0", "FGN8TC3", "FX592ZN", "G4WGL1P", "GMRCHL5", "GQ22UIC",
//				"HAQETMB", "HSOFV9O", "HVBFDQZ", "JZZCJS8", "KAMF96C", "KJ0G41K", "KML7F2M", "KQO8CVH", "KTL1B32",
//				"KW66VNM", "KWDER6A", "L87CBZX", "N7I80QB", "OCN5TPF", "R0AAKW2", "R504FV8", "SLOEASO", "SZ9CRGW",
//				"T5H57YY", "V5254PG", "W97TIGT", "XZMIZ0A", "YXDADRM", "ZFEQR7R", "ZGX4FNH", "ZLAI0U0" };// Total 85 on August28, 2018

		 String[] unityWorkDealerShipID = {"2FHFIYI","L87CBZX", "H5O4M8B"};

		// Returning nothing: "","",
		for (String id : unityWorkDealerShipID) {
			String jsonTextFrUnityWork = cPP.com_libs.getSourceCode(unityWorkURL, id);
			if (jsonTextFrUnityWork == "") {
				System.out.println("UnityWorkDealerShipID=" + id + " returns empty");
			} else {
				getUnityWorkInventoryFrJSONParser(savePathFile, titleString, jsonTextFrUnityWork, id);// Array OK - start with [ characters (square brackets - array)
			}
		}
	}

	public static void getUnityWorkInventoryFrJSONParser(String resultfile, String[] titleString, String text,
			String sourceUnityID) throws IOException {
		JSONArray jsonarray = new JSONArray(text);
		int size = jsonarray.length();
		int wSize = titleString.length;
		String[] jsonValue = new String[wSize];
		cPP.com_libs.writeTitle(resultfile, titleString);
		// System.out.println("S/N" + ", vehicleId:" + ", vin:" + ", stockNumber:" + ", used:" + ", certified:"
		// + ", dateReceived:" + ", make:" + ", model:" + ", year:" + ", mileage:" + ", trim:" + ", bodyStyle:"
		// + ", engine:" + ", transmission:" + ", exteriorColor:" + ", interiorColor:" + ", listPrice:"
		// + ", picture:" + ", ctx:");

		for (int i = 0; i < size; i++) {
			long vehicleId = jsonarray.getJSONObject(i).getLong("vehicleId");
			String vin = jsonarray.getJSONObject(i).getString("vin");
			String stockNumber = jsonarray.getJSONObject(i).getString("stockNumber");
			boolean used = jsonarray.getJSONObject(i).getBoolean("used");
			boolean certified = jsonarray.getJSONObject(i).getBoolean("certified");
			String dateReceived = jsonarray.getJSONObject(i).getString("dateReceived");
			String make = jsonarray.getJSONObject(i).getString("make");
			String model = jsonarray.getJSONObject(i).getString("model");
			String year = jsonarray.getJSONObject(i).getString("year");
			long mileage = jsonarray.getJSONObject(i).getLong("mileage");
			String trim = jsonarray.getJSONObject(i).getString("trim");
			String bodyStyle = jsonarray.getJSONObject(i).getString("bodyStyle");
			String engine = jsonarray.getJSONObject(i).getString("engine");
			String transmission = jsonarray.getJSONObject(i).getString("transmission");
			String exteriorColor = jsonarray.getJSONObject(i).getString("exteriorColor");
			String interiorColor = jsonarray.getJSONObject(i).getString("interiorColor");
			long listPrice = jsonarray.getJSONObject(i).getLong("listPrice");
			String picture = jsonarray.getJSONObject(i).getString("picture");
			String ctx = jsonarray.getJSONObject(i).getString("ctx");

			jsonValue[0] = Integer.toString(i);
			jsonValue[1] = Long.toString(vehicleId);
			jsonValue[2] = vin;
			jsonValue[3] = stockNumber;
			jsonValue[4] = Boolean.toString(used);
			jsonValue[5] = Boolean.toString(certified);
			jsonValue[6] = dateReceived;
			jsonValue[7] = make;
			jsonValue[8] = model;
			jsonValue[9] = year;
			jsonValue[10] = Long.toString(mileage);
			jsonValue[11] = trim;
			jsonValue[12] = bodyStyle;
			jsonValue[13] = engine;
			jsonValue[14] = transmission;
			jsonValue[15] = exteriorColor;
			jsonValue[16] = interiorColor;
			jsonValue[17] = Long.toString(listPrice);
			jsonValue[18] = picture;
			jsonValue[19] = ctx;
			jsonValue[20] = sourceUnityID;

			cPP.com_libs.writeToSheet(resultfile, jsonValue);
			if ((i + 1) == size) {
				System.out.println("sourceUnityID=" + sourceUnityID + ".  Total inventory= " + (i + 1));
			}

			// System.out.println("" + i + ", " + vehicleId + ", " + vin + ", " + stockNumber + ", " + used + ", "
			// + certified + ", " + dateReceived + ", " + make + ", " + model + ", " + year + ", " + mileage + ", "
			// + trim + ", " + bodyStyle + ", " + engine + ", " + transmission + ", " + exteriorColor + ", "
			// + interiorColor + ", " + listPrice + ", " + picture + ", " + ctx);

		}
		System.out.println(sourceUnityID + "  - Stop here!!!");
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		// jSonObjec_SydicationWS();
		// jSonObjec_CPP_FeulEconomyWS();
		// jSonObjec_CPP_MasterFeatureWS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		jSonArry_UnityInventory(); // On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");

	}

}
