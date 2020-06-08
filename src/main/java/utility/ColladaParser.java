package utility;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ColladaParser {
    static ColladaData data;

    public static ColladaData parse(String path) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        File file = new File(path);
        Document doc;
        data = new ColladaData();
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            parse(doc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void parse(Document doc) {
        parse(doc.getDocumentElement());
    }

    private static void parse(Node node) {
        String nodeName = node.getNodeName();
        String nodeValue = node.getNodeValue();

        data.addNode(nodeName, nodeValue);
        if (node.hasAttributes()) {
            NamedNodeMap map = node.getAttributes();
            Map<String, String> attributes = new HashMap<>();
            for (int i = 0; i < map.getLength(); i++) {
                String attribName = map.item(i).getNodeName();
                String attribValue = map.item(i).getNodeValue();
                attributes.put(attribName, attribValue);
            }
            data.addAttributes(nodeName, attributes);
        }
        if(node.getTextContent() != null){
            data.addTextContent(nodeName, node.getTextContent());
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                parse(currentNode);
            }
        }
    }
}
