package utility;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ColladaParser {
    StringBuilder sb;

    public ColladaData parse(String path) {
        sb = new StringBuilder();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(path);
            Document doc = db.parse(file);
            traverse(doc);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null; // TODO sensible return
    }

    private void traverse(Document doc) {
        NodeList list = doc.getElementsByTagName("*");

        for (int i = 0, len = list.getLength(); i < len; i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                System.out.println(node.getNodeName());

            }
        }
    }
}
