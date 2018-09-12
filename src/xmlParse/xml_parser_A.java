package xmlParse;
//This is based on xml_parser_8.java. this works for CPS return.  "Options" - OK. Only 2 top levels..."Options" and "option (include HEARDER and optCode)" - Fixed. 

// Print with one line option.

//Two top levels return - Options and Option. It returns option top level attributes names and their values. 
//Options -> Option  as an example below to show lines: 1,2,3,4

//<Options>
//1. <Option optCode="HEADER" optLevel="02" optOrder="0" realOrder="1" optCluster="29" optTrim="*">...</Option>
//2. <Option optUID="1041" optRevision="750452.0" optCode="ORDER1" optLevel="01" optOrder="199" realOrder="2" optCluster="29" optTrim="A*B*C*D*E*F*" ecc="0054" cwc="6 12">
//	   <optDescription>
//		   <en>Initial Order Date: 03/07/2013</en>
//	      </optDescription>
//	      <optDescriptionSuffix>
//	...
//	...
//   </Option>
//3. <Option optUID="1042" optRevision="750452.0" optCode="START1" optLevel="01" optOrder="200" realOrder="4" optCluster="29" optTrim="A*B*C*D*E*F*" ecc="0054" cwc="6 12">...</Option>
//4. <Option optUID="1043" optRevision="774906.0" optCode="FINAL1" optLevel="01" optOrder="201" realOrder="6" optCluster="29" optTrim="*" ecc="0054" cwc="6 12">...</Option>

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

public class xml_parser_A {
	public void getOptions(Document doc1) {

		// Get input element from user
		System.out.print("Enter element name: ");
		// element = reader.readLine();
		String NodeName = "Options";
		// Returns a NodeList of all the Elements in document order with a given tag name and are contained in the document.
		NodeList Options = doc1.getElementsByTagName(NodeName);
		int OptionsCount = Options.getLength();// 1
		System.out.println("\nHere you go => Total # of Elements: " + Options.getLength());
		System.out.println("\n");

		for (int i = 0; i < OptionsCount; i++) {

			org.w3c.dom.Node nL = Options.item(i);// first key
			// String keyA=nL
			String nodeName = nL.getNodeName();// Options
			String key1 = nL.getNodeValue();// null
			String key2 = nL.getLocalName();// null
			NamedNodeMap node1 = nL.getAttributes();
			NodeList node1_1 = nL.getChildNodes();

			int nodeAttributLength = node1.getLength();// 0
			if (nodeAttributLength > 1) {
				System.out.println("Node Num =   " + OptionsCount + " attribute Num=   " + nodeAttributLength + "\n");
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
			int OptionCount = node1_1.getLength();// 235
			// Option
			for (int l = 0; l < OptionCount; l++) {
				org.w3c.dom.Node node3 = node1_1.item(l);
				String OptionName = node3.getNodeName();// Option
				NodeList node3_3 = node3.getChildNodes();
				int OptionAttributeCount = node3_3.getLength();// 6

				NamedNodeMap OptionAttributeMap = node3.getAttributes();
				int OptionAttributeMapCount = OptionAttributeMap.getLength();// 6
				System.out.println("Option: " + (l + 1) + " of " + OptionCount + ".  Option Attribute size: "
						+ OptionAttributeMapCount);
				for (int m = 0; m < OptionAttributeMapCount; m++) {

					org.w3c.dom.Node OptionAttribute = OptionAttributeMap.item(m);
					String OptionAttributeName = OptionAttribute.getNodeName();// optCluster
					String OptionAttributeValue = OptionAttribute.getTextContent();// 29

					System.out.print("" + "  " + OptionAttributeName + " = " + OptionAttributeValue);

					// System.out.println("\n");

				}
				System.out.println(""); // System.out.println("Continue...\n");
			}

		}

	}

	public static void main(String[] args) throws IOException {
		// String inputFile = "C:\\data\\xml\\Order.xml"; //Tried. This works.
		String inputFile = "http://cps.london.autodata.net/api-ws/vehicle/UW/UW_ETL/USC40CHT279C0";
		String tagName = "";
		String element = "";
		String nodeString;
		try {
			// Reads text from a character-input stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			// The Document interface represents the entire HTML or XML document. Conceptually, it is the root of the document tree, and provides the primary access to the document's data.
			Document doc = factory.newDocumentBuilder().parse(inputFile);

			xml_parser_A CPS = new xml_parser_A();
			CPS.getOptions(doc);
			// while (!element.equalsIgnoreCase("stop")) {}
			System.out.println("Complete!!!\n");
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}
}