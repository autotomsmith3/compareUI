package compareS;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class ModelWalk_Models {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getModels() throws Exception {
		String VehicleSetCode = "";
		String LngCode = "";
		String CountryCode = "";
		String year = "";
		String makeId = "";
		String parameterString = "";
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(ModelWalk_Models.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[][] modelParameter = cPP.com_libs.fetchArrayFromPropFile("models_Parameters", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };
		int testDataTotal = modelParameter.length;
		int parametersize = modelParameter[testDataTotal - 1].length;

		String GetModelsSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\Models\\ModelWalk_Models.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetModelsWS = { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
				"modelYearId", "model" };

		// http://lnoc-q1cp-xws1.autodatacorp.org:8080/model-walk/rest/models/BASE/EN/US/2017/BU
		String envURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/model-walk/rest/models/";
		// int imageNums = 56;// default=34
		int count = 0;
		for (int Num = 0; Num <= testDataTotal - 1; Num++) {
			// for (String gvuid : modelParameter[imageNum]) {
			count++;
			VehicleSetCode = modelParameter[Num][0];
			LngCode = modelParameter[Num][1];
			CountryCode = modelParameter[Num][2];
			year = modelParameter[Num][3];
			makeId = modelParameter[Num][4];
			parameterString = VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year + "/" + makeId;
			String getModelsURL = envURL + VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year + "/"
					+ makeId;
			String jsonTextFrGetModelsWS = cPP.com_libs.getSourceCode(getModelsURL, "");
			getModelsDetails(GetModelsSavePathFile, titleStringGetModelsWS, jsonTextFrGetModelsWS, getModelsURL,
					parameterString, count);

			// }
		}
	}

	public static void getModelsDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultObj = "";
		String modelYearId = "";
		String model = "";
		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = URLString;
			temp[2] = "";
			temp[3] = "404 error";
			System.out.println("S/N: " + countNum);
			System.out.println("404 ERROR ON : " + URLString);
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				serverTime = obj.getString("serverTime");
				error = Boolean.toString(obj.getBoolean("error"));
				executionTimeMS = Long.toString(obj.getLong("executionTimeMS"));

				JSONArray result = obj.getJSONArray("result");
				int size = result.length();
				resultObj = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = "";
					temp[3] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + "  " + " - Result [] object is blank. ");

				} else {
					for (int i = 0; i < size; i++) {
						try {
							modelYearId = result.getJSONObject(i).getString("modelYearId");
						} catch (Exception ex) {
							modelYearId = "null";
						}
						try {
							model = result.getJSONObject(i).getString("model");
						} catch (Exception ex) {
							model = "null";
						}
						System.out.println("S/N: " + countNum);
						System.out.println("executionTimeMS: " + executionTimeMS);
						System.out.println("URLString: " + URLString);

						int wSize = titleString.length;
						String[] jsonValue = new String[wSize];
						jsonValue[0] = Integer.toString(countNum);
						jsonValue[1] = URLString;
						jsonValue[2] = serverTime;
						jsonValue[3] = error;
						jsonValue[4] = executionTimeMS;
						jsonValue[5] = resultObj;
						jsonValue[6] = modelYearId;
						jsonValue[7] = model;
						cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

					}
				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = URLString;
				temp[2] = "";
				temp[3] = "200 error";
				System.out.println("S/N: " + countNum);
				System.out.println("ERROR 200 ON : " + URLString);
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getModels();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
