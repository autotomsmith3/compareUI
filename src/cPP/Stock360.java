package cPP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

public class Stock360 {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getStock360() throws Exception {
		// Properties prop = new Properties();
		// try {
		// // prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		// prop.load(ComparePhoto.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		int vintotal = 15;
		int startToRun=1;//Default=1;
		int endToRun=15;//Default =vintotal
		String dateFolder="20170705";
		for (int k = startToRun; k <= endToRun; k++) {//vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k);
			String getStock360SavePathFile = "C:\\1\\Eclipse\\Test Results\\Colorized360\\stock360\\"+dateFolder+"\\getStock360_Vin_"
					+ kStr + ".xls";

			String[] titleStringgetStock360WS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "error",
					"executionTimeMS", "resultObj", "playlistObj", "vehicleDetailsObj", "oemLogo", "year", "make",
					"presentationsObj", "version", "date", "currentSessionId", "currentRequestId", "presentationId",
					"presentationsObj#", "type", "trackType", "location", "model", "trim", "colorGroup",
					"colorGroupCode", "color", "colorCode", "imagePlayListObj", "imagePlayListObj#", "url", "errors" };
			// DEV
			// String comparePutServiceURL = "http://lnoc-dvcp-xws1.autodatacorp.org:8080/image/rest/stock360";
			// QA
			String comparePutServiceURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/image/rest/stock360";

			int dataLength = 10;
			String[] Stock360BodyParameters = new String[dataLength + 1];
			int datasize = Stock360BodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

//				String bodyDatafile = "stock360BodyData_" + k + "/stock360Body" + iStr + ".txt"; // # Vin  working.
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\Colorized360\\stock360\\TestData\\stock360BodyData_" + k + "\\stock360Body" + iStr + ".txt"; // # Vin testing
				
				Stock360BodyParameters[i] = getFile(bodyDatafile);

				String jsonTextFrgetStock360WS = com_libs.getSourceCode(Stock360BodyParameters[i], comparePutServiceURL,
						"");// PUT

