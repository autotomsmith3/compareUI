package xmlParse;
//This is based on xml_parser_A1.java. this works for CPS return. "Options" - OK. Only 4 top levels...1. "Options" and 2. "option (include HEARDER and optCode)" Plus 3. OptDescription level and 4. optPrices level- Testing for 4 level. looks ok but not completed.

// Print with one line option.

//Two top levels return - Options and Option. It returns option top level attributes names and their values. 
//Options -> Option  as an example below to show lines: 1,2,3,4

//1.<Options>
//2.<Option optCode="HEADER" optLevel="02" optOrder="0" realOrder="1" optCluster="29" optTrim="*">...</Option>
//2.<Option optUID="1041" optRevision="750452.0" optCode="ORDER1" optLevel="01" optOrder="199" realOrder="2" optCluster="29" optTrim="A*B*C*D*E*F*" ecc="0054" cwc="6 12">
//	3. <optDescription>
//		<en>Initial Order Date: 03/07/2013</en>
//		</optDescription>
//	<optDescriptionSuffix>
//	<en/>
//	</optDescriptionSuffix>
//	<fullDescription>
//	<en>Initial Order Date: 03/07/2013</en>
//	</fullDescription>
//	<optNote/>
//	<plainLogic/>
//	3. <optPrices PriceState="CURRENT_MY_QC">
//		4. <Price priceType="MSRP">0.0</Price>
//		<Price priceType="INVOICE">0.0</Price>
//		<Price priceType="NET">0.0</Price>
//		<Price priceType="HOLDBACK">0.0</Price>
//		<Price priceType="MSRP_DISCOUNT">0.0</Price>
//		<Price priceType="INVOICE_DISCOUNT">0.0</Price>
//		<Price priceType="NET_DISCOUNT">0.0</Price>
//		<Price priceType="HOLDBACK_DISCOUNT">0.0</Price>
//		<Price priceType="TWT">0.0</Price>
//		<Price priceType="ADVERTISING">0.0</Price>
//		<Price priceType="FINANCE">0.0</Price>
//	</optPrices>
//	<Flags>
//		<Flag>REGULAR_OPT</Flag>
//		<Flag>NO_CHARGE</Flag>
//		<Flag>FLEET_OPT</Flag>
//		<Flag>INTERNAL_OPT</Flag>
//		<Flag>ORDER_DATE</Flag>
//	</Flags>
//	...
//	...
//   </Option>
//2. <Option optUID="1042" optRevision="750452.0" optCode="START1" optLevel="01" optOrder="200" realOrder="4" optCluster="29" optTrim="A*B*C*D*E*F*" ecc="0054" cwc="6 12">...</Option>
//2. <Option optUID="1043" optRevision="774906.0" optCode="FINAL1" optLevel="01" optOrder="201" realOrder="6" optCluster="29" optTrim="*" ecc="0054" cwc="6 12">...</Option>

//<Options>
//<Option optCode="HEADER" optLevel="02" optOrder="0" realOrder="1" optCluster="29" optTrim="*">...</Option>
//<Option optUID="1041" optRevision="750452.0" optCode="ORDER1" optLevel="01" optOrder="199" realOrder="2" optCluster="29" optTrim="A*B*C*D*E*F*" ecc="0054" cwc="6 12">
//<optDescription>
//<en>Initial Order Date: 03/07/2013</en>
//</optDescription>
//<optDescriptionSuffix>
//<en/>
//</optDescriptionSuffix>
//<fullDescription>
//<en>Initial Order Date: 03/07/2013</en>
//</fullDescription>
//<optNote/>
//<plainLogic/>
//<optPrices PriceState="CURRENT_MY_QC">
//<Price priceType="MSRP">0.0</Price>
//<Price priceType="INVOICE">0.0</Price>
//<Price priceType="NET">0.0</Price>
//<Price priceType="HOLDBACK">0.0</Price>
//<Price priceType="MSRP_DISCOUNT">0.0</Price>
//<Price priceType="INVOICE_DISCOUNT">0.0</Price>
//<Price priceType="NET_DISCOUNT">0.0</Price>
//<Price priceType="HOLDBACK_DISCOUNT">0.0</Price>
//<Price priceType="TWT">0.0</Price>
//<Price priceType="ADVERTISING">0.0</Price>
//<Price priceType="FINANCE">0.0</Price>
//</optPrices>
//<Flags>
//<Flag>REGULAR_OPT</Flag>
//<Flag>NO_CHARGE</Flag>
//<Flag>FLEET_OPT</Flag>
//<Flag>INTERNAL_OPT</Flag>
//<Flag>ORDER_DATE</Flag>
//</Flags>

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

