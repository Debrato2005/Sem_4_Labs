#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int FCFS(vector<int> req, int head) {
    int thm = 0;
    cout << head;
    for (int i = 0; i < req.size(); i++) {
        cout << " -> " << req[i];
        thm += abs(req[i] - head);
        head = req[i];
    }
    cout << endl;
    return thm;
}

int SSTF(vector<int> req, int head) {
    int thm = 0;
    vector<bool> visited(req.size(), false);

    cout << head;

    for (int i = 0; i < req.size(); i++) {
        int minDist = 1e9, index = -1;

        for (int j = 0; j < req.size(); j++) {
            if (!visited[j]) {
                int dist = abs(req[j] - head);
                if (dist < minDist) {
                    minDist = dist;
                    index = j;
                }
            }
        }

        visited[index] = true;
        cout << " -> " << req[index];
        thm += minDist;
        head = req[index];
    }

    cout << endl;
    return thm;
}

int SCAN(vector<int> req, int head, int disk_size) {
    int thm = 0;
    vector<int> left, right;

    for (int i : req) {
        if (i < head) left.push_back(i);
        else right.push_back(i);
    }

    sort(left.begin(), left.end());
    sort(right.begin(), right.end());

    cout << head;

    for (int i = left.size() - 1; i >= 0; i--) {
        cout << " -> " << left[i];
        thm += abs(head - left[i]);
        head = left[i];
    }

    cout << " -> 0";
    thm += abs(head - 0);
    head = 0;

    for (int i = 0; i < right.size(); i++) {
        cout << " -> " << right[i];
        thm += abs(head - right[i]);
        head = right[i];
    }

    cout << endl;
    return thm;
}

int CSCAN(vector<int> req, int head, int disk_size) {
    int thm = 0;
    vector<int> left, right;

    for (int i : req) {
        if (i < head) left.push_back(i);
        else right.push_back(i);
    }

    sort(left.begin(), left.end());
    sort(right.begin(), right.end());

    cout << head;

    for (int i = 0; i < right.size(); i++) {
        cout << " -> " << right[i];
        thm += abs(head - right[i]);
        head = right[i];
    }

    cout << " -> " << disk_size - 1;
    thm += abs(head - (disk_size - 1));

    cout << " -> 0";
    head = 0;

    for (int i = 0; i < left.size(); i++) {
        cout << " -> " << left[i];
        thm += abs(head - left[i]);
        head = left[i];
    }

    cout << endl;
    return thm;
}

int main() {
    int n, head, disk_size;

    cin >> n;

    vector<int> req(n);
    for (int i = 0; i < n; i++) cin >> req[i];

    cin >> head;
    cin >> disk_size;

    cout << "FCFS: ";
    cout << "THM = " << FCFS(req, head) << endl;

    cout << "SSTF: ";
    cout << "THM = " << SSTF(req, head) << endl;

    cout << "SCAN: ";
    cout << "THM = " << SCAN(req, head, disk_size) << endl;

    cout << "C-SCAN: ";
    cout << "THM = " << CSCAN(req, head, disk_size) << endl;

    return 0;
}