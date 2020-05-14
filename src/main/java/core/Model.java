package core;

import org.joml.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model extends Mesh {
    public Model(String path){
        super();
        parseOBJ(path);
    }

    private void parseOBJ(String obj){
        List<Vector3f> vList = new ArrayList<>();
        List<Integer> fList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(obj));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] parts = line.split(" ");

                // name
                if(parts[0].equalsIgnoreCase("o")){
                    name = parts[1];
                }
                // vertices
                else if(parts[0].equalsIgnoreCase("v")){
                    float x = Float.parseFloat(parts[1]);
                    float y = Float.parseFloat(parts[2]);
                    float z = Float.parseFloat(parts[3]);
                    vList.add(new Vector3f(x,y,z));
                }
                // faces
                else if(parts[0].equalsIgnoreCase("f")){
                    String xs = parts[1].split("/")[0];
                    String ys = parts[2].split("/")[0];
                    String zs = parts[3].split("/")[0];

                    int x = Integer.parseInt(xs)-1;
                    int y = Integer.parseInt(ys)-1;
                    int z = Integer.parseInt(zs)-1;
                    fList.add(x);
                    fList.add(y);
                    fList.add(z);
                }
                // TODO add normals and other obj data
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        vertices = new Vector3f[vList.size()];
        for (int i = 0; i < vList.size(); i++) {
            vertices[i] = vList.get(i);
        }

        indices = new int[fList.size()];
        for (int i = 0; i < fList.size(); i++){
            indices[i] = fList.get(i);
        }
    }
}
