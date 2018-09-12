package cPP;

//This is regression test for CPP-1928 endpoint: http://lnoc-q1cp-xws1:8080/vehicle-token/rest/vehicle/token
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

public class VehicleTokenBS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getVehicleTokenBS() throws Exception {
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
		String dateFolder = "20180323";
		String[] SpiraID = { "0. TC 202427", "1. TC 185573", "2. TC 185574", "3. TC 185575", "4. TC 185576",
				"5. TC 185577", "6. TC 185578", "7. TC 185579", "8. TC 185580", "9. TC 185621", "10. TC 185622",
				"11. TC 185603", "12. TC 185604", "12.1 TC 185604", "12.2 TC 185604", "12.3 TC 185604",
				"12.4 TC 185604", "13. TC 185605", "13.1 TC 185605", "13.2 TC 185605", "13.3 TC 185605",
				"13.4 TC 185605", "13.5 TC 185605", "14. TC 185606", "14.1 TC 185606", "14.2 TC 185606",
				"14.3 TC 185606", "14.4 TC 185606", "15. TC 185607", "15.1 TC 185607", "15.2 TC 185607",
				"15.3 TC 185607", "16. TC 185608", "16.1 TC 185608", "16.2 TC 185608", "17. TC 185609",
				"17.1 TC 185609", "17.2 TC 185609", "17.3 TC 185609", "18. TC 185610", "18.1 TC 185610",
				"18.2 TC 185610", "18.3 TC 185610", "18.4 TC 185610", "18.5 TC 185610", "19. TC 185611",
				"19.1 TC 185611", "19.2 TC 185611", "19.3 TC 185611", "19.4 TC 185611", "19.5 TC 185611",
				"20. TC 185613", "21. TC 185614", "22. TC 185615", "23. TC 185616", "24. TC 185617", "25. TC 185618",
				"26. TC 185619", "27. TC 185620", "28. TC 185593", "29. TC 185594", "29.1 TC 185594", "29.2 TC 185594",
				"29.3 TC 185594", "29.4 TC 185594", "29.5 TC 185594", "29.6 TC 185594", "29.7 TC 185594",
				"30. TC 185595", "30.1 TC 185595", "30.2 TC 185595", "30.3 TC 185595", "30.4 TC 185595",
				"30.5 TC 185595", "31. TC 185596", "31.1 TC 185596", "31.2 TC 185596", "31.3 TC 185596",
				"31.4 TC 185596", "32. TC 185597", "32.1 TC 185597", "32.2 TC 185597", "32.3 TC 185597",
				"33. TC 185598", "33.1 TC 185598", "33.2 TC 185598", "33.3 TC 185598", "34. TC 185599",
				"34.1 TC 185599", "34.2 TC 185599", "34.3 TC 185599", "34.4 TC 185599", "35. TC 185600",
				"35.1 TC 185600", "35.2 TC 185600", "35.3 TC 185600", "35.4 TC 185600", "35.5 TC 185600",
				"35.6 TC 185600", "35.7 TC 185600", "35.8 TC 185600", "35.9 TC 185600", "35.10 TC 185600",
				"35.11 TC 185600", "35.12 TC 185600", "35.13 TC 185600", "35.14 TC 185600", "36. TC 185601",
				"36.1 TC 185601", "36.2 TC 185601", "36.3 TC 185601", "36.4 TC 185601", "36.5 TC 185601",
				"36.6 TC 185601", "36.7 TC 185601", "36.8 TC 185601", "36.9 TC 185601", "36.10 TC 185601",
				"36.11 TC 185601", "36.12 TC 185601", "36.13 TC 185601", "37. TC 185583", "38. TC 185584",
				"38.1 TC 185584", "38.2 TC 185584", "38.3 TC 185584", "38.4 TC 185584", "38.5 TC 185584",
				"39. TC 185585", "39.1 TC 185585", "39.2 TC 185585", "39.3 TC 185585", "39.4 TC 185585",
				"39.5 TC 185585", "40. TC 185586", "40.1 TC 185586", "40.2 TC 185586", "40.3 TC 185586",
				"40.4 TC 185586", "40.5 TC 185586", "41. TC 185587", "41.1 TC 185587", "41.2 TC 185587",
				"41.3 TC 185587", "41.4 TC 185587", "41.5 TC 185587", "42. TC 185588", "42.1 TC 185588",
				"42.2 TC 185588", "42.3 TC 185588", "42.4 TC 185588", "42.5 TC 185588", "43. TC 185589",
				"43.1 TC 185589", "43.2 TC 185589", "43.3 TC 185589", "43.4 TC 185589", "43.5 TC 185589",
				"44. TC 185590", "44.1 TC 185590", "44.2 TC 185590", "44.3 TC 185590", "44.4 TC 185590",
				"44.5 TC 185590", "45. TC 185590", "45.1 TC 185590", "45.2 TC 185590", "45.3 TC 185590",
				"45.4 TC 185590", "45.5 TC 185590", "CPP-1928_05 - build data", "CPP-1928_06 - added sourceProviders" };// Total 172

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k);
			String getVehicleTokenBSSavePathFile = "C:\\1\\Eclipse\\Test Results\\VehicleTokenBS\\" + dateFolder
					+ "\\VehicleToken_" + kStr + ".xls";
			String jsonpathfilename = "C:\\1\\Eclipse\\Test Results\\VehicleTokenBS\\" + dateFolder + "\\VehicleToken_";// + ".json";

			String[] titleStringgetImageWS = { "S/N", "URL_parameter", "RequestBody", "id", "serverTime", "error ",
					"executionTimeMS", "copyright", "resultObj", "tokensObj", "token", "primaryGVUIDObj",
					"primaryGVUID", "gvuid", "typeId", "typeIdValue", "add_GVUIDSObj", "add_gvuid", "add_typeId",
					"add_typeIdValue", "buildData", "vehicleOptions" };

			// // QA PUT
