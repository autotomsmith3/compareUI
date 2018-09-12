package cPP;

//CPP-993 - getColorImages/gvuids
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

public class hasStock360_Phase2 {
	private static int blank = 0;
	private static int noObj = 0;

	public static void hasStock360MS() throws Exception {
		// ******************************************************
		// ******************************************************
		String testDateFolder;
		int vintotal = 2;
		int startToRun = 1;// Default=1;
		int endToRun = 1;// Default =vintotal
		String dateFolder = "20180618_180";
		//// ******************************************************
//		//// *******************180 TCs***********************************
		 String[] SpiraID = { "TC183778", "TC183779", "TC183780", "TC183781", "TC183782", "TC183783", "TC183784",
		 "TC183785", "TC183786", "TC183787", "TC183788", "TC183789", "TC183790", "TC183791", "TC183792",
		 "TC183793", "TC183794", "TC183795", "TC183796", "TC183797", "TC183798", "TC183799", "TC183800",
		 "TC183801", "TC183802", "TC183803", "TC183804", "TC183805", "TC183806", "TC183807", "TC183808",
		 "TC183809", "TC183810", "TC183811", "TC183812", "TC183813", "TC183814", "TC183815", "TC183816",
		 "TC183817", "TC183818", "TC183819", "TC183820", "TC183821", "TC183822", "TC183823", "TC183824",
		 "TC183825", "TC183826", "TC183827", "TC183828", "TC183829", "TC183830", "TC183831", "TC183832",
		 "TC183833", "TC183834", "TC183835", "TC183836", "TC183837", "TC183838", "TC183839", "TC183840",
		 "TC183841", "TC183842", "TC183843", "TC183844", "TC183845", "TC183846", "TC183847", "TC183848",
		 "TC183849", "TC183850", "TC183851", "TC183852", "TC183853", "TC183854", "TC183855", "TC183856",
		 "TC183857", "TC183858", "TC183859", "TC183860", "TC183861", "TC183862", "TC183863", "TC183864",
		 "TC183865", "TC183866", "TC183867", "TC183868", "TC183869", "TC183870", "TC183871", "TC183872",
		 "TC183873", "TC183874", "TC183875", "TC183876", "TC183877", "TC183878", "TC183879", "TC183880",
		 "TC183881", "TC183882", "TC183883", "TC183884", "TC183885", "TC183886", "TC183887", "TC183888",
		 "TC183889", "TC183890", "TC183891", "TC183892", "TC183893", "TC183894", "TC183895", "TC183896",
		 "TC183897", "TC183898", "TC183899", "TC183900", "TC183901", "TC183902", "TC183903", "TC183904",
		 "TC183905", "TC183906", "TC183907", "TC183908", "TC183909", "TC183910", "TC183911", "TC183912",
		 "TC183913", "TC183914", "TC183915", "TC183916", "TC183917", "TC183918", "TC183919", "TC183920",
		 "TC183921", "TC183922", "TC183923", "TC183924", "TC183925", "TC183926", "TC183927", "TC183928",
		 "TC183929", "TC183930", "TC183931", "TC183932", "TC183933", "TC183934", "TC183935", "TC183936",
		 "TC183937", "TC183938", "TC183939", "TC183940", "TC183941", "TC183942", "TC183943", "TC183944",
		 "TC183945", "TC183946", "TC183947", "TC183948", "TC183949", "TC183950", "TC183951", "TC183952",
		 "TC183953", "TC183954", "TC183955", "TC183956", "TC183957" };
//		//// ******************************************************
//		//// *******************72 TCs***********************************
//		String[] SpiraID = { "TC187748", "TC187749", "TC187750", "TC187751", "TC187752", "TC187753", "TC187754",
//				"TC187755", "TC187756", "TC187757", "TC187758", "TC187759", "TC187760", "TC187761", "TC187762",
//				"TC187763", "TC187764", "TC187765", "TC187766", "TC187767", "TC187768", "TC187769", "TC187770",
//				"TC187771", "TC187772", "TC187773", "TC187774", "TC187775", "TC187776", "TC187777", "TC187778",
//				"TC187779", "TC187780", "TC187781", "TC187782", "TC187783", "TC187784", "TC187785", "TC187786",
//				"TC187787", "TC187788", "TC187789", "TC187790", "TC187791", "TC187792", "TC187793", "TC187794",
//				"TC187796", "TC187797", "TC187798", "TC187799", "TC187800", "TC187801", "TC187802", "TC187803",
//				"TC187804", "TC187805", "TC187806", "TC187807", "TC187808", "TC187809", "TC187810", "TC187811",
//				"TC187812", "TC187813", "TC187814", "TC187815", "TC187816", "TC187817", "TC187818", "TC187819",
//				"TC187820" };
//
//		//// ******************************************************
//		//// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k);
			String pre_path = "C:\\1\\Eclipse\\Test Results\\Colorized360 Phase2\\hasStock360\\";
			String getHasStock360SavePathFile = pre_path + dateFolder + "\\getHasStock360.xls";
			String jsonpathfilename = pre_path + dateFolder + "\\hasStock360_" + kStr;// + ".json";
			String[] titleStringgetHasStock360WS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "error",
					"executionTimeMS", "resultObj", "vehiclesObj", "hasImages", "countryCode", "language", "vin",
					"style", "acode:", "token", "chromeymmid", "year", "make", "model", "trim", "colorObj",
					"colorContains", "oemcolorObj", "oemcolorContains", "errors" };
			// QA
			String hasStock360PutServiceURL = "http://lnoc-q1cp-xws1.autodatacorp.org:8080/image/rest/hasStock360";
			// DEV
			// String hasStock360PutServiceURL = "http://lnoc-dvcp-xws1.autodatacorp.org:8080/image/rest/hasStock360";
			// Prod
//			String stock360PutServiceURL = "http://cpp.chromedata.com/image/rest/hasStock360";//Prod
//			String stock360PutServiceURL = "http://cpp-mi.chromedata.com/image/rest/hasStock360";//Prod Flint
//			String stock360PutServiceURL = "http://cpp-or.chromedata.com/image/rest/hasStock360";//Prod Hillsboro
			// ******************************************************
			// ******************************************************
			int dataLength = SpiraID.length;//72;//180;// 72;// 30;// 18;// one vin has 5 tc.
			if (dataLength == 72) {
				testDateFolder = "TestData_72";
			} else if (dataLength == 180) {
				testDateFolder = "TestData_180";
			} else {
				testDateFolder = "";
			}
			// ******************************************************
			// ******************************************************
			String[] HasStock360BodyParameters = new String[dataLength + 1];
			int datasize = HasStock360BodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				// String bodyDatafile = "imageMSBodyData_" + k + "/imageMSBody" + iStr + ".txt"; // # Vin working.
				// C:\\1\\Eclipse\\Test Results\\CompareMS\\HasStock360\\TestData\\imageBodyDate_1\\imageBody1.txt
				String bodyDatafile = pre_path + testDateFolder + "\\hasstock360BodyData_" + k + "\\stock360Body" + iStr
						+ ".txt"; // # group number testing

				HasStock360BodyParameters[i] = getFile(bodyDatafile); // ok
				// HasStock360BodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgetImageWS = com_libs.getSourceCodeJson(HasStock360BodyParameters[i],
						hasStock360PutServiceURL, "");// PUT
				SaveScratch(jsonpathfilename + "_" + (i + 1) + "_" + SpiraID[i] + ".json", jsonTextFrgetImageWS);
				// GET old
				// String jsonTextFrgetImageWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				hasStock360MSDetails(getHasStock360SavePathFile, titleStringgetHasStock360WS, jsonTextFrgetImageWS,
						hasStock360PutServiceURL, HasStock360BodyParameters[i], count);
			}
		}
	}

	public static void hasStock360MSDetails(String wsResultfile, String[] titleString, String text, String URLString,
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
		System.out.println("Started...");
		hasStock360MS();
		System.out.println("Complete!!!");
	}

}
