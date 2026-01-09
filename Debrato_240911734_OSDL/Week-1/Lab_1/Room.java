// 2. Create a base class named Room to represent general room details in a hotel. The
// class should contain data members such as room number, room type, and base
// price. Implement multiple constructors (constructor overloading) in the Room
// class to initialize room objects in different ways, such as:
// i. Initializing only the room number and type
// ii. Initializing room number, type, and base price
// iii. Create a derived class named DeluxeRoom that inherits from the Room class
// using single inheritance. The derived class should include additional data
// members such as free Wi-Fi availability and complimentary breakfast.
// Implement appropriate constructors in the derived class that invoke the base
// class constructors using the super keyword.
// iv. Create a main class to instantiate objects of both Room and DeluxeRoom using
// different constructors and display the room details. This application should
// clearly illustrate constructor overloading and inheritance.
import java.util.*;
public class Room 
{
    int rno;
    String rtype;
    int base;
    public  Room(int rno, String rtype)
    {
        this.rno=rno;
        this.rtype=rtype;
    }
    public  Room(int rno, String rtype, int base)
    {
        this.rno=rno;
        this.rtype=rtype;
        this.base=base;
    }// part 1 and 2
}
class DeluxeRoom extends Room
{
     int wifi;
     int cbf;

    public DeluxeRoom(int rno, String rtype, int wifi, int cbf) {
    super(rno, rtype);  
    this.wifi = wifi;
    this.cbf = cbf;
}

    public DeluxeRoom(int rno, String rtype, int base, int wifi, int cbf)
    {
        super(rno,rtype,base);
        this.wifi=wifi;
        this.cbf=cbf;//3rd part
    }
}
class Main
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of rooms:");
        int n=sc.nextInt();
        Room rooms[]=new Room[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Normal or Deluxe:");
            String st1=sc.next();
            if (st1.equalsIgnoreCase("normal"))
            {
                System.out.println("With base price or not:");
                String st2=sc.next();
                if (st2.equalsIgnoreCase("no"))
                {
                    System.out.println("Enter rno, rtype:");
                    int rno=sc.nextInt();
                    String rtype=sc.next();
                    rooms[i]=new Room(rno,rtype);
                }
                else if (st2.equalsIgnoreCase("yes"))
                {
                    System.out.println("Enter rno, rtype, base price:");
                    int rno=sc.nextInt();
                    String rtype=sc.next();
                    int base=sc.nextInt();
                    rooms[i]=new Room(rno,rtype,base);
                }
            }
            else if (st1.equalsIgnoreCase("deluxe"))                
            {
                System.out.println("With base price or not:");
                String st2 = sc.next();

                System.out.println("Enter wifi (1/0) and breakfast (1/0):");
                int wifi = sc.nextInt();
                int cbf  = sc.nextInt();

                if (st2.equalsIgnoreCase("no"))
                {
                    System.out.println("Enter rno and rtype:");
                    int rno = sc.nextInt();
                    String rtype = sc.next();

                    rooms[i] = new DeluxeRoom(rno, rtype, wifi, cbf);
                }
                else if (st2.equalsIgnoreCase("yes"))
                {
                    System.out.println("Enter rno, rtype and base price:");
                    int rno = sc.nextInt();
                    String rtype = sc.next();
                    int base = sc.nextInt();
                    rooms[i] = new DeluxeRoom(rno, rtype, base, wifi, cbf);
                }
            }
        }
        System.out.println("\nRoom Details:");
        for (int i = 0; i < n; i++) {
        System.out.println("Room " + (i + 1) + ":");
        System.out.println("Room Number: " + rooms[i].rno);
        System.out.println("Room Type: " + rooms[i].rtype);

        if (rooms[i].base != 0) {
        System.out.println("Base Price: " + rooms[i].base);
        }   
        else {
        System.out.println("Base Price: Not specified");
        }
        if (rooms[i] instanceof DeluxeRoom) {
        DeluxeRoom d = (DeluxeRoom) rooms[i];
        System.out.println("Free Wi-Fi: " + (d.wifi));
        System.out.println("Complimentary Breakfast: " + (d.cbf));
        }
        System.out.println();
    }
    sc.close();
}
}
