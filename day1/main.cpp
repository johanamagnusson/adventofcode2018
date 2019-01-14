#include <iostream>
#include <fstream>

using namespace std;


void readFile(int *buffer, string& filename)
{
    fstream inputFile;
    int x, i;
    inputFile.open(filename);
    i = 0;
    while (inputFile >> x) {
        buffer[i++] = x;
    }
    inputFile.close();

}

int main()
{
    fstream inputFile;
    string filename;
    int array[1038];
    int frequencies[1038*1038];
    int i, currentFreq, j, num, notfound;

    filename = "input.txt";
    readFile(array, filename);

    // Part One
    currentFreq = 0;
    for (i = 0; i < 1038; i++) {
        currentFreq += array[i];
    }
    printf("Resulting frequency: %d\n", currentFreq);

    // Part Two
    currentFreq = 0;
    num = 0;
    i = 0;
    notfound = 1;
    while (notfound) {
        currentFreq += array[i];
        for (j = 0; j < num; j++) {
            if (currentFreq == frequencies[j]) {
                notfound = 0;
            } else {
                frequencies[num] = currentFreq;
            }
        }
        i++;
        i %= 1038;
        num++;
    }
    printf("First frequency reached twice: %d\n", currentFreq);


}
