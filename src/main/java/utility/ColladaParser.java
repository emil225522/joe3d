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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ColladaParser {
    static ColladaData data;
    static Document doc;

    public static ColladaData parse(String path) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        File file = new File(path);

        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            doc.normalize();

            String id = file.getName();
            int numTriangles = Integer.parseInt(doc.getElementsByTagName("triangles").item(0).getAttributes().getNamedItem("count").getNodeValue());
            data = new ColladaData(id, numTriangles);
            parseArrays();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void parseTrianglesInfo(Node node) {

    }

    private static void parseVerticesInfo(Node node) {

    }

    private static void parseArrays() {
        NodeList arrays = doc.getElementsByTagName("float_array");
        for (int i = 0; i < arrays.getLength(); i++) {
            Node array = arrays.item(i);
            String id = id(array);
            float[] floats = toFloats(array.getTextContent());
            if (id.contains("positions")) {
                data.setPositions(floats);
            } else if (id.contains("normals")) {
                data.setNormals(floats);
            } else if (id.contains("map")) {
                data.setTexCoords(floats);
            }
        }

        NodeList triangles = doc.getElementsByTagName("triangles");
        for (int i = 0; i < triangles.getLength(); i++) {
            Node triangle = triangles.item(i);
            NodeList list = triangle.getChildNodes();
            for (int j = 0; j < list.getLength(); j++) {
                Node p = list.item(j);
                if(p.getNodeName().equals("p")){
                    data.setIndices(toIntegers(p.getTextContent()));
                }
            }

        }
    }

    private static String id(Node node) {
        return node.getAttributes().getNamedItem("id").getNodeValue();
    }

    private static float[] toFloats(String s) {
        Scanner scan = new Scanner(s);
        List<Float> list = new ArrayList<>();
        while (scan.hasNext()) {
            list.add(Float.parseFloat(scan.next()));
        }

        float[] floats = new float[list.size()];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = list.get(i);
        }
        return floats;
    }

    private static int[] toIntegers(String s) {
        Scanner scan = new Scanner(s);
        List<Integer> list = new ArrayList<>();
        while (scan.hasNext()) {
            list.add(Integer.parseInt(scan.next()));
        }

        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }
}
