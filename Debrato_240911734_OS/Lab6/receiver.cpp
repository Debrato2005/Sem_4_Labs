#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
using namespace std;

struct Message {
    long msg_type;
    int number;
};

bool isPalindrome(int n) {
    int rev = 0, temp = n;
    while (n > 0) {
        rev = rev * 10 + n % 10;
        n /= 10;
    }
    return (rev == temp);
}

int main() {
    key_t key = ftok("progfile", 65);
    int msgid = msgget(key, 0666 | IPC_CREAT);

    Message msg;
    msgrcv(msgid, &msg, sizeof(msg.number), 1, 0);

    cout << "Received number: " << msg.number << endl;

    if (isPalindrome(msg.number))
        cout << "It is Palindrome\n";
    else
        cout << "Not Palindrome\n";

    msgctl(msgid, IPC_RMID, NULL);
    return 0;
}
