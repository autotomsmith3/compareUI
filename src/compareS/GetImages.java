package compareS;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class GetImages {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getImages() throws Exception {
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(GetImages.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] gvuids = cPP.com_libs.fetchOneDemArrayFromPropFile("gvuids", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };

		String GetImagesSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\getImages.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetImagesWS = { "S/N", "gvuid_result:", "imageNo.:", "gvuid:", "imageID:", "shotID:",
				"imageName:", "colorID:", "colorCode1:", "colorCode2:", "colorCode3:", "optionCode:",
				"shotDescription:", "rangeDescription:", "groupDescription", "rangeID", "groupID" };

		// http://lnoc-dvcp-xmw1:8080/image-service/getImages/7b47571c-b442-4be8-85da-f4baa7f4ccce/34
		String envURL = "http://lnoc-dvcp-xmw1:8080/image-service/getImages/";
		int imageNums = 56;// default=34
		int count = 0;
		for (int imageNum = 1; imageNum <= imageNums; imageNum++) {
			for (String gvuid : gvuids) {
				count++;
				String getImagesURL = envURL + gvuid + "/" + imageNum;
				String jsonTextFrGetImsageWS = cPP.com_libs.getSourceCode(getImagesURL, "");
				getImagesDetails(GetImagesSavePathFile, titleStringGetImagesWS, jsonTextFrGetImsageWS, gvuid, imageNum,
						count);

			}
		}
	}

	public static void getImagesDetails(String wsResultfile, String[] titleGetImages, String text, String gvuid,
			int imageNo, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleGetImages);
		String s_gvuid = "";
		double imageID = 0;
		double shotID = 0;
		String imageName = "";
		String colorID = "";
		String colorCode1 = "";
		String colorCode2 = "";
		String colorCode3 = "";
		String optionCode = "";
		String shotDescription = "";
		String rangeDescription = "";
		String groupDescription = "";
		double rangeID = 0;
		double groupID = 0;

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = gvuid;
			temp[2] = Integer.toString(imageNo);
			temp[3] = "404 error";
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				long serverTime = obj.getLong("serverTime");
				String serverTimeString = obj.getString("serverTimeString");
				String serverName = obj.getString("serverName");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONArray result = obj.getJSONArray("result");
				int size = result.length();

				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = gvuid;
					temp[2] = Integer.toString(imageNo);
					temp[3] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + gvuid + " - Result [] object is blank. ");

				} else {
					for (int i = 0; i < size; i++) {
						try {
							s_gvuid = result.getJSONObject(i).getString("gvuid");
						} catch (Exception ex) {
							s_gvuid = "null";
						}
						try {
							imageID = result.getJSONObject(i).getDouble("imageID");
						} catch (Exception ex) {
							imageID = 0;
						}
						try {
							shotID = result.getJSONObject(i).getDouble("shotID");
						} catch (Exception ex) {
							shotID = 0;
						}
						try {
							imageName = result.getJSONObject(i).getString("imageName");
						} catch (Exception ex) {
							imageName = "null";
						}
						try {
							colorID = result.getJSONObject(i).getString("colorID");
						} catch (Exception ex) {
							colorID = "null";
						}
						try {
							colorCode1 = result.getJSONObject(i).getString("colorCode1");
						} catch (Exception ex) {
							colorCode1 = "null";
						}
						try {
							colorCode2 = result.getJSONObject(i).getString("colorCode2");
						} catch (Exception ex) {
							colorCode2 = "null";
						}
						try {
							colorCode3 = result.getJSONObject(i).getString("colorCode3");
						} catch (Exception ex) {
							colorCode3 = "null";
						}
						try {
							optionCode = result.getJSONObject(i).getString("optionCode");
						} catch (Exception ex) {
							optionCode = "null";
						}
						try {
							shotDescription = result.getJSONObject(i).getString("shotDescription");
						} catch (Exception ex) {
							shotDescription = "null";
						}
						try {
							rangeDescription = result.getJSONObject(i).getString("rangeDescription");
						} catch (Exception ex) {
							rangeDescription = "null";
						}
						try {
							groupDescription = result.getJSONObject(i).getString("groupDescription");
						} catch (Exception ex) {
							groupDescription = "null";
						}
						try {
							rangeID = result.getJSONObject(i).getDouble("rangeID");
						} catch (Exception ex) {
							rangeID = 0;
						}
						try {
							groupID = result.getJSONObject(i).getDouble("groupID");
						} catch (Exception ex) {
							groupID = 0;
						}

						System.out.println("S/N: " + countNum);
						System.out.println("gvuid_result:: " + s_gvuid);
						System.out.println("imageNo.: " + imageNo);
						System.out.println("gvuid: " + gvuid);
						System.out.println("imageID: " + imageID);
						System.out.println("shotID: " + shotID);
						System.out.println("imageName: " + imageName);
						System.out.println("colorID: " + colorID);
						System.out.println("colorCode1: " + colorCode1);
						System.out.println("colorCode2: " + colorCode2);
						System.out.println("colorCode3: " + colorCode3);
						System.out.println("optionCode: " + optionCode);
						System.out.println("shotDescription: " + shotDescription);
						System.out.println("rangeDescription: " + rangeDescription);
						System.out.println("groupDescription: " + groupDescription);
						System.out.println("rangeID: " + rangeID);
						System.out.println("groupID: " + groupID);

						int wSize = titleGetImages.length;
						String[] jsonValue = new String[wSize];

						jsonValue[0] = Integer.toString(countNum);
						jsonValue[1] = s_gvuid;
						jsonValue[2] = Double.toString(imageNo);
						jsonValue[3] = gvuid;
						jsonValue[4] = Double.toString(imageID);
						jsonValue[5] = Double.toString(shotID);
						jsonValue[6] = imageName;
						jsonValue[7] = colorID;
						jsonValue[8] = colorCode1;
						jsonValue[9] = colorCode2;
						jsonValue[10] = colorCode3;
						jsonValue[11] = optionCode;
						jsonValue[12] = shotDescription;
						jsonValue[13] = rangeDescription;
						jsonValue[14] = groupDescription;
						jsonValue[15] = Double.toString(rangeID);
						jsonValue[16] = Double.toString(groupID);
						// jsonValue[17] = vinNum;
						// jsonValue[18] = vinNum;

						cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
					}
				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = gvuid;
				temp[2] = Integer.toString(imageNo);
				temp[3] = "200 error";
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getImages();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
