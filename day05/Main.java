import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;


public class Main
{

    /*
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
    }*/

    public static char[] readFile(String fileName) {
        char[] input = new char[50000];
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String str;
            str = scanner.useDelimiter("\\Z").next();
            input = str.toCharArray();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void reactPolymer(ArrayList<Character> array) {
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
    }

    public static void main(String []args) {
        String fileName = "input.txt";
        char[] inputArray = readFile(fileName);
        int i, j;
        ArrayList<Character> array = new ArrayList<Character>();
        for (i = 0; i < inputArray.length; i++) {
            array.add(inputArray[i]);
        }

        // Part One
        reactPolymer(array);
        System.out.println(String.format("Part One answer: %d", array.size()));

        // Part Two
        char[] alphabetLower = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int[] polymerLengths = new int[alphabetLower.length];

        for (i = 0; i < alphabetLower.length; i++ ) {
            array.clear();
            for (j = 0; j < inputArray.length; j++) {
                array.add(inputArray[j]);
            }
            final char lower = alphabetLower[i];
            final char upper = alphabetUpper[i];
            array.removeIf(c -> (c == lower || c == upper));
            reactPolymer(array);
            polymerLengths[i] += array.size();
        }

        int minPolymer = 50000;
        for (i = 0; i < alphabetLower.length; i++) {
            if (polymerLengths[i] < minPolymer) {
                minPolymer = polymerLengths[i];
            }
        }
        System.out.println(String.format("Part Two answer: %d", minPolymer));

    }
}




