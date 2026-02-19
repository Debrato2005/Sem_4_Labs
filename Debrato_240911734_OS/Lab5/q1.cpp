// 1. Consider the following set of processes, with length of the CPU burst given in
// milliseconds:
// ProcessArrival timeBurst timePriority
// P10603
// P23302
// P34401
// P49104
// Write a menu driven C/C++ program to simulate the following CPU scheduling algorithms.
// Display Gantt chart showing the order of execution of each process. Compute waiting time and
// turnaround time for each process. Hence, compute average waiting time and average turnaround
// time.
// (i) FCFS (ii) SRTF (iii) Round-Robin (quantum = 10 ) iv) non-preemptive priority (higher
// the number higher the priority)
#include <bits/stdc++.h>
using namespace std;

struct Process {
    int pid;
    int at;
    int bt;
    int rt;
    int pr;
    int ct;
    int tat;
    int wt;
    int response;
    bool started = false;
};

void printResults(vector<Process> &p, int n) {
    double totalWT = 0, totalTAT = 0;

    cout << "\nPID\tAT\tBT\tCT\tTAT\tWT\tRT\n";
    for (int i = 0; i < n; i++) {
        p[i].tat = p[i].ct - p[i].at;
        p[i].wt = p[i].tat - p[i].bt;
        totalWT += p[i].wt;
        totalTAT += p[i].tat;

        cout << p[i].pid << "\t"
             << p[i].at << "\t"
             << p[i].bt << "\t"
             << p[i].ct << "\t"
             << p[i].tat << "\t"
             << p[i].wt << "\t"
             << p[i].response << "\n";
    }

    cout << "\nAverage Waiting Time: " << totalWT / n;
    cout << "\nAverage Turnaround Time: " << totalTAT / n << "\n";
}

void FCFS(vector<Process> p, int n) {
    sort(p.begin(), p.end(), [](Process a, Process b) {
        return a.at < b.at;
    });

    int time = 0;

    cout << "\nGantt Chart:\n| ";
    for (int i = 0; i < n; i++) {
        if (time < p[i].at)
            time = p[i].at;

        p[i].response = time - p[i].at;
        time += p[i].bt;
        p[i].ct = time;

        cout << "P" << p[i].pid << " | ";
    }
    cout << "\n";

    printResults(p, n);
}

void SRTF(vector<Process> p, int n) {
    int completed = 0, time = 0;

    while (completed != n) {
        int idx = -1;
        int minRT = INT_MAX;

        for (int i = 0; i < n; i++) {
            if (p[i].at <= time && p[i].rt > 0 && p[i].rt < minRT) {
                minRT = p[i].rt;
                idx = i;
            }
        }

        if (idx != -1) {
            if (!p[idx].started) {
                p[idx].response = time - p[idx].at;
                p[idx].started = true;
            }

            p[idx].rt--;
            time++;

            if (p[idx].rt == 0) {
                p[idx].ct = time;
                completed++;
            }
        } else {
            time++;
        }
    }

    printResults(p, n);
}

void RoundRobin(vector<Process> p, int n) {
    int quantum = 10;
    queue<int> q;
    int time = 0, completed = 0;
    vector<bool> inQueue(n, false);

    while (completed < n) {
        for (int i = 0; i < n; i++) {
            if (p[i].at <= time && !inQueue[i] && p[i].rt > 0) {
                q.push(i);
                inQueue[i] = true;
            }
        }

        if (!q.empty()) {
            int idx = q.front();
            q.pop();

            if (!p[idx].started) {
                p[idx].response = time - p[idx].at;
                p[idx].started = true;
            }

            int execTime = min(quantum, p[idx].rt);
            p[idx].rt -= execTime;
            time += execTime;

            for (int i = 0; i < n; i++) {
                if (p[i].at <= time && !inQueue[i] && p[i].rt > 0) {
                    q.push(i);
                    inQueue[i] = true;
                }
            }

            if (p[idx].rt > 0)
                q.push(idx);
            else {
                p[idx].ct = time;
                completed++;
            }

        } else {
            time++;
        }
    }

    printResults(p, n);
}

void PriorityScheduling(vector<Process> p, int n) {
    int completed = 0, time = 0;

    while (completed < n) {
        int idx = -1;
        int highest = -1;

        for (int i = 0; i < n; i++) {
            if (p[i].at <= time && p[i].rt > 0 && p[i].pr > highest) {
                highest = p[i].pr;
                idx = i;
            }
        }

        if (idx != -1) {
            p[idx].response = time - p[idx].at;
            time += p[idx].bt;
            p[idx].rt = 0;
            p[idx].ct = time;
            completed++;
        } else {
            time++;
        }
    }

    printResults(p, n);
}

int main() {
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    vector<Process> p(n);

    for (int i = 0; i < n; i++) {
        p[i].pid = i + 1;
        cout << "\nProcess " << i + 1 << "\n";
        cout << "Arrival Time: ";
        cin >> p[i].at;
        cout << "Burst Time: ";
        cin >> p[i].bt;
        cout << "Priority: ";
        cin >> p[i].pr;
        p[i].rt = p[i].bt;
    }

    int choice;
    cout << "\n1. FCFS\n2. SRTF\n3. Round Robin\n4. Priority\n";
    cout << "Enter choice: ";
    cin >> choice;

    switch (choice) {
        case 1: FCFS(p, n); break;
        case 2: SRTF(p, n); break;
        case 3: RoundRobin(p, n); break;
        case 4: PriorityScheduling(p, n); break;
        default: cout << "Invalid Choice\n";
    }

    return 0;
}
