#include <iostream>
using namespace std;
 
void printFrames(int *frames, int f) {
    for (int i = 0; i < f; i++) {
        if (frames[i] == -1) cout << "- ";
        else cout << frames[i] << " ";
    }
}
 
int findLRU(int *time, int f) {
    int min = time[0], pos = 0;
    for (int i = 1; i < f; i++) {
        if (time[i] < min) {
            min = time[i];
            pos = i;
        }
    }
    return pos;
}
 
void LRU(int *pages, int n, int f) {
    int *frames = new int[f];
    int *time = new int[f];

    for (int i = 0; i < f; i++) {
        frames[i] = -1;
        time[i] = 0;
    }

    int faults = 0, hits = 0, counter = 0;

    cout << "\n--- LRU ---\n";
    for (int i = 0; i < n; i++) {
        cout << "Page " << pages[i] << " -> ";

        bool found = false;
        for (int j = 0; j < f; j++) {
            if (frames[j] == pages[i]) {
                counter++;
                time[j] = counter;
                hits++;
                found = true;
                break;
            }
        }

        if (!found) {
            int pos = -1;

            for (int j = 0; j < f; j++) {
                if (frames[j] == -1) {
                    pos = j;
                    break;
                }
            }

            if (pos == -1)
                pos = findLRU(time, f);

            counter++;
            frames[pos] = pages[i];
            time[pos] = counter;
            faults++;

            printFrames(frames, f);
            cout << " (FAULT)";
        } else {
            printFrames(frames, f);
            cout << " (HIT)";
        }

        cout << endl;
    }

    cout << "Total Page Faults = " << faults << endl;
    cout << "Total Hits = " << hits << endl;
    cout << "Hit Ratio = " << (float)hits / n << endl;

    delete[] frames;
    delete[] time;
}
 
int main() {
    int n, f;

    cout << "Enter number of pages: ";
    cin >> n;

    int *pages = new int[n];
    cout << "Enter page reference string:\n";
    for (int i = 0; i < n; i++) cin >> pages[i];

    cout << "Enter number of frames: ";
    cin >> f;

    LRU(pages, n, f);

    delete[] pages;
    return 0;
}