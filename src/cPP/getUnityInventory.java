package cPP;

import java.io.IOException;

import org.json.JSONArray;

public class getUnityInventory {

	public static void jSonArry_UnityInventory() throws IOException {
		String[] titleString = { "S/N", "vehicleId:", "vin:", "stockNumber:", "used:", "certified:", "dateReceived:",
				"make:", "model:", "year:", "mileage:", "trim:", "bodyStyle:", "engine:", "transmission:",
				"exteriorColor:", "interiorColor:", "listPrice:", "picture:", "ctx:", "unityWrokID" };
		String savePathFile = "C:\\1\\Eclipse\\Test Results\\UnityInventory\\UnityInventory_JSON_Return.xls";

		//
		// String inventoryJSONText = "[ { \"vehicleId\": 67793090, \"vin\": \"1G6AN1RY9G0101247\", \"stockNumber\": \"G0101247\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS-V Coupe\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"xxx\", \"bodyStyle\": \"2dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Gray\", \"interiorColor\": \"Black\", \"listPrice\": 76057, \"picture\": \"http://inventory-dmg.assets-cdk.com/4/4/3/13352307344.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002238, \"vin\": \"1G6AB5SS4G0109293\", \"stockNumber\": \"G0109293\",
		// \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"Luxury Collection RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 47555, \"picture\": \"http://inventory-dmg.assets-cdk.com/8/4/6/13352307648.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002241, \"vin\": \"1G6AV5S82G0113631\", \"stockNumber\": \"G0113631\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\":
		// \"Cadillac\", \"model\": \"CTS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"V-Sport Premium RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 73735, \"picture\": \"http://inventory-dmg.assets-cdk.com/5/4/9/13352307945.jpg\", \"ctx\": \"BVJR64Q\" } ]";
		// getUnityWorkInventoryFrJSONParser(savePathFile,titleString, inventoryJSONText, "testID");// Array OK - start with [ characters (square brackets - array)

		// String unityWorkURL = "https://data.dealervideos.com/v0.9/vehicles/";
		String unityWorkURL = "https://data.dealervideos.com/v1.0/vehicles/"; // see VDVIIMG-759
		// String[] unityWorkDealerShipID = {"FB7CIB0"};//{ "4B5CDSU", "2FHFIYI" };// {"4B5CDSU","7U9G76C","DP9B0OU","1V0BJMB","2FHFIYI","DTK5S8O","FB7CIB0","COA94NS","D2E8HW2","PDZE4CK","246GNLU","F2NF3UN","CGPB0MT","HSOFV9O","27OE966"};//{"4B5CDSU","7U9G76C","DP9B0OU","1V0BJMB","2FHFIYI","DTK5S8O","FB7CIB0","COA94NS","D2E8HW2","PDZE4CK","246GNLU","F2NF3UN","CGPB0MT","HSOFV9O","27OE966"};
		String[] unityWorkDealerShipID = { "4B5CDSU", "7U9G76C", "DP9B0OU", "1V0BJMB", "2FHFIYI", "DTK5S8O", "FB7CIB0",
				"COA94NS", "D2E8HW2", "PDZE4CK", "246GNLU", "F2NF3UN", "CGPB0MT", "HSOFV9O", "27OE966" };// {"4B5CDSU","7U9G76C","DP9B0OU","1V0BJMB","2FHFIYI","DTK5S8O","FB7CIB0","COA94NS","D2E8HW2","PDZE4CK","246GNLU","F2NF3UN","CGPB0MT","HSOFV9O","27OE966"};
		// no longer have inventory: "1V0BJMB",PDZE4CK
		for (String id : unityWorkDealerShipID) {
			String jsonTextFrUnityWork = com_libs.getSourceCode(unityWorkURL, id);
			if (jsonTextFrUnityWork.equalsIgnoreCase("")) {
				System.out.println("id=" + id + "  does not have any inventory!\n");
				// getUnityWorkInventoryFrJSONParser(savePathFile, titleString, "No inventory", id);
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
		com_libs.writeTitle(resultfile, titleString);
		System.out.println("S/N" + ", vehicleId:" + ", vin:" + ", stockNumber:" + ", used:" + ", certified:"
				+ ", dateReceived:" + ", make:" + ", model:" + ", year:" + ", mileage:" + ", trim:" + ", bodyStyle:"
				+ ", engine:" + ", transmission:" + ", exteriorColor:" + ", interiorColor:" + ", listPrice:"
				+ ", picture:" + ", ctx:");

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

			com_libs.writeToSheet(resultfile, jsonValue);

			// System.out.println("" + i + ", " + vehicleId + ", " + vin + ", " + stockNumber + ", " + used + ", "
			// + certified + ", " + dateReceived + ", " + make + ", " + model + ", " + year + ", " + mileage + ", "
			// + trim + ", " + bodyStyle + ", " + engine + ", " + transmission + ", " + exteriorColor + ", "
			// + interiorColor + ", " + listPrice + ", " + picture + ", " + ctx);

		}
		System.out.println("id=" + sourceUnityID + " is complete!");
		System.out.println("Stop here!!!");
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
