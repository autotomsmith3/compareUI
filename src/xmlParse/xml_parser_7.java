package xmlParse;
//This is based on xml_parser_6.java. this works for CPS return.  "Options" - OK. Only 2 top levels.

//Two top levels return - Options and Option. After enter node name, it returns option top level attributes names and their values. Options -> Option as an example.

//<Options>
//<Option optCode="HEADER" optLevel="02" optOrder="0" realOrder="1" optCluster="29" optTrim="*">
//	<optDescription>
//		<en>General Info</en>
//	</optDescription>
//<Option optUID="1041" optRevision="750452.0" optCode="ORDER1" optLevel="01" optOrder="199" realOrder="2" optCluster="29" optTrim="A*B*C*D*E*F*" ecc="0054" cwc="6 12">

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

public class xml_parser_7 {

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
				int nodeCount = nodes.getLength();// 1
				System.out.println("\nHere you go => Total # of Elements: " + nodes.getLength());
				System.out.println("\n");

				for (int i = 0; i < nodeCount; i++) {

					org.w3c.dom.Node nL = nodes.item(i);// first key
					// String keyA=nL
					String nodeName = nL.getNodeName();// Options
					String key1 = nL.getNodeValue();// null
					String key2 = nL.getLocalName();// null
					NamedNodeMap node1 = nL.getAttributes();
					NodeList node1_1 = nL.getChildNodes();

					int nodeAttributLength = node1.getLength();// 0
					if (nodeAttributLength > 1) {
						System.out.println(
								"Node Num =   " + nodeCount + " attribute Num=   " + nodeAttributLength + "\n");
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
					int childNodeSize = node1_1.getLength();// 235
					// Option
					for (int l = 0; l < childNodeSize; l++) {
						org.w3c.dom.Node node3 = node1_1.item(l);
						String nodeNmae3 = node3.getNodeName();// Option
						NodeList node3_3 = node3.getChildNodes();
						int nodeChild3Size = node3_3.getLength();// 6

						NamedNodeMap node4 = node3.getAttributes();
						int nodeChild4Size = node4.getLength();// 6

						for (int m = 0; m < nodeChild4Size; m++) {

							org.w3c.dom.Node node5 = node4.item(m);
							String nodeName4 = node5.getNodeName();// optCluster
							String nodeValue4 = node5.getTextContent();// 29
							System.out.println("\n");

							System.out.println("nodeName= " + nodeName + "   nodeNmae3= " + nodeNmae3
									+ " subNodeSize =   " + childNodeSize + "  " + (m + 1) + "   nodeChild3Size = "
									+ nodeChild3Size + "    SubNode Num = " + (l + 1) + " attribute Num= " + (m + 1));

							System.out.println("nodeNmae3=             " + nodeNmae3);

							System.out.println("nodeName4=             " + nodeName4);
							System.out.println("nodeValue4=            " + nodeValue4);

							System.out.println("\n");

						}
						System.out.println("Contine...");
					}

				}
			}
			System.out.println("Complete!!!\n");
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}
}