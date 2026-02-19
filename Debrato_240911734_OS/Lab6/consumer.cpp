#include <iostream>
#include <fcntl.h>
#include <unistd.h>

using namespace std;

int main() {
    const char* pipePath = "myfifo";
    int data[4];

    cout << "Consumer: Waiting for data..." << endl;

    // Open for Read-Only. This will BLOCK until the producer opens it.
    int fd = open(pipePath, O_RDONLY);
    if (fd == -1) {
        perror("Error opening FIFO for reading");
        return 1;
    }

    // Read the 4 integers from the pipe
    ssize_t bytesRead = read(fd, data, sizeof(data));

    if (bytesRead > 0) {
        cout << "Received integers: ";
        for (int i = 0; i < 4; i++) {
            cout << data[i] << " ";
        }
        cout << endl;
    } else {
        cout << "No data received." << endl;
    }

    close(fd);
    
    // Optional: Delete the FIFO file after use
    unlink(pipePath); 
    
    return 0;
}