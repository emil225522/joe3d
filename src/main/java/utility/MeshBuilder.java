package utility;

import graphics.Mesh;

import graphics.Vertex;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeshBuilder {
    public static Mesh build(String obj) {
        boolean hasTexCoord = false;
        boolean hasNormal = false;

        List<Vector3f> vList = new ArrayList<>();
        List<Vector2f> tList = new ArrayList<>();
        List<Vector3f> nList = new ArrayList<>();
        List<Integer> vertexIndexList = new ArrayList<>();
        List<Integer> texCoordIndexList = new ArrayList<>();
        List<Integer> normalIndexList = new ArrayList<>();

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
                } else if (isTexCoordLine) {
                    Vector2f tc = parseTexCoordLine(parts);
                    tList.add(tc);
                } else if (isNormalLine) {
                    Vector3f n = parseNormalLine(parts);
                    nList.add(n);
                } else if (isFaceLine) {
                    // Determine what data line has
                    String[] check = parts[1].split("/");
                    hasTexCoord = check.length > 1 && !check[1].equals("");
                    hasNormal = check.length == 3;

                    // vertex indices in groups of three
                    int[] vIndices = parseVertexIndices(parts);
                    vertexIndexList.add(vIndices[0]);
                    vertexIndexList.add(vIndices[1]);
                    vertexIndexList.add(vIndices[2]);

                    if (hasTexCoord) {
                        int[] tIndices = parseTexCoordIndices(parts);
                        texCoordIndexList.add(tIndices[0]);
                        texCoordIndexList.add(tIndices[1]);
                    }

                    if (hasNormal) {
                        int[] nIndices = parseNormalIndices(parts);
                        normalIndexList.add(nIndices[0]);
                        normalIndexList.add(nIndices[1]);
                        normalIndexList.add(nIndices[2]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Vertex[] vertices = new Vertex[vertexIndexList.size()];
        for (int i = 0; i < vertexIndexList.size(); i++) {
            Vector3f position;
            Vector2f texCoord = null;
            Vector3f normal = null;

            int vIndex = vertexIndexList.get(i);
            position = vList.get(vIndex);

            if (hasTexCoord) {
                int tIndex = texCoordIndexList.get(i);
                texCoord = tList.get(tIndex);
            }
            if (hasNormal) {
                int nIndex = normalIndexList.get(i);
                normal = nList.get(nIndex);
            }
            vertices[i] = new Vertex(position, texCoord, normal);
        }
        return new Mesh(vertices);
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
        return new Vector2f(x, y);
    }

    private static Vector3f parseNormalLine(String[] parts) {
        return parseVertexLine(parts);
    }

    private static int[] parseVertexIndices(String[] parts) {
        String indexString1 = parts[1].split("/")[0];
        String indexString2 = parts[2].split("/")[0];
        String indexString3 = parts[3].split("/")[0];
        int index1 = Integer.parseInt(indexString1) - 1;
        int index2 = Integer.parseInt(indexString2) - 1;
        int index3 = Integer.parseInt(indexString3) - 1;
        return new int[]{index1, index2, index3};
    }

    private static int[] parseTexCoordIndices(String[] parts) {
        String indexString1 = parts[1].split("/")[1];
        String indexString2 = parts[2].split("/")[1];
        int index1 = Integer.parseInt(indexString1) - 1;
        int index2 = Integer.parseInt(indexString2) - 1;
        return new int[]{index1, index2};
    }

    private static int[] parseNormalIndices(String[] parts) {
        String indexString1 = parts[1].split("/")[2];
        String indexString2 = parts[2].split("/")[2];
        String indexString3 = parts[3].split("/")[2];
        int index1 = Integer.parseInt(indexString1) - 1;
        int index2 = Integer.parseInt(indexString2) - 1;
        int index3 = Integer.parseInt(indexString3) - 1;
        return new int[]{index1, index2, index3};
    }
}