public class xml_parser_A2 {
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
				System.out.println("- Option: " + (l + 1) + " of " + OptionCount + ".  Option Attribute size: "
						+ OptionAttributeMapCount);
				for (int m = 0; m < OptionAttributeMapCount; m++) {

					org.w3c.dom.Node OptionAttribute = OptionAttributeMap.item(m);
					String OptionAttributeName = OptionAttribute.getNodeName();// optCluster
					String OptionAttributeValue = OptionAttribute.getTextContent();// 29
					if (m == 0) {
						System.out.print("-");
					}
					System.out.print("" + "  " + OptionAttributeName + " = " + OptionAttributeValue);

					// System.out.println("\n");

				}
				System.out.println("  ");
				// Option child node - optDescription
				NodeList OptionDescription = node3.getChildNodes();
				// NamedNodeMap OptionChild = OptionDescription.getAttributes();
				int OptionChildCount = OptionDescription.getLength();
				for (int n = 0; n < OptionChildCount; n++) {
					org.w3c.dom.Node OptionDescriptions = OptionDescription.item(n);
					String OptionDes = OptionDescriptions.getNodeName();
					String OptionDesValue = OptionDescriptions.getTextContent();//
					if (n == 0) {
						System.out.print("  --");
					}
					System.out.print("" + "  " + OptionDes + " = " + OptionDesValue);

					// optPrices child level - Price
					NodeList optPrices = OptionDescriptions.getChildNodes();
					int optPricesCount = optPrices.getLength();
					for (int o = 0; o < optPricesCount; o++) {
						org.w3c.dom.Node optPrice = optPrices.item(o);
						String optPriceDes = optPrice.getNodeName();
						String optPriceValue = optPrice.getTextContent();
						// String optPriceType = optPrice.getLocalName();
						boolean optPriceTypeName = optPrice.hasChildNodes();
						System.out.println("    ---" + "  " + optPriceDes + " = " + optPriceValue);
						// Price child level
						if (optPriceTypeName) {
							// System.out.println(" ---");
							NamedNodeMap optPriceChildAttributes = optPrice.getAttributes();
							int optPriceChildListCount = optPriceChildAttributes.getLength();

							for (int p = 0; p < optPriceChildListCount; p++) {
								// System.out.println(" ----");
								org.w3c.dom.Node optPriceChild = optPriceChildAttributes.item(p);
								String optPriceChildName = optPriceChild.getNodeName();
								String optPriceChildValue = optPriceChild.getNodeValue();
								String optPriceChildTextContent = optPriceChild.getTextContent();
								System.out.println("          -----" + "  " + optPriceChildName + " = "
										+ optPriceChildValue + "  " + optPriceValue + "  " + optPriceChildTextContent);
							}
						}
						// System.out.println(" ---" + " " + optPriceDes + " = " + optPriceValue);
					}
					// System.out.println(" ");
				}
				System.out.println("  ");

				System.out.println("    \n"); // System.out.println("Continue...\n");
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

			xml_parser_A2 CPS = new xml_parser_A2();
			CPS.getOptions(doc);
			// while (!element.equalsIgnoreCase("stop")) {}
			System.out.println("Complete!!!\n");
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}
}