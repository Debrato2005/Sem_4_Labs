import java.io.Serializable;
import java.io.*;
import java.util.*;
class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    int roomNumber;
    String roomType;
    double pricePerNight;
    boolean bookingStatus;
    String guestName;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.bookingStatus = false;
        this.guestName = "";
    }

    public void display() {
        System.out.println("--------------------------------");
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per Night: " + pricePerNight);
        System.out.println("Booking Status: " + (bookingStatus ? "Booked" : "Available"));
        System.out.println("Guest Name: " + (guestName.isEmpty() ? "None" : guestName));
    }
}
public class HotelBookingSerialization {

    static final String FILE_NAME = "rooms.ser";

    // Load rooms from file
    public static ArrayList<Room> loadRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(FILE_NAME));

            rooms = (ArrayList<Room>) ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            // File not created yet (first run)
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return rooms;
    }

    // Save rooms to file
    public static void saveRooms(ArrayList<Room> rooms) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME));

            oos.writeObject(rooms);
            oos.close();

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Add Room
    public static void addRoom() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Room> rooms = loadRooms();

        System.out.print("Enter Room Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Room Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Price per Night: ");
        double price = sc.nextDouble();

        rooms.add(new Room(number, type, price));

        saveRooms(rooms);
        System.out.println("Room added successfully.");
    }

    // Display All Rooms
    public static void displayAllRooms() {
        ArrayList<Room> rooms = loadRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        for (Room r : rooms) {
            r.display();
        }
    }

    // Search Room
    public static void searchRoom(int roomNumber) {
        ArrayList<Room> rooms = loadRooms();

        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                r.display();
                return;
            }
        }

        System.out.println("Room not found.");
    }

    // Update Booking
    public static void updateBooking(int roomNumber, boolean status, String guestName) {
        ArrayList<Room> rooms = loadRooms();

        boolean found = false;

        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                r.bookingStatus = status;

                if (status)
                    r.guestName = guestName;
                else
                    r.guestName = "";

                found = true;
                break;
            }
        }

        if (found) {
            saveRooms(rooms);
            System.out.println("Booking updated successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // Main Menu
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== HOTEL BOOKING SYSTEM (SERIALIZATION) ====");
            System.out.println("1. Add Room");
            System.out.println("2. Display All Rooms");
            System.out.println("3. Search Room");
            System.out.println("4. Update Booking");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addRoom();
                    break;

                case 2:
                    displayAllRooms();
                    break;

                case 3:
                    System.out.print("Enter Room Number: ");
                    int searchNo = sc.nextInt();
                    searchRoom(searchNo);
                    break;

                case 4:
                    System.out.print("Enter Room Number: ");
                    int updateNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter true to Book / false to Vacate: ");
                    boolean status = sc.nextBoolean();
                    sc.nextLine();

                    String guest = "";
                    if (status) {
                        System.out.print("Enter Guest Name: ");
                        guest = sc.nextLine();
                    }

                    updateBooking(updateNo, status, guest);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}