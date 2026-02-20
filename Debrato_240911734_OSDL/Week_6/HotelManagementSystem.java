// 1. Design and implement a Java application to manage hotel room bookings where 
// room records are stored in a file and accessed using RandomAccessFile. Each room 
// record should be of fixed length, enabling direct (random) access to any room’s 
// booking information without reading the file sequentially. 
// The system must support operations such as adding rooms, viewing room details, 
// and updating booking status by directly navigating to the required record position in 
// the file. 
// i. hotel room details in a file using RandomAccessFile. 
// ii. Each room record must contain: 
// iii. Room Number (int) 
// iv. Room Type (fixed-length String, e.g., 20 characters) 
// v. Price per Night (double) 
// vi. Booking Status (boolean) 
// vii. Provide an option to: 
// viii. Add new room records 
// ix. Display details of a specific room using its room number 
// x. Update booking status (book / vacate a room) 
// xi. Use the seek() method to jump directly to the position of a room record. 
// xii. Ensure data is read and written in the same sequence and format. 
// xiii. Close the file after each operation.
import java.io.*;
import java.util.*;

public class HotelManagementSystem {

    static final String fname = "hotel.dat";
    static final int rsize = 4 + (20 * 2) + 8 + 1;

    public static void addRoom(int roomno, String roomtype, double price) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fname, "rw");

            long posn = (roomno - 1) * rsize;
            raf.seek(posn);

            raf.writeInt(roomno);

            StringBuilder sb = new StringBuilder(roomtype);
            sb.setLength(20);
            raf.writeChars(sb.toString());

            raf.writeDouble(price);
            raf.writeBoolean(false);

            raf.close();
            System.out.println("Room added successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewRoom(int roomno) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fname, "r");

            long posn = (roomno - 1) * rsize;

            if (posn >= raf.length()) {
                System.out.println("Room not found.");
                raf.close();
                return;
            }

            raf.seek(posn);

            int rno = raf.readInt();

            char[] type = new char[20];
            for (int i = 0; i < 20; i++)
                type[i] = raf.readChar();

            double price = raf.readDouble();
            boolean booked = raf.readBoolean();

            System.out.println("\nRoom Number: " + rno);
            System.out.println("Room Type: " + new String(type).trim());
            System.out.println("Price per Night: " + price);
            System.out.println("Booking Status: " + (booked ? "Booked" : "Available"));

            raf.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateBooking(int roomno, boolean status) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fname, "rw");

            long posn = (roomno - 1) * rsize;

            if (posn >= raf.length()) {
                System.out.println("Room not found.");
                raf.close();
                return;
            }

            raf.seek(posn);

            raf.readInt();               // skip room number
            raf.skipBytes(20 * 2);       // skip room type
            raf.readDouble();            // skip price

            raf.writeBoolean(status);    // update booking status

            raf.close();
            System.out.println("Booking status updated successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== HOTEL ROOM MANAGEMENT ====");
            System.out.println("1. Add Room");
            System.out.println("2. View Room");
            System.out.println("3. Update Booking Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Room Number: ");
                    int roomNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Room Type: ");
                    String type = sc.nextLine();

                    System.out.print("Enter Price per Night: ");
                    double price = sc.nextDouble();

                    addRoom(roomNo, type, price);
                    break;

                case 2:
                    System.out.print("Enter Room Number: ");
                    int viewNo = sc.nextInt();
                    viewRoom(viewNo);
                    break;

                case 3:
                    System.out.print("Enter Room Number: ");
                    int updateNo = sc.nextInt();

                    System.out.print("Enter true to Book / false to Vacate: ");
                    boolean status = sc.nextBoolean();

                    updateBooking(updateNo, status);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}