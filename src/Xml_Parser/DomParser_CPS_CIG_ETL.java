package Xml_Parser;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DomParser_CPS_CIG_ETL {

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        try
        {
        	File file = new File ( "C:\\1\\Eclipse\\XML_fr_David\\Vic XML Project 2017-10\\src\\xml\\CPS_for_CIG_ETL.xml");//student2.xml" );
        	byte[] content = new byte[(int)file.length()];
        	FileInputStream fis = new FileInputStream ( file );
        	fis.read( content );
        	fis.close();
        	
//        	String schemaSource = "C:\\1\\Eclipse\\XML_fr_David\\Vic XML Project 2017-10\\src\\xml\\student.xsd";
        	
        	String xmlString = new String ( content );
        	
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            factory.setNamespaceAware(true);
//            dbf.setValidating(dtdValidate || xsdValidate);
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
//            factory.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaSource));
            factory.setValidating( false );
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream ( content ) );
            
            Element element = document.getDocumentElement();
//            printElement ( element );
//            
            traverseNode ( element );
            
            System.out.println ( "done." );


        }
        catch (FactoryConfigurationError fce)
        {
            // TODO Auto-generated catch block
        	fce.printStackTrace();
        }
        catch (ParserConfigurationException pce)
        {
            // TODO Auto-generated catch block
            pce.printStackTrace();
        }
        catch (SAXException saxe)
        {
            // TODO Auto-generated catch block
            saxe.printStackTrace();
        }
        catch (IOException ioe)
        {
            // TODO Auto-generated catch block
            ioe.printStackTrace();
        }

	}
	
	private static int level = 0;
	
	private static void traverseNode ( Node node )
	{
		level ++;
		if ( node == null )
		{
			System.out.println ( "Leaf node encountered." );
			level --;
			return;
		}
        Node child = node.getFirstChild();
        while ( child != null )
        {
        	printElement ( child );
        	traverseNode ( child );
        	child = child.getNextSibling();
        }
        level --;
	}
	
	private static void printElement ( Node element )
	{
		System.out.println ( "Level: " + level + " - nodeName = " + element.getNodeName() + ", nodeType = " + element.getNodeType() + ", nodeValue = " + element.getNodeValue() );
	}

}
