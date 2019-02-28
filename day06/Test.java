import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;

public class Test
{
    public static void updateGrid(int[][] grid) {
        grid[1][2] += 10;
    }

    public static void main(String args[]) {
        int[][] grid = new int[10][10];
        grid[1][2] = 1;
        System.out.println(grid[1][2]);
        updateGrid(grid);
        System.out.println(grid[1][2]);

        int x, y;
        int r = 3;
        for (x = -r; x <= r; x++) {
            y = r - Math.abs(x);
            System.out.println(String.format("%d, %d", x, y));
            System.out.println(String.format("%d, %d", x, -y));
        }
    }


}
