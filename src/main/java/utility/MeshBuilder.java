package utility;

import graphics.Mesh;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeshBuilder {
    public static Mesh build(String obj) {
        List<Vector3f> vList = new ArrayList<>();
        List<Vector2f> tList = new ArrayList<>();
        List<Vector3f> nList = new ArrayList<>();
        List<Integer> iList = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new File(obj));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(" ");
                boolean isVertexLine = parts[0].equalsIgnoreCase("v");
                boolean isTexCoordLine = parts[0].equalsIgnoreCase("vt");
                boolean isNormalLine = parts[0].equalsIgnoreCase("vn");
                boolean isFaceLine = parts[0].equalsIgnoreCase("f");

                if (isVertexLine) {
                    Vector3f v = parseVertexLine(parts);
                    vList.add(v);
                }

                else if (isTexCoordLine){
                    Vector2f tc = parseTexCoordLine(parts);
                    tList.add(tc);
                }
                // normals
                else if (isNormalLine){
                    Vector3f n = parseVertexLine(parts);
                    nList.add(n);
                }
                // indices
                else if (isFaceLine) {
                    // vertex strings
                    String vxs = parts[1].split("/")[0];
                    String vys = parts[2].split("/")[0];
                    String vzs = parts[3].split("/")[0];
                    int i1 = Integer.parseInt(vxs) - 1;
                    int i2 = Integer.parseInt(vys) - 1;
                    int i3 = Integer.parseInt(vzs) - 1;
                    iList.add(i1);
                    iList.add(i2);
                    iList.add(i3);

//                    // texel strings TODO actually add indices
//                    String txs = parts[1].split("/")[1];
//                    String tys = parts[2].split("/")[1];
//                    String tzs = parts[3].split("/")[1];
//
//                    // normal strings TODO actually add indices
//                    String nxs = parts[1].split("/")[2];
//                    String nys = parts[2].split("/")[2];
//                    String nzs = parts[3].split("/")[2];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Build the model once finished parsing
        Vector3f[] vertices = new Vector3f[vList.size()];
        for (int i = 0; i < vList.size(); i++) {
            vertices[i] = vList.get(i);
        }

        int[] indices = new int[iList.size()];
        for (int i = 0; i < iList.size(); i++) {
            indices[i] = iList.get(i);
        }

        // TODO return the built model
        return new Mesh(vertices, null, null, indices);
    }

    private static Vector3f parseVertexLine(String[] parts) {
        float x = Float.parseFloat(parts[1]);
        float y = Float.parseFloat(parts[2]);
        float z = Float.parseFloat(parts[3]);
        return new Vector3f(x, y, z);
    }

    private static Vector2f parseTexCoordLine(String[] parts) {
        float x = Float.parseFloat(parts[1]);
        float y = Float.parseFloat(parts[2]);
        return new Vector2f(x,y);
    }

    private static Vector3f parseNormalLine(String[] parts) {
        return parseVertexLine(parts);
    }
}
