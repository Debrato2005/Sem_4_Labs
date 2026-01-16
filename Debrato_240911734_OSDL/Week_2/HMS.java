// 2. Design and implement a Java application to manage room tariff details in a Hotel 
// Management System using Java enumerations (enum). The application should 
// demonstrate the use of enum constants, enum constructors, and enum methods. 
// i. 
// ii. 
// Define an enum named RoomType to represent different types of hotel rooms such as 
// STANDARD, DELUXE, and SUITE. Each enum constant should be associated with a base 
// tariff value using an enum constructor. The enum should also include methods to return 
// the base tariff and to calculate the total room cost based on the number of days stayed. 
// Create a main class to select a room type, specify the number of days of stay, and 
// compute the total room tariff by invoking the enum methods. The application should 
// clearly illustrate how enum constructors are used to initialize constant-specific data and 
// how enum methods operate on that data. 
import java.util.*;
public class HMS
{
    enum rtype
    {
        standard(10), deluxe(20), suite(30);
        private int tariff;
        rtype(int tariff)
        {
            this.tariff=tariff;
        }
        public int gettariff()
    {
        return tariff;
    }
    public int calculate(int days)
    {
        return tariff*days;
    }
}
    
    
}
class Main
{
     public static void main(String []args)
     {
        Scanner sc=new Scanner(System.in);
        System.out.println("standard, deluxe, suite");
            String input = sc.next().toLowerCase();
            HMS.rtype choice = HMS.rtype.valueOf(input);
            System.out.println("Enter number of days:");
            int days = sc.nextInt();
            System.out.println("Room Type: " + choice);
            System.out.println("Base Tariff: " + choice.gettariff());
            System.out.println("Total Cost: " + choice.calculate(days));
            sc.close();
     }
    }
