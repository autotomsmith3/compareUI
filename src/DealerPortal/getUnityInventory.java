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
		// String inventoryJSONText = "[ { \"vehicleId\": 67793090, \"vin\": \"1G6AN1RY9G0101247\", \"stockNumber\": \"G0101247\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS-V Coupe\",
		// \"year\": \"2016\", \"mileage\": 0, \"trim\": \"xxx\", \"bodyStyle\": \"2dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Gray\", \"interiorColor\": \"Black\", \"listPrice\": 76057, \"picture\":
		// \"http://inventory-dmg.assets-cdk.com/4/4/3/13352307344.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002238, \"vin\": \"1G6AB5SS4G0109293\", \"stockNumber\": \"G0109293\",
		// \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\": \"Cadillac\", \"model\": \"ATS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"Luxury Collection RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6
		// cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\": \"Black\", \"listPrice\": 47555, \"picture\": \"http://inventory-dmg.assets-cdk.com/8/4/6/13352307648.jpg\", \"ctx\": \"BVJR64Q\" }, { \"vehicleId\": 68002241, \"vin\":
		// \"1G6AV5S82G0113631\", \"stockNumber\": \"G0113631\", \"used\": false, \"certified\": false, \"dateReceived\": \"2016-08-24T00:00:00\", \"make\":
		// \"Cadillac\", \"model\": \"CTS Sedan\", \"year\": \"2016\", \"mileage\": 0, \"trim\": \"V-Sport Premium RWD\", \"bodyStyle\": \"4dr Car\", \"engine\": \"3.6L 6 cyl\", \"transmission\": \"Automatic\", \"exteriorColor\": \"Black\", \"interiorColor\":
		// \"Black\", \"listPrice\": 73735, \"picture\": \"http://inventory-dmg.assets-cdk.com/5/4/9/13352307945.jpg\", \"ctx\": \"BVJR64Q\" } ]";
		// getUnityWorkInventoryFrJSONParser(savePathFile,titleString, inventoryJSONText, "testID");// Array OK - start with [ characters (square brackets - array)

		// String unityWorkURL = "https://data.dealervideos.com/v0.9/vehicles/";
		String unityWorkURL = "https://data.dealervideos.com/v1.0/vehicles/"; // see VDVIIMG-759 - 20190306 from vinstatus: (unityworks.altid;275D2EQ): https://data.dealervideos.com/v1.0/vehicles/275D2EQ/3GNCJKSB4KL242291
		// ********************** This will get more images URLs https://data.dealervideos.com/v1.0/vehicles/BIZ2TXN/KL4CJESB9GB608047 //*******

		// From Prod on January 31, 2018 total 44. Please see details on AUTOpx.xlsx on Prod_UnityWorkID table
		// String[] unityWorkDealerShipID = {"27OE966","DP9B0OU","112Y2JUB","2FHFIYI"};

		// 493IVV2
		// below is full ids work=yes on October 18, 2019
