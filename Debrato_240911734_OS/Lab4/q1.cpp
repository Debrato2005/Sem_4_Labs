// 1. Write a C program to create a child process. Display different messages in parent
// process and child process. Display PID and PPID of both parent and child process.
// Block parent process until child completes using wait system call.
#include <bits/stdc++.h>
#include <unistd.h>
#include <sys/wait.h>
using namespace std;
int main()
{
    pid_t pid=fork();
    if(pid<0)
    {
        cout<<"fork failed"<<endl;
        return 1;
    }
    if(pid==0)
    {
        cout<<"child process";
        cout<<"child pid: "<<getpid()<<endl;
        cout<<"parent pid: "<<getppid()<<endl;
    }
    else
    {
        wait(NULL); 
        cout << "parent process" << endl;
        cout << "parent pid: " << getpid() << endl;
        cout << "child pid:  " << pid << endl;
    }
    return 0;
}