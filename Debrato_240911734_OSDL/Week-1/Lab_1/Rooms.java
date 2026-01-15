// 4. Create an abstract class named Room that represents a generic hotel room. The abstract
// class should contain common data members such as room number and base price, and
// include an abstract method calculateTariff() that must be implemented by all
// subclasses. It may also include concrete methods such as displayRoomDetails().
// i. Create derived classes such as StandardRoom and LuxuryRoom that extend the abstract
// Room class and provide concrete implementations for the calculateTariff() method
// based on room-specific features.
// ii. Create an interface named Amenities that declares methods such as provideWifi()
// and provideBreakfast(). The derived room classes should implement this interface to
// define the amenities offered for each room type.
// iii. Create a main class to instantiate different room objects using a base class reference and
// invoke the implemented methods to demonstrate abstraction and interface-based design.
import java.util.*;
abstract class Room
{
    int rno;
    int base;
    public Room(int rno,int base)
    {
        this.rno=rno;
        this.base=base;
    }
    abstract int calculateTariff();
    public void displayRoomDetails()
    {
        System.out.println("Room Number: "+rno);
        System.out.println("Base Price: "+base);
    }
}
interface Amenities
{
    void provideWifi();
    void provideBreakfast();
}
class StandardRoom extends Room implements Amenities
{
    int ac;
    public StandardRoom(int rno,int base,int ac)
    {
        super(rno,base);
        this.ac=ac;
    }
    int calculateTariff()
    {
        if(ac==1)
            return base+500;
        else
            return base;
    }
    public void provideWifi()
    {
        System.out.println("WiFi: Available");
    }
    public void provideBreakfast()
    {
        System.out.println("Breakfast: Not Available");
    }
}
class LuxuryRoom extends Room implements Amenities
{
    int premium;
    public LuxuryRoom(int rno,int base,int premium)
    {
        super(rno,base);
        this.premium=premium;
    }
    int calculateTariff()
    {
        if(premium==1)
            return base+3000;
        else
            return base+2000;
    }
    public void provideWifi()
    {
        System.out.println("WiFi: Available");
    }
    public void provideBreakfast()
    {
        System.out.println("Breakfast: Available");
    }
}
class Main
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Standard or Luxury:");
        String type=sc.next();
        Room r;
        if(type.equalsIgnoreCase("standard"))
        {
            int rno=sc.nextInt();
            int base=sc.nextInt();
            int ac=sc.nextInt();
            r=new StandardRoom(rno,base,ac);
        }
        else
        {
            int rno=sc.nextInt();
            int base=sc.nextInt();
            int premium=sc.nextInt();
            r=new LuxuryRoom(rno,base,premium);
        }
        r.displayRoomDetails();
        System.out.println("Tariff: "+r.calculateTariff());
        Amenities a=(Amenities)r;
        a.provideWifi();
        a.provideBreakfast();
        sc.close();
    }
}
