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

public class trimMS {
	private static int blank = 0;
	private static int noObj = 0;

	public static void getTrimMS() throws Exception {
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
		String dateFolder = "20170418";
		String [] SpiraID={"TC17xxx1","TC17xxx2","TC17xxx3"};

		// ******************************************************
		// ******************************************************
		for (int k = startToRun; k <= endToRun; k++) {// vintotal; k++) {
			int count = 0;
			String kStr = Integer.toString(k); /// C:\1\Eclipse\Test Results\CompareMS\TrimMS\20170401
			String getTrimMSSavePathFile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\TrimMS\\" + dateFolder + "\\trim_"
					+ kStr + ".xls";
			String jsonpathfilename = "C:\\1\\Eclipse\\Test Results\\CompareMS\\TrimMS\\" + dateFolder + "\\trim_"
					+ kStr;// + ".json";
			
			String[] titleStringgettrimWS = { "S/N", "URL_parameter", "RequestBody", "serverTime", "serverTimeString",
					"serverName", "executionTimeMS", "resultObj", "gvuid", "countryCode", "year", "divisionId",
					"division", "modelYearId", "model", "trimId", "trim", "variation", "effectiveDate", "pricingObj",
					"destinationCharge", "msrp", "invoice", "dealerNet", "doubleNet", "tripleNet",
					"initialPriceEffectiveDate1", "statusObj", "initialPriceEffectiveDate2", "estimatedBasePrice",
					"estimatedBaseContent", "estimatedOptionPricing", "estimatedOptionContent", "estimatedStdContent",
					"estimatedFleetOptionPricing", "estimatedFleetOptionContent", "estimatedPaintOptionPricing",
					"estimatedPaintOptionContent", "estimatedDIOPricing", "estimatedDIOContent", "estimatedPIOPricing",
					"estimatedPIOContent", "estimatedPPOPricing", "estimatedPPOContent", "attributesObj1", "code1",
					"type", "description", "attributesObj2", "id", "code2", "valuesObj", "valueId", "value" };
			//// // DEV PUT
			// String imagePutServiceURL = "http://lnoc-dvcp-xmw1:8080/trim-service/trim/gvuids";

			// // QA
			String trimPutServiceURL = "http://lnoc-q1cp-xmw1:8080/model-walk-service/vehicles";

			// int dataLength = 10;//one vin has 10 tcs.// colorized360(stock360) used before. OK
			// ******************************************************
			// ******************************************************
			int dataLength = 2;// one vin has 5 tc.
			// ******************************************************
			// ******************************************************
			String[] TrimMSBodyParameters = new String[dataLength + 1];
			int datasize = TrimMSBodyParameters.length;
			for (int i = 0; i < datasize - 1; i++) {
				count++;
				String iStr = Integer.toString(i + 1);

				// String bodyDatafile = "TrimMSBodyData_" + k + "/TrimMSBody" + iStr + ".txt"; // # Vin working.
				// C:\\1\\Eclipse\\Test Results\\CompareMS\\TrimMS\\TestData\\imageBodyDate_1\\imageBody1.txt
				String bodyDatafile = "C:\\1\\Eclipse\\Test Results\\CompareMS\\TrimMS\\TestData\\trimBodyData_" + k
						+ "\\trimBody" + iStr + ".txt"; // # group number testing

				TrimMSBodyParameters[i] = getFile(bodyDatafile); // ok
				// TrimMSBodyParameters[i] = LoadFileToString(bodyDatafile); //failed
				// String getSourceCode(String prefix, String url)

				// PUT New MS Image endpoint
				String jsonTextFrgettrimWS = com_libs.getSourceCodeJson(TrimMSBodyParameters[i], trimPutServiceURL, "");// PUT
				SaveScratch(jsonpathfilename+"_"+ SpiraID[i]+"_"+(i+1)+".json",jsonTextFrgettrimWS);
				// GET old
				// String jsonTextFrgettrimWS = com_libs.getSourceCode("", imagePutServiceURL);// get OK
				getTrimMSDetails(getTrimMSSavePathFile, titleStringgettrimWS, jsonTextFrgettrimWS, trimPutServiceURL,
						TrimMSBodyParameters[i], count);
			}
		}
	}

