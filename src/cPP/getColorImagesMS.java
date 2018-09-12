package cPP;

//CPP-993 - getColorImages/gvuids  -- /image-service/getColorImages/gvuids
//20180522-Looks like CPP-993 folder is not showing in Spira but below TCs showing in folder CPP-1377 - Color Image MS - CPP QA 1.4.3
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

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

public class getColorImagesMS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getColorImagesMS() throws Exception {
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
		String dateFolder = "20170719";
		String[] SpiraID = { "TC185525", "TC185526", "TC185527", "TC185528", "TC185529", "TC185530", "TC185531",
				"TC185533", "TC185534", "TC185535", "TC185536", "TC185537", "TC185538", "TC185539", "TC185540",
				"TC185541", "TC185542", "TC185543", "TC185544", "TC185545", "TC185546", "TC185547", "TC185548",
				"TC185549", "TC185550", "TC185551", "TC185552", "TC185553", "TC185554", "TC185555", "TC185556",
				"TC185557", "TC185558", "TC185559", "TC185560", "TC185561", "TC185562", "TC185563", "TC185564",
				"TC185565", "TC185566", "TC185567", "TC185568", "TC185569", "TC185570", "TC185571", "TC18xx47",
				"TC181246", "TC181247", "TC181248", "TC181249", "TC181250", "TC181251", "TC181252", "TC181253",
				"TC181254", "TC181255", "TC181256", "TC181257", "TC181258", "TC181259", "TC181260", "TC181261",
				"TC181262", "TC181263", "TC181264", "TC181265", "TC181266", "TC181267", "TC181268", "TC181269",
				"TC181270", "TC181271", "TC181272", "TC181273", "TC181274", "TC181275", "TC181276", "TC181277",
				"TC181278", "TC181279", "TC181280", "TC181281", "TC181282", "TC181314", "TC181316", "TC181317",
				"TC181318", "TC181319", "TCxxxx90" }; //Total is 90

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k); /// C:\1\Eclipse\Test Results\CompareMS\ColorImagesMS\20170401
			String getColorImagesMSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorImagesMS_CPP-993\\"
					+ dateFolder + "\\ColorImages_" + kStr + ".xls";
			String jsonpathfilename = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorImagesMS_CPP-993\\" + dateFolder
					+ "\\image_color_" + kStr;// + ".json";

			String[] titleStringgetImageWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "serverTimeString",
					"serverName", "executionTimeMS", "resultObj", "gvuidObj", "gvuid", "imageName", "colorId",
					"primaryColorCode", "primaryColorDesc", "genericColor", "secondaryColorCode", "secondaryColorDesc",
					"color2GenericDesc", "accentColorCode", "accentColorDesc", "color3GenericDesc", "optionCode",
					"shotDescription", "rangeDescription", "groupDescription", "shotId", "rangeId", "groupId",
					"ImageViews" };
			// // DEV PUT
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/image-service/getColorImages/gvuids";
			// // // QA PUT
			String imagePutServiceURL = "http://lnoc-q1cp-xmw1:8080/image-service/getColorImages/gvuids";
			// ******************************************************
			// ******************************************************
			int dataLength = 46;// 30;// 18;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] ColorImagesMSBodyParameters = new String[dataLength + 1];
			int datasize = ColorImagesMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				// String bodyDatafile = "imageMSBodyData_" + k + "/imageMSBody" + iStr + ".txt"; // # Vin working.
				// C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorImagesMS\\TestData\\imageBodyDate_1\\imageBody1.txt
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorImagesMS_CPP-993\\TestData\\imageBodyData_"
						+ k + "\\imageBody" + iStr + ".txt"; // # group number testing

				ColorImagesMSBodyParameters[i] = getFile(bodyDatafile); // ok
				// ColorImagesMSBodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgetImageWS = com_libs.getSourceCodeJson(ColorImagesMSBodyParameters[i],
						imagePutServiceURL, "");// PUT
				SaveScratch(jsonpathfilename + "_" + SpiraID[i] + "_" + (i + 1) + ".json", jsonTextFrgetImageWS);
				// GET old
				// String jsonTextFrgetImageWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getColorImagesMSDetails(getColorImagesMSSavePathFile, titleStringgetImageWS, jsonTextFrgetImageWS,
						imagePutServiceURL, ColorImagesMSBodyParameters[i], count);
			}
		}
	}

	public static void getColorImagesMSDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String serverTimeString = "";
		String serverName = "";
		String executionTimeMS = "";
		String resultObj = "";
		String error = "";
		String gvuidObj = "";
		String gvuid = "";
		String modelYearId = "";
		String shotId = "";
		String imageName = "";
		String colorId = "";
		String primaryColorCode = "";
		String primaryColorDesc = "";
		String genericColor = "";
		String secondaryColorCode = "";
		String secondaryColorDesc = "";
		String color2GenericDesc = "";
		String accentColorCode = "";
		String accentColorDesc = "";
		String color3GenericDesc = "";
		String optionCode = "";
		String shotDescription = "";
		String rangeDescription = "";
		String groupDescription = "";
		String rangeId = "";
		String groupId = "";
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
				error = Boolean.toString(obj.getBoolean("error"));
				JSONObject result = obj.getJSONObject("result");
				int size = result.length();
				resultObj = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = parameterS;
					temp[7] = "result {} object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("modelYearId = " + "  " + " - Result {} object is blank. ");

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
								gvuid = "null";
							}
							try {
								imageName = currentDynamicValue.getJSONObject(i).getString("imageName");
							} catch (Exception ex) {
								imageName = "null";
							}
							try {
								colorId = currentDynamicValue.getJSONObject(i).getString("colorId");
							} catch (Exception ex) {
								colorId = "null";
							}

							try {
								primaryColorCode = currentDynamicValue.getJSONObject(i).getString("primaryColorCode");
							} catch (Exception ex) {
								primaryColorCode = "null";
							}
							try {
								primaryColorDesc = currentDynamicValue.getJSONObject(i).getString("primaryColorDesc");
							} catch (Exception ex) {
								primaryColorDesc = "null";
							}
							try {
								genericColor = currentDynamicValue.getJSONObject(i).getString("genericColor");
							} catch (Exception ex) {
								genericColor = "null";
							}
							try {
								secondaryColorCode = currentDynamicValue.getJSONObject(i)
										.getString("secondaryColorCode");
							} catch (Exception ex) {
								secondaryColorCode = "null";
							}
							try {
								secondaryColorDesc = currentDynamicValue.getJSONObject(i)
										.getString("secondaryColorDesc");
							} catch (Exception ex) {
								secondaryColorDesc = "null";
							}
							try {
								color2GenericDesc = currentDynamicValue.getJSONObject(i).getString("color2GenericDesc");
							} catch (Exception ex) {
								color2GenericDesc = "null";
							}
							try {
								accentColorCode = currentDynamicValue.getJSONObject(i).getString("accentColorCode");
							} catch (Exception ex) {
								accentColorCode = "null";
							}
							try {
								accentColorDesc = currentDynamicValue.getJSONObject(i).getString("accentColorDesc");
							} catch (Exception ex) {
								accentColorDesc = "null";
							}
							try {
								color3GenericDesc = currentDynamicValue.getJSONObject(i).getString("color3GenericDesc");
							} catch (Exception ex) {
								color3GenericDesc = "null";
							}
							try {
								optionCode = currentDynamicValue.getJSONObject(i).getString("optionCode");
							} catch (Exception ex) {
								optionCode = "null";
							}
							try {
								shotDescription = currentDynamicValue.getJSONObject(i).getString("shotDescription");
							} catch (Exception ex) {
								shotDescription = "null";
							}
							try {
								rangeDescription = currentDynamicValue.getJSONObject(i).getString("rangeDescription");
							} catch (Exception ex) {
								rangeDescription = "null";
							}
							try {
								groupDescription = currentDynamicValue.getJSONObject(i).getString("groupDescription");
							} catch (Exception ex) {
								groupDescription = "null";
							}
							try {
								shotId = Integer.toString(currentDynamicValue.getJSONObject(i).getInt("shotId"));
							} catch (Exception ex) {
								shotId = "null";
							}
							try {
								rangeId = Integer.toString(currentDynamicValue.getJSONObject(i).getInt("rangeId"));
							} catch (Exception ex) {
								rangeId = "null";
							}
							try {
								groupId = Integer.toString(currentDynamicValue.getJSONObject(i).getInt("groupId"));
							} catch (Exception ex) {
								groupId = "null";
							}
							try {
								imageView = currentDynamicValue.getJSONObject(i).getString("imageView");
							} catch (Exception ex) {
								imageView = "null";
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
							jsonValue[12] = primaryColorCode;
							jsonValue[13] = primaryColorDesc;
							jsonValue[14] = genericColor;
							jsonValue[15] = secondaryColorCode;
							jsonValue[16] = secondaryColorDesc;
							jsonValue[17] = color2GenericDesc;
							jsonValue[18] = accentColorCode;
							jsonValue[19] = accentColorDesc;
							jsonValue[20] = color3GenericDesc;
							jsonValue[21] = optionCode;
							jsonValue[22] = shotDescription;
							jsonValue[23] = rangeDescription;
							jsonValue[24] = groupDescription;
							jsonValue[25] = shotId;
							jsonValue[26] = rangeId;
							jsonValue[27] = groupId;
							jsonValue[28] = imageView;

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
		getColorImagesMS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
