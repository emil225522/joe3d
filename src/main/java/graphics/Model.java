package graphics;

import org.joml.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model extends Mesh {

    /**
     * Creates a new 3D mesh, importing the model data from a Wavefront .obj file.
     * @param path the path to the .obj file.
     */
    public Model(String path) {
        super();
        parseOBJ(path, false, false);
    }

    /**
     * Parses an obj file, filling the vertices, texels, normals and indices of the mesh.
     * @param obj the path to the .obj file.
     * @param importTexels whether the mesh wants texels or not. If false, texels are not imported.
     * @param importNormals whether the mesh wants normals or not. If false, normals are not imported.
     */
    private void parseOBJ(String obj, boolean importTexels, boolean importNormals) {
        List<Vector3f> vList = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(obj));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(" ");

                // name
                if (parts[0].equalsIgnoreCase("o")) {
                    name = parts[1];
                }
                // vertices
                else if (parts[0].equalsIgnoreCase("v")) {
                    float x = Float.parseFloat(parts[1]);
                    float y = Float.parseFloat(parts[2]);
                    float z = Float.parseFloat(parts[3]);
                    vList.add(new Vector3f(x, y, z));
                }
                // texels
                else if (importTexels && parts[0].equalsIgnoreCase("vt")){
                    // TODO parse texel lines
                }
                // normals
                else if (importNormals && parts[0].equalsIgnoreCase("vn")){
                    // TODO parse normal lines
                }
                // indices
                else if (parts[0].equalsIgnoreCase("f")) {
                    // vertex strings
                    String vxs = parts[1].split("/")[0];
                    String vys = parts[2].split("/")[0];
                    String vzs = parts[3].split("/")[0];
                    int x = Integer.parseInt(vxs) - 1;
                    int y = Integer.parseInt(vys) - 1;
                    int z = Integer.parseInt(vzs) - 1;
                    indices.add(x);
                    indices.add(y);
                    indices.add(z);

                    if(importTexels){
                        // texel strings TODO actually add indices
                        String txs = parts[1].split("/")[1];
                        String tys = parts[2].split("/")[1];
                        String tzs = parts[3].split("/")[1];
                    }

                    if(importNormals){
                        // normal strings TODO actually add indices
                        String nxs = parts[1].split("/")[2];
                        String nys = parts[2].split("/")[2];
                        String nzs = parts[3].split("/")[2];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        vertices = new Vector3f[vList.size()];
        for (int i = 0; i < vList.size(); i++) {
            vertices[i] = vList.get(i);
        }

        this.indices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            this.indices[i] = indices.get(i);
        }
    }
}
