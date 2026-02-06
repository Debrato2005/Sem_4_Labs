class Hotel {
    private int availableRooms;

    Hotel(int rooms) {
        this.availableRooms = rooms;
    }

    synchronized void bookRoom(String customerName) {
        while (availableRooms == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        availableRooms--;
        System.out.println(customerName + " booked a room. Available rooms: " + availableRooms);
    }

    synchronized void releaseRoom(String customerName) {
        availableRooms++;
        System.out.println(customerName + " released a room. Available rooms: " + availableRooms);
        notify();
    }
}

class Customer extends Thread {
    private Hotel hotel;
    private String customerName;

    Customer(Hotel hotel, String customerName) {
        this.hotel = hotel;
        this.customerName = customerName;
    }

    public void run() {
        hotel.bookRoom(customerName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        hotel.releaseRoom(customerName);
    }
}

public class HotelRoomBookingDemo {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(2);

        Customer c1 = new Customer(hotel, "Customer-1");
        Customer c2 = new Customer(hotel, "Customer-2");
        Customer c3 = new Customer(hotel, "Customer-3");
        Customer c4 = new Customer(hotel, "Customer-4");

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}
