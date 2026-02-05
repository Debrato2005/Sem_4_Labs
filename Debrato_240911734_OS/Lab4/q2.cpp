// 2. Write a C program to load the binary executable of the previous program in a child
// process using exec system call.
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
        cout << "Child process: executing Program 1 using exec" << endl;
        execl("./q1", "q1", NULL);
        cout << "Exec failed" << endl;
    }
    else
    {
        wait(NULL); 
        cout << "Parent process: child execution completed" << endl;
    }
    return 0;
}