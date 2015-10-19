package couch;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class WriteToXmlFile {


	String FilePath;
	public WriteToXmlFile()
	{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			org.w3c.dom.Document doc = docBuilder.newDocument();
			org.w3c.dom.Element rootElement = doc.createElement("RootNode");
			doc.appendChild(rootElement);		

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss") ;
			File file = new File(dateFormat.format(date) + "-ParentChildMapping.xml") ;
			FilePath=file.getAbsolutePath();
			StreamResult result = new StreamResult(file);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}
	public boolean CheckIfParentIdExists(String id)
	{
		try {
			File fXmlFile = new File(FilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Node");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                    if(eElement.getAttribute("id").contains(id))
                    	return true;
                    else
                    	return false;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	
	public void ChangePageStatus(String id,String status)
	{
		try {
			File fXmlFile = new File(FilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Node");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

					System.out.println("id : " + eElement.getAttribute("id"));
					System.out.println("Parent-id : " + eElement.getElementsByTagName("Parent-id").item(0).getTextContent());
					System.out.println("child-id : " + eElement.getElementsByTagName("child-id").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public void WriteParentAndChildsIdToXml(String [] ids)
	{
		try {

			File xmlFile = new File(FilePath);
			//Create the documentBuilderFactory
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			//Create the documentBuilder
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			//Create the Document  by parsing the file
			org.w3c.dom.Document document;

			document = documentBuilder.parse(xmlFile);

			//Get the root element of the xml Document;
			org.w3c.dom.Element rootElement = document.getDocumentElement();
			System.out.println(rootElement.getNodeName());
			// staff elements
			org.w3c.dom.Element Node = document.createElement("Node");
			rootElement.appendChild(Node);

			// set attribute to staff element
			Attr attr = document.createAttribute("id");
			attr.setValue("1");
			Node.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");


			org.w3c.dom.Element ParentId= document.createElement("Parent-id");
			ParentId.appendChild(document.createTextNode(ids[0]));
			Node.appendChild(ParentId);

			for(int i=1;i<ids.length;i++)
			{
				org.w3c.dom.Element child = document.createElement("child-id");
				child.appendChild(document.createTextNode(ids[i]+",false"));
				Node.appendChild(child);
			}



			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(FilePath));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
