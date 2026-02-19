// 3. Implement a parent process, which sends an English alphabet to child process using shared
// memory. Child process responds back with next English alphabet to the parent. Parent
// displays the reply from the Child.
#include <iostream>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
using namespace std;

int main() {

    // Generate key
    key_t key = ftok("shmfile", 75);

    // Create shared memory
    int shmid = shmget(key, 1024, 0666 | IPC_CREAT);

    // Attach shared memory
    char* data = (char*) shmat(shmid, NULL, 0);

    if (fork() == 0) {
        // -------- Child Process --------
        sleep(1);   // ensure parent writes first

        char ch = data[0];

        // Compute next alphabet
        if (ch == 'z')
            data[0] = 'a';
        else if (ch == 'Z')
            data[0] = 'A';
        else
            data[0] = ch + 1;

        cout << "Child sent: " << data[0] << endl;

        shmdt(data);
    }
    else {
        // -------- Parent Process --------
        char ch;
        cout << "Enter an alphabet: ";
        cin >> ch;

        data[0] = ch;

        wait(NULL);  // wait for child to respond

        cout << "Parent received: " << data[0] << endl;

        shmdt(data);
        shmctl(shmid, IPC_RMID, NULL);  // delete shared memory
    }

    return 0;
}
