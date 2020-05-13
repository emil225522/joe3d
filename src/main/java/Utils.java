import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

    public static String parseText(String filepath){
        String string = "";

        try {
            Scanner scan = new Scanner(new File(filepath));

            while(scan.hasNextLine()){
                string = string.concat(scan.nextLine()  + "\n");
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return string;
    }
}
