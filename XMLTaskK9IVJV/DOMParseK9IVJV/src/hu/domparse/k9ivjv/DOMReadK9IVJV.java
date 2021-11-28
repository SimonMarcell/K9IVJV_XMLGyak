package hu.domparse.k9ivjv;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMReadK9IVJV {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

//      Uj DocumentBuilder letrehozasa
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

//      Document letrehozasa XML fajlbol
        File xmlFile = new File("src/XMLK9IVJV.xml");
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

//      Root element meghatarozasa es kiiratasa
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

//      "Jatekos" elemek megkeresese tag nev alapjan, egy listaba kerulnek a node-ok
        NodeList nList = doc.getElementsByTagName("jatekos");
//      Node-okon valo iteracio a nodelista hosszanak segitsegevel
        for (int i = 0; i < nList.getLength(); i++) {

//          A nodelista i. eleme
            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

//              Az i. node adott nevu attributumanak a megkeresese es ertekenek tarolasa egy String-ben
                String jatekos_ID = elem.getAttribute("jatekos_ID");

//              Az i. node adott nevu gyerekelemenek a megkeresese es ertekenek tarolasa egy String-ben
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("szuletesihely").item(0);
                String szuletesihely = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("szuletesiido").item(0);
                String szuletesiido = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("allampolgarsag").item(0);
                String allampolgarsag = node4.getTextContent();

//              A poszt gyerekelemrol tudjuk, hogy tobbszor is elofordulhat (maxOccurs="unbounded"), igy egy uj nodelist-et keszitunk, melyben csak posztok szerepelnek, majd ezen iteralunk vegig
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

//              Osszegyujtott adatok kiiratasa
                System.out.println("Jatekos azonosito: " + jatekos_ID);
                System.out.println("Nev: " + nev);
                System.out.println("Szuletesi hely: " + szuletesihely);
                System.out.println("Szuletesi ido: " + szuletesiido);
                System.out.println("Allampolgarsag: " + allampolgarsag);
                System.out.println("Poszt: " + poszt);
            }
        }

        nList = doc.getElementsByTagName("csapat");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String csapat_ID = elem.getAttribute("csapat_ID");
                String kapitany_IDREF = elem.getAttribute("kapitany_IDREF");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("alapitaseve").item(0);
                String alapitaseve = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("edzo").item(0);
                String edzo = node3.getTextContent();

                System.out.println("Csapat azonosito: " + csapat_ID);
                System.out.println("Csapat neve: " + nev);
                System.out.println("Csapatkapitany azonosito: " + kapitany_IDREF);
                System.out.println("Alapitas eve: " + alapitaseve);
                System.out.println("Edzo: " + edzo);
            }
        }

        nList = doc.getElementsByTagName("reszvetel");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String sportesemeny_IDREF = elem.getAttribute("sportesemeny_IDREF");
                String csapat_IDREF = elem.getAttribute("csapat_IDREF");

                Node node1 = elem.getElementsByTagName("helyezes").item(0);
                String helyezes = node1.getTextContent();

                System.out.println("Sportesemeny azonositoja: " + sportesemeny_IDREF);
                System.out.println("Resztvevo csapat azonositoja: " + csapat_IDREF);
                System.out.println("Elert helyezes: " + helyezes);
            }
        }

        nList = doc.getElementsByTagName("sportesemeny");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String sportesemeny_ID = elem.getAttribute("sportesemeny_ID");
                String jatekvezeto_IDREF = elem.getAttribute("jatekvezeto_IDREF");

                Node node1 = elem.getElementsByTagName("sportag").item(0);
                String sportag = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("helyszin").item(0);
                String helyszin = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("datum").item(0);
                String datum = node3.getTextContent();

                System.out.println("Sportesemeny azonosito: " + sportesemeny_ID);
                System.out.println("Jatekvezeto azonositoja: " + jatekvezeto_IDREF);
                System.out.println("Sportag: " + sportag);
                System.out.println("Helyszin: " + helyszin);
                System.out.println("Datum: " + datum);
            }
        }

        nList = doc.getElementsByTagName("jatekvezeto");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String jatekvezeto_ID = elem.getAttribute("jatekvezeto_ID");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("szuletesihely").item(0);
                String szuletesihely = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("szuletesiido").item(0);
                String szuletesiido = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("allampolgarsag").item(0);
                String allampolgarsag = node4.getTextContent();

                System.out.println("Jatekvezeto azonosito: " + jatekvezeto_ID);
                System.out.println("Nev: " + nev);
                System.out.println("Szuletesi hely: " + szuletesihely);
                System.out.println("Szuletesi ido: " + szuletesiido);
                System.out.println("Allampolgarsag: " + allampolgarsag);
            }
        }
    }
}