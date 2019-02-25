import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;


public class Main
{

    public static void readFile(String fileName, ArrayList<Character> input) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String str;
            char[] array = new char[50000];
            str = scanner.useDelimiter("\\Z").next();
            array = str.toCharArray();
            scanner.close();
            for (int i = 0; i < array.length; i++) {
                input.add(array[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []args) {
        String fileName = "input.txt";
        ArrayList<Character> array = new ArrayList<Character>();
        readFile(fileName, array);

        // Part One
        int i = 0;
        while (i < array.size() - 1) {
            if (Character.isUpperCase(array.get(i))) {
                if (Character.toLowerCase(array.get(i)) == array.get(i + 1)) {
                    array.remove(i);
                    array.remove(i);
                    i = Math.max(0, i - 1);
                } else {
                    i++;
                }
            } else {
                if (Character.toUpperCase(array.get(i)) == array.get(i + 1)) {
                    array.remove(i);
                    array.remove(i);
                    i = Math.max(0, i - 1);
                } else {
                    i++;
                }
            }
        }

        System.out.println(String.format("Part one answer: %d", array.size()));

        // Part Two
    }
}
