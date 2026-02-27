// 4. Design a Java program that uses generic methods to manage an array of hotel rooms. 
// The program should be capable of storing and displaying arrays of different room 
// attributes. 
// • Create a generic method to print arrays 
// • Use it for: 
// o Room numbers array 
// o Room types array 
// o Room prices array 
// • Do not use collections framework 
import java.util.Scanner;

public class q4 {

    public static <T> void printArray(T[] arr) {
        for (T element : arr) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of rooms:");
        int n = sc.nextInt();
        sc.nextLine();  // consume newline

        // Room Numbers
        Integer[] roomNumbers = new Integer[n];
        System.out.println("Enter Room Numbers:");
        for (int i = 0; i < n; i++) {
            roomNumbers[i] = sc.nextInt();
        }

        sc.nextLine(); 

        String[] roomTypes = new String[n];
        System.out.println("Enter Room Types:");
        for (int i = 0; i < n; i++) {
            roomTypes[i] = sc.nextLine();
        }

        // Room Prices
        Double[] roomPrices = new Double[n];
        System.out.println("Enter Room Prices:");
        for (int i = 0; i < n; i++) {
            roomPrices[i] = sc.nextDouble();
        }

        System.out.println("\nRoom Numbers:");
        printArray(roomNumbers);

        System.out.println("Room Types:");
        printArray(roomTypes);

        System.out.println("Room Prices:");
        printArray(roomPrices);

        sc.close();
    }
}