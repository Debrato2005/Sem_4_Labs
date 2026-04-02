#include <iostream>
using namespace std;

// Check if page exists
int search(int *frames, int f, int key) {
    for (int i = 0; i < f; i++)
        if (frames[i] == key)
            return 1;
    return 0;
}


void printFrames(int *frames, int f) {
    for (int i = 0; i < f; i++) {
        if (frames[i] == -1) cout << "- ";
        else cout << frames[i] << " ";
    }
}

void FIFO(int *pages, int n, int f) {
    int *frames = new int[f];
    for (int i = 0; i < f; i++) frames[i] = -1;

    int index = 0, faults = 0;

    cout << "\n--- FIFO ---\n";
    for (int i = 0; i < n; i++) {
        cout << "Page " << pages[i] << " -> ";

        if (!search(frames, f, pages[i])) {
            frames[index] = pages[i];
            index = (index + 1) % f;
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
    delete[] frames;
}

int findOptimal(int *pages, int *frames, int n, int f, int index) {
    int farthest = index, pos = -1;

    for (int i = 0; i < f; i++) {
        int j;
        for (j = index; j < n; j++) {
            if (frames[i] == pages[j]) {
                if (j > farthest) {
                    farthest = j;
                    pos = i;
                }
                break;
            }
        }
        if (j == n) return i;
    }
    return (pos == -1) ? 0 : pos;
}

void OPTIMAL(int *pages, int n, int f) {
    int *frames = new int[f];
    for (int i = 0; i < f; i++) frames[i] = -1;

    int faults = 0;

    cout << "\n--- OPTIMAL ---\n";
    for (int i = 0; i < n; i++) {
        cout << "Page " << pages[i] << " -> ";

        if (!search(frames, f, pages[i])) {
            int j;
            for (j = 0; j < f; j++) {
                if (frames[j] == -1) {
                    frames[j] = pages[i];
                    break;
                }
            }

            if (j == f) {
                int pos = findOptimal(pages, frames, n, f, i + 1);
                frames[pos] = pages[i];
            }

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
    delete[] frames;
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

    FIFO(pages, n, f);
    OPTIMAL(pages, n, f);

    delete[] pages;
    return 0;
}