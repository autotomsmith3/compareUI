package compareS;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class ModelWalk_ModelsForDivision {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getModelsForDivision() throws Exception {
		String VehicleSetCode = "";
		String LngCode = "";
		String CountryCode = "";
		String year = "";
		String division = "";
		String genericColor = "";
		String parameterString = "";
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(ModelWalk_ModelsForDivision.class.getClassLoader()
					.getResourceAsStream("cPP_data/modelwalk.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[][] modelParameter = cPP.com_libs.fetchArrayFromPropFile("modelsForDivision_Parameters", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };
		int testDataTotal = modelParameter.length;
		int parametersize = modelParameter[testDataTotal - 1].length;

		String GetModelsForDivisionSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\ModelsForDivision\\ModelWalk_ModelsForDivision.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetModelsForDivisionWS = { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS",
				"result", "modelYearId", "year", "make", "model", "segment", "pricing_Obj:", "destinationCharge",
				"msrp", "invoice", "dealerNet", "doubleNet", "tripleNet", "media", "mediaImage" };

		// http://lnoc-q1cp-xws1.autodatacorp.org:8080/model-walk/rest/ModelsForDivision/BASE/EN/CA/2017/Buick?genericColor=Black
		// /ModelsForDivision/{VehicleSetCode}/{LngCode}/{CountryCode}/{Year}/{division}?GenericColor={genericColor}
		String envURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/model-walk/rest/ModelsForDivision/";
		String genericColorString = "?genericColor=";
		String getModelsForDivisionURL = "";
		int count = 0;
		for (int Num = 0; Num <= testDataTotal - 1; Num++) {
			// for (String gvuid : modelParameter[imageNum]) {
			count++;
			VehicleSetCode = modelParameter[Num][0];
			LngCode = modelParameter[Num][1];
			CountryCode = modelParameter[Num][2];
			year = modelParameter[Num][3];
			division = modelParameter[Num][4];
			int genericColorExist = modelParameter[Num].length;
			if (genericColorExist == 6) { // has genericColor like Black, Red...
				genericColor = modelParameter[Num][5];
				parameterString = VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year + "/" + division
						+ genericColorString + genericColor;
				getModelsForDivisionURL = envURL + VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year + "/"
						+ division + genericColorString + genericColor;

			} else {

				genericColor = "";
				parameterString = VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year + "/" + division;
				getModelsForDivisionURL = envURL + VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year + "/"
						+ division;

			}

			String jsonTextFrGetModelsForDivisionWS = cPP.com_libs.getSourceCode(getModelsForDivisionURL, "");
			getModelsForDivisionDetails(GetModelsForDivisionSavePathFile, titleStringGetModelsForDivisionWS,
					jsonTextFrGetModelsForDivisionWS, getModelsForDivisionURL, parameterString, count);

			// }
		}
	}

	public static void getModelsForDivisionDetails(String wsResultfile, String[] titleString, String text,
			String URLString, String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultsize = "";
		String modelYearId = "";
		String year = "";
		String make = "";
		String model = "";
		String segment = "";
		String pricing = "";
		String destinationCharge = "";
		String msrp = "";
		String invoice = "";
		String dealerNet = "";
		String doubleNet = "";
		String tripleNet = "";
		String media = "";
		String mediaImage = "";

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
				resultsize = Integer.toString(size);
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
							year = result.getJSONObject(i).getString("year");
						} catch (Exception ex) {
							year = "";
						}
						try {
							make = result.getJSONObject(i).getString("make");
						} catch (Exception ex) {
							make = "null";
						}
						try {
							model = result.getJSONObject(i).getString("model");
						} catch (Exception ex) {
							model = "null";
						}
						try {
							segment = result.getJSONObject(i).getString("segment");
						} catch (Exception ex) {
							segment = "null";
						}
						try {
							JSONObject pricingObj = result.getJSONObject(i).getJSONObject("pricing");
							int pricingSize = pricingObj.length();
							pricing = Integer.toString(pricingSize);
							destinationCharge = Integer.toString(pricingObj.getInt("destinationCharge"));
							msrp = Integer.toString(pricingObj.getInt("msrp"));
							invoice = Integer.toString(pricingObj.getInt("invoice"));
							dealerNet = Integer.toString(pricingObj.getInt("dealerNet"));
							doubleNet = Integer.toString(pricingObj.getInt("doubleNet"));
							try {
								tripleNet = Integer.toString(pricingObj.getInt("tripleNet"));
							} catch (Exception ex) {
								tripleNet = "null";
							}
						} catch (Exception ex) {
							pricing = "null";
							destinationCharge = "null";
							msrp = "null";
							invoice = "null";
							dealerNet = "null";
							doubleNet = "null";
						}
						try {
							JSONObject mediaObj = result.getJSONObject(i).getJSONObject("media");
							int mediaSize = mediaObj.length();
							media = Integer.toString(mediaSize);
							mediaImage = mediaObj.getString("mediaImage");
						} catch (Exception ex) {
							media = "null";
							mediaImage = "null";
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
						jsonValue[5] = resultsize;
						jsonValue[6] = modelYearId;
						jsonValue[7] = year;
						jsonValue[8] = make;
						jsonValue[9] = model;
						jsonValue[10] = segment;
						jsonValue[11] = pricing;
						jsonValue[12] = destinationCharge;
						jsonValue[13] = msrp;
						jsonValue[14] = invoice;
						jsonValue[15] = dealerNet;
						jsonValue[16] = doubleNet;
						jsonValue[17] = tripleNet;
						jsonValue[18] = media;
						jsonValue[19] = mediaImage;

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
		getModelsForDivision();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
