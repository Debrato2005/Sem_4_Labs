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
class room
{
    int rno;
    int base;
}
class standard extends room
{

}
class luxury extends room
{
    
}
public class hrbs 
{
    public static void main(String args[])
    {

    }
}
