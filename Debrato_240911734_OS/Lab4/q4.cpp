// 4. Write a multithreaded program that performs different sorting algorithms. The
// program should work as follows: the user enters on the command line the number
// of elements to sort and the elements themselves. The program then creates
// separate threads, each using a different sorting algorithm. Each thread sorts the
// array using its corresponding algorithm and displays the time taken to produce the
// result. The main thread waits for all threads to finish and then displays the final
// sorted array.
#include <bits/stdc++.h>
#include <sys/wait.h>
#include <chrono> 
using namespace std;
using namespace chrono;

struct ThreadData {
    vector<int> arr;
    string algo;
};

void bubbleSort(vector<int>& a) {
    int n = a.size();
    for (int i = 0; i < n - 1; i++)
        for (int j = 0; j < n - i - 1; j++)
            if (a[j] > a[j + 1])
                swap(a[j], a[j + 1]);
}

void selectionSort(vector<int>& a) {
    int n = a.size();
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++)
            if (a[j] < a[minIdx])
                minIdx = j;
        swap(a[i], a[minIdx]);
    }
}

void insertionSort(vector<int>& a) {
    int n = a.size();
    for (int i = 1; i < n; i++) {
        int key = a[i];
        int j = i - 1;
        while (j >= 0 && a[j] > key) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = key;
    }
}

void* sortThread(void* arg) {
    ThreadData* data = (ThreadData*)arg;

    auto start = high_resolution_clock::now();

    if (data->algo == "Bubble")
        bubbleSort(data->arr);
    else if (data->algo == "Selection")
        selectionSort(data->arr);
    else if (data->algo == "Insertion")
        insertionSort(data->arr);

    auto end = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(end - start);

    cout << data->algo << " Sort Time: "
         << duration.count() << " ms" << endl;

    pthread_exit(NULL);
}

int main(int argc, char* argv[]) {
    int n = stoi(argv[1]);
    vector<int> input;

    for (int i = 2; i < argc; i++)
        input.push_back(stoi(argv[i]));

    pthread_t t1, t2, t3;

    ThreadData d1{input, "Bubble"};
    ThreadData d2{input, "Selection"};
    ThreadData d3{input, "Insertion"};

    pthread_create(&t1, NULL, sortThread, &d1);
    pthread_create(&t2, NULL, sortThread, &d2);
    pthread_create(&t3, NULL, sortThread, &d3);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t3, NULL);

    for (int x : d1.arr)
        cout << x << " ";
    cout << endl;

    return 0;
}
