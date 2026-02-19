// 1. Process A wants to send a number to Process B. Once received, Process B has to check
// whether the number is palindrome or not. Write a C program to implement this interprocess
// communication using message queue.
#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
using namespace std;

struct Message {
    long msg_type;
    int number;
};

int main() {
    key_t key = ftok("progfile", 65);
    int msgid = msgget(key, 0666 | IPC_CREAT);

    Message msg;
    msg.msg_type = 1;

    cout << "Enter a number: ";
    cin >> msg.number;

    msgsnd(msgid, &msg, sizeof(msg.number), 0);

    cout << "Number sent successfully\n";
    return 0;
}