//		String[] unityWorkDealerShipID = { "10DWC56S", "10UUQ6J0", "10V692JH", "10VC50MZ", "112Y2JUB", "11BW9H2I",
//				"11CN47A9", "11D0Y1R8", "11FK6G5J", "11FYPTW0", "11GFTTMR", "11GLU8DF", "11J18GNF", "11JQM19D",
//				"11OTGAXK", "19D6Q4P", "1DACJ4G", "1E543A9", "1QWGIJS", "1V0BJMB", "1V2FAJZ", "1Y6F36U", "20A1B9C",
//				"246GNLU", "275D2EQ", "27IBZXO", "27OE966", "2BDCDUA", "2CT1GBZ", "2D962HH", "2FC1ZF6", "2FDCAJ5",
//				"2FEASPJ", "2FFF58W", "2FHFIYI", "2FV5V6U", "2G3BOM8", "2GELMFG", "2IM8V9R", "2IN1YG0", "2JH38WM",
//				"2KAGUTE", "2NN4M8F", "2OLET45", "2P5ABRK", "2QO23PC", "2QQD4R1", "2RRG76P", "2RV24ZM", "2UHFMRX",
//				"2UWAMX2", "2V0F2Q8", "2VM1YSY", "2Z93TKG", "2ZA75G7", "2ZLGTS2", "30OE50J", "32Y1PCW", "33B5OGV",
//				"33M1H95", "37B998C", "37P3L8Q", "3AOCXEK", "3D72K6I", "3ELG9F7", "3F6D163", "3FB2YCC", "3GF7WKT",
//				"3J6F6HT", "3JTH6TX", "3K3GR5N", "3LAC8ND", "3QXAM0S", "3RY5KFE", "3S09LIW", "3SG8XJ1", "3T2EOSE",
//				"48YFTUA", "493IVV2", "4B5CDSU", "4CZ4ER3", "4DI7BBA", "4FGC316", "4OQ33O4", "7QB5LYY", "7TI6EUF",
//				"7VC73SH", "7Z2EDFX", "80479CR", "82P7HWC", "8418N7S", "85D7QI6", "8EU6HMR", "8IU4NI2", "917EKQ6",
//				"91F3YRQ", "9A880Q7", "9MD753B", "9MT8KBA", "9P93FSC", "9VBBXKX", "9XPGMW9", "ARQFM74", "BB77RAC",
//				"BBM3FGT", "BF32R16", "BIZ2TXN", "BMUBRUK", "BN3H7HP", "BN5ECJP", "BNB8B19", "BPNGGA4", "BQOCAM0",
//				"BTX24S6", "BUNDG1V", "BW83FV1", "BX3EWMP", "BX72MDM", "C03EBOJ", "C3MC3TL", "C4QWU9Y", "C6C1MBD",
//				"CE7C54X", "CFO7UIZ", "CGPB0MT", "CH140C1", "CUG4744", "CY05E0F", "D29DGRX", "D2QGVYI", "D2ZDQQW",
//				"DA7C289", "DDF4FME", "DET66GO", "DGK5V9L", "DGLB2FH", "DJX6JEP", "DP9B0OU", "DRE4C5R", "E9D6FCA",
//				"EEY9NYG", "EH1UY6J", "EHJF4K9", "EHZ1O0U", "EJU7HGM", "EPY41RX", "ESW5ME9", "EUO7OIT", "EVL8ODB",
//				"EVV73HI", "F2L5S40", "F2NF3UN", "F30FQ97", "F4E9Q64", "F72BCJN", "FB7CIB0", "FB98MIY", "FGN8TC3",
//				"FMDWAD3", "FUTAFJN", "FX592ZN", "FX9CQKC", "G08CAFX", "G64264O", "GG81MN0", "GKD73H5", "GKLFUO3",
//				"GMRCHL5", "GNCD1MS", "GNYD218", "GQ22UIC", "GV97K2O", "GWBF9Y3", "H5O4M8B", "HAQETMB", "HH0ESG0",
//				"HI0ER7N", "HQO47S3", "HSOFV9O", "HVBFDQZ", "HZO9BHL", "I8X52IT", "IKL88QJ", "ISG2K4A", "IVFENHM",
//				"J242HQX", "J5LCP07", "JZZCJS8", "K30D73Y", "K6XG7MI", "KAMF96C", "KBY8P0Y", "KDK7Q4M", "KHE7LSP",
//				"KJ0G41K", "KQO8CVH", "KTL1B32", "KUQ5EG8", "KW66VNM", "KWDER6A", "KYFFYM4", "L4Z70X3", "L5KDIPI",
//				"L87CBZX", "LK174X8", "N7I80QB", "OCN5TPF", "OM95VHT", "OVGH22E", "PI3BDJY", "PL9CWKZ", "PPHGS6Z",
//				"R0AAKW2", "R504FV8", "SGE95S0", "SLOEASO", "SW1B3J6", "SZ9CRGW", "T5H57YY", "TDRARWM", "TH46625",
//				"TIFFY3P", "U6Y76GP", "V5254PG", "W97TIGT", "X8IZNFE", "Y6LS1GS", "YXDADRM", "ZFEQR7R", "ZGX4FNH",
//				"ZVQNOZO" };// Total 231 - October 18, 2019

