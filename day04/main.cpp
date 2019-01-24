#include <iostream>
#include <fstream>
#include <regex>

using namespace std;




class Entry
{
    private:
        string entryString;

    public:
        int year, month, day, min, sec;

        Entry();
        void setEntryString(string newEntryString);
        string getEntryString();
};

Entry::Entry()
{
    year = 0; month = 0; day = 0; min = 0; sec = 0;
}

void Entry::setEntryString(string newEntryString)
{
    entryString = newEntryString;
}

string Entry::getEntryString()
{
    return entryString;
}


void readFile(Entry *buffer, string& filename)
{
    fstream inputFile;
    smatch results;
    bool found;
    int i;
    string c;
    inputFile.open(filename);
    i = 0;
    while (getline(inputFile, c)) {
        buffer[i].setEntryString(c);
        found = regex_match(c, results, regex("\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})\\] .*$"));
        buffer[i].year = stoi(results[1].str());
        buffer[i].month = stoi(results[2].str());
        buffer[i].day = stoi(results[3].str());
        buffer[i].min = stoi(results[4].str());
        buffer[i].sec = stoi(results[5].str());
        i++;
    }
    inputFile.close();
}





int main()
{
    const int numEntries = 1186;
    string filename;
    Entry array[numEntries];

    filename = "input.txt";
    readFile(array, filename);



}




