	public static void getTrimMSDetails(String wsResultfile, String[] titleString, String text, String URLString,
			String parameterS, int countNum) throws IOException {
		cPP.com_libs.writeTitle(wsResultfile, titleString);
		String serverTime = "";
		String serverTimeString = "";
		String serverName = "";
		String executionTimeMS = "";
		String resultObj = "";
		String gvuid = "";
		String countryCode = "";
		String year = "";
		String divisionId = "";
		String division = "";
		String modelYearId = "";
		String model = "";
		String trimId = "";
		String trim = "";
		String variation = "";
		String effectiveDate = "";
		String pricingObj = "";
		String destinationCharge = "";
		String msrp = "";
		String invoice = "";
		String dealerNet = "";
		String doubleNet = "";
		String tripleNet = "";
		String initialPriceEffectiveDate1 = "";
		String statusObj = "";
		String initialPriceEffectiveDate2 = "";
		String estimatedBasePrice = "";
		String estimatedBaseContent = "";
		String estimatedOptionPricing = "";
		String estimatedOptionContent = "";
		String estimatedStdContent = "";
		String estimatedFleetOptionPricing = "";
		String estimatedFleetOptionContent = "";
		String estimatedPaintOptionPricing = "";
		String estimatedPaintOptionContent = "";
		String estimatedDIOPricing = "";
		String estimatedDIOContent = "";
		String estimatedPIOPricing = "";
		String estimatedPIOContent = "";
		String estimatedPPOPricing = "";
		String estimatedPPOContent = "";
		String attributesObj1 = "";
		String code1 = "";
		String type = "";
		String description = "";
		String attributesObj2 = "";
		String id = "";
		String code2 = "";
		String valuesObj = "";
		String valueId = "";
		String value = "";

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
					for (int i = 0; i < size; i++) {
						try {
							gvuid = result.getJSONObject(i).getString("gvuid");
						} catch (Exception ex) {
							gvuid = "null";
						}
						try {
							countryCode = result.getJSONObject(i).getString("countryCode");
						} catch (Exception ex) {
							countryCode = "null";
						}
						try {
							year = result.getJSONObject(i).getString("year");
						} catch (Exception ex) {
							year = "null";
						}
						try {
							divisionId = result.getJSONObject(i).getString("divisionId");
						} catch (Exception ex) {
							divisionId = "null";
						}
						try {
							division = result.getJSONObject(i).getString("division");
						} catch (Exception ex) {
							division = "null";
						}
						try {
							modelYearId = result.getJSONObject(i).getString("modelYearId");
						} catch (Exception ex) {
							modelYearId = "null";
						}
						try {
							model = result.getJSONObject(i).getString("model");
						} catch (Exception ex) {
							model = "null";
						}
						try {
							trimId = result.getJSONObject(i).getString("trimId");
						} catch (Exception ex) {
							trimId = "null";
						}
						try {
							trim = result.getJSONObject(i).getString("trim");
						} catch (Exception ex) {
							trim = "null";
						}
						try {
							variation = result.getJSONObject(i).getString("variation");
						} catch (Exception ex) {
							variation = "null";
						}
						try {
							effectiveDate = Long.toString(result.getJSONObject(i).getLong("effectiveDate"));
						} catch (Exception ex) {
							effectiveDate = "null";
						}

						try {
							initialPriceEffectiveDate1 = Boolean
									.toString(result.getJSONObject(i).getBoolean("initialPriceEffectiveDate"));
						} catch (Exception ex) {
							initialPriceEffectiveDate1 = "null";
						}

						// ***********************pricing**************************************
						JSONObject pricingObject = result.getJSONObject(i).getJSONObject("pricing");
						int pricingObjSize = pricingObject.length();
						pricingObj = Integer.toString(pricingObjSize);
						if (pricingObjSize == 0) {
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
							jsonValue[9] = countryCode;
							jsonValue[10] = year;
							jsonValue[11] = divisionId;
							jsonValue[12] = division;
							jsonValue[13] = modelYearId;
							jsonValue[14] = model;
							jsonValue[15] = trimId;
							jsonValue[16] = trim;
							jsonValue[17] = variation;
							jsonValue[18] = effectiveDate;
							jsonValue[19] = pricingObj;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
						} else {
							try {
								destinationCharge = Integer.toString(pricingObject.getInt("destinationCharge"));
							} catch (Exception ex) {
								destinationCharge = "null";
							}
							try {
								msrp = Integer.toString(pricingObject.getInt("msrp"));
							} catch (Exception ex) {
								msrp = "null";
							}
							try {
								invoice = Integer.toString(pricingObject.getInt("invoice"));
							} catch (Exception ex) {
								invoice = "null";
							}
							try {
								dealerNet = Integer.toString(pricingObject.getInt("dealerNet"));
							} catch (Exception ex) {
								dealerNet = "null";
							}
							try {
								doubleNet = Integer.toString(pricingObject.getInt("doubleNet"));
							} catch (Exception ex) {
								doubleNet = "null";
							}
							try {
								tripleNet = Integer.toString(pricingObject.getInt("tripleNet"));
							} catch (Exception ex) {
								tripleNet = "null";
							}
						}
						// ***********************End of pricing**************************************

						// ***********************Status**************************************
						JSONObject statusObject = result.getJSONObject(i).getJSONObject("status");
						int statusObjectSize = statusObject.length();
						statusObj = Integer.toString(statusObjectSize);
						if (statusObjectSize == 0) {
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
							jsonValue[9] = countryCode;
							jsonValue[10] = year;
							jsonValue[11] = divisionId;
							jsonValue[12] = division;
							jsonValue[13] = modelYearId;
							jsonValue[14] = model;
							jsonValue[15] = trimId;
							jsonValue[16] = trim;
							jsonValue[17] = variation;
							jsonValue[18] = effectiveDate;
							jsonValue[19] = pricingObj;
							jsonValue[20] = destinationCharge;
							jsonValue[21] = msrp;
							jsonValue[22] = invoice;
							jsonValue[23] = dealerNet;
							jsonValue[24] = doubleNet;
							jsonValue[25] = tripleNet;
							jsonValue[26] = initialPriceEffectiveDate1;
							jsonValue[27] = statusObj;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);

						} else {
							try {
								initialPriceEffectiveDate2 = Integer
										.toString(statusObject.getInt("initialPriceEffectiveDate"));
							} catch (Exception ex) {
								initialPriceEffectiveDate2 = "null";
							}
							try {
								estimatedBasePrice = Double.toString(statusObject.getDouble("estimatedBasePrice"));
							} catch (Exception ex) {
								estimatedBasePrice = "null";
							}
							try {
								estimatedBaseContent = statusObject.getString("estimatedBaseContent");
							} catch (Exception ex) {
								estimatedBaseContent = "null";
							}
							try {
								estimatedOptionPricing = statusObject.getString("estimatedOptionPricing");
							} catch (Exception ex) {
								estimatedOptionPricing = "null";
							}
							try {
								estimatedOptionContent = statusObject.getString("estimatedOptionContent");
							} catch (Exception ex) {
								estimatedOptionContent = "null";
							}
							try {
								estimatedStdContent = statusObject.getString("estimatedStdContent");
							} catch (Exception ex) {
								estimatedStdContent = "null";
							}
							try {
								estimatedFleetOptionPricing = statusObject.getString("estimatedFleetOptionPricing");
							} catch (Exception ex) {
								estimatedFleetOptionPricing = "null";
							}
							try {
								estimatedFleetOptionContent = statusObject.getString("estimatedFleetOptionContent");
							} catch (Exception ex) {
								estimatedFleetOptionContent = "null";
							}
							try {
								estimatedPaintOptionPricing = statusObject.getString("estimatedPaintOptionPricing");
							} catch (Exception ex) {
								estimatedPaintOptionPricing = "null";
							}
							try {
								estimatedPaintOptionContent = statusObject.getString("estimatedPaintOptionContent");
							} catch (Exception ex) {
								estimatedPaintOptionContent = "null";
							}
							try {
								estimatedDIOPricing = statusObject.getString("estimatedDIOPricing");
							} catch (Exception ex) {
								estimatedDIOPricing = "null";
							}
							try {
								estimatedDIOContent = statusObject.getString("estimatedDIOContent");
							} catch (Exception ex) {
								estimatedDIOContent = "null";
							}
							try {
								estimatedPIOPricing = statusObject.getString("estimatedPIOPricing");
							} catch (Exception ex) {
								estimatedPIOPricing = "null";
							}
							try {
								estimatedPIOContent = statusObject.getString("estimatedPIOContent");
							} catch (Exception ex) {
								estimatedPIOContent = "null";
							}
							try {
								estimatedPPOPricing = statusObject.getString("estimatedPPOPricing");
							} catch (Exception ex) {
								estimatedPPOPricing = "null";
							}
							try {
								estimatedPPOContent = statusObject.getString("estimatedPPOContent");
							} catch (Exception ex) {
								estimatedPPOContent = "null";
							}
						}

						// **********************End of Status******************************

						// ***********************attributes1**************************************
						JSONArray attributesObject1 = result.getJSONObject(i).getJSONArray("attributes");
						int attributesObject1Size = attributesObject1.length();
						attributesObj1 = Integer.toString(attributesObject1Size);
						if (attributesObject1Size == 0) {
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
							jsonValue[9] = countryCode;
							jsonValue[10] = year;
							jsonValue[11] = divisionId;
							jsonValue[12] = division;
							jsonValue[13] = modelYearId;
							jsonValue[14] = model;
							jsonValue[15] = trimId;
							jsonValue[16] = trim;
							jsonValue[17] = variation;
							jsonValue[18] = effectiveDate;
							jsonValue[19] = pricingObj;
							jsonValue[20] = destinationCharge;
							jsonValue[21] = msrp;
							jsonValue[22] = invoice;
							jsonValue[23] = dealerNet;
							jsonValue[24] = doubleNet;
							jsonValue[25] = tripleNet;
							jsonValue[26] = initialPriceEffectiveDate1;
							jsonValue[27] = statusObj;
							jsonValue[28] = initialPriceEffectiveDate2;
							jsonValue[29] = estimatedBasePrice;
							jsonValue[30] = estimatedBaseContent;
							jsonValue[31] = estimatedOptionPricing;
							jsonValue[32] = estimatedOptionContent;
							jsonValue[33] = estimatedStdContent;
							jsonValue[34] = estimatedFleetOptionPricing;
							jsonValue[35] = estimatedFleetOptionContent;
							jsonValue[36] = estimatedPaintOptionPricing;
							jsonValue[37] = estimatedPaintOptionContent;
							jsonValue[38] = estimatedDIOPricing;
							jsonValue[39] = estimatedDIOContent;
							jsonValue[40] = estimatedPIOPricing;
							jsonValue[41] = estimatedPIOContent;
							jsonValue[42] = estimatedPPOPricing;
							jsonValue[43] = estimatedPPOContent;
							jsonValue[44] = attributesObj1;
							cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
						} else {
							for (int j = 0; j < attributesObject1Size; j++) {

								try {
									code1 = attributesObject1.getJSONObject(j).getString("code");
								} catch (Exception ex) {
									code1 = "null";
								}
								try {
									type = attributesObject1.getJSONObject(j).getString("type");
								} catch (Exception ex) {
									type = "null";
								}
								try {
									description = attributesObject1.getJSONObject(j).getString("description");
								} catch (Exception ex) {
									description = "null";
								}

								// ***********************attributes2**************************************
								JSONArray attributesObject2 = attributesObject1.getJSONObject(j)
										.getJSONArray("attributes");
								int attributesObject2Size = attributesObject2.length();
								attributesObj2 = Integer.toString(attributesObject2Size);
								if (attributesObject2Size == 0) {
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
									jsonValue[9] = countryCode;
									jsonValue[10] = year;
									jsonValue[11] = divisionId;
									jsonValue[12] = division;
									jsonValue[13] = modelYearId;
									jsonValue[14] = model;
									jsonValue[15] = trimId;
									jsonValue[16] = trim;
									jsonValue[17] = variation;
									jsonValue[18] = effectiveDate;
									jsonValue[19] = pricingObj;
									jsonValue[20] = destinationCharge;
									jsonValue[21] = msrp;
									jsonValue[22] = invoice;
									jsonValue[23] = dealerNet;
									jsonValue[24] = doubleNet;
									jsonValue[25] = tripleNet;
									jsonValue[26] = initialPriceEffectiveDate1;
									jsonValue[27] = statusObj;
									jsonValue[28] = initialPriceEffectiveDate2;
									jsonValue[29] = estimatedBasePrice;
									jsonValue[30] = estimatedBaseContent;
									jsonValue[31] = estimatedOptionPricing;
									jsonValue[32] = estimatedOptionContent;
									jsonValue[33] = estimatedStdContent;
									jsonValue[34] = estimatedFleetOptionPricing;
									jsonValue[35] = estimatedFleetOptionContent;
									jsonValue[36] = estimatedPaintOptionPricing;
									jsonValue[37] = estimatedPaintOptionContent;
									jsonValue[38] = estimatedDIOPricing;
									jsonValue[39] = estimatedDIOContent;
									jsonValue[40] = estimatedPIOPricing;
									jsonValue[41] = estimatedPIOContent;
									jsonValue[42] = estimatedPPOPricing;
									jsonValue[43] = estimatedPPOContent;
									jsonValue[44] = attributesObj1;
									jsonValue[45] = code1;
									jsonValue[46] = type;
									jsonValue[47] = description;
									jsonValue[48] = attributesObj2;
//									jsonValue[49] = code2;
//									jsonValue[50] = attributesObj2;
									cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
								} else {
									for (int k = 0; k < attributesObject2Size; k++) {
										try {
											id = Integer.toString(attributesObject2.getJSONObject(k).getInt("id"));
										} catch (Exception ex) {
											id = "null";
										}
										try {
											code2 = attributesObject2.getJSONObject(k).getString("code");
										} catch (Exception ex) {
											code2 = "null";
										}
										JSONArray valuesObject = attributesObject2.getJSONObject(k)
												.getJSONArray("values");
										int valuesObjectSize = valuesObject.length();
										valuesObj = Integer.toString(valuesObjectSize);
										if (valuesObjectSize == 0) {
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
											jsonValue[9] = countryCode;
											jsonValue[10] = year;
											jsonValue[11] = divisionId;
											jsonValue[12] = division;
											jsonValue[13] = modelYearId;
											jsonValue[14] = model;
											jsonValue[15] = trimId;
											jsonValue[16] = trim;
											jsonValue[17] = variation;
											jsonValue[18] = effectiveDate;
											jsonValue[19] = pricingObj;
											jsonValue[20] = destinationCharge;
											jsonValue[21] = msrp;
											jsonValue[22] = invoice;
											jsonValue[23] = dealerNet;
											jsonValue[24] = doubleNet;
											jsonValue[25] = tripleNet;
											jsonValue[26] = initialPriceEffectiveDate1;
											jsonValue[27] = statusObj;
											jsonValue[28] = initialPriceEffectiveDate2;
											jsonValue[29] = estimatedBasePrice;
											jsonValue[30] = estimatedBaseContent;
											jsonValue[31] = estimatedOptionPricing;
											jsonValue[32] = estimatedOptionContent;
											jsonValue[33] = estimatedStdContent;
											jsonValue[34] = estimatedFleetOptionPricing;
											jsonValue[35] = estimatedFleetOptionContent;
											jsonValue[36] = estimatedPaintOptionPricing;
											jsonValue[37] = estimatedPaintOptionContent;
											jsonValue[38] = estimatedDIOPricing;
											jsonValue[39] = estimatedDIOContent;
											jsonValue[40] = estimatedPIOPricing;
											jsonValue[41] = estimatedPIOContent;
											jsonValue[42] = estimatedPPOPricing;
											jsonValue[43] = estimatedPPOContent;
											jsonValue[44] = attributesObj1;
											jsonValue[45] = code1;
											jsonValue[46] = type;
											jsonValue[47] = description;
											jsonValue[48] = attributesObj2;
											jsonValue[49] = id;
											jsonValue[50] = code2;
											jsonValue[51] = valuesObj;
											cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
										} else {
											for (int l = 0; l < valuesObjectSize; l++) {
												try {
													valueId = Long
															.toString(valuesObject.getJSONObject(l).getLong("valueId"));
												} catch (Exception ex) {
													valueId = "null";
												}
												try {
													value = valuesObject.getJSONObject(l).getString("value");
												} catch (Exception ex) {
													value = "null";
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
												jsonValue[9] = countryCode;
												jsonValue[10] = year;
												jsonValue[11] = divisionId;
												jsonValue[12] = division;
												jsonValue[13] = modelYearId;
												jsonValue[14] = model;
												jsonValue[15] = trimId;
												jsonValue[16] = trim;
												jsonValue[17] = variation;
												jsonValue[18] = effectiveDate;
												jsonValue[19] = pricingObj;
												jsonValue[20] = destinationCharge;
												jsonValue[21] = msrp;
												jsonValue[22] = invoice;
												jsonValue[23] = dealerNet;
												jsonValue[24] = doubleNet;
												jsonValue[25] = tripleNet;
												jsonValue[26] = initialPriceEffectiveDate1;
												jsonValue[27] = statusObj;
												jsonValue[28] = initialPriceEffectiveDate2;
												jsonValue[29] = estimatedBasePrice;
												jsonValue[30] = estimatedBaseContent;
												jsonValue[31] = estimatedOptionPricing;
												jsonValue[32] = estimatedOptionContent;
												jsonValue[33] = estimatedStdContent;
												jsonValue[34] = estimatedFleetOptionPricing;
												jsonValue[35] = estimatedFleetOptionContent;
												jsonValue[36] = estimatedPaintOptionPricing;
												jsonValue[37] = estimatedPaintOptionContent;
												jsonValue[38] = estimatedDIOPricing;
												jsonValue[39] = estimatedDIOContent;
												jsonValue[40] = estimatedPIOPricing;
												jsonValue[41] = estimatedPIOContent;
												jsonValue[42] = estimatedPPOPricing;
												jsonValue[43] = estimatedPPOContent;
												jsonValue[44] = attributesObj1;
												jsonValue[45] = code1;
												jsonValue[46] = type;
												jsonValue[47] = description;
												jsonValue[48] = attributesObj2;
												jsonValue[49] = id;
												jsonValue[50] = code2;
												jsonValue[51] = valuesObj;
												jsonValue[52] = valueId;
												jsonValue[53] = value;
												cPP.com_libs.writeToSheet(wsResultfile, jsonValue);
											}
										}
									}
								}
							}
						}
						// **********************End of attributes******************************
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
//				BufferedWriter out2 = new BufferedWriter(new FileWriter(dataDir+ "Acodes.txt", true)); //original OK
				BufferedWriter out2 = new BufferedWriter(new FileWriter(pathfilename, true));
				// out2.write("("+i+"): "+Acode+": ");
//				out2.write(i + ". " + Acode + ": "); //Original OK
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
		getTrimMS();
		// jSonObjec_CPP_BuildDataExtractOrchestrationWS();
		// // ******************************************************End of curly brace - object*********************************************************

		// //// ******************************************************start with [ characters (square brackets - array*********************************************************
		//
		// jSonArry_UnityInventory(); //On Tuesday Auguest 30, 2016: API returns { "message": "An error has occurred." } from https://data.dealervideos.com/v1.0/vehicles/2FHFIYI
		// // //******************************************************End of start with [ characters (square brackets - array*********************************************************

		System.out.println("Complete!!!");
	}

}
