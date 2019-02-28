import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;



public class Main
{
    static final int DATA_SIZE = 7;

    public static int[][] readFile(String fileName) {
        int[][] input = new int[DATA_SIZE + 1][2];
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String str;
            String[] xy;
            int i = 0, maxX = 0, maxY = 0;
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                xy = str.split(", ");
                input[i][0] = Integer.parseInt(xy[0]);
                input[i][1] = Integer.parseInt(xy[1]);
                if (input[i][0] > maxX) maxX = input[i][0];
                if (input[i][1] > maxY) maxY = input[i][1];
                i++;
            }
            input[DATA_SIZE][0] = maxX;
            input[DATA_SIZE][1] = maxY;
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    public static void main(String []args) {
        String fileName = "testInput.txt";
        int[][] input;
        input = readFile(fileName);
        int maxX = input[DATA_SIZE][0]; int maxY = input[DATA_SIZE][1];
        int numberCoords = maxX * maxY;

        int[][] grid = new int[maxY + 1][maxX + 1];

        for (int i = 0; i < DATA_SIZE; i++) {
            grid[input[i][1]][input[i][0]] = i + 1;
        }

        printGrid(grid);

        /*
        int checked = 0;
        int r = 1;
        while (checked < numberCoords) {
            for (i = 0; i < 50; i++) {
          */      

    }
}




