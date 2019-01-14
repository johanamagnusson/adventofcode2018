#include <iostream>
#include <fstream>

using namespace std;


void readFile(int *buffer, string& filename)
{
    fstream inputFile;
    int x, i;
    inputFile.open(filename);
    while (inputFile >> x) {
        buffer[i++] = x;
    }
    inputFile.close();

}

int main()
{
    fstream inputFile;
    filename = "input.txt";
    int array[1038];

    readFile(*array);

}