				getStock360Details(getStock360SavePathFile, titleStringgetStock360WS, jsonTextFrgetStock360WS,
						comparePutServiceURL, Stock360BodyParameters[i], count);
			}
		}
	}

	public static void getStock360Details(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultObj = "";
		String playlistObj = "";
		String vehicleDetailsObj = "";
		String oemLogo = "";
		String year = "";
		String make = "";
		String presentationsObj = "";
		String version = "";
		String date = "";
		String currentSessionId = "";
		String currentRequestId = "";
		String presentationId = "";
		String presentationsObjNum = "";
		String type = "";
		String trackType = "";
		String location = "";
		String model = "";
		String trim = "";
		String colorGroup = "";
		String colorGroupCode = "";
		String color = "";
		String colorCode = "";
		String imagePlayListObj = "";
		String imagePlayListObjNum = "";
		String url = "";
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
					try {
						errors = Boolean.toString(result.getBoolean("errors"));
					} catch (Exception ex) {
						errors = "null";
					}

					try {
						JSONObject playlistObject = result.getJSONObject("playlist");
						int playlistSize = playlistObject.length();
						playlistObj = Integer.toString(playlistSize);
						if (playlistSize == 0) {
							playlistObj = "null";
						}
						try {
							version = playlistObject.getString("version");
						} catch (Exception ex) {
							version = "null";
						}
						try {
							date = playlistObject.getString("date");
						} catch (Exception ex) {
							date = "null";
						}
						try {
							currentSessionId = playlistObject.getString("currentSessionId");
						} catch (Exception ex) {
							currentSessionId = "null";
						}
						try {
							currentRequestId = playlistObject.getString("currentRequestId");
						} catch (Exception ex) {
							currentRequestId = "null";
						}
						try {
							presentationId = playlistObject.getString("presentationId");
						} catch (Exception ex) {
							presentationId = "null";
						}

						JSONObject vehicleDetailsObject = playlistObject.getJSONObject("vehicleDetails");
						int vehicleDetailsSize = vehicleDetailsObject.length();
						vehicleDetailsObj = Integer.toString(vehicleDetailsSize);
						if (vehicleDetailsSize == 0) {
							vehicleDetailsObj = "null";
						}
						try {
							oemLogo = vehicleDetailsObject.getString("oemLogo");
						} catch (Exception ex) {
							oemLogo = "null";
						}
						try {
							year = vehicleDetailsObject.getString("year");
						} catch (Exception ex) {
							year = "null";
						}
						try {
							make = vehicleDetailsObject.getString("make");
						} catch (Exception ex) {
							make = "null";
						}

						JSONArray presentationsObject = playlistObject.getJSONArray("presentations");
						int presentationsSize = presentationsObject.length();
						presentationsObj = Integer.toString(presentationsSize);
						if (presentationsSize == 0) {
							presentationsObj = "null";
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
							jsonValue[7] = playlistObj;
							jsonValue[8] = vehicleDetailsObj;
							jsonValue[9] = oemLogo;
							jsonValue[10] = year;
							jsonValue[11] = make;
							jsonValue[12] = presentationsObj;
							jsonValue[13] = version;
							jsonValue[14] = date;
							jsonValue[15] = currentSessionId;
							jsonValue[16] = currentRequestId;
							jsonValue[17] = presentationId;
							// jsonValue[18] = presentationsObjNum;
							// jsonValue[19] = type;
							// jsonValue[20] = trackType;
							// jsonValue[21] = location;
							// jsonValue[22] = model;
							// jsonValue[23] = trim;
							// jsonValue[24] = colorGroup;
							// jsonValue[25] = colorGroupCode;
							// jsonValue[26] = color;
							// jsonValue[27] = colorCode;
							// jsonValue[28] = imagePlayListObj;
							// jsonValue[29] = imagePlayListObjNum;
							// jsonValue[30] = url;
							jsonValue[31] = errors;

							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

						}
						for (int i = 0; i < presentationsSize; i++) {
							presentationsObjNum = Integer.toString(i);
							try {
								type = presentationsObject.getJSONObject(i).getString("type");
							} catch (Exception ex) {
								type = "null";
							}
							try {
								trackType = presentationsObject.getJSONObject(i).getString("trackType");
							} catch (Exception ex) {
								trackType = "null";
							}
							try {
								location = presentationsObject.getJSONObject(i).getString("location");
							} catch (Exception ex) {
								location = "null";
							}
							try {
								model = presentationsObject.getJSONObject(i).getString("model");
							} catch (Exception ex) {
								model = "null";
							}
							try {
								trim = presentationsObject.getJSONObject(i).getString("trim");
							} catch (Exception ex) {
								trim = "null";
							}
							try {
								colorGroup = presentationsObject.getJSONObject(i).getString("colorGroup");
							} catch (Exception ex) {
								colorGroup = "null";
							}
							try {
								colorGroupCode = presentationsObject.getJSONObject(i).getString("colorGroupCode");
							} catch (Exception ex) {
								colorGroupCode = "null";
							}
							try {
								color = presentationsObject.getJSONObject(i).getString("color");
							} catch (Exception ex) {
								color = "null";
							}
							try {
								colorCode = presentationsObject.getJSONObject(i).getString("colorCode");
							} catch (Exception ex) {
								colorCode = "null";
							}

							JSONArray imagePlayListObject = presentationsObject.getJSONObject(i)
									.getJSONArray("imagePlayList");
							int imagePlayListSize = imagePlayListObject.length();
							imagePlayListObj = Integer.toString(imagePlayListSize);
							if (imagePlayListSize == 0) {
								imagePlayListObj = "null";
							}

							for (int j = 0; j < imagePlayListSize; j++) {
								imagePlayListObjNum = Integer.toString(j);
								try {
									url = imagePlayListObject.getJSONObject(j).getString("url");
								} catch (Exception ex) {
									url = "null";
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
								jsonValue[7] = playlistObj;
								jsonValue[8] = vehicleDetailsObj;
								jsonValue[9] = oemLogo;
								jsonValue[10] = year;
								jsonValue[11] = make;
								jsonValue[12] = presentationsObj;
								jsonValue[13] = version;
								jsonValue[14] = date;
								jsonValue[15] = currentSessionId;
								jsonValue[16] = currentRequestId;
								jsonValue[17] = presentationId;
								jsonValue[18] = presentationsObjNum;
								jsonValue[19] = type;
								jsonValue[20] = trackType;
								jsonValue[21] = location;
								jsonValue[22] = model;
								jsonValue[23] = trim;
								jsonValue[24] = colorGroup;
								jsonValue[25] = colorGroupCode;
								jsonValue[26] = color;
								jsonValue[27] = colorCode;
								jsonValue[28] = imagePlayListObj;
								jsonValue[29] = imagePlayListObjNum;
								jsonValue[30] = url;
								jsonValue[31] = errors;

								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							}

						}

					} catch (Exception ex) {
						playlistObj = "null";

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
						jsonValue[7] = playlistObj;
						jsonValue[8] = "";
						jsonValue[9] = "";
						jsonValue[10] = "";
						jsonValue[11] = "";
						jsonValue[12] = "";
						jsonValue[13] = "";
						jsonValue[14] = "";
						jsonValue[15] = "";
						jsonValue[16] = "";
						jsonValue[17] = "";
						jsonValue[18] = "";
						jsonValue[19] = "";
						jsonValue[20] = "";
						jsonValue[21] = "";
						jsonValue[22] = "";
						jsonValue[23] = "";
						jsonValue[24] = "";
						jsonValue[25] = "";
						jsonValue[26] = "";
						jsonValue[27] = "";
						jsonValue[28] = "";
						jsonValue[29] = "";
						jsonValue[30] = "";
						jsonValue[31] = errors;

						cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

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

	public static String getFile_fromEclips(String fileName) throws IOException {
		int lineNum;
		String line = "";
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream(fileName);

//		 File file = new File(classLoader.getResource(fileName).getFile());
		int i = 1;
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				i++;
				result.append(line).append("\n");
				// result.append(line).append("\n");
			}
			lineNum = i - 1;
//			file.close();
//			file.();
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
		getStock360();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
