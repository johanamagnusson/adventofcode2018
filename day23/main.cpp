#include <iostream>
#include <fstream>
#include <regex>
#include <string>
#include <cstdlib>
#include <queue>

using namespace std;


struct Nanobot {
    int x;
    int y;
    int z;
    int r;
};

class Qitem
{
    public:
    int d;
    int e;
    Qitem()
    {
        d = 0;
        e = 0;
    }
    Qitem(int d, int e)
    {
        d = d;
        e = e;
    }
};

struct Compare
{
    bool operator()(const Qitem &lhs, const Qitem &rhs) const {
        return lhs.d > rhs.d;
    }
};


void readFile(Nanobot *buffer, string& filename)
{
    fstream inputFile;
    smatch results;
    bool found;
    int i;
    string c;
    inputFile.open(filename);
    i = 0;
    while (getline(inputFile, c)) {
        found = regex_match(c, results, regex("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)"));
        buffer[i].x = stoi(results[1].str());
        buffer[i].y = stoi(results[2].str());
        buffer[i].z = stoi(results[3].str());
        buffer[i].r = stoi(results[4].str());
        i++;
    }
    inputFile.close();

}

int taxiDistance(Nanobot bot1, Nanobot bot2)
{
    int dx, dy, dz, distance;
    dx = abs(bot2.x - bot1.x);
    dy = abs(bot2.y - bot1.y);
    dz = abs(bot2.z - bot1.z);
    distance = dx + dy + dz;
    return distance;
}


int main()
{
    const int numBots = 1000;
    string filename;
    Nanobot array[numBots];
    int i, strongest, distance, numBotsInRange;

    filename = "input.txt";
    readFile(array, filename);

    // PART ONE
    strongest = 0;
    for (i = 0; i < numBots; i++) {
        if (array[strongest].r < array[i].r) {
            strongest = i;
        }
    }

    numBotsInRange = 0;
    for (i = 0; i < numBots; i++) {
        distance = taxiDistance(array[strongest], array[i]);
        if (distance <= array[strongest].r) {
            numBotsInRange++;
        }
    }
    printf("Strongest index: %d\n", strongest);
    printf("Number of bots in range: %d\n", numBotsInRange);

    // PART TWO
    int botsInRange[numBots];
    int j, mostInRange, maxInRange;
    for (i = 0; i < numBots; i++) {
        botsInRange[i] = 0;
    }
    for (i = 0; i < numBots; i++) {
        for (j = i; j < numBots; j++) {
            distance = taxiDistance(array[i], array[j]);
            if (array[i].r >= distance && i != j) {
                botsInRange[i]++;
            }
            if (array[j].r >= distance && i != j) {
                botsInRange[j]++;
            }
        }
    }
    for (i = 0; i < numBots; i++) {
        if (botsInRange[i] > botsInRange[mostInRange]) {
            mostInRange = i;
        }
        /*
        if (i == strongest) {
            cout << botsInRange[i] << " STRONK\n";
        } else {
            cout << botsInRange[i] << "\n";
        }*/
    }
    printf("Bot with most in range: %d\n", mostInRange);
    printf("%d\n", botsInRange[mostInRange]);

    Nanobot origin;
    origin.x = 0; origin.y = 0; origin.z = 0; origin.r = 0;
    Qitem qarray1[numBots]; Qitem qarray2[numBots];
    priority_queue <Qitem, vector<Qitem>, Compare> q;
    for (i = 0; i < numBots; i++) {
        distance = taxiDistance(origin, array[i]);
        qarray1[i] = Qitem(max(0, distance - array[i].r), 1);
        qarray2[i] = Qitem(distance + array[i].r, -1);
        q.push(qarray1[i]);
        q.push(qarray2[i]);
    }
    int count, maxCount, distanceResult;
    Qitem qi;
    count = 0; maxCount = 0; distanceResult = 0;
    while (!q.empty()) {
        qi = q.top();
        count += qi.e;
        if (count > maxCount) {
            distanceResult = qi.d;
            maxCount = count;
        }
        q.pop();
    }

    printf("Distance to coordinate: %d\n", distanceResult);



}
