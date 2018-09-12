package compareS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class ModelWalk_MakeModel {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getMakeModel() throws Exception {
		String VehicleSetCode = "";
		String LngCode = "";
		String CountryCode = "";
		String year = "";
		String makeId = "";
		String parameterString = "";
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(ModelWalk_MakeModel.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[][] modelParameter = cPP.com_libs.fetchArrayFromPropFile("MakeModel_Parameters", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };
		int testDataTotal = modelParameter.length;
		int parametersize = modelParameter[testDataTotal - 1].length;

		String GetMakeModelSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\MakeModel\\ModelWalk_MakeModel.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetMakeModelWS = { "S/N", "URL_parameter", "serverTime", "error", "executionTimeMS",
				"resultObj", "id", "make", "modelsObj", "modelYearId", "model"};
		;
		// /makemodel/{VehicleSetCode}/{LngCode}/{CountryCode}/{year}
		// DEV: http://lnoc-dvcp-xws1:8080/model-walk/rest/makemodel/BASE/EN/US/2016
		// QA: http://lnoc-q1cp-xws1:8080/model-walk/rest/makemodel/BASE/EN/US/2016
		// QA: http://lnoc-q1cp-xws1:8080/model-walk/rest/makemodel/STYLEID/EN/US/2017
		String envURL = "http://lnoc-q1cp-xws1:8080/model-walk/rest/makemodel/";
		// int imageNums = 56;// default=34
		int count = 0;
		for (int Num = 0; Num <= testDataTotal - 1; Num++) {
			// for (String gvuid : modelParameter[imageNum]) {
			count++;
			VehicleSetCode = modelParameter[Num][0];
			LngCode = modelParameter[Num][1];
			CountryCode = modelParameter[Num][2];
			year = modelParameter[Num][3];

			parameterString = VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year;
			String getMakeModelURL = envURL + VehicleSetCode + "/" + LngCode + "/" + CountryCode + "/" + year;
			 String jsonTextFrGetMakeModelWS = cPP.com_libs.getSourceCode(getMakeModelURL, "");

//			String filepath = "H:\\My Documents\\CompareService\\QA\\Testing\\Test Data\\makemodel.txt";
//			String jsonTextFrGetMakeModelWS = readFile(filepath);

			getMakeModelDetails(GetMakeModelSavePathFile, titleStringGetMakeModelWS, jsonTextFrGetMakeModelWS,
					getMakeModelURL, parameterString, count);

			// }
		}
	}

	public static void getMakeModelDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultObj="";
		String id = "";
		String make = "";
		 String modelsObj = "";
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
							id = result.getJSONObject(i).getString("id");
						} catch (Exception ex) {
							id = "null";
						}
						try {
							make = result.getJSONObject(i).getString("make");
						} catch (Exception ex) {
							make = "null";
						}

						JSONArray modelsObject = result.getJSONObject(i).getJSONArray("models");
						int modelsObjSize = modelsObject.length();
						modelsObj=Integer.toString(modelsObjSize);
						for (int j = 0; j < modelsObjSize; j++) {
							try {
								modelYearId = modelsObject.getJSONObject(j).getString("modelYearId");
							} catch (Exception ex) {
								modelYearId = "";
							}
							try {
								model = modelsObject.getJSONObject(j).getString("model");
							} catch (Exception ex) {
								model = "";
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
							jsonValue[5] = Integer.toString(size);// result.toString();
							jsonValue[6] = id;
							jsonValue[7] = make;
							jsonValue[8] = modelsObj;// modelsObj.toString();
							jsonValue[9] = modelYearId;
							jsonValue[10] = model;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
						}
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

	public static String readFile(String path) throws IOException {
		String sCurrentLine;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getMakeModel();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
