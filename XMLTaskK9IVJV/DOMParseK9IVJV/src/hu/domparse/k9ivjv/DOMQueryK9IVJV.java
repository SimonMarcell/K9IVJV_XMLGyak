package hu.domparse.k9ivjv;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static java.lang.Math.floor;

public class DOMQueryK9IVJV {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ParseException {

        File xmlFile = new File("src/XMLK9IVJV.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        System.out.println("\nAzon jatekosok azonositoja, neve, szuletesi ideje, eletkora es posztjai, akik 26 evnel fiatalabbak es tudnak kapus poszton jatszani:");
        NodeList jatekosNodeList = doc.getElementsByTagName("jatekos");
        for (int i = 0; i < jatekosNodeList.getLength(); i++) {

            Node nNode = jatekosNodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String jatekos_ID = elem.getAttribute("jatekos_ID");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("szuletesiido").item(0);
                String szuletesiido = node2.getTextContent();

                NodeList node3list = elem.getElementsByTagName("poszt");
                StringBuilder poszt = new StringBuilder();
                for (int j = 0; j < node3list.getLength(); j++) {
                    Node node5 = elem.getElementsByTagName("poszt").item(j);
                    if (node5.getNodeType() == Node.ELEMENT_NODE) {
                        if (j < elem.getElementsByTagName("poszt").getLength() - 1) {
                            poszt.append(node5.getTextContent()).append(", ");
                        } else {
                            poszt.append(node5.getTextContent());
                        }
                    }
                }

//              Eletkor meghatarozasahoz szukseges parancsok
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date now = new Date();
                Date birthday = sdf.parse(szuletesiido);
                long diff = now.getTime() - birthday.getTime();
                TimeUnit time = TimeUnit.DAYS;
                long difference = time.convert(diff, TimeUnit.MILLISECONDS);

//              Itt tortenik a tenyleges vizsgalat. A program megnezi, hogy a "kapus" String resze-e a "poszt"-nak.
//              A "difference" valojaban a jatekos szuletese ota eltelt napok szama, igy ha azt szeretnenk vizsgalni,
//              hogy 26 evnel fiatalabb-e, akkor a 26*365 ertekkel kell osszevetnunk. Ha mindket feltetel igaz,
//              kiirasra kerulnek az adatok.
                if (poszt.toString().contains("kapus") && difference < 26 * 365) {
                    System.out.println("\nCurrent Element: " + nNode.getNodeName());
                    System.out.println("Jatekos azonosito: " + jatekos_ID);
                    System.out.println("Nev: " + nev);
                    System.out.println("Szuletesi ido: " + szuletesiido);
                    System.out.println("Eletkor: " + (int) floor(difference / 365));
                    System.out.println("Poszt: " + poszt);
                }
            }
        }


        System.out.println("\nA csapatok azonositoja, neve es a csapatkapitanyuk neve:");
        NodeList csapatNodeList = doc.getElementsByTagName("csapat");
        for (int i = 0; i < csapatNodeList.getLength(); i++) {

            Node csapatNode = csapatNodeList.item(i);

            if (csapatNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) csapatNode;

                String csapat_ID = elem.getAttribute("csapat_ID");
                String kapitany_IDREF = elem.getAttribute("kapitany_IDREF");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                String jatekosNev = "";

//              Keressuk azt a jatekost, melynek azonositoja megegyezik a csapatban talalhato kapitany_IDREF-fel.
//              Ehhez vegig kell iteralni a jatekosokhoz tartozo nodelist-en.
                for (int j = 0; j < jatekosNodeList.getLength(); j++) {

                    Node jatekosNode = jatekosNodeList.item(j);

                    if (jatekosNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element jatekosElem = (Element) jatekosNode;

                        String jatekos_ID = jatekosElem.getAttribute("jatekos_ID");

//                      Itt kerul ellenorzesre, hogy megtalaltuk-e a csapathoz tartozo csapatkapitanyt
                        if (jatekos_ID.equals(kapitany_IDREF)) {
                            Node jatekosNode1 = jatekosElem.getElementsByTagName("nev").item(0);
                            jatekosNev = jatekosNode1.getTextContent();
                        }
                    }
                }
//              Mivel minden csapatnak van csapatkapitanya, igy tovabbi vizsgalat nem szukseges, kiirasra kerulnek az adatok
                System.out.println("\nCurrent Element: " + csapatNode.getNodeName());
                System.out.println("Csapat azonosito: " + csapat_ID);
                System.out.println("Csapat neve: " + nev);
                System.out.println("Csapatkapitany neve: " + jatekosNev);
            }
        }

        System.out.println("\nA miskolci sportesemenyek elso helyezett csapatainak neve:");
        NodeList reszvetelNodeList = doc.getElementsByTagName("reszvetel");
        NodeList sportesemenyNodeList = doc.getElementsByTagName("sportesemeny");
        for (int i = 0; i < sportesemenyNodeList.getLength(); i++) {

            Node nNode = sportesemenyNodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String sportesemeny_ID = elem.getAttribute("sportesemeny_ID");

                Node node1 = elem.getElementsByTagName("helyszin").item(0);
                String helyszin = node1.getTextContent();

//              Elobb megvizsgaljuk, hogy miskolci-e a helyszin. Ha nem, akkor megsporoljuk a tovabbi muveleteket.
                if (helyszin.equals("Miskolc")) {
                    String elsoCsapat = "";
//                  Megkeressuk a reszvetel kapcsolotablaban a miskolci esemenyhez tartozo csapatokat
                    for (int j = 0; j < reszvetelNodeList.getLength(); j++) {

                        Node reszvetelNode = reszvetelNodeList.item(j);

                        if (reszvetelNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element reszvetelElem = (Element) reszvetelNode;

                            String sportesemeny_IDREF = reszvetelElem.getAttribute("sportesemeny_IDREF");

                            Node reszvetelNode1 = reszvetelElem.getElementsByTagName("helyezes").item(0);
                            String helyezes = reszvetelNode1.getTextContent();

//                          Megkeressuk, hogy melyik azonositoju csapat erte el az elso helyezest
                            if (sportesemeny_IDREF.equals(sportesemeny_ID) && helyezes.equals("1")) {
                                String csapat_IDREF = reszvetelElem.getAttribute("csapat_IDREF");
//                              A csapat_IDREF segitsegevel kikeressuk, hogy mi az adott csapat neve
                                for (int k = 0; k < csapatNodeList.getLength(); k++) {

                                    Node csapatNode = csapatNodeList.item(k);

                                    if (csapatNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element csapatElem = (Element) csapatNode;

                                        String csapat_ID = csapatElem.getAttribute("csapat_ID");

                                        if (csapat_ID.equals(csapat_IDREF)) {
                                            Node csapatNode1 = csapatElem.getElementsByTagName("nev").item(0);
                                            elsoCsapat = csapatNode1.getTextContent();
                                        }
                                    }
                                }
                            }
                        }
                    }
//                  Kiirasra kerulnek az adatok
                    System.out.println("\nCurrent Element: " + nNode.getNodeName());
                    System.out.println("Sportesemeny azonosito: " + sportesemeny_ID);
                    System.out.println("Helyszin: " + helyszin);
                    System.out.println("Elso helyezett csapat neve: " + elsoCsapat);
                }
            }
        }
    }
}