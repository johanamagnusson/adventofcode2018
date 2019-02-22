import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.*;

class Entry
{
    int year, month, day, hour, min;
    String entryString;

    public Entry(int year, int month, int day, int hour, int min, String entryString)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.entryString = entryString;
    }

    public String toString()
    {
        return String.format("%d - %d - %d - %d - %d: %s",
                this.year, this.month, this.day, this.hour, this.min, this.entryString);
    }

    public String getEntryString()
    {
        return this.entryString;
    }

    public int getMin()
    {
        return this.min;
    }
}

class SortEntriesByDate implements Comparator<Entry>
{
    public int compare(Entry a, Entry b)
    {
        int ret;
        ret = a.year - b.year;
        if (ret != 0)
            return ret;
        ret = a.month - b.month;
        if (ret != 0)
            return ret;
        ret = a.day - b.day;
        if (ret != 0)
            return ret;
        ret = a.hour - b.hour;
        if (ret != 0)
            return ret;
        ret = a.min - b.min;
        return ret;
    }
}

class Guard
{
    int id, minutesSlept, maxIndex;
    int[] minuteHist;

    public Guard(int id) {
        this.id = id;
        this.minuteHist = new int[60];
    }

    public void updateHistIndex(int i) {
        this.minuteHist[i]++;
    }

    public void updateMinutesSlept(int i) {
        this.minutesSlept += i;
    }

    public int getMinuteHistMax() {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < 60; i++) {
            if (this.minuteHist[i] > max) {
                max = this.minuteHist[i];
                maxIndex = i;
            }
        }
        this.maxIndex = maxIndex;
        return max;
    }

    public int getMinuteHistMaxIndex() {
        return this.maxIndex;
    }

    public int getId() {
        return this.id;
    }

    public int getMinutesSlept() {
        return this.minutesSlept;
    }

    public int getMinuteHistIndex(int i) {
        return this.minuteHist[i];
    }

    public String toString() {
        return String.format("#%d ", this.id) + Arrays.toString(this.minuteHist);
    }
}

public class Main
{
    private static void readFile(String fileName, ArrayList<Entry> ar)
    {
        String regexString = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})\\] .*$";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher;
        String str;
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    ar.add(new Entry(
                                Integer.parseInt(matcher.group(1)),
                                Integer.parseInt(matcher.group(2)),
                                Integer.parseInt(matcher.group(3)),
                                Integer.parseInt(matcher.group(4)),
                                Integer.parseInt(matcher.group(5)),
                                str.substring(19, str.length())));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []args)
    {
        ArrayList<Entry> ar = new ArrayList<Entry>();
        HashMap<Integer, Guard> map = new HashMap<Integer, Guard>();
        String str;
        int id, fallAsleep, wakeUp;

        readFile("input.txt", ar);
        Collections.sort(ar, new SortEntriesByDate());

        int i = 0;
        id = 0;
        while (i < ar.size()) {
            str = ar.get(i).getEntryString();
            if (str.charAt(0) == 'G') {
                id = Integer.parseInt(str.replaceAll("[\\D]", ""));
                if (!map.containsKey(id)) {
                    map.put(id, new Guard(id));
                }
                i++;
            }
            fallAsleep = ar.get(i).getMin();
            i++;
            for (int j = fallAsleep; j < ar.get(i).getMin(); j++) {
                map.get(id).updateHistIndex(j);
            }
            map.get(id).updateMinutesSlept(ar.get(i).getMin() - fallAsleep);
            i++;
        }

        int maxSleep = 0;
        Guard g;
        for (Map.Entry me : map.entrySet()) {
            g = (Guard) me.getValue();
            if (g.getMinutesSlept() > maxSleep) {
                maxSleep = g.getMinutesSlept();
                id = g.getId();
            }
        }
        System.out.println(String.format("%d - %d", id, maxSleep));

        int maxMinute = 0, maxValue = 0;
        g = (Guard) map.get(id);
        for (i = 0; i < 60; i++) {
            if (g.getMinuteHistIndex(i) > maxValue) {
                maxValue = g.getMinuteHistIndex(i);
                maxMinute = i;
            }
        }

        System.out.println(String.format("Final answer: %d", maxMinute * id));

        int max = 0, maxIndex = 0, tmp = 0;
        for (Map.Entry me : map.entrySet()) {
            g = (Guard) me.getValue();
            tmp = g.getMinuteHistMax();
            if (tmp > max) {
                max = tmp;
                maxIndex = g.getMinuteHistMaxIndex();
                id = g.getId();
            }
        }

        System.out.println(String.format("Final answer 2: %d", maxIndex * id));


        //for (i = 0; i < 10; i++)
        //    System.out.println(ar.get(i));

        /*
        // Test
        ArrayList<Entry> ar = new ArrayList<Entry>();
        ar.add(new Entry(2018, 12, 10, 34, 10));
        ar.add(new Entry(2018, 11, 10, 34, 10));
        ar.add(new Entry(2019, 12, 10, 34, 10));
        ar.add(new Entry(2018, 12, 10, 34, 11));
        ar.add(new Entry(2018, 12, 10, 33, 10));

        System.out.println("Unsorted");
        for (int i = 0; i < ar.size(); i++)
            System.out.println(ar.get(i));

        Collections.sort(ar, new SortEntriesByDate());

        System.out.println("Sorted by date");
        for (int i = 0; i < ar.size(); i++)
          System.out.println(ar.get(i));
        */
    }
}
