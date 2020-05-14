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
        int numV=0;
        List<Vector3f> vList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(obj));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] parts = line.split(" ");

                if(parts[0].equalsIgnoreCase("o")) name = parts[1];
                else if(parts[0].equalsIgnoreCase("v")){
                    float x = Float.parseFloat(parts[1]);
                    float y = Float.parseFloat(parts[2]);
                    float z = Float.parseFloat(parts[3]);
                    vList.add(new Vector3f(x,y,z));
                    numV++;
                }
                // TODO add normals and other obj data
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        vertices = new Vector3f[numV];
        for (int i = 0; i < numV; i++) {
            vertices[i] = vList.get(i);
        }
    }
}
