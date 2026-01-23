// 1. Design and implement a Java application to simulate a Hotel Room Service 
// Management System where multiple service requests are handled concurrently 
// using multithreading. 
// In a hotel, different room service tasks such as room cleaning, food delivery, and 
// maintenance may occur at the same time. To efficiently manage these tasks, the 
// application should create separate threads for each service request so that they can 
// execute concurrently rather than sequentially. 
// Create individual threads for different service operations using Java thread creation 
// techniques (Thread class or Runnable interface). Each thread should simulate a service 
// task by displaying status messages and pausing execution using the sleep() method to 
// represent processing time. 
// The main program should start multiple threads simultaneously and demonstrate 
// concurrent execution of hotel service tasks. 
class HotelService implements Runnable {

    private String serviceName;
    private int serviceTime;

    HotelService(String serviceName, int serviceTime) {
        this.serviceName = serviceName;
        this.serviceTime = serviceTime;
    }

    public void run() {
        try {
            System.out.println(serviceName + " started.");
            
            Thread.sleep(serviceTime);

            System.out.println(serviceName + " completed.");
        } catch (InterruptedException e) {
            System.out.println(serviceName + " interrupted.");
        }
    }
}

public class hbs
{
    public static void main(String args[])
    {
        HotelService clean = new HotelService("Room Cleaning", 3000);
        HotelService food = new HotelService("Food Delivery", 2000);
        HotelService maintenance = new HotelService("Maintenance", 4000);

        // Create threads for each service
        Thread t1 = new Thread(clean);
        Thread t2 = new Thread(food);
        Thread t3 = new Thread(maintenance);

        System.out.println("Hotel services started...\n");
        t1.start();
        t2.start();
        t3.start();

        System.out.println("\nMain thread continues execution...");
    }
}