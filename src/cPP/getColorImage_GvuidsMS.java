package cPP;

// This is for CPP-889 endpoint: /getColorImages/gvuids
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

public class getColorImage_GvuidsMS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getColorImage_GvuidsMS() throws Exception {
		// Properties prop = new Properties();
		// try {
		// // prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		// prop.load(ComparePhoto.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// ******************************************************
		// ******************************************************
		int vintotal = 2;
		int startToRun = 1;// Default=1;
		int endToRun = 1;// Default =vintotal
		String dateFolder = "20180501";
		String[] SpiraID = { "TC193461", "TC193462", "TC193463", "TC193464", "TC193465", "TC193466", "TC193467",
				"TC193468", "TC193469", "TC193470", "TC193471", "TC193472", "TC193473", "TC193474", "TC193475",
				"TC193476", "TC193477", "TC193478", "TC193479", "TC193480", "TC193481", "TC193482", "TC193483",
				"TC193484", "TC193485", "TC193486", "TC193487", "TC193488", "TC193489", "TC193490", "TC193491",
				"TC193492", "TC193493", "TC193494", "TC193495", "TC193496", "TC193497", "TC193498", "TC193499" };//total=39

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k);
			String getColorImage_GvuidsMSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\Images_getColorImages_GvuidsMS_CPP-889\\"
					+ dateFolder + "\\image_" + kStr + ".xls";
			String jsonpathfilename = "C:\\1\\Eclipse\\Test Results\\CompareMS\\Images_getColorImages_GvuidsMS_CPP-889\\"
					+ dateFolder + "\\image";// + ".json";

			String[] titleStringgetImageWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "serverTimeString",
					"serverName", "executionTimeMS", "resultObj", "gvuidObj", "gvuid", "imageName", "colorId",
					"optionCode", "genericColor", "primaryColorCode", "primaryColorDesc", "secondaryColorCode",
					"secondaryColorDesc", "accentColorCode", "accentColorDesc", "shotId", "shotDescription", "rangeId",
					"rangeDescription", "groupId", "groupDescription", "imageView" };
			//// // DEV PUT
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/image-service/getColorImages/gvuids";

			// // QA
			// String imagePutServiceURL = "http://lnoc-q1cp-xmw1:8080/image-service/getColorImages/gvuids";
			// // QA PUT
			String imagePutServiceURL = "http://lnoc-q1cp-xmw1:8080/image-service/getColorImages/gvuids";
			// int dataLength = 10;//one vin has 10 tcs.// colorized360(stock360) used before. OK
			// ******************************************************
			// ******************************************************
			int dataLength = SpiraID.length;// 39;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] imageMSBodyParameters = new String[dataLength + 1];
			int datasize = imageMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\Images_getColorImages_GvuidsMS_CPP-889\\TestData\\imageBodyData_"
						+ k + "\\imageBody" + iStr + ".txt"; // # group number testing

				imageMSBodyParameters[i] = getFile(bodyDatafile); // ok
				// imageMSBodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgetImageWS = "";
				try {
					jsonTextFrgetImageWS = com_libs.getSourceCodeJson(imageMSBodyParameters[i], imagePutServiceURL, "");// PUT
				} catch (Exception ex) {
					jsonTextFrgetImageWS = "";
				}
				SaveScratch(jsonpathfilename + "_" + SpiraID[i] + "_" + (i + 1) + ".json", jsonTextFrgetImageWS);
				// GET old
				// String jsonTextFrgetImageWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getColorImage_GvuidsMSDetails(getColorImage_GvuidsMSSavePathFile, titleStringgetImageWS,
						jsonTextFrgetImageWS, imagePutServiceURL, imageMSBodyParameters[i], count);
			}
		}
	}

	public static void getColorImage_GvuidsMSDetails(String wsResultfile, String[] titleString, String text,
			String URLString, String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String serverTimeString = "";
		String serverName = "";
		String executionTimeMS = "";
		String resultObj = "";

		String gvuidObj = "";
		String gvuid = "";
		String imageName = "";
		String colorId = "";
		String optionCode = "";
		String genericColor = "";
		String primaryColorCode = "";
		String primaryColorDesc = "";
		String secondaryColorCode = "";
		String secondaryColorDesc = "";
		String accentColorCode = "";
		String accentColorDesc = "";
		String shotId = "";
		String shotDescription = "";
		String rangeId = "";
		String rangeDescription = "";
		String groupId = "";
		String groupDescription = "";
		String imageView = "";

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = URLString;
			temp[2] = parameterS;
			temp[3] = "404 error";
			System.out.println("S/N: " + countNum);
			System.out.println("404 ERROR ON : " + URLString);
			cPP.com_libs.writeToSheet(wsResultfile, temp);
		} else {
			try {
				JSONObject obj = new JSONObject(text);
				int objLen = obj.length();

				serverTime = Long.toString(obj.getLong("serverTime"));
				serverTimeString = obj.getString(("serverTimeString"));
				serverName = obj.getString(("serverName"));
				executionTimeMS = Long.toString(obj.getLong("executionTimeMS"));

				JSONObject result = obj.getJSONObject("result");
				int size = result.length();
				resultObj = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = parameterS;
					temp[3] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + "  " + " - Result [] object is blank. ");

				} else {
					gvuid = result.toString();
					Iterator keys = result.keys();
					int keysCount = 0;
					gvuidObj = Integer.toString(keysCount);
					while (keys.hasNext()) {
						// loop to get the dynamic key
						String currentDynamicKey = (String) keys.next();
						keysCount++;
						gvuidObj = Integer.toString(keysCount);
						// get the value of the dynamic key
						JSONArray currentDynamicValue = result.getJSONArray(currentDynamicKey);
						// do something here with the value...
						int gvuidSize = currentDynamicValue.length();

						for (int i = 0; i < gvuidSize; i++) {
							try {
								gvuid = currentDynamicValue.getJSONObject(i).getString("gvuid");
							} catch (Exception ex) {
								gvuid = "not exist";
							}
							try {
								imageName = currentDynamicValue.getJSONObject(i).getString("imageName");
							} catch (Exception ex) {
								imageName = "not exist";
							}
							try {
								colorId = currentDynamicValue.getJSONObject(i).getString("colorId");
							} catch (Exception ex) {
								colorId = "not exist";
							}
							try {
								optionCode = currentDynamicValue.getJSONObject(i).getString("optionCode");
							} catch (Exception ex) {
								optionCode = "not exist";
							}
							try {
								genericColor = currentDynamicValue.getJSONObject(i).getString("genericColor");
							} catch (Exception ex) {
								genericColor = "not exist";
							}
							try {
								primaryColorCode = currentDynamicValue.getJSONObject(i).getString("primaryColorCode");
							} catch (Exception ex) {
								primaryColorCode = "not exist";
							}
							try {
								primaryColorDesc = currentDynamicValue.getJSONObject(i).getString("primaryColorDesc");
							} catch (Exception ex) {
								primaryColorDesc = "not exist";
							}
							try {
								secondaryColorCode = currentDynamicValue.getJSONObject(i)
										.getString("secondaryColorCode");
							} catch (Exception ex) {
								secondaryColorCode = "not exist";
							}
							try {
								secondaryColorDesc = currentDynamicValue.getJSONObject(i)
										.getString("secondaryColorDesc");
							} catch (Exception ex) {
								secondaryColorDesc = "not exist";
							}
							try {
								accentColorCode = currentDynamicValue.getJSONObject(i).getString("accentColorCode");
							} catch (Exception ex) {
								accentColorCode = "not exist";
							}
							try {
								accentColorDesc = currentDynamicValue.getJSONObject(i).getString("accentColorDesc");
							} catch (Exception ex) {
								accentColorDesc = "not exist";
							}
							try {
								shotId = Integer.toString(currentDynamicValue.getJSONObject(i).getInt("shotId"));
							} catch (Exception ex) {
								shotId = "not exist";
							}
							try {
								shotDescription = currentDynamicValue.getJSONObject(i).getString("shotDescription");
							} catch (Exception ex) {
								shotDescription = "not exist";
							}
							try {
								rangeId = Integer.toString(currentDynamicValue.getJSONObject(i).getInt("rangeId"));
							} catch (Exception ex) {
								rangeId = "not exist";
							}
							try {
								rangeDescription = currentDynamicValue.getJSONObject(i).getString("rangeDescription");
							} catch (Exception ex) {
								rangeDescription = "not exist";
							}
							try {
								groupId = Integer.toString(currentDynamicValue.getJSONObject(i).getInt("groupId"));
							} catch (Exception ex) {
								groupId = "not exist";
							}
							try {
								groupDescription = currentDynamicValue.getJSONObject(i).getString("groupDescription");
							} catch (Exception ex) {
								groupDescription = "not exist";
							}
							try {
								imageView = currentDynamicValue.getJSONObject(i).getString("imageView");
							} catch (Exception ex) {
								imageView = "not exist";
							}

							System.out.println("S/N: " + countNum);
							System.out.println("executionTimeMS: " + executionTimeMS);
							System.out.println("URLString: " + URLString);

							int wSize = titleString.length;
							String[] jsonValue = new String[wSize];

							jsonValue[0] = Integer.toString(countNum);
							jsonValue[1] = URLString;
							jsonValue[2] = parameterS;
							jsonValue[3] = serverTime;
							jsonValue[4] = serverTimeString;
							jsonValue[5] = serverName;
							jsonValue[6] = executionTimeMS;

							jsonValue[7] = resultObj;

							jsonValue[8] = gvuidObj;
							jsonValue[9] = gvuid;
							jsonValue[10] = imageName;
							jsonValue[11] = colorId;
							jsonValue[12] = optionCode;
							jsonValue[13] = genericColor;
							jsonValue[14] = primaryColorCode;
							jsonValue[15] = primaryColorDesc;
							jsonValue[16] = secondaryColorCode;
							jsonValue[17] = secondaryColorDesc;
							jsonValue[18] = accentColorCode;
							jsonValue[19] = accentColorDesc;
							jsonValue[20] = shotId;
							jsonValue[21] = shotDescription;
							jsonValue[22] = rangeId;
							jsonValue[23] = rangeDescription;
							jsonValue[24] = groupId;
							jsonValue[25] = groupDescription;
							jsonValue[26] = imageView;

							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

						}

					}

				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = URLString;
				temp[2] = parameterS;
				temp[3] = "200 error";
				System.out.println("S/N: " + countNum);
				System.out.println("ERROR 200 ON : " + URLString);
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}

		}

	}

	public static String getFile_old(String fileName) throws IOException {
		int lineNum;
		String line = "";
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream(fileName);

		// File file = new File(classLoader.getResource(fileName).getFile());
		int i = 1;
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				i++;
				result.append(line).append("\n");
				// result.append(line).append("\n");
			}
			lineNum = i - 1;
			// file.close();
			// file.();
			scanner.close();
		}
		return result.toString();
	}

	public static String getFile(String filePathName) {
		String sCurrentLine;
		String fileString = "";
		StringBuilder sb = new StringBuilder();
		String prefix_fileName, tempfile;
		tempfile = "";
		// prefix_fileName = wsName;?
		String path = filePathName;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
			fileString = sb.toString();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		prefix_fileName = "";
		return fileString;
	}

	public static void SaveScratch(String pathfilename, String ScratchText) {
		try {
			// BufferedWriter out2 = new BufferedWriter(new FileWriter(dataDir+ "Acodes.txt", true)); //original OK
			BufferedWriter out2 = new BufferedWriter(new FileWriter(pathfilename, true));
			// out2.write("("+i+"): "+Acode+": ");
			// out2.write(i + ". " + Acode + ": "); //Original OK
			out2.newLine();
			out2.write(ScratchText);
			out2.newLine();
			out2.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getColorImage_GvuidsMS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
