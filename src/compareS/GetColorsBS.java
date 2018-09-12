package compareS;
//Double JSONArray parser

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class GetColorsBS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getColors() throws Exception {
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(GetColorsBS.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] gvuids = cPP.com_libs.fetchOneDemArrayFromPropFile("gvuids", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };

		String GetImagesSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\getColors.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetImagesWS = { "S/N", "gvuid_result:", "language:", "colorsList1:", "id:", "colorType:",
				"colorsList2:", "code:", "description:", "genericDescription:", "rgb:", "type:" };

		// http://lnoc-dvcp-xmw1:8080/color-service/color/9e310896-5469-4384-83a1-6e3e80958649/E/EN
		String envURL = "http://lnoc-dvcp-xmw1:8080/color-service/color/";
		String colorType = "E";// default is exterior: "E", can be interior: "I";
		String lanague = "EN"; // default: "EN",
		int count = 0;

		for (String gvuid : gvuids) {
			count++;
			String getColorsURL = envURL + gvuid + "/" + colorType + "/" + lanague;
			String jsonTextFrGetColorsBS = cPP.com_libs.getSourceCode(getColorsURL, "");
			getColorDetails(GetImagesSavePathFile, titleStringGetImagesWS, jsonTextFrGetColorsBS, gvuid, count);

		}
	}

	public static void getColorDetails(String wsResultfile, String[] titleGetColors, String text, String gvuid,
			int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleGetColors);
		String s_gvuid = "";
		String language = "";
		String colorsList1 = "";
		String id = "";
		String colorType = "";
		String colorList2 = "";
		String code = "";
		String description = "";
		String genericDescription = "";
		String rgb = "";
		String type = "";

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
				long serverTime = obj.getLong("serverTime");
				String serverTimeString = obj.getString("serverTimeString");
				String serverName = obj.getString("serverName");
				long executionTimeMS = obj.getLong("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");

				try {
					s_gvuid = result.getString("gvuid");
				} catch (Exception ex) {
					s_gvuid = "null";
				}
				try {
					language = result.getString("language");
				} catch (Exception ex) {
					language = "null";
				}

				JSONArray colorsList = result.getJSONArray("colorsList");
				colorsList1 = colorsList.toString();
				int colorsList1Size = colorsList.length();
				if (colorsList1Size == 0) {
					// code = "null";
					// description = "null";
					// genericDescription = "null";
					// rgb = "null";
					// type = "null";
					id = "null";
					colorType = "null";
					colorList2 = "null";

				} else {
					for (int colorsSize = 0; colorsSize < colorsList1Size; colorsSize++) {

						try {
							id = colorsList.getJSONObject(colorsSize).getString("id");
						} catch (Exception ex) {
							id = "null";
						}

						try {
							colorType = colorsList.getJSONObject(colorsSize).getString("colorType");
						} catch (Exception ex) {
							colorType = "null";
						}

						JSONArray colorList = colorsList.getJSONObject(colorsSize).getJSONArray("colorList");
						colorList2 = colorList.toString();
						int colorSize = colorList.length();
						if (colorSize == 0) {
							code = "null";
							description = "null";
							genericDescription = "null";
							rgb = "null";
							type = "null";
						} else {
							for (int c = 0; c < colorSize; c++) {
								try {
									code = colorList.getJSONObject(c).getString("code");
								} catch (Exception ex) {
									code = "null";
								}

								try {
									description = colorList.getJSONObject(c).getString("description");
								} catch (Exception ex) {
									description = "null";
								}

								try {
									rgb = colorList.getJSONObject(c).getString("rgb");
								} catch (Exception ex) {
									rgb = "null";
								}

								try {
									type = colorList.getJSONObject(c).getString("type");
								} catch (Exception ex) {
									type = "null";
								}
							}
							System.out.println("S/N: " + countNum);
							System.out.println("gvuid_result:: " + s_gvuid);
							System.out.println("language.: " + language);
							System.out.println("colorsList1: " + colorsList1);
							System.out.println("id: " + id);
							System.out.println("colorType: " + colorType);
							System.out.println("colorsList2: " + "colorsList2[]");// colorsList2
							System.out.println("code: " + code);
							System.out.println("description: " + description);
							System.out.println("genericDescription: " + genericDescription);
							System.out.println("rgb: " + rgb);
							System.out.println("type: " + type);

							int wSize = titleGetColors.length;
							String[] jsonValue = new String[wSize];

							jsonValue[0] = Integer.toString(countNum);
							jsonValue[1] = s_gvuid;
							jsonValue[2] = language;
							jsonValue[3] = colorsList1;
							jsonValue[4] = id;
							jsonValue[5] = colorType;
							jsonValue[6] = colorList2;
							jsonValue[7] = code;
							jsonValue[8] = description;
							jsonValue[9] = genericDescription;
							jsonValue[10] = rgb;
							jsonValue[11] = type;

							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
						}

					}

				}

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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getColors();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