//			String imagePutServiceURL = "http://lnoc-q1cp-xws1:8080/vehicle-token/rest/vehicle/token";
			String imagePutServiceURL = "http://lnoc-q2cp-xws1.autodatacorp.org:8080/combined-vehicle/rest/vehicle/token";
			// int dataLength = 10;//one vin has 10 tcs.// colorized360(stock360) used before. OK
			// ******************************************************
			// ******************************************************
			int dataLength = SpiraID.length;// 35;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] imageMSBodyParameters = new String[dataLength + 1];
			int datasize = imageMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\VehicleTokenBS\\TestData\\VehicleTokenBody" + iStr
						+ ".txt"; // # group number testing

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
				getVehicleTokenBSDetails(getVehicleTokenBSSavePathFile, titleStringgetImageWS, jsonTextFrgetImageWS,
						imagePutServiceURL, imageMSBodyParameters[i], count);
			}
		}
	}

	public static void getVehicleTokenBSDetails(String wsResultfile, String[] titleString, String text,
			String URLString, String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String id = "";
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String copyright = "";
		String resultObj = "";
		String tokensObj = "";
		String token = "";
		String primaryGVUIDObj = "";
		String primaryGVUID = "";
		String gvuid = "";
		String typeId = "";
		String typeIdValue = "";

		String additionalGVUIDSObj = "";
		String additional_gvuid = "";
		String additional_typeId = "";
		String additional_typeIdValue = "";

		String buildData = "";
		String vehicleOptions = "";

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
				id = obj.getString(("id"));
				serverTime = obj.getString(("serverTime"));
				error = Boolean.toString(obj.getBoolean("error"));
				executionTimeMS = Long.toString(obj.getLong("executionTimeMS"));
				copyright = obj.getString(("copyright"));

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
					for (int i = 0; i < size; i++) {

						try {
							JSONArray tokensArray = result.getJSONArray("tokens");
							int tokenssize = tokensArray.length();
							tokensObj = Integer.toString(tokenssize);
							for (int j = 0; j < tokenssize; j++) {
								try {
									token = tokensArray.getJSONObject(j).getString("token");
								} catch (Exception ex) {
									token = "null";
								}

								try {
									buildData = Boolean.toString(tokensArray.getJSONObject(j).getBoolean("buildData"));
								} catch (Exception ex) {
									buildData = "null";
								}
								try {
									vehicleOptions = Boolean
											.toString(tokensArray.getJSONObject(j).getBoolean("vehicleOptions"));
								} catch (Exception ex) {
									vehicleOptions = "null";
								}

								JSONObject primaryGVUIDObject = tokensArray.getJSONObject(j)
										.getJSONObject("primaryGVUID");
								int primarysize = primaryGVUIDObject.length();
								try {
									primaryGVUIDObj = Integer.toString(primarysize);
								} catch (Exception ex) {
									primaryGVUIDObj = "null";
								}

								try {
									gvuid = primaryGVUIDObject.getString("gvuid");
								} catch (Exception ex) {
									gvuid = "null";
								}

								try {
									typeId = primaryGVUIDObject.getString("typeId");
								} catch (Exception ex) {
									typeId = "null";
								}
								try {
									typeIdValue = primaryGVUIDObject.getString("typeIdValue");
								} catch (Exception ex) {
									typeIdValue = "null";
								}
								int additionalGVUIDSsize = 0;
								if (countNum==160) {
									System.out.println("S/N: " + countNum);
								}
								try {
									JSONArray additionalGVUIDSArray = tokensArray.getJSONObject(i).getJSONArray("additionalGVUIDs");
									additionalGVUIDSsize = additionalGVUIDSArray.length();
									additionalGVUIDSObj = Integer.toString(additionalGVUIDSsize);
									for (int k = 0; k < additionalGVUIDSsize; k++) {
										try {
											additional_gvuid = additionalGVUIDSArray.getJSONObject(k)
													.getString("gvuid");
										} catch (Exception ex) {
											additional_gvuid = "null";
										}

										try {
											additional_typeId = additionalGVUIDSArray.getJSONObject(k)
													.getString("typeId");
										} catch (Exception ex) {
											additional_typeId = "null";
										}
										try {
											additional_typeIdValue = additionalGVUIDSArray.getJSONObject(k)
													.getString("typeIdValue");
										} catch (Exception ex) {
											additional_typeIdValue = "null";
										}

										System.out.println("S/N: " + countNum);
										System.out.println("executionTimeMS: " + executionTimeMS);
										System.out.println("URLString: " + URLString);

										int wSize = titleString.length;
										String[] jsonValue = new String[wSize];

										jsonValue[0] = Integer.toString(countNum);
										jsonValue[1] = URLString;
										jsonValue[2] = parameterS;
										jsonValue[3] = id;
										jsonValue[4] = serverTime;
										jsonValue[5] = error;
										jsonValue[6] = executionTimeMS;
										jsonValue[7] = copyright;
										jsonValue[8] = resultObj;
										jsonValue[9] = tokensObj;
										jsonValue[10] = token;
//										jsonValue[11] = primaryGVUIDObj;
//										jsonValue[12] = primaryGVUID;
//										jsonValue[13] = gvuid;
//										jsonValue[14] = typeId;
//										jsonValue[15] = typeIdValue;
										jsonValue[16] = additionalGVUIDSObj;
										jsonValue[17] = additional_gvuid;
										jsonValue[18] = additional_typeId;
										jsonValue[19] = additional_typeIdValue;

										jsonValue[20] = buildData;
										jsonValue[21] = vehicleOptions;

										cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

									}
								} catch (Exception ex) {
									additionalGVUIDSObj = "null";
//									additionalGVUIDSsize = 0;
//									System.out.println("S/N: " + countNum);
//									System.out.println("executionTimeMS: " + executionTimeMS);
//									System.out.println("URLString: " + URLString);
//
//									int wSize = titleString.length;
//									String[] jsonValue = new String[wSize];
//
//									jsonValue[0] = Integer.toString(countNum);
//									jsonValue[1] = URLString;
//									jsonValue[2] = parameterS;
//									jsonValue[3] = id;
//									jsonValue[4] = serverTime;
//									jsonValue[5] = error;
//									jsonValue[6] = executionTimeMS;
//									jsonValue[7] = copyright;
//									jsonValue[8] = resultObj;
//									jsonValue[9] = tokensObj;
//									jsonValue[10] = token;
//									// jsonValue[11] = primaryGVUIDObj;
//									// jsonValue[12] = primaryGVUID;
//									// jsonValue[13] = gvuid;
//									// jsonValue[14] = typeId;
//									// jsonValue[15] = typeIdValue;
//									jsonValue[16] = additionalGVUIDSObj;
//									// jsonValue[17] = additional_gvuid;
//									// jsonValue[18] = additional_typeId;
//									// jsonValue[19] = additional_typeIdValue;
//
//									jsonValue[20] = buildData;
//									jsonValue[21] = vehicleOptions;
//
//									cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
								}

								System.out.println("S/N: " + countNum);
								System.out.println("executionTimeMS: " + executionTimeMS);
								System.out.println("URLString: " + URLString);

								int wSize = titleString.length;
								String[] jsonValue = new String[wSize];

								jsonValue[0] = Integer.toString(countNum);
								jsonValue[1] = URLString;
								jsonValue[2] = parameterS;
								jsonValue[3] = id;
								jsonValue[4] = serverTime;
								jsonValue[5] = error;
								jsonValue[6] = executionTimeMS;
								jsonValue[7] = copyright;
								jsonValue[8] = resultObj;
								jsonValue[9] = tokensObj;
								jsonValue[10] = token;
								jsonValue[11] = primaryGVUIDObj;
								jsonValue[12] = primaryGVUID;
								jsonValue[13] = gvuid;
								jsonValue[14] = typeId;
								jsonValue[15] = typeIdValue;
//								jsonValue[16] = additionalGVUIDSObj;
//								jsonValue[17] = additional_gvuid;
//								jsonValue[18] = additional_typeId;
//								jsonValue[19] = additional_typeIdValue;

								jsonValue[20] = buildData;
								jsonValue[21] = vehicleOptions;

								cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

							}

						} catch (Exception ex) {
							tokensObj = "not exist";
						}

						// try {
						// imageName = result.getJSONObject(i).getString("imageName");
						// } catch (Exception ex) {
						// imageName = "not exist";
						// }
						// try {
						// colorId = result.getJSONObject(i).getString("colorId");
						// } catch (Exception ex) {
						// colorId = "not exist";
						// }
						// try {
						// optionCode = result.getJSONObject(i).getString("optionCode");
						// } catch (Exception ex) {
						// optionCode = "not exist";
						// }

						// System.out.println("S/N: " + countNum);
						// System.out.println("executionTimeMS: " + executionTimeMS);
						// System.out.println("URLString: " + URLString);
						//
						// int wSize = titleString.length;
						// String[] jsonValue = new String[wSize];
						//
						// jsonValue[0] = Integer.toString(countNum);
						// jsonValue[1] = URLString;
						// jsonValue[2] = parameterS;
						// jsonValue[3] = id;
						// jsonValue[4] = serverTime;
						// jsonValue[5] = executionTimeMS;
						// jsonValue[6] = copyright;
						//
						// jsonValue[7] = resultObj;
						//
						// jsonValue[8] = tokensObj;
						//
						// jsonValue[9] = token;
						// jsonValue[10] = primaryGVUID;
						// jsonValue[11] = gvuid;
						// jsonValue[12] = typeId;
						// jsonValue[13] = buildData;
						// jsonValue[14] = vehicleOptions;
						//
						// cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

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
		getVehicleTokenBS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
