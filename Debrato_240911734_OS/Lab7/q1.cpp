#include <iostream>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <vector>

using namespace std;

#define LIMIT 10 
#define TOTAL_ITEMS 20

int buffer[LIMIT];
int in = 0, out = 0;

sem_t empty_slots;
sem_t full_slots;
pthread_mutex_t mutex_lock;

void* producer(void* arg) {
    for (int i = 1; i <= TOTAL_ITEMS; i++) {
        sem_wait(&empty_slots); 
        pthread_mutex_lock(&mutex_lock);

        buffer[in] = i;
        cout << "[Producer] Produced Item: " << i << " at index " << in << endl;
        in = (in + 1) % LIMIT;

        pthread_mutex_unlock(&mutex_lock);
        sem_post(&full_slots);
        sleep(1); 
    }
    return nullptr;
}

void* consumer(void* arg) {
    for (int i = 1; i <= TOTAL_ITEMS; i++) {
        sem_wait(&full_slots);
        pthread_mutex_lock(&mutex_lock);

        int item = buffer[out];
        cout << "[Consumer] Consumed Item: " << item << " from index " << out << endl;
        out = (out + 1) % LIMIT;

        pthread_mutex_unlock(&mutex_lock);
        sem_post(&empty_slots);
        usleep(1500000); 
    }
    return nullptr;
}

int main() {
    pthread_t prod, cons;

    sem_init(&empty_slots, 0, LIMIT);
    sem_init(&full_slots, 0, 0);
    pthread_mutex_init(&mutex_lock, NULL);

    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    sem_destroy(&empty_slots);
    sem_destroy(&full_slots);
    pthread_mutex_destroy(&mutex_lock);

    return 0;
}