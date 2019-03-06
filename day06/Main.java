import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;

class DistanceToNode implements Comparable<DistanceToNode>
{
    int id, value;

    public DistanceToNode(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int compareTo(DistanceToNode d) {
        return this.value - d.value;
    }
}

class Grid
{
    int[][] grid;
    int xMin, xMax, yMin, yMax;

    public Grid(int xMin, int xMax, int yMin, int yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.grid = new int[xMax][yMax];
    }

    public void setPoint(int x, int y, int id) {
        grid[x - this.xMin][y - this.yMin] = id;
    }
}

public class Main
{
    static final int DATA_SIZE = 50;

    public static int[][] readFile(String fileName) {
        int[][] input = new int[DATA_SIZE + 2][2];
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String str;
            String[] xy;
            int i = 0, maxX = 0, maxY = 0, minX = 1000, minY = 1000;
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                xy = str.split(", ");
                input[i][0] = Integer.parseInt(xy[0]);
                input[i][1] = Integer.parseInt(xy[1]);
                if (input[i][0] > maxX) maxX = input[i][0];
                if (input[i][1] > maxY) maxY = input[i][1];
                if (input[i][0] < minX) minX = input[i][0];
                if (input[i][1] < minY) minY = input[i][1];
                i++;
            }
            input[DATA_SIZE][0] = maxX;
            input[DATA_SIZE][1] = maxY;
            input[DATA_SIZE + 1][0] = minX;
            input[DATA_SIZE + 1][1] = minY;
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String []args) {
        String fileName = "input.txt";
        int[][] input;
        input = readFile(fileName);
        int maxX = input[DATA_SIZE][0]; int maxY = input[DATA_SIZE][1];
        int minX = input[DATA_SIZE + 1][0]; int minY = input[DATA_SIZE + 1][1];
        int numberCoords = maxX * maxY;
        int infinity = numberCoords;
        Grid grid = new Grid(minX, maxX, minY, maxY);
        int[] nodeHistogram = new int[DATA_SIZE];
        ArrayList<DistanceToNode> distances = new ArrayList<DistanceToNode>();

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int i = 0; i < DATA_SIZE; i++) {
                    int distance = manhattanDistance(x, y, input[i][0], input[i][1]);
                    distances.add(new DistanceToNode(i, distance));
                }
                Collections.sort(distances);
                if (distances.get(0).value == distances.get(1).value) {
                    grid.setPoint(x, y, -1);
                } else {
                    grid.setPoint(x, y, distances.get(0).id);
                    if (x == minX || x == maxX || y == minY || y == maxY) {
                        nodeHistogram[distances.get(0).id] = infinity;
                    } else {
                        nodeHistogram[distances.get(0).id]++;
                    }
                }
                distances.clear();
            }
        }

        //System.out.println(Arrays.toString(nodeHistogram));

        int maxArea = 0;
        for (int i = 0; i < DATA_SIZE; i++) {
            if ((nodeHistogram[i] > maxArea) && !(nodeHistogram[i] >= infinity)) {
                maxArea = nodeHistogram[i];
            }
        }

        System.out.println(String.format("Part 1 answer: %d", maxArea));

        int totalDistance;
        int regionSize = 0;
        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                totalDistance = 0;
                for (int i = 0; i < DATA_SIZE; i++) {
                    totalDistance += manhattanDistance(x, y, input[i][0], input[i][1]);
                }
                if (totalDistance < 10000) {
                    regionSize++;
                }
            }
        }

        System.out.println(String.format("Part 2 answer: %d", regionSize));

    }
}




