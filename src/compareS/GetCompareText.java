package compareS;
//Double JSONArray parser  - Compare Photo - initial for debugging......January 09, 2017

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

//import cPP.JSONParser;

public class GetCompareText {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getComparePhoto() throws Exception {
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(GetCompareText.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] gvuids = cPP.com_libs.fetchOneDemArrayFromPropFile("gvuids", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };

		String GetCompareSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\getComparePhoto.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetCompareS = { "S/N", "VIN:", "vehicles", "trimid", "year", "make", "model", "trim",
				"variation", "pricing", "msrp", "media", "Compare:", "exterior:", "ex_shot1:", "ex_image1:",
				"ex_shot2:", "ex_image2:", "interior:", "in_shot1:", "in_image1:", "in_shot2:", "in_image2:" };
		String envURL ="http://lnoc-dvcp-xws1.autodatacorp.org:8080/compare/rest/compare/photo/EN";

		int count = 0;

		for (String gvuid : gvuids) {
			count++;

			// http://lnoc-dvcp-xws1.autodatacorp.org:8080/compare/rest/compare/text/BASE/EN/com.chromedata.commons-compare

			// {
			// "country": "US",
			// "vehicleIDType": "BASE",
			// "vehicleID": "USC60FOH632A0"
			// }

			String country = "US";
			String vehicleIDType = "BASE";
			String vehicleID = "USC60FOH632A0";
//			String compareBodyParameters = "{\"country\":\"" + country + "\",\"vehicleIDType\":\"" + vehicleIDType
//					+ "\",\"vehicleID\":\"" + vehicleID + "\"}";
			String compareBodyParameters ="{\"competitorSetCode\":\"AD\",\"compareVehicles\":[{\"country\":\"US\",\"vehicleIDType\":\"STYLEID\",\"vehicleID\":\"363109\"}]}";
			String comparePutServiceURL = envURL;
			// String jsonTextFrGetColorsBS = cPP.com_libs.getSourceCode(getColorsURL, "");
			 String jsonTextFrGetCompareBS = com_libs.getSourceCode(compareBodyParameters, comparePutServiceURL, "");//PUT

//			String filepath = "H:\\My Documents\\CompareService\\QA\\Testing\\Responses\\CompareS_1_STYLEID_response_20170119_RawFormat.txt";
//			String jsonTextFrGetCompareBS = readFile(filepath);

			getCompareServiceDetails(GetCompareSavePathFile, titleStringGetCompareS, jsonTextFrGetCompareBS, gvuid,
					count);

		}
	}

	public static void getCompareServiceDetails(String wsResultfile, String[] titleGetPhotos, String text, String gvuid,
			int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleGetPhotos);
		String vehicles = "";
		String trimId = "";
		String year = "";
		String make = "";
		String model = "";
		String trim = "";
		String variation = "";
		String pricing = "";
		String msrp = "";
		String media = "";
		String compareImage = "";
		String colorized = "";

		String image = "";
		String color = "";

		//
		// String exterior="";
		// String ex_shot1="";
		// String ex_image1="";
		// String ex_shot2="";
		// String ex_image2="";
		// String interior="";
		// String in_shot1="";
		// String in_image1="";
		// String in_shot2="";
		// String in_image2="";

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = gvuid;
			temp[2] = "404 error";
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				String id = obj.getString("id");
				String serverTime = obj.getString("serverTime");
				String error = Boolean.toString(obj.getBoolean("error"));
				String executionTimeMS = Long.toString(obj.getLong("executionTimeMS"));

				JSONArray result = obj.getJSONArray("result");

				int resultLength = result.length();
				for (int i = 0; i < resultLength; i++) {
					try {
						// JSONArray compare = result.getJSONArray(i);
						// JSONObject compare = result.getJSONArray(i).getJSONObject("compare"); //not work
						// JSONArray compare = new JSONArray(result.getJSONObject(i).getString("compare")); //not work
						JSONObject pricingObj = result.getJSONObject(i).getJSONObject("pricing"); // not work

						int pricingObjLength = pricingObj.length();
						for (int j = 0; j < pricingObjLength; j++) {
							// JSONArray trimId =vehiclesArray.getString(j)
							msrp=Long.toString(pricingObj.getLong("msrp"));
						}

						System.out.println("Stop here 01!");

//						trimId = result.getJSONObject(i).getString("compare");
					} catch (Exception ex) {
						trimId = "null";
					}
					System.out.println("Stop here!");
				}

