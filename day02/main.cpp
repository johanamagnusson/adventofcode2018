#include <iostream>
#include <fstream>
#include <map>

using namespace std;


void readFile(string *buffer, string& filename)
{
    fstream inputFile;
    string x;
    int i;
    inputFile.open(filename);
    i = 0;
    while (inputFile >> x) {
        buffer[i++] = x;
    }
    inputFile.close();

}

int main()
{
    const int dataSize = 250;
    const int stringSize = 26;
    fstream inputFile;
    string filename;
    string array[dataSize];
    map<char, int> letterMap;
    map<char, int>::iterator it;
    int i, j, k, count;
    bool foundTwo, foundThree;
    char c;
    int twoLetterCount, threeLetterCount;

    filename = "input.txt";
    readFile(array, filename);

    // Part One
    twoLetterCount = 0; threeLetterCount = 0;
    for (i = 0; i < dataSize; i++) {
        for (j = 0; j < array[i].size(); j++) {
            c = array[i][j];
            if (letterMap.find(c) == letterMap.end()) {
                letterMap[c] = 1;
            } else {
                letterMap[c]++;
            }
        }
        it = letterMap.begin();
        foundTwo = false; foundThree = false;
        while (it != letterMap.end()) {
            count = it->second;
            if (count == 2 && !foundTwo) {
                twoLetterCount++;
                foundTwo = true;
            } else if (count == 3 && !foundThree) {
                threeLetterCount++;
                foundThree = true;
            }
            it++;
        }
        letterMap.clear();
    }
    printf("My checksum: %d\n", twoLetterCount * threeLetterCount);

    // Part Two
    bool notMatching;
    bool done = false;
    int result1, result2, resultk;
    i = 0;
    while (!done) {
        for (j = i + 1; j < dataSize; j++) {
            if (done) {
                break;
            } else {
                notMatching = false;
                for (k = 0; k < stringSize; k++) {
                    if (array[i][k] != array[j][k]) {
                        if (notMatching) {
                            break;
                        } else {
                            notMatching = true;
                            resultk = k;
                        }
                    } else if (k == stringSize - 1 && notMatching) {
                        done = true;
                        result1 = i;
                        result2 = j;
                    }
                }
            }
        }
        i++;
    }
    printf("%d, %d, %d\n", result1, result2, resultk);
    printf("%s\n", array[result1].c_str());
    printf("%s\n", array[result2].c_str());
    array[result1].erase(resultk, 1);
    printf("%s\n", array[result1].c_str());

}
