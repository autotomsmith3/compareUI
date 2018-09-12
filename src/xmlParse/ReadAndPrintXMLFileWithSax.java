package xmlParse;

//This Sax parser is a little bit slower
//From http://stackoverflow.com/questions/12269504/fastest-and-optimized-way-to-read-the-xml

//If the performances are important in your case, you should prefer SAX or StAX(http://en.wikipedia.org/wiki/StAX) to DOM.
//
//With DOM, in a first time the XML file is parsed into an object model then you have can ask it. So for you algorithm there are two pass.
//
//With SAX, during the parse, some callbacks are invoked (startDocument, endElement...),  SAX is event-based or a push model.
//
//With StAX, you control the parsing. You move a cursor from an element to another one. This is a pull model.
//
//With a file containing 32910000 persons , i compare my version with SAX to the over answer (of Blaise Doughan) with StAX. I remove all the System.out.println instrusctions. My program took 106 seconds to read all the file and the other took 94 seconds. I suppose that SAX is slower because all the callback are invoked even if they does nothing (the push model) whereas with StAX the cursor is moved only on the "interresting" elements (the pull model).
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadAndPrintXMLFileWithSax {

	public static void main(String[] args) throws Exception {
		SAXParserFactory fabrique = SAXParserFactory.newInstance();
		SAXParser parser = fabrique.newSAXParser();

		File file = new File("C:\\data\\xml\\book.xml");
		BookHandler handler = new BookHandler();
		parser.parse(file, handler);
	}

	public static class BookHandler extends DefaultHandler {
		private int count = 0;
		private StringBuilder buffer;

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			switch (qName) {
			case "person":
				count++;
				break;
			case "first":
				buffer = new StringBuilder("First Name : ");
				break;
			case "last":
				buffer = new StringBuilder("Last Name : ");
				break;
			case "age":
				buffer = new StringBuilder("Age : ");
				break;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String content = new String(ch, start, length);
			if (buffer != null)
				buffer.append(content);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch (qName) {
			case "first":
			case "last":
			case "age":
				System.out.println(buffer.toString());
				break;
			}
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println(count + " persons");
		}
	}
}
