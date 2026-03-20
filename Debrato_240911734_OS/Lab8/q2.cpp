#include <bits/stdc++.h>
using namespace std;

#define MAX 100

int main() {
    int n, m;
    cout << "Enter number of processes: ";
    cin >> n;
    cout << "Enter number of resource types: ";
    cin >> m;

    int alloc[MAX][MAX], req[MAX][MAX], avail[MAX];
    bool finish[MAX] = {0};

    cout << "\nEnter Allocation Matrix:\n";
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> alloc[i][j];
        }
    }

    cout << "\nEnter Request Matrix:\n";
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> req[i][j];
        }
    }

    cout << "\nEnter Available Resources:\n";
    for(int j=0;j<m;j++){
        cin >> avail[j];
    }

    int work[MAX];
    for(int j=0;j<m;j++) work[j] = avail[j];

    int safeSeq[MAX], count = 0;

    while(true) {
        bool found = false;

        for(int i=0;i<n;i++){
            if(!finish[i]) {
                bool canRun = true;

                for(int j=0;j<m;j++){
                    if(req[i][j] > work[j]) {
                        canRun = false;
                        break;
                    }
                }

                if(canRun) {
                    for(int j=0;j<m;j++){
                        work[j] += alloc[i][j];
                    }

                    safeSeq[count++] = i;
                    finish[i] = true;
                    found = true;
                }
            }
        }

        if(!found) break;
    }

    bool deadlock = false;

    for(int i=0;i<n;i++){
        if(!finish[i]) {
            deadlock = true;
            break;
        }
    }

    if(!deadlock) {
        cout << "\nSystem is in SAFE state\n";
        cout << "Safe Sequence: ";
        for(int i=0;i<count;i++){
            cout << "P" << safeSeq[i];
            if(i != count-1) cout << " -> ";
        }
        cout << endl;
    }
    else {
        cout << "\nSystem is in DEADLOCK\n";
        cout << "Processes in deadlock: ";
        for(int i=0;i<n;i++){
            if(!finish[i]) cout << "P" << i << " ";
        }
        cout << endl;
    }

    return 0;
}