				// try {
				// vehicles = result.getString("gvuid");
				// } catch (Exception ex) {
				// s_gvuid = "null";
				// }
				// try {
				// language = result.getString("language");
				// } catch (Exception ex) {
				// language = "null";
				// }
				//
				// JSONArray colorsList = result.getJSONArray("colorsList");
				// colorsList1 = colorsList.toString();
				// int colorsList1Size = colorsList.length();
				// if (colorsList1Size == 0) {
				// // code = "null";
				// // description = "null";
				// // genericDescription = "null";
				// // rgb = "null";
				// // type = "null";
				// id = "null";
				// colorType = "null";
				// colorList2 = "null";

				// } else {
				// for (int colorsSize = 0; colorsSize < colorsList1Size; colorsSize++) {
				//
				// try {
				// id = colorsList.getJSONObject(colorsSize).getString("id");
				// } catch (Exception ex) {
				// id = "null";
				// }
				//
				// try {
				// colorType = colorsList.getJSONObject(colorsSize).getString("colorType");
				// } catch (Exception ex) {
				// colorType = "null";
				// }
				//
				// JSONArray colorList = colorsList.getJSONObject(colorsSize).getJSONArray("colorList");
				// colorList2 = colorList.toString();
				// int colorSize = colorList.length();
				// if (colorSize == 0) {
				// code = "null";
				// description = "null";
				// genericDescription = "null";
				// rgb = "null";
				// type = "null";
				// } else {
				// for (int c = 0; c < colorSize; c++) {
				// try {
				// code = colorList.getJSONObject(c).getString("code");
				// } catch (Exception ex) {
				// code = "null";
				// }
				//
				// try {
				// description = colorList.getJSONObject(c).getString("description");
				// } catch (Exception ex) {
				// description = "null";
				// }
				//
				// try {
				// rgb = colorList.getJSONObject(c).getString("rgb");
				// } catch (Exception ex) {
				// rgb = "null";
				// }
				//
				// try {
				// type = colorList.getJSONObject(c).getString("type");
				// } catch (Exception ex) {
				// type = "null";
				// }
				// }
				// System.out.println("S/N: " + countNum);
				// System.out.println("gvuid_result:: " + s_gvuid);
				// System.out.println("language.: " + language);
				// System.out.println("colorsList1: " + colorsList1);
				// System.out.println("id: " + id);
				// System.out.println("colorType: " + colorType);
				// System.out.println("colorsList2: " + "colorsList2[]");// colorsList2
				// System.out.println("code: " + code);
				// System.out.println("description: " + description);
				// System.out.println("genericDescription: " + genericDescription);
				// System.out.println("rgb: " + rgb);
				// System.out.println("type: " + type);
				//
				// int wSize = titleGetPhotos.length;
				// String[] jsonValue = new String[wSize];
				//
				// jsonValue[0] = Integer.toString(countNum);
				// jsonValue[1] = s_gvuid;
				// jsonValue[2] = language;
				// jsonValue[3] = colorsList1;
				// jsonValue[4] = id;
				// jsonValue[5] = colorType;
				// jsonValue[6] = colorList2;
				// jsonValue[7] = code;
				// jsonValue[8] = description;
				// jsonValue[9] = genericDescription;
				// jsonValue[10] = rgb;
				// jsonValue[11] = type;
				//
				// cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
				// }
				//
				// }
				//
				// }
				//
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = gvuid;
				temp[2] = "200 error";
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

	public static String readFileToString(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		String filepath = "H:\\My Documents\\CompareService\\QA\\Testing\\Responses\\CompareS_1_STYLEID_response_20170119_RawFormat.txt";
		String jsonFileString = readFile(filepath); // works - not add /n in the end
		// String jsonFileString=readFileToString(filepath); //works - add /n in the end

		getComparePhoto();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
