// 2. Write a C program to simulate multi-level feedback queue scheduling algorithm.
#include <stdio.h>

struct Process {
    int pid;
    int burst;
    int remaining;
    int completion;
    int waiting;
    int turnaround;
};

int main() {
    int n, i;
    int time = 0;

    printf("Enter number of processes: ");
    scanf("%d", &n);

    struct Process p[n];

    for (i = 0; i < n; i++) {
        p[i].pid = i + 1;
        printf("Burst time for P%d: ", i + 1);
        scanf("%d", &p[i].burst);
        p[i].remaining = p[i].burst;
    }

    int q0 = 8, q1 = 16;

    printf("\nGantt Chart:\n| ");

    // Q0 Round Robin
    for (i = 0; i < n; i++) {
        if (p[i].remaining > 0) {
            if (p[i].remaining <= q0) {
                time += p[i].remaining;
                p[i].remaining = 0;
                p[i].completion = time;
                printf("P%d | ", p[i].pid);
            } else {
                p[i].remaining -= q0;
                time += q0;
                printf("P%d | ", p[i].pid);
            }
        }
    }

    // Q1 Round Robin
    for (i = 0; i < n; i++) {
        if (p[i].remaining > 0) {
            if (p[i].remaining <= q1) {
                time += p[i].remaining;
                p[i].remaining = 0;
                p[i].completion = time;
                printf("P%d | ", p[i].pid);
            } else {
                p[i].remaining -= q1;
                time += q1;
                printf("P%d | ", p[i].pid);
            }
        }
    }

    // Q2 FCFS
    for (i = 0; i < n; i++) {
        if (p[i].remaining > 0) {
            time += p[i].remaining;
            p[i].remaining = 0;
            p[i].completion = time;
            printf("P%d | ", p[i].pid);
        }
    }

    printf("\n");

    // Calculate WT & TAT
    float totalWT = 0, totalTAT = 0;

    printf("\nPID\tBT\tCT\tTAT\tWT\n");

    for (i = 0; i < n; i++) {
        p[i].turnaround = p[i].completion;
        p[i].waiting = p[i].turnaround - p[i].burst;

        totalWT += p[i].waiting;
        totalTAT += p[i].turnaround;

        printf("P%d\t%d\t%d\t%d\t%d\n",
               p[i].pid,
               p[i].burst,
               p[i].completion,
               p[i].turnaround,
               p[i].waiting);
    }

    printf("\nAverage Waiting Time: %.2f", totalWT / n);
    printf("\nAverage Turnaround Time: %.2f\n", totalTAT / n);

    return 0;
}
