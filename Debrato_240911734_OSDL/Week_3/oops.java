// 2. Design and implement a Java application to simulate an Online Order 
// Processing System where multiple customer orders are processed simultaneously 
// using multithreading. 
// In an e-commerce platform, several operations such as order validation, payment 
// processing, and order shipment must be handled concurrently for different customers. 
// To improve system performance and responsiveness, each order processing task should 
// be executed in a separate thread. 
// Create individual threads for handling different customer orders or different stages of 
// order processing. Each thread should simulate processing by displaying status messages 
// and using the sleep() method to represent time-consuming operations. 
// The main program should start multiple threads at the same time and demonstrate 
// concurrent execution of order-related tasks. 

class service implements Runnable {

    private String serviceName;
    private int serviceTime;

    service(String serviceName, int serviceTime) {
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

public class oops
{
    public static void main(String args[])
    {
        service order = new service("order", 3000);
        service payment = new service("paymenty", 2000);
        service shipment = new service("shipment", 4000);

        // Create threads for each service
        Thread t1 = new Thread(order);
        Thread t2 = new Thread(payment);
        Thread t3 = new Thread(shipment);

        System.out.println("Online services started...\n");
        t1.start();
        t2.start();
        t3.start();

        System.out.println("\nMain thread continues execution...");
    }
}