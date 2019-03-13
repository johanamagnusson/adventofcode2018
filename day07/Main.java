import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;


class Instruction
{
    char id, child;

    public Instruction(char id, char child) {
        this.id = id;
        this.child = child;
    }
}

public class Main
{
    //static final int DATA_SIZE = 7;
    static final int DATA_SIZE = 101;
    static final int STEP = 60; // s

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
        //String fileName = "testInput.txt";
        char[][] input = new char[DATA_SIZE][2];

        LinkedList<Character> list = new LinkedList<Character>();
        ArrayList<Character> available = new ArrayList<Character>();
        Map<Character, ArrayList<Character>> map = new HashMap<Character, ArrayList<Character>>();
        Map<Character, ArrayList<Character>> dependencies = new HashMap<Character, ArrayList<Character>>();
        ArrayList<Character> instructions = new ArrayList<Character>();
        ArrayList<Character> right = new ArrayList<Character>();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        //char[] alphabet = "ABCDEF".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            instructions.add(alphabet[i]);
        }

        readFile(fileName, input);

        for (int i = 0; i < DATA_SIZE; i++) {
            if (map.get(input[i][0]) == null) {
                map.put(input[i][0], new ArrayList<Character>());
            }
            if (dependencies.get(input[i][1]) == null) {
                dependencies.put(input[i][1], new ArrayList<Character>());
            }
            map.get(input[i][0]).add(input[i][1]);
            dependencies.get(input[i][1]).add(input[i][0]);
            if (!right.contains(input[i][1])) {
                right.add(input[i][1]);
            }
        }

        for (int i = 0; i < instructions.size(); i++) {
            if (!right.contains(instructions.get(i)) && !available.contains(instructions.get(i))) {
                available.add(instructions.get(i));
            }
        }

        while (!instructions.isEmpty()) {
            Collections.sort(available);
            System.out.println(available);
            list.add(available.remove(0));
            instructions.remove(list.getLast());
            if (map.get(list.getLast()) == null) {
                break;
            }
            for (int i = 0; i < map.get(list.getLast()).size(); i++) {
                char c = map.get(list.getLast()).get(i);
                for (int j = 0; j < dependencies.get(c).size(); j++) {
                    if (!list.contains(dependencies.get(c).get(j))) {
                        break;
                    } else if (j == dependencies.get(c).size() - 1 &&
                            !available.contains(c)) {
                        available.add(c);
                    }
                }
            }
        }

        for (int i = 0; i < instructions.size(); i++) {
            list.add(instructions.get(i));
        }
        Character[] array = list.toArray(new Character[list.size()]);
        String answer = "";
        for (int i = 0; i < array.length; i++) {
            answer += array[i];
        }
        System.out.println(answer);

        // Debug
        /*
        for (char key : map.keySet()) {
            System.out.print(String.format("%c ", key));
            System.out.println(Arrays.toString(map.get(key).toArray()));
        }
        */

    }
}




