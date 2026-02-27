// 1. Develop a Java application that uses a generic class with two type parameters 
// to store hotel room information. The generic class should be capable of holding 
// different data types for room identifiers and room attributes. 
// • Create a generic class Room<T, U> 
// • T represents Room Number or Room ID 
// • U represents Room Type or Price 
// • Demonstrate usage with different data types (e.g., Integer, String, Double) 
// • Display stored room details 
import java.util.Scanner;
class Room<t, u>
{
   private t rid;
   private u rtpr;
   public Room(t rid, u rtpr )
   {
    this.rid=rid;
    this.rtpr=rtpr;
   }
   public t getrid()
   {
    return rid;
   }
   public u getrtpr()
   {
    return rtpr;
   }
   public void display()
   {
     System.out.println("RID: " + rid); 
     System.out.println("RTPR: " + rtpr);
   }
}
public class q1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // First Room: Integer + String
        System.out.println("Enter Room Number:");
        Integer roomNumber = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Enter Room Type:");
        String roomType = sc.nextLine();

        Room<Integer, String> room1 = new Room<>(roomNumber, roomType);

        // Second Room: String + Double
        System.out.println("Enter Room ID:");
        String roomId = sc.nextLine();

        System.out.println("Enter Room Price:");
        Double price = sc.nextDouble();

        Room<String, Double> room2 = new Room<>(roomId, price);

        System.out.println("\nStored Room Details:");
        room1.display();
        room2.display();

        sc.close();
    }
}