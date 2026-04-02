#include <iostream>
#include <vector>
using namespace std;

void print(vector<int> alloc) {
    for (int i = 0; i < alloc.size(); i++) {
        if (alloc[i] != -1) cout << alloc[i] + 1 << " ";
        else cout << "0 ";
    }
    cout << endl;
}

int main() {
    int nh, np;
    cin >> nh;

    vector<int> holes(nh);
    for (int i = 0; i < nh; i++) cin >> holes[i];

    cin >> np;
    vector<int> processes(np);
    for (int i = 0; i < np; i++) cin >> processes[i];

    vector<int> hole, alloc;

    hole = holes;
    alloc.assign(np, -1);
    for (int i = 0; i < np; i++) {
        for (int j = 0; j < nh; j++) {
            if (hole[j] >= processes[i]) {
                alloc[i] = j;
                hole[j] -= processes[i];
                break;
            }
        }
    }
    cout << "First Fit: ";
    print(alloc);

    hole = holes;
    alloc.assign(np, -1);
    for (int i = 0; i < np; i++) {
        int best = -1;
        for (int j = 0; j < nh; j++) {
            if (hole[j] >= processes[i]) {
                if (best == -1 || hole[j] < hole[best]) best = j;
            }
        }
        if (best != -1) {
            alloc[i] = best;
            hole[best] -= processes[i];
        }
    }
    cout << "Best Fit: ";
    print(alloc);

    hole = holes;
    alloc.assign(np, -1);
    for (int i = 0; i < np; i++) {
        int worst = -1;
        for (int j = 0; j < nh; j++) {
            if (hole[j] >= processes[i]) {
                if (worst == -1 || hole[j] > hole[worst]) worst = j;
            }
        }
        if (worst != -1) {
            alloc[i] = worst;
            hole[worst] -= processes[i];
        }
    }
    cout << "Worst Fit: ";
    print(alloc);

    return 0;
}