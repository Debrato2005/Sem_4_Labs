#include <iostream>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;

    int alloc[n][m], max[n][m], need[n][m], avail[m];

    for(int i = 0; i < n; i++)
        for(int j = 0; j < m; j++)
            cin >> alloc[i][j];

    for(int i = 0; i < n; i++)
        for(int j = 0; j < m; j++)
            cin >> max[i][j];

    for(int i = 0; i < m; i++)
        cin >> avail[i];

    for(int i = 0; i < n; i++)
        for(int j = 0; j < m; j++)
            need[i][j] = max[i][j] - alloc[i][j];

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++)
            cout << need[i][j] << " ";
        cout << endl;
    }

    bool finish[n] = {false};
    int safeSeq[n], count = 0;
    int work[m];

    for(int i = 0; i < m; i++)
        work[i] = avail[i];

    while(count < n) {
        bool found = false;

        for(int i = 0; i < n; i++) {
            if(!finish[i]) {
                bool possible = true;

                for(int j = 0; j < m; j++) {
                    if(need[i][j] > work[j]) {
                        possible = false;
                        break;
                    }
                }

                if(possible) {
                    for(int j = 0; j < m; j++)
                        work[j] += alloc[i][j];

                    safeSeq[count++] = i;
                    finish[i] = true;
                    found = true;
                }
            }
        }

        if(!found)
            break;
    }

    if(count == n) {
        cout << "Safe sequence: ";
        for(int i = 0; i < n; i++)
            cout << "P" << safeSeq[i] << " ";
    } else {
        cout << "System is not safe";
    }

    return 0;
}