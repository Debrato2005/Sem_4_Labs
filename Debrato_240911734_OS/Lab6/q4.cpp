// 4. Write a producer-consumer program in C in which producer writes a set of words into shared
// memory and then consumer reads the set of words from the shared memory. The shared
// memory need to be detached and deleted after use.
#include <iostream>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <cstring>
using namespace std;

int main() {

    // Generate unique key
    key_t key = ftok("shmfile", 90);

    // Create shared memory (1KB)
    int shmid = shmget(key, 1024, 0666 | IPC_CREAT);

    if (shmid < 0) {
        perror("shmget failed");
        return 1;
    }

    // Attach shared memory
    char* data = (char*) shmat(shmid, NULL, 0);

    if (data == (char*) -1) {
        perror("shmat failed");
        return 1;
    }

    if (fork() == 0) {
        // -------- Consumer --------
        sleep(1);  // ensure producer writes first

        cout << "\nConsumer reads:\n";
        cout << data << endl;

        shmdt(data);
    }
    else {
        // -------- Producer --------
        cout << "Producer writing words...\n";

        strcpy(data, "Operating Systems Lab Shared Memory Producer Consumer Example");

        wait(NULL);  // wait for child

        shmdt(data);
        shmctl(shmid, IPC_RMID, NULL);  // delete shared memory
    }

    return 0;
}
