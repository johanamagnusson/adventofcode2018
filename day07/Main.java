import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;


public class Main
{
    static final int DATA_SIZE = 101;

    public static void readFile(String fileName, char[][] input) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String str;
            int i = 0;
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                char[] charArray = str.toCharArray();
                input[i][0] = charArray[5];
                input[i][1] = charArray[36];
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []args) {
        String fileName = "input.txt";
        char[][] input = new char[DATA_SIZE][2];

        readFile(fileName, input);


    }
}




