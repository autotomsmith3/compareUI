package cig;

//The original code is from package Xml_Parser, xml_parser_A3_CIG_CPS.java;
//This is based on xml_parser_A3.java. this works for CPS return. "MediaList"  - OK. 
//How to write console to a file. Run -> Run Configurations... -> Java Application -> Your Application -> Common tab -> File, Check Append for append mode. 
//Used to save here: H:\My Documents\CIG\QA\BodyFieldAlgorithm\CpsXmlMediaListParse_Console_TestOutput.txt
//This output can be got from console or write console to a file. See above.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class CpsXmlMediaListParse {
	public void getMediaList(Document doc1, String style_id, String bodyField) {

		// Get input element from user
		// System.out.print("Enter element name: ");
		// element = reader.readLine();
		String NodeName = "MediaList";
		// Returns a NodeList of all the Elements in document order with a given tag name and are contained in the document.
		NodeList MediaList = doc1.getElementsByTagName(NodeName);
		int MediaListCount = MediaList.getLength();// 1
		// System.out.println("\nHere you go => Total # of Elements: " + MediaList.getLength());
		// System.out.println("\n");

		for (int i = 0; i < MediaListCount; i++) {

			org.w3c.dom.Node nL = MediaList.item(i);// first key
			// String keyA=nL
			String nodeName = nL.getNodeName();// Options
			String key1 = nL.getNodeValue();// null
			String key2 = nL.getLocalName();// null
			NamedNodeMap node1 = nL.getAttributes();
			NodeList node1_1 = nL.getChildNodes();

			int nodeAttributLength = node1.getLength();// 0
			if (nodeAttributLength > 1) {
				System.out.println("Node Num =   " + MediaListCount + " attribute Num=   " + nodeAttributLength + "\n");
			}
			for (int j = 0; j < nodeAttributLength; j++) {
				org.w3c.dom.Node node2 = node1.item(j);

				String nodeAttributeName = node2.getNodeName();//
				String nodeAttributeValue = node2.getNodeValue();//
				String nodeContent = nL.getTextContent();//

				System.out.println("Node Num =   " + (i + 1) + " attribute Num=   " + (j + 1));
				System.out.println("NodeName=                      " + nodeName);
				System.out.println("NodeAttributeName=             " + nodeAttributeName);
				System.out.println("nodeAttributeValue=            " + nodeAttributeValue);
				System.out.println("nodeContent=                   " + nodeContent);
				System.out.println("\n");
			}
			int MediaCount = node1_1.getLength();// 235
			// Option
			// System.out.println(
			// "category,fileName,groupUID,isActive,isPrimary,mediaID,mediaID,oemTemp,optionCode,paintCode,rangeName,shotUID ");
			for (int l = 0; l < MediaCount; l++) {
				org.w3c.dom.Node node3 = node1_1.item(l);
				String MediaName = node3.getNodeName();// Media
				NodeList node3_3 = node3.getChildNodes();
				int MediaAttributeCount = node3_3.getLength();// 4

				NamedNodeMap MediaAttributeMap = node3.getAttributes();
				int MediaAttributeMapCount = MediaAttributeMap.getLength();// 19

				System.out.print(style_id + ",");
				for (int m = 0; m < MediaAttributeMapCount; m++) {

					org.w3c.dom.Node MediaAttribute = MediaAttributeMap.item(m);
					String MediaAttributeName = MediaAttribute.getNodeName();// category, fileName

					// fileName
					if (MediaAttributeName.equalsIgnoreCase("fileName")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();// ES, USC50FOT113A021001
						System.out.print(MediaAttributeValue + ",");
					}
					// paintCode
					if (MediaAttributeName.equalsIgnoreCase("paintCode")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// isActive
					if (MediaAttributeName.equalsIgnoreCase("isActive")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// mediaID
					if (MediaAttributeName.equalsIgnoreCase("mediaID")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// shotUID
					if (MediaAttributeName.equalsIgnoreCase("shotUID")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue);
					}
					// oemTemp
					if (MediaAttributeName.equalsIgnoreCase("oemTemp")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// optionCode
					if (MediaAttributeName.equalsIgnoreCase("optionCode")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// mediaID
					if (MediaAttributeName.equalsIgnoreCase("mediaID")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// category
					if (MediaAttributeName.equalsIgnoreCase("category")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(bodyField + ",");
						System.out.print(MediaAttributeValue + ",");
					}
					// isPrimary
					if (MediaAttributeName.equalsIgnoreCase("isPrimary")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// rangeName
					if (MediaAttributeName.equalsIgnoreCase("rangeName")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}
					// groupUID
					if (MediaAttributeName.equalsIgnoreCase("groupUID")) {
						String MediaAttributeValue = MediaAttribute.getTextContent();//
						System.out.print(MediaAttributeValue + ",");
					}

				}
				System.out.println("  ");
				// Option child node - optDescription
				NodeList MediaDescription = node3.getChildNodes();
				// NamedNodeMap OptionChild = OptionDescription.getAttributes();
				int MediaChildCount = MediaDescription.getLength();

			}

		}

	}

	public static String getStyleName(Document doc1, String style_id) {

		// Get input element from user
		// System.out.print("Enter element name: ");
		// element = reader.readLine();
		String NodeName = "ExtendedInfo";
		String bodyField = "";
		String styleField = "";
		boolean styleidFlag = false;
		// Returns a NodeList of all the Elements in document order with a given tag name and are contained in the document.
		NodeList ExtendedInfo = doc1.getElementsByTagName(NodeName);
		int DetailsCount = ExtendedInfo.getLength();// 1
		// System.out.println("\nHere you go => Total # of Elements: " + ExtendedInfo.getLength());
		// System.out.println("\n");

		for (int i = 0; i < DetailsCount; i++) {

			org.w3c.dom.Node nL = ExtendedInfo.item(i);// first key
			// String keyA=nL
			String nodeName = nL.getNodeName();// ExtendedInfo
			String key1 = nL.getNodeValue();// null
			String key2 = nL.getLocalName();// null
			NamedNodeMap node1 = nL.getAttributes();
			NodeList node1_1 = nL.getChildNodes();

			int nodeAttributLength = node1_1.getLength();// 1
			if (nodeAttributLength > 1) {
				// System.out.println("Node Num = " + DetailsCount + " attribute Num= " + nodeAttributLength + "\n");
			}
			for (int j = 0; j < nodeAttributLength; j++) {
				org.w3c.dom.Node node2 = node1_1.item(j);

				String nodeAttributeName = node2.getNodeName();// Item
				String nodeAttributeValue = node2.getNodeValue();// null
				String nodeContent = node2.getTextContent();// ""
				NodeList node1_2 = node2.getChildNodes();
				int itemNoteCount = node1_2.getLength();
				for (int k = 0; k < itemNoteCount; k++) {
					org.w3c.dom.Node node3 = node1_2.item(k);
					String node3AttributeName = node3.getNodeName();// Details
					String node3AttributeValue = node3.getNodeValue();// null
					String node3Content = node3.getTextContent();// ""
					NodeList node1_3 = node3.getChildNodes();
					int detailsCount = node1_3.getLength();// 24

					for (int l = 0; l < detailsCount; l++) {
						org.w3c.dom.Node node4 = node1_3.item(l);
						String node4AttributeName = node4.getNodeName();// Detail
						String node4AttributeValue = node4.getNodeValue();// null
						String node4Content = node4.getTextContent();// ""

						NamedNodeMap DetailAttributeMap = node4.getAttributes();
						int DetailAttributeMapCount = DetailAttributeMap.getLength();// 3
						for (int m = 0; m < DetailAttributeMapCount; m++) {

							org.w3c.dom.Node DetailAttribute = DetailAttributeMap.item(m);
							String DetailAttributeName = DetailAttribute.getNodeName();// dataDesc, dataValue
							String DetailAttributeValue = DetailAttribute.getTextContent();// YMM_ID, 29803
							if (DetailAttributeValue.equalsIgnoreCase("STYLE_ID")) {
								org.w3c.dom.Node DetailAttribute1 = DetailAttributeMap.item(m + 1);
								styleField = DetailAttribute1.getTextContent();

								if (styleField.equalsIgnoreCase(style_id)) {

									// System.out.println("styleField = " + styleField);
									styleidFlag = true;
								}

							}
							if (DetailAttributeValue.equalsIgnoreCase("STYLE_NAME") && styleidFlag) {
								org.w3c.dom.Node DetailAttribute1 = DetailAttributeMap.item(m + 1);
								bodyField = DetailAttribute1.getTextContent();

								styleidFlag = false;

							}
						}

					}
				}
			}
		}
		return bodyField;
	}

	public static void main(String[] args) throws IOException {
		// Test data below
		// 1***********************************************
		// 3 Styleids only for testing in Spira
//		String[] styleid = {"387896"} ;//{ "385655" };// { "387896", "374391", "389544" };
//		 //2***********************************************
//		 // USC70FOT11 - 2017 Ford F-150 - All 44 Stylids:
//		 String[] styleid = { "387896", "387897", "387898", "387899", "387900", "387901", "387902", "387903", "387904",
//		 "387905", "387906", "387907", "387908", "387909", "387910", "387911", "387912", "387913", "387914",
//		 "387915", "387916", "387917", "387918", "387919", "387920", "387921", "387922", "387923", "387924",
//		 "387925", "387926", "387927", "387928", "387929", "387930", "387931", "387932", "387933", "387934",
//		 "387935", "387936", "387937", "388260", "388261" };
		// 3***********************************************
		// USC60ACS11 - 2016 Acura MDX - All styleids, total=16
		// String[] styleid = { "374377", "374383", "374378", "374384", "374379", "374385", "374386", "374387", "374380",
		// "374391", "374381", "374392", "374382", "374388", "374389", "374390" };
		// 4***********************************************
		// CAC70CRC131 - 2017 Chrysler 300 All 10 styleids
//		 String[] styleid ={ "389544", "389545","389552","389548","389549","389546","389547","389553","389550","389551"};

		//Multi-tone image CIG-137 - USC40FOT124C0 - 2014 Ford F-250 styleid
		 String[] styleid ={ "380333"};//380333, 361502
	 
		 
		 
		int styleidLength = styleid.length;
		System.out.println(
				"Styleid,bodyfield,category,fileName,groupUID,isActive,isPrimary,mediaID,mediaID,oemTemp,optionCode,paintCode,rangeName,shotUID ");
		for (int i = 0; i < styleidLength; i++) {
			String inputFile = "http://cps.london.autodata.net/api-ws/customvehicle/CMS/IOF_ETL/CDS/" + styleid[i];
//			String inputFile = "http://cps-versioned-for-qa.autodatacorp.org/cps-1.28/customvehicle/CMS/IOF_ETL/CDS/" + styleid[i]; //This comes from Allen. The result is the same as above. January 15, 2018
			
			String tagName = "";
			String element = "";
			String nodeString;
			String body = "";
			try {
				// Reads text from a character-input stream
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				// Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

				// The Document interface represents the entire HTML or XML document. Conceptually, it is the root of the document tree, and provides the primary access to the document's data.
				Document doc = factory.newDocumentBuilder().parse(inputFile);

				CpsXmlMediaListParse CPS = new CpsXmlMediaListParse();

				body = CPS.getStyleName(doc, styleid[i]);
				// System.out.println("Styleid ="+styleid[i]+" BodyField = "+body);

				CPS.getMediaList(doc, styleid[i], body);
				// while (!element.equalsIgnoreCase("stop")) {}
				// System.out.println("Complete!!!\n");
			} catch (

			Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("Complete!!!\n");
	}
}