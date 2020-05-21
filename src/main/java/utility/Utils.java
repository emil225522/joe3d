package utility;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A helper class containing methods for various external functionalities, such as file manipulation etc.
 */
public class Utils {
    /**
     * Parse a text file into a Java string.
     * @param filepath the absolute text file path.
     * @return a string of all the text in the file.
     */
    public static String parseText(String filepath) {
        String string = "";

        try {
            Scanner scan = new Scanner(new File(filepath));

            while (scan.hasNextLine()) {
                string = string.concat(scan.nextLine() + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return string;
    }

    public static Map<Integer, String> parseKeyMap(String filepath){
        Map<Integer, String> map = new HashMap<>();
        try {
            Scanner scan = new Scanner(new File(filepath));

            while (scan.hasNextLine() ) {
                String line = scan.nextLine();
                if(line.charAt(0) == '#')
                    continue;

                String[] parts = line.split(" ");
                // TODO implement
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
