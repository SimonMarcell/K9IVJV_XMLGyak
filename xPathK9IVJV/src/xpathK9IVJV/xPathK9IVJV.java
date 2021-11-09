package xpathK9IVJV;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathK9IVJV {

	public static void main(String[] args) {
		try {
			// File xmlFile = newFile("class.xml");

			// DocumentBuilder létrehozása
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("src/studentK9IVJV.xml");
			document.getDocumentElement().normalize();

			// az XPath készítése
			XPath xPath = XPathFactory.newInstance().newXPath();

			// Meg kell adni az elérési út kifejezését és a csomópont listát:
			//String expression = "class";
			//String expression = "/class/student";
			//String expression = "//student[@id='01']";
			//String expression = "//student";
			//String expression = "/class/student[2]";
			//String expression = "/class/student[last()]";
			//String expression = "/class/student[last()-1]";
			//String expression = "/class/student[position()<3]";
			//String expression = "/class/*";
			//String expression = "//student[@*]";
			String expression = "//*";
			
			
			// Készítsunk egy listát, majd a Path kifejezést meg kell írni és ki kell
			// értékelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			// A for ciklus segítségével a NodeList csomópontjait végig kell iterrálni
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("\nAktuális elem: " + node.getNodeName());

				// Meg kel lvizsgálni a csomópontot az subelement tesztelése megtörtént.
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;

					// az id attribútomot ad vissza
					System.out.println("Hallgató ID: " + element.getAttribute("id"));

					// keresztnevet, stb. ad vissza
					System.out.println(
							"Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println(
							"Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());

				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}
}
