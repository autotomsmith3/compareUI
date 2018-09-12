package cPP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

public class colorMS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getColorMS() throws Exception {
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
		String dateFolder = "20170712";
//		String [] SpiraID={"TC173798","TC173799","TC173800","TC173801","TC173802","TC173803","TC173804","TC173805","TC173806","TC173807","TC173808","TC173809","TC173810","TC173811","TC173812","TC173813","TC173814","TC173815","TC173816","TC173817","TC173818","TC173819","TC173820","TC173821","TC173822","TC173823","TC173824","TC173825","TC173826","TC173827","TC173828","TC173829","TC173830","TC173831","TC173832","TC173833","TC173834","TC173835","TC173836","TC173837","TC173838","TC174450","TC174451","TC174452","TC174453","TC174878","TC174879","TC174880","TC174881","TC174882","TC174883","TC174884","TC174885","TC174886","TC174887","TC174888","TC174889","TC175289","TC175290","TC175291","TC175292"};
		String [] SpiraID={"TC173798","TC173799","TC173800","TC173801","TC173802",
				"TC173803","TC173804","TC173805","TC173806","TC173807",
				"TC173808","TC173809","TC173810","TC173811","TC173812","TC173813","TC173814","TC173815"};
				//,"TC173816","TC173817","TC173818","TC173819","TC173820","TC173821","TC173822","TC173823","TC173824","TC173825","TC173826","TC173827","TC173828","TC173829","TC173830","TC173831","TC173832","TC173833","TC173834","TC173835","TC173836","TC173837","TC173838","TC174450","TC174451","TC174452","TC174453","TC174878","TC174879","TC174880","TC174881","TC174882","TC174883","TC174884","TC174885","TC174886","TC174887","TC174888","TC174889","TC175289","TC175290","TC175291","TC175292"};

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k); /// C:\1\Eclipse\Test Results\CompareMS\ColorMS\20170401
			String getColorMSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorMS\\" + dateFolder
					+ "\\color_" + kStr + ".xls";
//ADD 1
			String jsonpathfilename="C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorMS\\" + dateFolder
					+ "\\color_" + kStr;// + ".json";;
			
			String[] titleStringgetColorWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "serverTimeString",
					"serverName", "executionTimeMS", "resultObj", "gvuid", "language", "colorsListObj", "id",
					"colorType", "colorListObj", "code", "description", "genericDescription", "hex", "type" };
			//// // DEV PUT
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/color-service/color/gvuids";

			// // QA
			String colorPutServiceURL = "http://lnoc-q1cp-xmw1:8080/color-service/color/gvuids";

			// int dataLength = 10;//one vin has 10 tcs.// colorized360(stock360) used before. OK
			// ******************************************************
			// ******************************************************
			int dataLength = 18;//61;//57;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] ColorMSBodyParameters = new String[dataLength + 1];
			int datasize = ColorMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				// String bodyDatafile = "ColorMSBodyData_" + k + "/ColorMSBody" + iStr + ".txt"; // # Vin working.
				// C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorMS\\TestData\\imageBodyDate_1\\imageBody1.txt
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ColorMS\\TestData\\colorBodyData_" + k
						+ "\\colorBody" + iStr + ".txt"; // # group number testing
				
				ColorMSBodyParameters[i] = getFile(bodyDatafile); // ok
				// ColorMSBodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgetColorWS = com_libs.getSourceCodeJson(ColorMSBodyParameters[i], colorPutServiceURL,
						"");// PUT
