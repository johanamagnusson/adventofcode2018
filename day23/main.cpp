#include <iostream>
#include <fstream>
#include <regex>
#include <string>
#include <cstdlib>

using namespace std;


struct nanobot {
    int x;
    int y;
    int z;
    int r;
};

void readFile(nanobot *buffer, string& filename)
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

int taxiDistance(nanobot bot1, nanobot bot2)
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
    nanobot array[numBots];
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
    printf("Number of bots in range: %d\n", numBotsInRange);

    // PART TWO




}
