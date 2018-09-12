package cPP;

//CPP-1073 - Create Model Image Color Micro Service. /model-image-color-service/images/by/modelYearIds
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

public class modelImageColorMS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getModelImageColorMS() throws Exception {
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
		String dateFolder = "20180208";
		String[] SpiraID = { "TC185433", "TC185434", "TC185435", "TC185436", "TC185437", "TC185438", "TC185439",
				"TC185440", "TC185441", "TC185442", "TC185443", "TC185444", "TC185445", "TC185446", "TC185447",
				"TC185448", "TC185449", "TC185450", "TC185451", "TC185452", "TC185453", "TC185454", "TC185455",
				"TC185456", "TC185457", "TC185458", "TC185459", "TC185460", "TC185461", "TC185462", "TC185463",
				"TC185464", "TC185465", "TC185466", "TC185467", "TC185468", "TC185469", "TC185470", "TC185471",
				"TC185472", "TC185473", "TC185474", "TC185475", "TC185476", "TC185477", "TC185478", "TC185479",
				"TC185480", "TC185481", "TC185482", "TC185483", "TC185484", "TC185485", "TC185486", "TC185487",
				"TC185488", "TC185489", "TC185490", "TC185491", "TC185492", "TC185493", "TC185494", "TC185495",
				"TC185496", "TC185497", "TC185498", "TC185499", "TC185500", "TC185501", "TC185502", "TC185503",
				"TC185504", "TC185505", "TC185506", "TC185507", "TC185508", "TC185509", "TC185510", "TC185511",
				"TC185512", "TC185513", "TC185514", "TC185515", "TC185516", "TC185517", "TC185518", "TC185519",
				"TC185520", "TC185521", "TC185522" };//Total 90

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k); /// C:\1\Eclipse\Test Results\CompareMS\ModelImageColorMS\20170401
			String getModelImageColorMSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageBy_modelYearIds_CPP-1073\\"
					+ dateFolder + "\\ModelImageColor_" + kStr + ".xls";
			String jsonpathfilename = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageBy_modelYearIds_CPP-1073\\"
					+ dateFolder + "\\image_color_" + kStr;// + ".json";

			String[] titleStringgetImageWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "serverTimeString",
					"serverName", "executionTimeMS", "resultObj", "modelYearId", "imageName", "colorId",
					"primaryColorCode", "primaryColorDesc", "genericColor", "secondaryColorCode", "secondaryColorDesc",
					"color2GenericDesc", "accentColorCode", "accentColorDesc", "color3GenericDesc", "optionCode",
					"shotDescription", "rangeDescription", "groupDescription", "shotId", "rangeId", "groupId",
					"ImageViews" };
			// // DEV PUT
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/model-image-color-service/images/by/modelYearIds";
			// // // QA PUT
			String imagePutServiceURL = "http://lnoc-q1cp-xmw1:8080/model-image-color-service/images/by/modelYearIds";
			// ******************************************************
			// ******************************************************
			int dataLength = 90;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] ModelImageColorMSBodyParameters = new String[dataLength + 1];
			int datasize = ModelImageColorMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				// String bodyDatafile = "imageMSBodyData_" + k + "/imageMSBody" + iStr + ".txt"; // # Vin working.
				// C:\\1\\Eclipse\\Test Results\\CompareMS\\ModelImageColorMS\\TestData\\imageBodyDate_1\\imageBody1.txt
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\ImageBy_modelYearIds_CPP-1073\\TestData\\imageBodyData_"
						+ k + "\\imageBody" + iStr + ".txt"; // # group number testing

				ModelImageColorMSBodyParameters[i] = getFile(bodyDatafile); // ok
				// ModelImageColorMSBodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgetImageWS = com_libs.getSourceCodeJson(ModelImageColorMSBodyParameters[i],
						imagePutServiceURL, "");// PUT
				SaveScratch(jsonpathfilename + "_" + SpiraID[i] + "_" + (i + 1) + ".json", jsonTextFrgetImageWS);
				// GET old
				// String jsonTextFrgetImageWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getModelImageColorMSDetails(getModelImageColorMSSavePathFile, titleStringgetImageWS,
						jsonTextFrgetImageWS, imagePutServiceURL, ModelImageColorMSBodyParameters[i], count);
			}
		}
	}

	public static void getModelImageColorMSDetails(String wsResultfile, String[] titleString, String text,
			String URLString, String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String serverTimeString = "";
		String serverName = "";
		String executionTimeMS = "";
		String resultObj = "";

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

				JSONArray result = obj.getJSONArray("result");
				int size = result.length();
				resultObj = Integer.toString(size);
				if (size == 0) {
					blank++;
					temp[0] = Integer.toString(countNum);
					temp[1] = URLString;
					temp[2] = parameterS;
					temp[7] = "result [] object is blank";
					cPP.com_libs.writeToSheet(wsResultfile, temp);
					System.out.println("modelYearId = " + "  " + " - Result [] object is blank. ");

				} else {
					// try {
					// errors = Boolean.toString(result.getBoolean("errors"));
					// } catch (Exception ex) {
					// errors = "null";
					// }
					for (int i = 0; i < size; i++) {

						try {
							modelYearId = result.getJSONObject(i).getString("modelYearId");
						} catch (Exception ex) {
							modelYearId = "null";
						}
						try {
							shotId = Integer.toString(result.getJSONObject(i).getInt("shotId"));
						} catch (Exception ex) {
							shotId = "null";
						}
						try {
							imageName = result.getJSONObject(i).getString("imageName");
						} catch (Exception ex) {
							imageName = "null";
						}
						try {
							colorId = result.getJSONObject(i).getString("colorId");
						} catch (Exception ex) {
							colorId = "null";
						}
						try {
							primaryColorCode = result.getJSONObject(i).getString("primaryColorCode");
						} catch (Exception ex) {
							primaryColorCode = "null";
						}
						try {
							primaryColorDesc = result.getJSONObject(i).getString("primaryColorDesc");
						} catch (Exception ex) {
							primaryColorDesc = "null";
						}
						try {
							genericColor = result.getJSONObject(i).getString("genericColor");
						} catch (Exception ex) {
							genericColor = "null";
						}
						try {
							secondaryColorCode = result.getJSONObject(i).getString("secondaryColorCode");
						} catch (Exception ex) {
							secondaryColorCode = "null";
						}
						try {
							secondaryColorDesc = result.getJSONObject(i).getString("secondaryColorDesc");
						} catch (Exception ex) {
							secondaryColorDesc = "null";
						}
						try {
							color2GenericDesc = result.getJSONObject(i).getString("color2GenericDesc");
						} catch (Exception ex) {
							color2GenericDesc = "null";
						}
						try {
							accentColorCode = result.getJSONObject(i).getString("accentColorCode");
						} catch (Exception ex) {
							accentColorCode = "null";
						}
						try {
							accentColorDesc = result.getJSONObject(i).getString("accentColorDesc");
						} catch (Exception ex) {
							accentColorDesc = "null";
						}
						try {
							color3GenericDesc = result.getJSONObject(i).getString("color3GenericDesc");
						} catch (Exception ex) {
							color3GenericDesc = "null";
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
							rangeId = Integer.toString(result.getJSONObject(i).getInt("rangeId"));
						} catch (Exception ex) {
							rangeId = "null";
						}
						try {
							groupId = Integer.toString(result.getJSONObject(i).getInt("groupId"));
						} catch (Exception ex) {
							groupId = "null";
						}
						try {
							imageView = result.getJSONObject(i).getString("imageView");
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

						jsonValue[8] = modelYearId;
						jsonValue[9] = imageName;
						jsonValue[10] = colorId;
						jsonValue[11] = primaryColorCode;
						jsonValue[12] = primaryColorDesc;
						jsonValue[13] = genericColor;
						jsonValue[14] = secondaryColorCode;
						jsonValue[15] = secondaryColorDesc;
						jsonValue[16] = color2GenericDesc;
						jsonValue[17] = accentColorCode;
						jsonValue[18] = accentColorDesc;
						jsonValue[19] = color3GenericDesc;
						jsonValue[20] = optionCode;
						jsonValue[21] = shotDescription;
						jsonValue[22] = rangeDescription;
						jsonValue[23] = groupDescription;
						jsonValue[24] = shotId;
						jsonValue[25] = rangeId;
						jsonValue[26] = groupId;
						jsonValue[27] = imageView;

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
		getModelImageColorMS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
