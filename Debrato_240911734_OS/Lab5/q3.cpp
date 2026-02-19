// 3. Write a C program to simulate Earliest-Deadline-First scheduling for real time systems.
#include <stdio.h>

struct Task {
    int pid;
    int execution;
    int deadline;
    int completion;
    int waiting;
    int turnaround;
};

int main() {
    int n, i, j;
    int time = 0;

    printf("Enter number of tasks: ");
    scanf("%d", &n);

    struct Task t[n];

    for (i = 0; i < n; i++) {
        t[i].pid = i + 1;
        printf("Execution time for T%d: ", i + 1);
        scanf("%d", &t[i].execution);
        printf("Deadline for T%d: ", i + 1);
        scanf("%d", &t[i].deadline);
    }

    // Sort by earliest deadline
    for (i = 0; i < n - 1; i++) {
        for (j = i + 1; j < n; j++) {
            if (t[i].deadline > t[j].deadline) {
                struct Task temp = t[i];
                t[i] = t[j];
                t[j] = temp;
            }
        }
    }

    printf("\nGantt Chart:\n| ");

    for (i = 0; i < n; i++) {
        time += t[i].execution;
        t[i].completion = time;
        printf("T%d | ", t[i].pid);
    }

    printf("\n");

    float totalWT = 0, totalTAT = 0;

    printf("\nPID\tExec\tDead\tCT\tTAT\tWT\n");

    for (i = 0; i < n; i++) {
        t[i].turnaround = t[i].completion;
        t[i].waiting = t[i].turnaround - t[i].execution;

        totalWT += t[i].waiting;
        totalTAT += t[i].turnaround;

        printf("T%d\t%d\t%d\t%d\t%d\t%d\n",
               t[i].pid,
               t[i].execution,
               t[i].deadline,
               t[i].completion,
               t[i].turnaround,
               t[i].waiting);
    }

    printf("\nAverage Waiting Time: %.2f", totalWT / n);
    printf("\nAverage Turnaround Time: %.2f\n", totalTAT / n);

    return 0;
}