//		 // below is full ids on April 10, 2019 from Prod Metadata unityWorks ids
//		 String[] unityWorkDealerShipID = { "10V692JH", "112Y2JUB", "11BW9H2I", "11D0Y1R8", "11FDEWDR", "11FK6G5J",
//		 "11FYPTW0", "11GFTTMR", "11GLU8DF", "11J18GNF", "11JQM19D", "11OTGAXK", "1QWGIJS", "1V0BJMB", "246GNLU",
//		 "275D2EQ", "27OE966", "2BDCDUA", "2D962HH", "2FHFIYI", "2FV5V6U", "2JH38WM", "2OLET45", "2QO23PC",
//		 "2ZLGTS2", "33M1H95", "3AOCXEK", "3QXAM0S", "4B5CDSU", "8EU6HMR", "8IU4NI2", "9L83MUH", "ARQFM74",
//		 "BX3EWMP", "BX72MDM", "C4QWU9Y", "C6C1MBD", "CGPB0MT", "COA94NS", "D29DGRX", "D2QGVYI", "D2ZDQQW",
//		 "DP9B0OU", "DVAESPR", "EH1UY6J", "ESW5ME9", "EVV73HI", "F72BCJN", "FB7CIB0", "FGN8TC3", "FX592ZN",
//		 "G08CAFX", "G4WGL1P", "GQ22UIC", "H5O4M8B", "HAQETMB", "HI0ER7N", "HSOFV9O", "HVBFDQZ", "IVFENHM",
//		 "JZZCJS8", "KAMF96C", "KJ0G41K", "KML7F2M", "KTL1B32", "KW66VNM", "KWDER6A", "L87CBZX", "N7I80QB",
//		 "OCN5TPF", "R0AAKW2", "R504FV8", "SLOEASO", "SZ9CRGW", "T5H57YY", "t6k7yx5", "U6Y76GP", "V5254PG",
//		 "W97TIGT", "X8IZNFE", "Y6LS1GS", "ZFEQR7R", "ZGX4FNH", "ZVQNOZO"};// Total 84 - April 10 - 11, 2019 from Prod Metadata valid unityWorks ids

		// String[] unityWorkDealerShipID = { "10V692JH", "112Y2JUB", "11BW9H2I", "11D0Y1R8", "11FDEWDR", "11FK6G5J",
		// "11FYPTW0", "11GFTTMR", "11GLU8DF", "11J18GNF", "11JQM19D", "11OTGAXK", "1QWGIJS", "1V0BJMB", "246GNLU",
		// "275D2EQ", "27OE966", "2BDCDUA", "2D962HH", "2FHFIYI", "2FV5V6U", "2JH38WM", "2OLET45", "2QO23PC",
		// "2ZLGTS2", "33M1H95", "3AOCXEK", "3QXAM0S", "4B5CDSU", "8EU6HMR", "8IU4NI2", "9L83MUH", "ARQFM74",
		// "BX3EWMP", "BX72MDM", "C4QWU9Y", "C6C1MBD", "CGPB0MT", "COA94NS", "D29DGRX", "D2QGVYI", "D2ZDQQW",
		// "DP9B0OU", "DVAESPR", "EH1UY6J", "ESW5ME9", "EVV73HI", "F72BCJN", "FB7CIB0", "FGN8TC3", "FX592ZN",
		// "G08CAFX"};//GMRCHL5 - is for FCA
		// String[] unityWorkDealerShipID = {"2L41W3M"};// 2L41W3M-no exterior, no trim - 2019-02-12
		// String[] unityWorkDealerShipID = {"GMRCHL5"};//GMRCHL5 FCA only - provided by Drew on 2018-12-10 via Teams
		// String[] unityWorkDealerShipID = {"F72BCJN","27OE966"};//F72BCJN - Hyundai, 27OE966 - Honda 20190211
		// String[] unityWorkDealerShipID = {"11FDEWDR","2FHFIYI","DP9B0OU","GMRCHL5"};// 20190529-testing for 1728
		// String[] unityWorkDealerShipID = {"KTL1B32"};// 20191001-testing Buick Encore for 2008
		// String[] unityWorkDealerShipID = {"GMRCHL5"};// 20191002-testing Chrysler for 2008
		// String[] unityWorkDealerShipID = {"1WB6RYW"};// 20191002-testing Chrysler for 2008 1WB6RYW - jeep
//		String[] unityWorkDealerShipID = { "H5O4M8B", "275D2EQ", "27IBZXO", "27OE966", "2BDCDUA", "2CT1GBZ", "2D962HH",
//				"2FC1ZF6", "2FDCAJ5", "2FEASPJ", "2FFF58W", "2FHFIYI", "2FV5V6U" };// 20200130-20200203 testing select all OEM/Brands for 2388
		String[] unityWorkDealerShipID = {"KHE7LSP"};//20200204 VW dealer
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
		String exteriorColor = "";
		String interiorColor = "";
		String trim = "";
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
			try {
				trim = jsonarray.getJSONObject(i).getString("trim");
			} catch (Exception e) {
				e.printStackTrace();
				trim = "null";
			}
			String bodyStyle = jsonarray.getJSONObject(i).getString("bodyStyle");
			String engine = jsonarray.getJSONObject(i).getString("engine");
			String transmission = jsonarray.getJSONObject(i).getString("transmission");
			try {
				exteriorColor = jsonarray.getJSONObject(i).getString("exteriorColor");
			} catch (Exception e) {
				e.printStackTrace();
				exteriorColor = "null";
			}
			try {
				interiorColor = jsonarray.getJSONObject(i).getString("interiorColor");
			} catch (Exception e) {
				e.printStackTrace();
				interiorColor = "null";
			}
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
		System.out.println(sourceUnityID + "  - is complete!!! Continue......");
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

		System.out.println("\nCompleted!!!");

	}

}
