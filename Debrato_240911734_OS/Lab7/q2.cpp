#include <iostream>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

using namespace std;

sem_t rw_mutex;
sem_t mutex_rc;
int read_count = 0;
int shared_data = 0;

void* reader(void* arg) {
    int id = *(int*)arg;
    
    sem_wait(&mutex_rc);
    read_count++;
    if (read_count == 1) {
        sem_wait(&rw_mutex); 
    }
    sem_post(&mutex_rc);

    cout << "Reader " << id << " read data: " << shared_data << endl;
    sleep(1); 

    sem_wait(&mutex_rc);
    read_count--;
    if (read_count == 0) {
        sem_post(&rw_mutex);
    }
    sem_post(&mutex_rc);
    
    return nullptr;
}

void* writer(void* arg) {
    int id = *(int*)arg;

    sem_wait(&rw_mutex);
    shared_data += 10;
    cout << "Writer " << id << " updated data to: " << shared_data << endl;
    sleep(2);
    sem_post(&rw_mutex);

    return nullptr;
}

int main() {
    pthread_t r[5], w[2];
    sem_init(&rw_mutex, 0, 1);
    sem_init(&mutex_rc, 0, 1);

    int ids[5] = {1, 2, 3, 4, 5};

    for(int i = 0; i < 2; i++) pthread_create(&w[i], NULL, writer, &ids[i]);
    for(int i = 0; i < 5; i++) pthread_create(&r[i], NULL, reader, &ids[i]);

    for(int i = 0; i < 2; i++) pthread_join(w[i], NULL);
    for(int i = 0; i < 5; i++) pthread_join(r[i], NULL);

    sem_destroy(&rw_mutex);
    sem_destroy(&mutex_rc);
    return 0;
}