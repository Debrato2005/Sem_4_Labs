// 5. Develop a hotel room booking module that uses a generic pair class to associate room 
// numbers with guest details. 
// • Create a generic class Pair<T, U> 
// • Store: 
// o Room Number (Integer) 
// o Guest Name (String) 
// • Display booking records 
// • Ensure no type casting is required 
import java.util.Scanner;

// Generic Pair Class
class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void display() {
        System.out.println("Room Number: " + first);
        System.out.println("Guest Name : " + second);
        System.out.println("--------------------------");
    }
}

public class q5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of bookings:");
        int n = sc.nextInt();
        sc.nextLine();   // consume newline

        Pair<Integer, String>[] bookings = new Pair[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nBooking " + (i + 1));

            System.out.println("Enter Room Number:");
            int roomNumber = sc.nextInt();
            sc.nextLine();   // consume newline

            System.out.println("Enter Guest Name:");
            String guestName = sc.nextLine();

            bookings[i] = new Pair<>(roomNumber, guestName);
        }


        for (Pair<Integer, String> booking : bookings) {
            booking.display();
        }

        sc.close();
    }
}