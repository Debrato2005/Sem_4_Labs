// 3. Create a zombie (defunct) child process (a child with exit() call, but no
// corresponding wait() in the sleeping parent) and allow the init process to adopt it
// (after parent terminates). Run the process as background process and run “ps”
// command.
#include <bits/stdc++.h>
#include <unistd.h>
#include <sys/wait.h>
using namespace std;
int main()
{
    pid_t pid = fork();

    if (pid < 0)
    {
        cout << "Fork failed" << endl;
        return 1;
    }

    if (pid == 0)
    {
        cout << "Child process exiting" << endl;
        _exit(0); 
    }
    else
    {
        cout << "Parent " << pid << " process sleeping..." << endl;
        sleep(10); 
        cout << "Parent " << pid << " exiting" << endl;
    }

    return 0;
}