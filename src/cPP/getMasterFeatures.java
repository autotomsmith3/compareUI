package cPP;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class getMasterFeatures {
	private static int blank = 0;
	private static int noObj = 0;
	
	public static void jSonObjec_CPP_MasterFeatureWS() throws Exception {
		Properties prop = new Properties();
		try {
			prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/jsondata.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] vinNums = com_libs.fetchOneDemArrayFromPropFile("master_feature_vins", prop);
		// String[] vinNums = { "3GCPCREC3FG408056", "1GTN2LEH6GZ134145", "W04WV3N58GG042641" };

		String MasterFeatureWSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CPP\\MasterFeatureWS_Return.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringMasterFeatureWS = { "S/N", "id:", "description:", "category:", "VIN:", "executionTimeMS:" };

		// ************QA**********:
		// http://lnoc-q1cp-xws1.autodatacorp.org:8080/master-feature-orchestration/rest/vehicle/features/
		String envURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/master-feature-orchestration/rest/vehicle/features/";

		// ************QA**********:
		// // ************DEV**********:
		// String env = "http://lnoc-dvcp-xws1:8080/master-feature-orchestration/rest/vehicle/features/";
		//
		// // ************DEV**********:
		//// // ************Prod********:
		// String env = "http://xxxxxxxlnoc-dvcp-xws1:8080/master-feature-orchestration/rest/vehicle/features/";
		// String [] vinNums = {"3GCPCREC3FG408056","1GTN2LEH6GZ134145","W04WV3N58GG042641"};
		//// // ************Prod********:
		// String masterFeatureParameters="vin=\"3GCPCREC3FG408056\"&used=true&unstructuredText=tires: p245/35r20 performance summer&unstructuredText=auto-dimming interior mirror w/homelink/compass&unstructuredText=power w/tilt down heated mirrors";
		// String masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
		String masterFeatureParameters = "";
		for (String vin : vinNums) {
			// masterFeatureParameters = "{\"vin\":\"1G1PE5SB1G7169014\"}";// 3GCPCREC3FG408056, 1G1PE5SB1G7169014
			masterFeatureParameters = "{\"vin\":\"" + vin + "\"}";
			String masterFeatureURL = envURL;
			//PUT
			String jsonTextFrMasterFeatureWS = com_libs.getSourceCode(masterFeatureParameters, masterFeatureURL, "");//PUT
			masterFeaturesWS(MasterFeatureWSSavePathFile, titleStringMasterFeatureWS, jsonTextFrMasterFeatureWS, vin);

		}

	}
	
	
	
	public static void masterFeaturesWS(String wsResultfile, String[] titleMasterFeatureWS, String text, String vinNum)
			throws IOException {
		com_libs.writeTitle(wsResultfile, titleMasterFeatureWS);
		String[] temp = new String[9];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(blank);
			temp[1] = "404 error";
			temp[2] = "404 error";
			temp[3] = "404 error";
			temp[4] = vinNum;
			com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				String id = obj.getString("id");
				String serverTime = obj.getString("serverTime");
				boolean error = obj.getBoolean("error");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");
				boolean errors = result.getBoolean("errors");
				JSONArray masterFeature = result.getJSONArray("masterFeatures");

				System.out.println("id: " + obj.getString("id"));
				System.out.println("serverTime: " + obj.getString("serverTime"));
				System.out.println("error: " + obj.getBoolean("error"));
				System.out.println("executionTimeMS: " + obj.getInt("executionTimeMS"));
				System.out.println("obj: " + obj);
				System.out.println("result: " + result);
				System.out.println("fuelEconomyList: " + masterFeature);
				int wSize = titleMasterFeatureWS.length;
				String[] jsonValue = new String[wSize];
				// writeTitle(wsResultfile, titleMasterFeatureWS);
				// String pageName = obj.getJSONObject("posts").getString("pageName");
				String description = "";
				String category = "";
				JSONArray arr = result.getJSONArray("masterFeatures");
				int sizeArray = arr.length();
				for (int i = 0; i < sizeArray; i++) {
					String idn = arr.getJSONObject(i).getString("id");
					try {
						description = arr.getJSONObject(i).getString("description");
					} catch (Exception ex) {
						description = "";
					}
					try {
						category = arr.getJSONObject(i).getString("category");
					} catch (Exception ex) {
						category = "";
					}
					jsonValue[0] = Integer.toString(i);
					jsonValue[1] = idn;
					jsonValue[2] = description;
					jsonValue[3] = category;
					jsonValue[4] = vinNum;
					if (i == 0) {
						jsonValue[5] = Integer.toString(executionTimeMS);
					} else {
						jsonValue[5] = "";
					}
					com_libs.writeToSheet(wsResultfile, jsonValue);

					// System.out.println("S/N:" + i + ", id:" + idn + ", description:" + description + ", category:"
					// + category + ", vinNum:" + vinNum);

				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(noObj);
				temp[1] = "200 No masterFeatures Obj";
				temp[2] = "200 No masterFeatures Obj";
				temp[3] = "200 No masterFeatures Obj";
				temp[4] = vinNum;
				com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		// jSonObjec_SydicationWS();
//		 jSonObjec_CPP_FeulEconomyWS();
		jSonObjec_CPP_MasterFeatureWS();
//		jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
