#include <iostream>
#include <fstream>
#include <regex>
#include <string>
#include <cstdlib>
#include <map>

using namespace std;




class Claim
{
    public:
        int id, x, y, w, h;
        Claim();
        //friend bool operator<(const Claim &c1, const Claim &c2);
};
Claim::Claim()
{
    id = 0; x = 0; y = 0; w = 0; h = 0;
}
bool operator<(Claim c1, Claim c2)
{
    return c1.id != c2.id;
}

bool overlap(Claim c1, Claim c2)
{
    if (c1.x >= c2.x + c2.w || c2.x >= c1.x + c1.w) {
        return false;
    }

    if (c1.y >= c2.y + c2.h || c2.y >= c1.y + c1.h) {
        return false;
    }

    return true;
}

class Square
{
    private:
        int x, y;

    public:
        int matrix[1000][1000];
        Square();
        void updateCount(Claim);
};
Square::Square()
{
    x = 0; y = 0;
    int i, j;
    for (i = 0; i < 1000; i++) {
        for (j = 0; j < 1000; j++) {
            matrix[i][j] = 0;
        }
    }

}
void Square::updateCount(Claim c)
{
    int i, j;
    for (i = c.x; i < c.x + c.w; i++) {
        for (j = c.y; j < c.y + c.h; j++) {
            matrix[i][j]++;
        }
    }
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
        found = regex_match(c, results, regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)"));
        buffer[i].id = stoi(results[1].str());
        buffer[i].x  = stoi(results[2].str());
        buffer[i].y  = stoi(results[3].str());
        buffer[i].w  = stoi(results[4].str());
        buffer[i].h  = stoi(results[5].str());
        i++;
    }
    inputFile.close();
}





int main()
{
    const int numClaims = 1367;
    const int squareSize = 1000;
    string filename;
    Claim array[numClaims];
    Square square;
    int i, j, count;

    filename = "input.txt";
    readFile(array, filename);

    // Part One
    for (i = 0; i < numClaims; i++) {
        square.updateCount(array[i]);
    }

    count = 0;
    for (i = 0; i < squareSize; i++) {
        for (j = 0; j < squareSize; j++) {
            if (square.matrix[i][j] >= 2) {
                count++;
            }
        }
    }
    printf("%d\n", count);


    // Part Two
    Claim result;
    bool done;
    done = false;
    for (i = 0; i < numClaims; i++) {
        for (j = 0; j < numClaims; j++) {
            if (i == j) {
                continue;
            } else if (overlap(array[i], array[j])) {
                break;
            } else if (j == numClaims - 1) {
                result = array[i];
                done = true;
            }
        }
        if (done) {
            break;
        }
    }

    printf("%d\n", result.id);



    /*
    map<int, Claim> mapOfClaims;
    map<int, Claim>::iterator it;
    Claim c1, c2;
    bool done;
    for (i = 0; i < numClaims; i++) {
        mapOfClaims[array[i].id] = array[i];
    }

    done = false;
    while(!done) {
        it = mapOfClaims.begin();
        c1 = it -> second;
        it++;
        while(it != mapOfClaims.end()) {
            c2 = it -> second;
            if (overlap(c1, c2)) {
                mapOfClaims.erase(c1.id);
                break;
            }
            it++;
        }
        if (it == mapOfClaims.end()) {
            done = true;
        }
    }

    printf("%d\n", c1.id);
    */


}




