//ADD 3
				
				SaveScratch(jsonpathfilename+"_"+SpiraID[i]+"_"+(i+1)+".json",jsonTextFrgetColorWS);
				// GET old
				// String jsonTextFrgetColorWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getColorMSDetails(getColorMSSavePathFile, titleStringgetColorWS, jsonTextFrgetColorWS,
						colorPutServiceURL, ColorMSBodyParameters[i], count);
			}
		}
	}

	public static void getColorMSDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String serverTimeString = "";
		String serverName = "";
		String executionTimeMS = "";
		String resultObj = "";

		String gvuid = "";
		String language = "";
		String colorsListObj = "";
		String id = "";
		String colorType = "";
		String colorListObj = "";
		String code = "";
		String description = "";
		String genericDescription = "";
		String hex = "";
		String type = "";

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

				JSONArray result = obj.getJSONArray("result");
				int size = result.length();
				resultObj = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = parameterS;
					temp[3] = serverTime;
					temp[4] = serverTimeString;
					temp[5] = serverName;
					temp[6] = executionTimeMS;
					temp[7] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + "  " + " - Result [] object is blank. ");

				} else {
					// try {
					// errors = Boolean.toString(result.getBoolean("errors"));
					// } catch (Exception ex) {
					// errors = "null";
					// }
					for (int i = 0; i < size; i++) {

						try {
							gvuid = result.getJSONObject(i).getString("gvuid");
						} catch (Exception ex) {
							gvuid = "null";
						}

						try {
							language = result.getJSONObject(i).getString("language");
						} catch (Exception ex) {
							language = "null";
						}

						JSONArray colorsListObject = result.getJSONObject(i).getJSONArray("colorsList");
						int colorListsize = colorsListObject.length();
						colorsListObj = Integer.toString(colorListsize);
						if (colorListsize == 0) {
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
							jsonValue[8] = gvuid;
							jsonValue[9] = language;
							jsonValue[10] = colorsListObj;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

						} else {
							for (int j = 0; j < colorListsize; j++) {

								try {
									id = colorsListObject.getJSONObject(j).getString("id");
								} catch (Exception ex) {
									id = "null";
								}
								try {
									colorType = colorsListObject.getJSONObject(j).getString("colorType");
								} catch (Exception ex) {
									colorType = "null";
								}

								JSONArray colorListObject = colorsListObject.getJSONObject(j).getJSONArray("colorList");
								int colorListSize = colorListObject.length();
								colorListObj = Integer.toString(colorListSize);
								if (colorListSize == 0) {
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
									jsonValue[8] = gvuid;
									jsonValue[9] = language;
									jsonValue[10] = colorsListObj;

									jsonValue[11] = id;
									jsonValue[12] = colorType;
									jsonValue[13] = colorListObj;
									cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

								} else {
									for (int l = 0; l < colorListSize; l++) {

										try {
											code = colorListObject.getJSONObject(l).getString("code");
										} catch (Exception ex) {
											code = "null";
										}

										try {
											description = colorListObject.getJSONObject(l).getString("description");
										} catch (Exception ex) {
											description = "null";
										}

										try {
											genericDescription = colorListObject.getJSONObject(l)
													.getString("genericDescription");
										} catch (Exception ex) {
											genericDescription = "null";
										}

										try {
											hex = colorListObject.getJSONObject(l).getString("hex");
										} catch (Exception ex) {
											hex = "null";
										}

										try {
											type = colorListObject.getJSONObject(l).getString("type");
										} catch (Exception ex) {
											type = "null";
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
										jsonValue[8] = gvuid;
										jsonValue[9] = language;
										jsonValue[10] = colorsListObj;
										jsonValue[11] = id;
										jsonValue[12] = colorType;
										jsonValue[13] = colorListObj;

										jsonValue[14] = code;
										jsonValue[15] = description;
										jsonValue[16] = genericDescription;
										jsonValue[17] = hex;
										jsonValue[18] = type;

										cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
									}
								}
							}
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
//ADD 2
	public static void SaveScratch(String pathfilename, String ScratchText) {
		try {
//			BufferedWriter out2 = new BufferedWriter(new FileWriter(dataDir+ "Acodes.txt", true)); //original OK
			BufferedWriter out2 = new BufferedWriter(new FileWriter(pathfilename, true));
			// out2.write("("+i+"): "+Acode+": ");
//			out2.write(i + ". " + Acode + ": "); //Original OK
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
		getColorMS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
