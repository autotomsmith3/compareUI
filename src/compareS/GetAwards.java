package compareS;
//Awards Micro Service. First initial looks good. Works fine.
import java.io.IOException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

//import cPP.JSONParser;

public class GetAwards {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getAwards() throws Exception {
		Properties prop = new Properties();
		try {
			// prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
			prop.load(GetAwards.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String env = prop.getProperty("environment");
		String[] gvuids = cPP.com_libs.fetchOneDemArrayFromPropFile("gvuids", prop);
		// String[] gvuid = { "7b47571c-b442-4be8-85da-f4baa7f4ccce", "7b47571c-b442-4be8-85da-f4baa7f4ccce" };

		String GetImagesSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\getAwards.xls";
		// String jSONText = "";
		// jSONParser(jSONText);// start with { (curly brace - object) OK
		String[] titleStringGetImagesWS = { "S/N", "result:", "gvuid:", "awardList:", "awardID:", "isoLanguageCode:",
				"category:", "awarded:", "awardSource:", "desc:", "created:", "modified:", "awardListCreated",
				"awardListModified" };

		// http://lnoc-dvcp-xmw1:8080/award-service/awards/EN/058bbf50-6fd8-4642-a181-297a5f68a710
		String envURL = "http://lnoc-dvcp-xmw1:8080/award-service/awards/";
		// http://lnoc-dvcp-xmw1:8080/award-service/awards
		String lan = "EN";// default=EN
		int count = 0;

		for (String gvuid : gvuids) {
			count++;
			String getAwardsURL = envURL + lan + "/" + gvuid;
			String jsonTextFrGetAwardsWS = cPP.com_libs.getSourceCode(getAwardsURL, "");
			getAwardsDetails(GetImagesSavePathFile, titleStringGetImagesWS, jsonTextFrGetAwardsWS, gvuid, count);

		}

	}

	public static void getAwardsDetails(String wsResultfile, String[] titleGetImages, String text, String gvuid,
			int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleGetImages);
		String s_gvuid = "";
		String awardList = "";
		String awardID = "";
		String isoLanguageCode = "";
		String category = "";
		String awarded = "";
		String awardSource = "";
		String desc = "";
		String created = "";
		String modified = "";
		String awardListCreated = "";
		String awardListModified = "";
		// String rangeDescription = "";
		// String groupDescription = "";
		// double rangeID = 0;
		// double groupID = 0;

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = gvuid;
			temp[2] = "";
			temp[3] = "404 error";
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				long serverTime = obj.getLong("serverTime");
				String serverTimeString = obj.getString("serverTimeString");
				String serverName = obj.getString("serverName");
				int executionTimeMS = obj.getInt("executionTimeMS");
				JSONObject result = obj.getJSONObject("result");
				int size = result.length();

				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = "result {} object is blank";
					temp[2] = gvuid;
					temp[3] = "";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + gvuid + " - Result {} object is blank. ");

				} else {
					for (int i = 0; i < size-3; i++) {
						try {
							s_gvuid = result.getString("gvuid");
						} catch (Exception ex) {
							s_gvuid = "null";
						}
						try {
							awardListCreated = Long.toString(result.getLong("created"));
						} catch (Exception ex) {
							awardListCreated = "null";
						}
						try {
							awardListModified = Long.toString(result.getLong("modified"));
						} catch (Exception ex) {
							awardListModified = "null";
						}
						JSONArray awardListObj = result.getJSONArray("awardList");
						int awardCount = awardListObj.length();
						if (awardCount == 0) {
							temp[0] = Integer.toString(countNum);
							temp[1] = gvuid;
							temp[2] = "";
							temp[3] = "awardList [] object is blank";
							cPP.com_libs.writeToSheet(wsResultfile, temp);
							System.out.println("gvuid = " + gvuid + " - awardList [] object is blank. ");
						} else {
							for (int j = 0; j < awardCount; j++) {

								try {
									awardID = Long.toString(awardListObj.getJSONObject(j).getLong("awardID"));
								} catch (Exception ex) {
									awardID = "null";
								}

								try {
									isoLanguageCode = awardListObj.getJSONObject(j).getString("isoLanguageCode");
								} catch (Exception ex) {
									isoLanguageCode = "null";
								}

								try {
									category = awardListObj.getJSONObject(j).getString("category");
								} catch (Exception ex) {
									category = "null";
								}

								try {
									awarded = Long.toString(awardListObj.getJSONObject(j).getLong("awarded"));
								} catch (Exception ex) {
									awarded = "null";
								}

								try {
									awardSource = awardListObj.getJSONObject(j).getString("awardSource");
								} catch (Exception ex) {
									awardSource = "null";
								}

								try {
									desc = awardListObj.getJSONObject(j).getString("desc");
								} catch (Exception ex) {
									desc = "null";
								}

								try {
									created = Long.toString(awardListObj.getJSONObject(j).getLong("created"));
								} catch (Exception ex) {
									created = "null";
								}

								try {
									modified = Long.toString(awardListObj.getJSONObject(j).getLong("modified"));
								} catch (Exception ex) {
									modified = "null";
								}

								System.out.println("S/N: " + countNum);
								System.out.println("result:");
								System.out.println("gvuid.: " + s_gvuid);
								System.out.println("gvuid: " + gvuid);
								System.out.println("imageID: " + awardList);
								System.out.println("shotID: " + awardID);
								System.out.println("imageName: " + isoLanguageCode);
								System.out.println("colorID: " + category);
								System.out.println("colorCode1: " + awarded);
								System.out.println("colorCode2: " + awardSource);
								System.out.println("colorCode3: " + desc);
								System.out.println("optionCode: " + awardListCreated);
								System.out.println("shotDescription: " + awardListModified);

								int wSize = titleGetImages.length;
								String[] jsonValue = new String[wSize];

								jsonValue[0] = Integer.toString(countNum);
								jsonValue[1] = Integer.toString (i+1);
								jsonValue[2] = s_gvuid;
								jsonValue[3] = Integer.toString (j+1);
								jsonValue[4] = awardID;
								jsonValue[5] = isoLanguageCode;
								jsonValue[6] = category;
								jsonValue[7] = awarded;
								jsonValue[8] = awardSource;
								jsonValue[9] = desc;
								jsonValue[10] = created;
								jsonValue[11] = modified;
								jsonValue[12] = awardListCreated;
								jsonValue[13] = awardListModified;

								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
							}

						}
					}

				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = "404 error";
				temp[2] = gvuid;
				temp[3] = "";
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getAwards();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
