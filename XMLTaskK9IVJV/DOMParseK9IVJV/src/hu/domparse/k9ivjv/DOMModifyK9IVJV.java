package hu.domparse.k9ivjv;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyK9IVJV {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/XMLK9IVJV.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            Node root = doc.getFirstChild();

//          Modositsuk ugy a sportesemenyeket, hogy amiket a 7-es szamu jatekvezeto vezetett, azt a 2-es szamu vezesse!
            NodeList sportesemenyNodeList = doc.getElementsByTagName("sportesemeny");
            for (int i = 0; i < sportesemenyNodeList.getLength(); i++) {

                Node sportesemenyNode = sportesemenyNodeList.item(i);

                if (sportesemenyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) sportesemenyNode;

                    String jatekvezeto_IDREF = elem.getAttribute("jatekvezeto_IDREF");

                    if (jatekvezeto_IDREF.equals("7")) {
                        NamedNodeMap attr = sportesemenyNode.getAttributes();
                        Node nodeAttr = attr.getNamedItem("jatekvezeto_IDREF");
                        nodeAttr.setTextContent("2");
                    }
                }
            }

//          Adjuk hozza a tamado posztot minden olyan jatekoshoz, aki jelenleg nem tamado, de tud jatszani kozeppalyas poszton!
            NodeList nList = doc.getElementsByTagName("jatekos");
            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;

                    NodeList node5list = elem.getElementsByTagName("poszt");
                    StringBuilder poszt = new StringBuilder();
                    for (int j = 0; j < node5list.getLength(); j++) {
                        Node node5 = elem.getElementsByTagName("poszt").item(j);
                        if (node5.getNodeType() == Node.ELEMENT_NODE) {
                            if (j < elem.getElementsByTagName("poszt").getLength() - 1) {
                                poszt.append(node5.getTextContent()).append(", ");
                            } else {
                                poszt.append(node5.getTextContent());
                            }
                        }
                    }

                    if (poszt.toString().contains("középpályás") && !poszt.toString().contains("támadó")) {
                        Element posztElement = doc.createElement("poszt");
                        posztElement.appendChild(doc.createTextNode("támadó"));
                        elem.appendChild(posztElement);
                    }
                }
            }

//          Toroljuk a "reszvetelek" kapcsolotablat!
            NodeList childNodes = root.getChildNodes();
            for (int count = 0; count < childNodes.getLength(); count++) {
                Node node = childNodes.item(count);

                if (node.getNodeName().equals("reszvetelek"))
                    root.removeChild(node);
            }

//          Modositott xml dokumentum kiirasa konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}