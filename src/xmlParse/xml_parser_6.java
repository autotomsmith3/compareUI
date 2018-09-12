package xmlParse;
//This is based on xml_parser_2.java. this works for CPS return.  "GeneralInfo" - OK..

//Three levels return. After enter node name, it returns attribute names and their values. "GeneralInfo" as an example.

//<GeneralInfo carCode="CC15543" mfgCode="CC15543" country="US" year="2014" manufacCode="GE" typeCode="T" divisionCode="CH" model="27" varType="9" doors="4" bodySize="02" styleCode="03" gvwr="6900.0" drw="false" effectiveDate="2014-07-01T00:00:00.000" mdlIntro="1998-03-04T00:00:00.000" vehIntro="2013-03-04T00:00:00.000" varCodes="BLVW" initialRevCode="731400.0" initialEffectiveDate="2013-04-09T00:00:00.000" wheelBaseInches="143.5" trimListCode="2646" nameplateCode="305">
// <varText>
//	 <en>4x2 Crew Cab 5.75' box 143.5" WB</en>
// </varText>
// <newVarText/>
// <manufacDesc>
//	 <en>General Motors</en>
// </manufacDesc>
// <typeDesc>
//	 <en>Truck</en>
// </typeDesc>
// <divisionDesc>
//	 <en>Chevrolet</en>
// </divisionDesc>
//<namePlate>
//...
//...
//...
//<initialPrice PriceState="UNASSIGNED">
//<Price priceType="MSRP">34265.0</Price>
//<Price priceType="INVOICE">31866.45</Price>
//<Price priceType="NET">30838.5</Price>

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

public class xml_parser_6 {

	public static void main(String[] args) throws IOException {
		// String inputFile = "C:\\data\\xml\\CPS01.xml";
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
			while (!element.equalsIgnoreCase("stop")) {

				// Get input element from user
				System.out.print("Enter element name: ");
				element = reader.readLine();
				// Returns a NodeList of all the Elements in document order with a given tag name and are contained in the document.
				NodeList nodes = doc.getElementsByTagName(element);
				int nodeCount = nodes.getLength();
				System.out.println("\nHere you go => Total # of Elements: " + nodes.getLength());
				System.out.println("\n");

				for (int i = 0; i < nodeCount; i++) {

					org.w3c.dom.Node nL = nodes.item(i);// first key
					// String keyA=nL
					String nodeName = nL.getNodeName();// GeneralInfo
					String key1 = nL.getNodeValue();// null
					String key2 = nL.getLocalName();// null
					NamedNodeMap node1 = nL.getAttributes();
					NodeList node1_1 = nL.getChildNodes();

					int nodeAttributLength = node1.getLength();// 23
					if (nodeAttributLength > 1) {
						System.out.println(
								"Node Num =   " + nodeCount + " attribute Num=   " + nodeAttributLength + "\n");
					}
					for (int j = 0; j < nodeAttributLength; j++) {
						org.w3c.dom.Node node2 = node1.item(j);

						String nodeAttributeName = node2.getNodeName();// bodySize
						String nodeAttributeValue = node2.getNodeValue();// 02
						String nodeContent = nL.getTextContent();// 4x2 Crew Cab 5.75' ......

						System.out.println("Node Num =   " + (i + 1) + " attribute Num=   " + (j + 1));
						System.out.println("NodeName=                      " + nodeName);
						System.out.println("NodeAttributeName=             " + nodeAttributeName);
						System.out.println("nodeAttributeValue=            " + nodeAttributeValue);
						System.out.println("nodeContent=                   " + nodeContent);
						System.out.println("\n");
					}
					int childNodeSize = node1_1.getLength();// 21. It's correct.
					// varText
					for (int l = 0; l < childNodeSize; l++) {
						org.w3c.dom.Node node3 = node1_1.item(l);
						String nodeNmae3 = node3.getNodeName();// varText
						NodeList node3_3 = node3.getChildNodes();
						int nodeChild3Size = node3_3.getLength();
						if (nodeChild3Size == 0) {

							System.out.println("NodeName= " + nodeNmae3 + "    SubNode Num = " + (l + 1)
									+ " attribute Num= " + (nodeChild3Size + 1));
							System.out.println("SubnodeName4=                      ");
							System.out.println("SubnodeValue4=             ");
							System.out.println("\n");
						}
						for (int m = 0; m < nodeChild3Size; m++) {
							org.w3c.dom.Node node4 = node3_3.item(m);
							String nodeName4 = node4.getNodeName();// en
							String nodeValue4 = node4.getTextContent();// 4x2 Crew Cab 5.75' box 143.5" WB

							String nodeAttributeName4 = node4.getLocalName();

							NamedNodeMap node5 = node4.getAttributes();
							int node5Size = node5.getLength();
							String node5AttributeName = "";
							String node5AttributeValue = "";
							String node5AttributeContent = "";
							for (int n = 0; n < node5Size; n++) {
								org.w3c.dom.Node node5Attribute = node5.item(n);
								node5AttributeName = node5Attribute.getNodeName();// priceType
								node5AttributeValue = node5Attribute.getNodeValue();// MSRP
								node5AttributeContent = node5Attribute.getTextContent();// MSRP
								System.out.println("\n");
							}

							System.out.println("NodeName= " + nodeNmae3 + "   nodeChild3SizeNum= " + (m + 1)
									+ "   nodeChild3Size = " + nodeChild3Size + "    SubNode Num = " + (l + 1)
									+ " attribute Num= " + (m + 1));

							System.out.println("SubnodeName4=                   " + nodeName4);

							System.out.println("node5AttributeName=             " + node5AttributeName);
							System.out.println("node5AttributeValue=            " + node5AttributeValue);
							System.out.println("SubnodeValue4=                  " + nodeValue4);
							System.out.println("\n");
						}
						System.out.println("Contine...");
					}

				}
			}
			System.out.println("Complete!!!\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}