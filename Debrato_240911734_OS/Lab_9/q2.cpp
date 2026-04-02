#include <iostream>
using namespace std;

int main() {
    int page_size, n;
    cin >> page_size;

    cin >> n;

    for (int i = 0; i < n; i++) {
        int addr;
        cin >> addr;

        int page = addr / page_size;
        int offset = addr % page_size;

        cout << page << " " << offset << endl;
    }

    return 0;
}