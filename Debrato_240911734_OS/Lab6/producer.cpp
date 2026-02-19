// 2. Write a producer and consumer program in C using FIFO queue. The producer should write
// a set of 4 integers into the FIFO queue and the consumer should display the 4 integers.
#include <iostream>
#include <fcntl.h>
#include <sys/stat.h>
#include <unistd.h>
#include <vector>

using namespace std;

int main() {
    const char* pipePath = "myfifo";
    int data[4];

    // Create the FIFO (named pipe)
    // 0666 gives read/write permissions to everyone
    mkfifo(pipePath, 0666);

    cout << "Producer: Waiting for consumer to join..." << endl;
    
    // Open for Write-Only. This will BLOCK until the consumer opens it.
    int fd = open(pipePath, O_WRONLY);
    if (fd == -1) {
        perror("Error opening FIFO for writing");
        return 1;
    }

    cout << "Enter 4 integers: ";
    for (int i = 0; i < 4; i++) {
        cin >> data[i];
    }

    // Write the integer array to the pipe
    write(fd, data, sizeof(data));
    cout << "Data sent successfully!" << endl;

    close(fd);
    return 0;
}
