package cPP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

//import cPP.JSONParser;

public class hasStock360 {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getHasStock360() throws Exception {
		// Properties prop = new Properties();
		// try {
		// // prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		// prop.load(ComparePhoto.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// int count = 0;
		// int vintotal = 1;
		// int startToRun = 1;// Default=1;
		// int endToRun = 1;// Default =vintotal

		int vintotal = 1;
		int startToRun = 1;// Default=1;
		int endToRun = 1;// Default =vintotal
		String dateFolder = "20170707";
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k);

			// String getHasStock360SavePathFile = "C:\\1\\Eclipse\\Test Results\\Colorized360\\hasStock360\\getHasStock360.xls";
			String getHasStock360SavePathFile = "C:\\1\\Eclipse\\Test Results\\Colorized360 Phase2\\hasStock360\\"+dateFolder+"\\getHasStock360.xls";
			// "C:\\1\\Eclipse\\Test Results\\Colorized360\\stock360\\"+dateFolder+"\\getStock360_Vin_"
//			C:\1\Eclipse\Test Results\Colorized360 Phase2\hasStock360\TestData\stock360BodyData_1
			
			String[] titleStringgetHasStock360WS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "error",
					"executionTimeMS", "resultObj", "vehiclesObj", "hasImages", "countryCode", "language", "vin",
					"style", "acode:", "token", "chromeymmid", "year", "make", "model", "trim", "colorObj",
					"colorContains", "oemcolorObj", "oemcolorContains", "errors" };
			// QA
			String comparePutServiceURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/image/rest/hasStock360";
			// DEV
			// String comparePutServiceURL = "http://lnoc-dvcp-xws1.autodatacorp.org:8080/image/rest/hasStock360";

			int dataLength = 150;
			String[] hasStock360BodyParameters = new String[dataLength + 1];
			int datasize = hasStock360BodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);
				// String bodyDatafile = "hasstock360BodyData/hasStock360Body_" + iStr + ".txt";
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\Colorized360 Phase2\\hasstock360\\TestData\\hasstock360BodyData_"
						+ k + "\\stock360Body" + iStr + ".txt"; // # Vin testing

				hasStock360BodyParameters[i] = getFile(bodyDatafile);

				String jsonTextFrgetHasStock360WS = com_libs.getSourceCode(hasStock360BodyParameters[i],
						comparePutServiceURL, "");// PUT

				getHasStock360Details(getHasStock360SavePathFile, titleStringgetHasStock360WS,
						jsonTextFrgetHasStock360WS, comparePutServiceURL, hasStock360BodyParameters[i], count);
			}
		}
	}

	public static void getHasStock360Details(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		serverTime = "";
		error = "";
		executionTimeMS = "";
		String resultObj = "";
		String vehiclesObj = "";
		String hasImages = "";
		String countryCode = "";
		String language = "";
		String vin = "";
		String style = "";
		String acode = "";
		String token = "";
		String chromeymmid = "";
		String year = "";
		String make = "";
		String model = "";
		String trim = "";
		String colorObj = "";
		String colorContains = "";
		String oemcolorObj = "";
		String oemcolorContains = "";
		String errors = "";

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
					temp[2] = parameterS;
					temp[3] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + "  " + " - Result [] object is blank. ");

				} else {
					// for (int i = 0; i < size; i++) {
					try {
						errors = Boolean.toString(result.getBoolean("errors"));
					} catch (Exception ex) {
						errors = "null";
					}

					try {
						JSONArray vehiclesObject = result.getJSONArray("vehicles");
						int vehiclesSize = vehiclesObject.length();
						vehiclesObj = Integer.toString(vehiclesSize);
						for (int j = 0; j < vehiclesSize; j++) {
							try {
								hasImages = Boolean.toString(vehiclesObject.getJSONObject(j).getBoolean("hasImages"));
							} catch (Exception ex) {
								hasImages = "null";
							}

							try {
								countryCode = vehiclesObject.getJSONObject(j).getString("countryCode");
							} catch (Exception ex) {
								countryCode = "null";
							}

							try {
								language = vehiclesObject.getJSONObject(j).getString("language");
							} catch (Exception ex) {
								language = "null";
							}

							try {
								vin = vehiclesObject.getJSONObject(j).getString("vin");
							} catch (Exception ex) {
								vin = "null";
							}
							try {
								style = vehiclesObject.getJSONObject(j).getString("style");
							} catch (Exception ex) {
								style = "null";
							}
							try {
								acode = vehiclesObject.getJSONObject(j).getString("acode");
							} catch (Exception ex) {
								acode = "null";
							}
							try {
								token = vehiclesObject.getJSONObject(j).getString("token");
							} catch (Exception ex) {
								token = "null";
							}

							try {
								chromeymmid = vehiclesObject.getJSONObject(j).getString("chromeymmid");
							} catch (Exception ex) {
								chromeymmid = "null";
							}
							try {
								year = vehiclesObject.getJSONObject(j).getString("year");
							} catch (Exception ex) {
								year = "null";
							}
							try {
								make = vehiclesObject.getJSONObject(j).getString("make");
							} catch (Exception ex) {
								make = "null";
							}
							try {
								model = vehiclesObject.getJSONObject(j).getString("model");
							} catch (Exception ex) {
								model = "null";
							}
							try {
								trim = vehiclesObject.getJSONObject(j).getString("trim");
							} catch (Exception ex) {
								trim = "null";
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
							jsonValue[4] = error;
							jsonValue[5] = executionTimeMS;
							jsonValue[6] = resultObj;
							jsonValue[7] = vehiclesObj;

							jsonValue[8] = hasImages;

							jsonValue[9] = countryCode;
							jsonValue[10] = language;
							jsonValue[11] = vin;
							jsonValue[12] = style;
							jsonValue[13] = acode;
							jsonValue[14] = token;
							jsonValue[15] = chromeymmid;

							jsonValue[16] = year;
							jsonValue[17] = make;
							jsonValue[18] = model;
							jsonValue[19] = trim;
							jsonValue[20] = "";
							jsonValue[21] = "";
							jsonValue[22] = "";
							jsonValue[23] = "";
							jsonValue[24] = errors;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							try {

								JSONArray colorObject = vehiclesObject.getJSONObject(j).getJSONArray("color");
								int colorSize = colorObject.length();
								colorObj = Integer.toString(colorSize);
								for (int k = 0; k < colorSize; k++) {

									colorContains = colorObject.getString(k).toString();

									System.out.println("S/N: " + countNum);
									System.out.println("executionTimeMS: " + executionTimeMS);
									System.out.println("URLString: " + URLString);

									// int w1Size = titleString.length;
									// String[] jsonValue = new String[w1Size];

									jsonValue[0] = Integer.toString(countNum);
									jsonValue[1] = URLString;
									jsonValue[2] = parameterS;
									jsonValue[3] = serverTime;
									jsonValue[4] = error;
									jsonValue[5] = executionTimeMS;
									jsonValue[6] = resultObj;
									jsonValue[7] = vehiclesObj;
									jsonValue[8] = hasImages;
									jsonValue[9] = countryCode;
									jsonValue[10] = language;
									jsonValue[11] = vin;
									jsonValue[12] = style;
									jsonValue[13] = acode;
									jsonValue[14] = token;
									jsonValue[15] = chromeymmid;
									jsonValue[16] = year;
									jsonValue[17] = make;
									jsonValue[18] = model;
									jsonValue[19] = trim;
									jsonValue[20] = colorObj;
									jsonValue[21] = colorContains;
									jsonValue[22] = "";
									jsonValue[23] = "";
									jsonValue[24] = errors;
									cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

								}

							} catch (Exception ex) {
								System.out.println("S/N: " + ex);
								colorObj = "null";
								colorContains = "null";
								System.out.println("S/N: " + countNum);
								System.out.println("executionTimeMS: " + executionTimeMS);
								System.out.println("URLString: " + URLString);

								// int w1Size = titleString.length;
								// String[] jsonValue = new String[w1Size];

								jsonValue[0] = Integer.toString(countNum);
								jsonValue[1] = URLString;
								jsonValue[2] = parameterS;
								jsonValue[3] = serverTime;
								jsonValue[4] = error;
								jsonValue[5] = executionTimeMS;
								jsonValue[6] = resultObj;
								jsonValue[7] = vehiclesObj;
								jsonValue[8] = hasImages;
								jsonValue[9] = countryCode;
								jsonValue[10] = language;
								jsonValue[11] = vin;
								jsonValue[12] = style;
								jsonValue[13] = acode;
								jsonValue[14] = token;
								jsonValue[15] = chromeymmid;
								jsonValue[16] = year;
								jsonValue[17] = make;
								jsonValue[18] = model;
								jsonValue[19] = trim;
								jsonValue[20] = colorObj;
								jsonValue[21] = colorContains;
								jsonValue[22] = "";
								jsonValue[23] = "";
								jsonValue[24] = errors;
								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							}

							try {

								JSONArray oemcolorObject = vehiclesObject.getJSONObject(j).getJSONArray("oemcolor");
								int oemcolorSize = oemcolorObject.length();
								oemcolorObj = Integer.toString(oemcolorSize);
								for (int k = 0; k < oemcolorSize; k++) {
									oemcolorContains = oemcolorObject.getJSONObject(k).toString();

									System.out.println("S/N: " + countNum);
									System.out.println("executionTimeMS: " + executionTimeMS);
									System.out.println("URLString: " + URLString);

									// int w1Size = titleString.length;
									// String[] jsonValue = new String[w1Size];

									jsonValue[0] = Integer.toString(countNum);
									jsonValue[1] = URLString;
									jsonValue[2] = parameterS;
									jsonValue[3] = serverTime;
									jsonValue[4] = error;
									jsonValue[5] = executionTimeMS;
									jsonValue[6] = resultObj;
									jsonValue[7] = vehiclesObj;
									jsonValue[8] = hasImages;
									jsonValue[9] = countryCode;
									jsonValue[10] = language;
									jsonValue[11] = vin;
									jsonValue[12] = style;
									jsonValue[13] = acode;
									jsonValue[14] = token;
									jsonValue[15] = chromeymmid;
									jsonValue[16] = year;
									jsonValue[17] = make;
									jsonValue[18] = model;
									jsonValue[19] = trim;
									jsonValue[20] = colorObj;
									jsonValue[21] = colorContains;
									jsonValue[22] = oemcolorObj;
									jsonValue[23] = oemcolorContains;
									jsonValue[24] = errors;
									cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

								}

							} catch (Exception ex) {
								oemcolorObj = "null";
								oemcolorContains = "null";
								System.out.println("S/N: " + countNum);
								System.out.println("executionTimeMS: " + executionTimeMS);
								System.out.println("URLString: " + URLString);

								// int w1Size = titleString.length;
								// String[] jsonValue = new String[w1Size];

								jsonValue[0] = Integer.toString(countNum);
								jsonValue[1] = URLString;
								jsonValue[2] = parameterS;
								jsonValue[3] = serverTime;
								jsonValue[4] = error;
								jsonValue[5] = executionTimeMS;
								jsonValue[6] = resultObj;
								jsonValue[7] = vehiclesObj;
								jsonValue[8] = hasImages;
								jsonValue[9] = countryCode;
								jsonValue[10] = language;
								jsonValue[11] = vin;
								jsonValue[12] = style;
								jsonValue[13] = acode;
								jsonValue[14] = token;
								jsonValue[15] = chromeymmid;
								jsonValue[16] = year;
								jsonValue[17] = make;
								jsonValue[18] = model;
								jsonValue[19] = trim;
								jsonValue[20] = colorObj;
								jsonValue[21] = colorContains;
								jsonValue[22] = oemcolorObj;
								jsonValue[23] = oemcolorContains;
								jsonValue[24] = errors;
								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							}

						}

					} catch (Exception ex) {
						vehiclesObj = "null";
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

	public static String getFile0(String fileName) throws IOException { //good to load in package before. fails to load from folder
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
			file.close();
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
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
		getHasStock360();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
