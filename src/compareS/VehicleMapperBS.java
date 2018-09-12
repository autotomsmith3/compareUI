package compareS;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class VehicleMapperBS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getGvuid() throws Exception {
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(VehicleMapperBS.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] styleIDs = cPP.com_libs.fetchOneDemArrayFromPropFile("styleID", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };

		String GetGvuidSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\getMapper_gvuids.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetGvuidBS = { "S/N", "StyleId_result:", "typeId:", "id:", "gvuid:" };

		// Prod: http://13.89.54.236:8080/vehicle-mapper-service/gvuid/StyleId/356695
		// DEV: http://lnoc-dvcp-xmw1.autodatacorp.org:8080/vehicle-mapper-service/gvuid/StyleId/356695
//		String envURL = "http://13.89.54.236:8080/vehicle-mapper-service/gvuid/StyleId/"; // Prod - not working on Jan 11,2017
		 String envURL = "http://lnoc-dvcp-xmw1.autodatacorp.org:8080/vehicle-mapper-service/gvuid/StyleId/"; //DEV

		int count = 0;
		for (String styleID : styleIDs) {
			count++;
			String getGvuidURL = envURL + styleID;
			String jsonTextFrGetGvuidBS = cPP.com_libs.getSourceCode(getGvuidURL, "");
			getVehicleGvuidDetails(GetGvuidSavePathFile, titleStringGetGvuidBS, jsonTextFrGetGvuidBS, styleID, count);

		}

	}

	public static void getVehicleGvuidDetails(String wsResultfile, String[] titleGetGvuid, String text, String styleid,
			int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleGetGvuid);

		String typeId = "";
		String id = "";
		String s_gvuid = "";

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = styleid;
			temp[2] = "404 error";
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				long serverTime = obj.getLong("serverTime");
				String serverTimeString = obj.getString("serverTimeString");
				String serverName = obj.getString("serverName");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");

				try {
					typeId = result.getString("typeId");
				} catch (Exception ex) {
					typeId = "null";
				}
				try {
					id = result.getString("id");
				} catch (Exception ex) {
					id = "null";
				}
				try {
					s_gvuid = result.getString("gvuid");
				} catch (Exception ex) {
					s_gvuid = "null";
				}

				System.out.println("S/N: " + countNum);
				System.out.println("StyleId_result: " + styleid);
				System.out.println("typeId: " + typeId);
				System.out.println("id: " + id);
				System.out.println("gvuid: " + s_gvuid);

				int wSize = titleGetGvuid.length;
				String[] jsonValue = new String[wSize];

				jsonValue[0] = Integer.toString(countNum);
				jsonValue[1] = styleid;
				jsonValue[2] = typeId;
				jsonValue[3] = id;
				jsonValue[4] = s_gvuid;

				cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = styleid;
				temp[2] = "200 error";
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		getGvuid();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
