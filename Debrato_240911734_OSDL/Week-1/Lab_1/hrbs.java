// 3. Design and implement a Java application to simulate a Hotel Room Booking System
// that demonstrates the object-oriented concepts of inheritance and runtime
// polymorphism.
// i. Create a base class named Room that represents a general hotel room. The class should
// contain data members such as room number and base tariff, and a method
// calculateTariff() to compute the room cost.
// ii. Create derived classes such as StandardRoom and LuxuryRoom that inherit from the
// Room class. Each derived class should override the calculateTariff() method to
// compute the tariff based on room-specific features such as air conditioning, additional
// amenities, or premium services.
// iii. In the main class, create a base class reference of type Room and assign it to objects of
// different derived classes (StandardRoom, LuxuryRoom). Invoke the
// calculateTariff() method using the base class reference to demonstrate runtime
// polymorphism, where the method call is resolved at runtime based on the actual
// object type.
import java.util.*;
class Room
{
    int rno;
    int base;
    public Room(int rno,int base)
    {
        this.rno=rno;
        this.base=base;
    }
    public int calculateTariff()
    {
        return base;
    }
}
class StandardRoom extends Room
{
    int ac;
    public StandardRoom(int rno,int base,int ac)
    {
        super(rno,base);
        this.ac=ac;
    }
    public int calculateTariff()
    {
        if(ac==1)
            return base+500;
        else
            return base;
    }
}
class LuxuryRoom extends Room
{
    int amenities;
    public LuxuryRoom(int rno,int base,int amenities)
    {
        super(rno,base);
        this.amenities=amenities;
    }
    public int calculateTariff()
    {
        if(amenities==1)
            return base+2000;
        else
            return base+1000;
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
            int amenities=sc.nextInt();
            r=new LuxuryRoom(rno,base,amenities);
        }
        System.out.println("Room Number: "+r.rno);
        System.out.println("Tariff: "+r.calculateTariff());
        sc.close();
    }
}
