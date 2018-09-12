package xmlParse;


//DOM parser is too slow
//SAX is a little bit slower
//StAX is fast way
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.*;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadAndPrintXMLFileWithStAX {

	public static void FastWay_StAX_ToReadXMLFile() throws FileNotFoundException, XMLStreamException {
		// Streaming API for XML (StAX) is an application programming interface
		// (API) to read and write XML documents, originating from the Java
		// programming language community.
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream("C:\\data\\xml\\book.xml");
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
		streamReader.nextTag(); // Advance to "book" element
		streamReader.nextTag(); // Advance to "person" element

		int persons = 0;
		while (streamReader.hasNext()) {
			if (streamReader.isStartElement()) {
				switch (streamReader.getLocalName()) {
				case "first": {
					System.out.print("First Name : ");
					System.out.println(streamReader.getElementText());
					break;
				}
				case "last": {
					System.out.print("Last Name : ");
					System.out.println(streamReader.getElementText());
					break;
				}
				case "age": {
					System.out.print("Age : ");
					System.out.println(streamReader.getElementText());
					break;
				}
				case "person": {
					persons++;
				}
				}
			}
			streamReader.next();
		}
		System.out.print(persons);
		System.out.println(" persons");
	}

	public static void FastWay_StAX_ToReadXMLFileCPS() throws FileNotFoundException, XMLStreamException {
		// Streaming API for XML (StAX) is an application programming interface
		// (API) to read and write XML documents, originating from the Java
		// programming language community.
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream("C:\\data\\xml\\CPS01.xml");
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
		int i = streamReader.nextTag(); // Advance to "vehicle" element
		String taxname = streamReader.getLocalName();// "vehicle"
		i = streamReader.nextTag(); // Advance to "ExtractInfo"
		taxname = streamReader.getLocalName();// "ExtractInfo"
		String warehoueBuildDate = streamReader.getAttributeLocalName(0);// warehouseBuildDate
		String warehoueBuildDate1 = streamReader.getAttributeValue(0);// "2016-11-10T16:04:54.778-05:00"
		String requestedRevision = streamReader.getAttributeLocalName(1);// requestedRevision
		String requestedRevisionValue = streamReader.getAttributeValue(1);// "-1.0"
		String key = "";

		String keyValue = "";
		String key161 = "";
		i = streamReader.nextTag(); // Advance to "LoadTimes"
		int attributCount = streamReader.ATTRIBUTE; // 10
		taxname = streamReader.getLocalName();// "LoadTimes"
		for (int j = 0; j <= attributCount; j++) {
			i = streamReader.nextTag();
			i = streamReader.getNamespaceCount();
			i = streamReader.ATTRIBUTE; // 10
			i = streamReader.getAttributeCount();

			taxname = streamReader.getLocalName();// "entry"

			key = streamReader.getAttributeLocalName(0);// key

			keyValue = streamReader.getAttributeValue(0);// Transform Vehicle
			key161 = streamReader.getElementText();// 161
		}
		i = streamReader.nextTag();
		attributCount = streamReader.ATTRIBUTE;
		taxname = streamReader.getLocalName();// "entry"
		i = streamReader.nextTag();
		taxname = streamReader.getLocalName();// "entry"
		attributCount = streamReader.ATTRIBUTE;

		// .
		// .
		// .
		// .
		streamReader.getAttributeCount();

		String keyValue11 = streamReader.getAttributeLocalName(0);
		i = streamReader.nextTag(); // Advance to "entry"
		taxname = streamReader.getLocalName();// "entry"
		i = streamReader.getAttributeCount();

		streamReader.next(); // Advance to "entry"
		i = streamReader.nextTag(); // Advance to "entry"

		streamReader.next(); // Advance to "entry"
		streamReader.nextTag(); // Advance to "entry"

		taxname = streamReader.getLocalName();// "entry"
		streamReader.nextTag(); // Advance to "entry"
		taxname = streamReader.getLocalName();// "entry"
		streamReader.nextTag(); // Advance to "entry"
		taxname = streamReader.getLocalName();// "entry"

		// streamReader.nextTag(); // Advance to "Options" element
		String vehicle1 = streamReader.getElementText();
		taxname = streamReader.getLocalName();// "vehicle"
		// streamReader.nextTag(); //
		// String vehicle2=streamReader.getElementText();

		int persons = 0;
		while (streamReader.hasNext()) {
			taxname = streamReader.getLocalName();
			streamReader.next(); //
			taxname = streamReader.getLocalName();
			streamReader.nextTag();
			taxname = streamReader.getLocalName();
			String vehicle3 = streamReader.getElementText();
			System.out.println("Vehicle3=" + vehicle3);

			if (streamReader.isStartElement()) {
				// String vehicle3=streamReader.getElementText();
				// System.out.println("Vehicle3="+vehicle3);
				switch (streamReader.getLocalName()) {
				case "optDescription": {
					System.out.print("optDescription");
					System.out.println(streamReader.getElementText());
					break;
				}
				case "last": {
					System.out.print("Last Name : ");
					System.out.println(streamReader.getElementText());
					break;
				}
				case "age": {
					System.out.print("Age : ");
					System.out.println(streamReader.getElementText());
					break;
				}
				case "person": {
					persons++;
				}
				}
			}
			streamReader.next();
			String s = streamReader.toString();
			System.out.println("S=" + s);
		}
		System.out.print(persons);
		System.out.println(" persons");
	}

	public static void OrigianWayToReadXMLFile() {
		// DOM parser
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("C:\\data\\xml\\book.xml"));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList listOfPersons = doc.getElementsByTagName("person");
			int totalPersons = listOfPersons.getLength();
			System.out.println("Total no of people : " + totalPersons);

			for (int s = 0; s < listOfPersons.getLength(); s++) {

				Node firstPersonNode = listOfPersons.item(s);
				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstPersonElement = (Element) firstPersonNode;

					// -------
					NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
					Element firstNameElement = (Element) firstNameList.item(0);

					NodeList textFNList = firstNameElement.getChildNodes();
					System.out.println("First Name : " + ((Node) textFNList.item(0)).getNodeValue().trim());

					// -------
					NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
					Element lastNameElement = (Element) lastNameList.item(0);

					NodeList textLNList = lastNameElement.getChildNodes();
					System.out.println("Last Name : " + ((Node) textLNList.item(0)).getNodeValue().trim());

					// ----
					NodeList ageList = firstPersonElement.getElementsByTagName("age");
					Element ageElement = (Element) ageList.item(0);

					NodeList textAgeList = ageElement.getChildNodes();
					System.out.println("Age : " + ((Node) textAgeList.item(0)).getNodeValue().trim());

					// ------

				} // end of if clause

			} // end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		// System.exit (0);
	}

	public static void ReadAndPrintXMLFileWithSax() {
		// See Class ReadAndPrintXMLFileWithSax
	}

	public static void main(String argv[]) throws Exception {
		System.out.println("Starting:\n");
		// System.out.println("\nOriginal Read XML File:\n");
		// OrigianWayToReadXMLFile();
		// System.out.println("\nFast Way - StAX To Read XML File:\n");
		// FastWay_StAX_ToReadXMLFile();
		//
		// System.out.println("\nFast Way - StAX To Read XML File:\n");
		// FastWay_StAX_ToReadXMLFile();
		System.out.println("\nFast Way - StAX To Read CPS XML File:\n");
		FastWay_StAX_ToReadXMLFileCPS();

		System.out.println("\nEnd!\n");
	}

}
