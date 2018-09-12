package compareS;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import cPP.com_libs;

//import cPP.JSONParser;

public class ComparePhoto {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getComparePhoto() throws Exception {
		// Properties prop = new Properties();
		// try {
		// // prop.load(JSONParser.class.getClassLoader().getResourceAsStream("cPP_data/compareS.properties"));
		// prop.load(ComparePhoto.class.getClassLoader().getResourceAsStream("cPP_data/modelwalk.properties"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		String GetComparePhotoSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareS\\ComparePhoto.xls";

		String[] titleStringGetComparePhotoWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "error",
				"executionTimeMS", "result", "trimId", "year", "make", "model", "trim", "variation", "pricing_Obj:",
				"msrp", "ColorsObj", "exteriorObj", "colorCode", "genericColor", "name", "rgb", "mediaObj",
				"mediaexteriorObj", "shotID", "colorCode", "image" };

		String compareBodyParameters = getFile("compareBodyData/PhotoBody001.txt");

		String comparePutServiceURL = "http://lnoc-q1cp-xws1:8080/compare/rest/compare/photo?debug=false";

		String jsonTextFrGetComparePhotoWS = com_libs.getSourceCode(compareBodyParameters, comparePutServiceURL, "");// PUT

		getComparePhotoDetails(GetComparePhotoSavePathFile, titleStringGetComparePhotoWS, jsonTextFrGetComparePhotoWS,
				comparePutServiceURL, compareBodyParameters, 1);

	}

	public static void getComparePhotoDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String error = "";
		String executionTimeMS = "";
		String resultsize = "";
		// URLString = "";
		serverTime = "";
		error = "";
		executionTimeMS = "";
		resultsize = "";
		String msrp = "";
		String trimId = "";
		String year = "";
		String make = "";
		String model = "";
		String trim = "";
		String pricingO = "";
		String variation = "";
		String colorsObj = "";
		String exteriorObj = "";
		String colorCode = "";
		String genericColor = "";
		String name = "";
		String rgb = "";
		String mediaObj = "";
		String mediaexteriorObj = "";
		String mediaexteriorOjbString = "";
		String shotID = "";
		String mediacolorCode = "";
		String image = "";

		String[] temp = new String[30];
		if (text.equals("")) {
			blank++;
			temp[0] = Integer.toString(countNum);
			temp[1] = URLString;
			temp[2] = "";
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

				JSONArray result = obj.getJSONArray("result");
				int size = result.length();
				resultsize = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = "";
					temp[3] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("gvuid = " + "  " + " - Result [] object is blank. ");

				} else {
					for (int i = 0; i < size; i++) {
						try {
							trimId = result.getJSONObject(i).getString("trimId");
						} catch (Exception ex) {
							trimId = "null";
						}
						try {
							year = result.getJSONObject(i).getString("year");
						} catch (Exception ex) {
							year = "";
						}
						try {
							make = result.getJSONObject(i).getString("make");
						} catch (Exception ex) {
							make = "null";
						}
						try {
							trim = result.getJSONObject(i).getString("trim");
						} catch (Exception ex) {
							trim = "null";
						}
						try {
							model = result.getJSONObject(i).getString("model");
						} catch (Exception ex) {
							model = "null";
						}
						try {
							variation = result.getJSONObject(i).getString("variation");
						} catch (Exception ex) {
							variation = "null";
						}
						try {
							JSONObject pricingObj = result.getJSONObject(i).getJSONObject("pricing");
							int pricingSize = pricingObj.length();
							pricingO = Integer.toString(pricingSize);
							msrp = Integer.toString(pricingObj.getInt("msrp"));
						} catch (Exception ex) {
							pricingO = "null";
							msrp = "null";
						}

						try {
							JSONObject colorsObject = result.getJSONObject(i).getJSONObject("colors");
							int colorssize = colorsObject.length();
							colorsObj = Integer.toString(colorssize);

							try {
								JSONArray exeriorObject = colorsObject.getJSONArray("exterior");
								int exteriorsize = exeriorObject.length();
								exteriorObj = Integer.toString(exteriorsize);
								for (int j = 0; j < exteriorsize; j++) {
									colorCode = exeriorObject.getJSONObject(j).getString("colorCode");
									genericColor = exeriorObject.getJSONObject(j).getString("genericColor");
									name = exeriorObject.getJSONObject(j).getString("name");
									rgb = exeriorObject.getJSONObject(j).getString("rgb");
								}
							} catch (Exception ex) {
								exteriorObj = "null";
								colorCode = "null";
								genericColor = "null";
								name = "null";
								rgb = "null";
							}

							// msrp = Integer.toString(colorsObject.getInt("msrp"));
						} catch (Exception ex) {
							colorsObj = "null";
						}

						try {
							JSONObject mediaObject = result.getJSONObject(i).getJSONObject("media");
							int mediaSize = mediaObject.length();
							mediaObj = Integer.toString(mediaSize);
							for (int k = 0; k < mediaSize; k++) {
								try {
									JSONArray exeriorObj = mediaObject.getJSONArray("exterior");
									int exteriorsize = exeriorObj.length();
									mediaexteriorObj = Integer.toString(exteriorsize);
									mediaexteriorOjbString = exeriorObj.toString();
									for (int j = 0; j < exteriorsize; j++) {
										shotID = exeriorObj.getJSONObject(j).getString("shotID");
										colorCode = exeriorObj.getJSONObject(j).getString("colorCode");
										image = exeriorObj.getJSONObject(j).getString("image");
										// rgb = exeriorObj.getJSONObject(j).getString("rgb");

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
										jsonValue[6] = resultsize;
										jsonValue[7] = trimId;
										jsonValue[8] = year;
										jsonValue[9] = make;
										jsonValue[10] = model;
										jsonValue[11] = trim;
										jsonValue[12] = variation;
										jsonValue[13] = pricingO;
										jsonValue[14] = msrp;
										jsonValue[15] = colorsObj;
										jsonValue[16] = exteriorObj;
										jsonValue[17] = colorCode;
										jsonValue[18] = genericColor;
										jsonValue[19] = name;
										jsonValue[20] = rgb;
										jsonValue[21] = mediaObj;
										jsonValue[22] = mediaexteriorObj + "    =====>  " + mediaexteriorOjbString;
										jsonValue[23] = shotID;
										jsonValue[24] = colorCode;
										jsonValue[25] = image;

										cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

									}
								} catch (Exception ex) {
									mediaexteriorObj = "null";
									shotID = "null";
									colorCode = "null";
									image = "null";
								}
							}

						} catch (Exception ex) {
							colorsObj = "null";
						}

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
						// jsonValue[3] = serverTime;
						// jsonValue[4] = error;
						// jsonValue[5] = executionTimeMS;
						// jsonValue[6] = resultsize;
						// jsonValue[7] = trimId;
						// jsonValue[8] = year;
						// jsonValue[9] = make;
						// jsonValue[10] = model;
						// jsonValue[11] = trim;
						// jsonValue[12] = variation;
						// jsonValue[13] = pricingO;
						// jsonValue[14] = msrp;
						// jsonValue[15] = colorsObj;
						// jsonValue[16] = exteriorObj;
						// jsonValue[17] = colorCode;
						// jsonValue[18] = genericColor;
						// jsonValue[19] = name;
						// jsonValue[20] = rgb;
						// jsonValue[21] = mediaObj;
						// jsonValue[22] = mediaexteriorObj+ " =====> "+mediaexteriorOjbString;
						// jsonValue[23] = shotID;
						// jsonValue[24] = colorCode;
						// jsonValue[25] = image;
						// cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

					}
				}
			} catch (Exception ex) {
				System.out.println("error occurs!");
				noObj++;
				temp[0] = Integer.toString(countNum);
				temp[1] = URLString;
				temp[2] = "";
				temp[3] = "200 error";
				System.out.println("S/N: " + countNum);
				System.out.println("ERROR 200 ON : " + URLString);
				cPP.com_libs.writeToSheet(wsResultfile, temp);
			}
		}

	}

	public static String getFile(String fileName) throws IOException {
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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// // From what I can read on json.org, all JSON strings should start with { (curly brace - object), and [ characters (square brackets - array) represent an array element in JSON.
		// // ******************************************************curly brace - object*********************************************************
		System.out.println("Started...");
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
