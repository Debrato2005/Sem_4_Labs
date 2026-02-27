// 2. Create a Java program for a hotel room management system that uses a generic 
// method to display room-related data of different types such as room numbers, 
// room types, prices, and booking status. 
// • Implement a generic method <T> void display(T data) 
// • Call the method with: 
// 1. Room number (Integer) 
// 2. Room type (String) 
// 3. Price per night (Double) 
// 4. Booking status (Boolean) 
// • Ensure type safety without explicit casting
import java.util.*;
public class q2{

    public static <T> void display(T data) {
        System.out.println("Room Data: " + data);
    }

    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);

        System.out.println("Enter Room Number:");
        Integer roomNumber = sc.nextInt();
        sc.nextLine();   // consume leftover newline

        System.out.println("Enter Room Type:");
        String roomType = sc.nextLine();

        System.out.println("Enter Price per Night:");
        Double price = sc.nextDouble();

        System.out.println("Is Room Booked? (true/false):");
        Boolean bookingStatus = sc.nextBoolean();

        System.out.println("\nDisplaying Room Details:\n");

        display(roomNumber);
        display(roomType);
        display(price);
        display(bookingStatus);

        sc.close();
    }
}