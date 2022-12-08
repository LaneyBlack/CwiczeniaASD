#include <iostream>
#include <fstream>

using namespace std;

int main(int argc, char *argv[]) {
    if (argc == 2) {
        fstream file;
        file.open(argv[1], ios::in);
        if (file.is_open()) {
            int number = -1, prevNumber = -1;
            int count = 0, sum = 0;
            int maxSum, maxCount = 0, simCount = 1;
            bool isIncr = false;
            while (file >> number) {
                if (number >= prevNumber && isIncr) {
                    count++;
                    sum += number;
                } else if (number <= prevNumber && !isIncr) {
                    count++;
                    sum += number;
                } else {
                    if (count > maxCount)
                        maxCount = count, maxSum = sum;
                    isIncr = !isIncr;
                    count = 1 + ((prevNumber >= 0) ? simCount : 0);
                    sum = (prevNumber >= 0 ? prevNumber : 0) * simCount + number;
                }
                if (number == prevNumber)
                    simCount++;
                else
                    simCount = 1;
                prevNumber = number;
            }
            if (count > maxCount)
                maxCount = count, maxSum = sum;
            cout << maxCount << " " << maxSum << endl;
        } else
            cout << "Error! Could not open file - " << argv[1] << endl;
    } else {
        cout << "Error! Wrong number of arguments - " << argc << endl;
        return -1;
    }
    return 0;
}