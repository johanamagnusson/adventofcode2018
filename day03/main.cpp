#include <iostream>
#include <fstream>
#include <regex>
#include <string>
#include <cstdlib>
#include <queue>

using namespace std;




class Claim
{
    int id, x, y, w, h;

    public:
    Claim();
    Claim(int, int, int, int, int);
}

Claim::Claim()
{
    id = 0;
    x = 0;
    y = 0;
    w = 0;
    h = 0;
}

Claim::Claim(int id, int x, int y, int w, int h)
{
    id = id;
    x = x;
    y = y;
    w = w;
    h = h;
}

void readFile(Claim *buffer, string& filename)
{
    fstream inputFile;
    smatch results;
    bool found;
    int i;
    string c;
    inputFile.open(filename);
    i = 0;
    while (getline(inputFile, c)) {
        //found = regex_match(c, results, regex("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)"));
        found = regex_match(c, results, regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)"));
        buffer[i].id = stoi(results[1].str());
        buffer[i].x = stoi(results[2].str());
        buffer[i].y = stoi(results[3].str());
        buffer[i].h = stoi(results[4].str());
        buffer[i].w = stoi(results[5].str());
        i++;
    }
    inputFile.close();

}



int main()
{
    const int numClaims = 1367;

}
