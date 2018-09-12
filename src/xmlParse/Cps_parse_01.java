package xmlParse;
//This works only partly for CPS XML. Not complete yet.

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
public class Cps_parse_01 {
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
		taxname = streamReader.getLocalName(); //LoadTimes
				
		i = streamReader.nextTag();
		attributCount = streamReader.ATTRIBUTE;
		taxname = streamReader.getLocalName();//CPSComponentVersions
		
	
		i = streamReader.nextTag();
		
		
		taxname = streamReader.getLocalName();// "entry"

		key = streamReader.getAttributeLocalName(0);// key

		keyValue = streamReader.getAttributeValue(0);// Vehicle Processor
		key161 = streamReader.getElementText();// 1.18.0.5
		
		
		// .Stop here!!!!!!
		// .
		// .
		// .
		
		
		
		attributCount = streamReader.ATTRIBUTE;
		taxname = streamReader.getLocalName();
		
		keyValue = streamReader.getAttributeValue(0);// Transform Vehicle
		key161 = streamReader.getElementText();// 161
		
		
		i = streamReader.nextTag();
		attributCount = streamReader.ATTRIBUTE;
		taxname = streamReader.getLocalName();
		
		
				
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
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		// TODO Auto-generated method stub
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
