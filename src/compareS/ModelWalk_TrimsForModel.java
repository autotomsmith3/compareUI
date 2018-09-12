package compareS;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class ModelWalk_TrimsForModel {
	private static int blank = 0;
	private static int noObj = 0;

	public static void TrimsForModel() throws Exception {
		String VehicleSetCode = "";
		String LngCode = "";
		String CountryCode = "";
		String modelId = "";
		String parameterString = "";
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(ModelWalk_TrimsForModel.class.getClassLoader()
					.getResourceAsStream("cPP_data/modelwalk.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[][] TrimsForModelParameter = cPP.com_libs.fetchArrayFromPropFile("TrimsForModel_Parameters", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };
		int testDataTotal = TrimsForModelParameter.length;
		int parametersize = TrimsForModelParameter[testDataTotal - 1].length;

		String TrimsForModelSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\TrimsForModel\\ModelWalk_TrimsForModel.xls";

		String[] titleStringTrimsForModelWS = { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS",
				"result", "filtersObj", "code", "label", "vehiclesObj", "trimId", "trim", "mfgCode", "pricingObj",
				"msrp", "filtersObj", "filterscode", "filterslabel" };
		// -/TrimsForModel/{VehicleSetCode}/{LngCode}/{CountryCode}/{modelId}
		// http://lnoc-q1cp-xws1.autodatacorp.org:8080/model-walk/rest/TrimsForModel/BASE/EN/CA/CAC70FOT11
		String envURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/model-walk/rest/TrimsForModel/";
		// int imageNums = 56;// default=34
		int count = 0;
		for (int Num = 0; Num <= testDataTotal - 1; Num++) {

			count++;
			VehicleSetCode = TrimsForModelParameter[Num][0];
			LngCode = TrimsForModelParameter[Num][1];
			CountryCode = TrimsForModelParameter[Num][2];
			modelId = TrimsForModelParameter[Num][3];

			parameterString = VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + modelId;
			String TrimsForModelURL = envURL + VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + modelId;
			String jsonTextFrTrimsForModelWS = cPP.com_libs.getSourceCode(TrimsForModelURL, "");
			TrimsForModelDetails(TrimsForModelSavePathFile, titleStringTrimsForModelWS, jsonTextFrTrimsForModelWS,
					TrimsForModelURL, parameterString, count);

		}
	}

	public static void TrimsForModelDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultObj = "";
		String filtersObj = "";
		String code = "";
		String label = "";
		String vehiclesObj = "";
		String trimId = "";
		String trim = "";
		String mfgCode = "";
		String pricingObj = "";
		String msrp = "";
		String vehiclefiltersObj = "";
		String filterscode = "";
		String filterslabel = "";
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

				JSONObject result = obj.getJSONObject("result");
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

					try {
						JSONArray filterObject = result.getJSONArray("filters");
						int filterSize = filterObject.length();
						filtersObj = Integer.toString(filterSize);

						if (filterSize == 0) {
							System.out.println("S/N: " + countNum);
							System.out.println("executionTimeMS: " + executionTimeMS);
							System.out.println("URLString: " + URLString);
							// { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
							// "filtersObj", "code", "label" , "vehiclesObj" , "trimId", "trim" , "mfgCode" , "pricingObj", "msrp" , "vehiclefiltersObj", "filterscode", "filterslabel"};
							int wSize = titleString.length;
							String[] jsonValue = new String[wSize];
							jsonValue[0] = Integer.toString(countNum);
							jsonValue[1] = URLString;
							jsonValue[2] = serverTime;
							jsonValue[3] = error;
							jsonValue[4] = executionTimeMS;
							jsonValue[5] = resultObj;
							jsonValue[6] = filtersObj;
							jsonValue[7] = "";
							jsonValue[8] = "";
							jsonValue[9] = "";
							jsonValue[10] = "";
							jsonValue[11] = "";
							jsonValue[12] = "";
							jsonValue[13] = "";
							jsonValue[14] = "";
							jsonValue[15] = "";
							jsonValue[16] = "";
							jsonValue[17] = "";
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
						}

						for (int i = 0; i < filterSize; i++) {
							try {
								code = filterObject.getJSONObject(i).getString("code");
							} catch (Exception ex) {
								code = "null";
							}
							try {
								label = filterObject.getJSONObject(i).getString("label");
							} catch (Exception ex) {
								label = "null";
							}
							System.out.println("S/N: " + countNum);
							System.out.println("executionTimeMS: " + executionTimeMS);
							System.out.println("URLString: " + URLString);
							// { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
							// "filtersObj", "code", "label" , "vehiclesObj" , "trimId", "trim" , "mfgCode" , "pricingObj", "msrp" , "vehiclefiltersObj", "filterscode", "filterslabel"};
							int wSize = titleString.length;
							String[] jsonValue = new String[wSize];
							jsonValue[0] = Integer.toString(countNum);
							jsonValue[1] = URLString;
							jsonValue[2] = serverTime;
							jsonValue[3] = error;
							jsonValue[4] = executionTimeMS;
							jsonValue[5] = resultObj;
							jsonValue[6] = filtersObj;
							jsonValue[7] = code;
							jsonValue[8] = label;
							jsonValue[9] = "";
							jsonValue[10] = "";
							jsonValue[11] = "";
							jsonValue[12] = "";
							jsonValue[13] = "";
							jsonValue[14] = "";
							jsonValue[15] = "";
							jsonValue[16] = "";
							jsonValue[17] = "";
							// jsonValue[18] = ;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

						}

					} catch (Exception ex) {
						filtersObj = "null";
						System.out.println("S/N: " + countNum);
						System.out.println("executionTimeMS: " + executionTimeMS);
						System.out.println("URLString: " + URLString);
						// { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
						// "filtersObj", "code", "label" , "vehiclesObj" , "trimId", "trim" , "mfgCode" , "pricingObj", "msrp" , "vehiclefiltersObj", "filterscode", "filterslabel"};
						int wSize = titleString.length;
						String[] jsonValue = new String[wSize];
						jsonValue[0] = Integer.toString(countNum);
						jsonValue[1] = URLString;
						jsonValue[2] = serverTime;
						jsonValue[3] = error;
						jsonValue[4] = executionTimeMS;
						jsonValue[5] = resultObj;
						jsonValue[6] = filtersObj;
						jsonValue[7] = "";
						jsonValue[8] = "";
						jsonValue[9] = "";
						jsonValue[10] = "";
						jsonValue[11] = "";
						jsonValue[12] = "";
						jsonValue[13] = "";
						jsonValue[14] = "";
						jsonValue[15] = "";
						jsonValue[16] = "";
						jsonValue[17] = "";
						cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

					}

					try {
						JSONArray vehiclesObject = result.getJSONArray("vehicles");
						int vehiclesSize = vehiclesObject.length();
						vehiclesObj = Integer.toString(vehiclesSize);

						if (vehiclesSize == 0) {
							System.out.println("S/N: " + countNum);
							System.out.println("executionTimeMS: " + executionTimeMS);
							System.out.println("URLString: " + URLString);
							// { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
							// "filtersObj", "code", "label" , "vehiclesObj" , "trimId", "trim" , "mfgCode" , "pricingObj", "msrp" , "vehiclefiltersObj", "filterscode", "filterslabel"};
							int wSize = titleString.length;
							String[] jsonValue = new String[wSize];
							jsonValue[0] = Integer.toString(countNum);
							jsonValue[1] = URLString;
							jsonValue[2] = serverTime;
							jsonValue[3] = error;
							jsonValue[4] = executionTimeMS;
							jsonValue[5] = resultObj;
							jsonValue[6] = filtersObj;
							jsonValue[7] = code;
							jsonValue[8] = label;
							jsonValue[9] = vehiclesObj;
							jsonValue[10] = "";
							jsonValue[11] = "";
							jsonValue[12] = "";
							jsonValue[13] = "";
							jsonValue[14] = "";
							jsonValue[15] = "";
							jsonValue[16] = "";
							jsonValue[17] = "";
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
						}

						for (int i = 0; i < vehiclesSize; i++) {
							try {
								trimId = vehiclesObject.getJSONObject(i).getString("trimId");
							} catch (Exception ex) {
								trimId = "null";
							}
							try {
								trim = vehiclesObject.getJSONObject(i).getString("trim");
							} catch (Exception ex) {
								trim = "null";
							}
							try {
								mfgCode = vehiclesObject.getJSONObject(i).getString("mfgCode");
							} catch (Exception ex) {
								mfgCode = "null";
							}
							try {
								JSONObject pricingObject = vehiclesObject.getJSONObject(i).getJSONObject("pricing");
								msrp = Integer.toString(pricingObject.getInt("msrp"));
							} catch (Exception ex) {
								msrp = "null";
							}
							JSONArray vehiclefiltersObject = vehiclesObject.getJSONObject(i).getJSONArray("filters");
							int vehiclefiltersSize = vehiclefiltersObject.length();
							vehiclefiltersObj = Integer.toString(vehiclefiltersSize);
							if (vehiclefiltersSize == 0) {
								System.out.println("S/N: " + countNum);
								System.out.println("executionTimeMS: " + executionTimeMS);
								System.out.println("URLString: " + URLString);
								// { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
								// "filtersObj", "code", "label" , "vehiclesObj" , "trimId", "trim" , "mfgCode" , "pricingObj", "msrp" , "vehiclefiltersObj", "filterscode", "filterslabel"};
								int wSize = titleString.length;
								String[] jsonValue = new String[wSize];
								jsonValue[0] = Integer.toString(countNum);
								jsonValue[1] = URLString;
								jsonValue[2] = serverTime;
								jsonValue[3] = error;
								jsonValue[4] = executionTimeMS;
								jsonValue[5] = resultObj;
								jsonValue[6] = filtersObj;
								jsonValue[7] = "";
								jsonValue[8] = "";
								jsonValue[9] = vehiclesObj;
								jsonValue[10] = trimId;
								jsonValue[11] = trim;
								jsonValue[12] = mfgCode;
								jsonValue[13] = pricingObj;
								jsonValue[14] = msrp;
								jsonValue[15] = vehiclefiltersObj;
								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
							}
							for (int j = 0; j < vehiclefiltersSize; j++) {

								filterscode = vehiclefiltersObject.getJSONObject(j).getString("code");
								filterslabel = vehiclefiltersObject.getJSONObject(j).getString("label");
								System.out.println("S/N: " + countNum);
								System.out.println("executionTimeMS: " + executionTimeMS);
								System.out.println("URLString: " + URLString);
								// { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS", "result",
								// "filtersObj", "code", "label" , "vehiclesObj" , "trimId", "trim" , "mfgCode" , "pricingObj", "msrp" , "vehiclefiltersObj", "filterscode", "filterslabel"};
								int wSize = titleString.length;
								String[] jsonValue = new String[wSize];
								jsonValue[0] = Integer.toString(countNum);
								jsonValue[1] = URLString;
								jsonValue[2] = serverTime;
								jsonValue[3] = error;
								jsonValue[4] = executionTimeMS;
								jsonValue[5] = resultObj;
								jsonValue[6] = filtersObj;
								jsonValue[7] = "";
								jsonValue[8] = "";
								jsonValue[9] = vehiclesObj;
								jsonValue[10] = trimId;
								jsonValue[11] = trim;
								jsonValue[12] = mfgCode;
								jsonValue[13] = pricingObj;
								jsonValue[14] = msrp;
								jsonValue[15] = vehiclefiltersObj;
								jsonValue[16] = filterscode;
								jsonValue[17] = filterslabel;
								// jsonValue[18] = ;
								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							}

						}

					} catch (Exception ex) {
						resultObj = "null";
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
		TrimsForModel();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
