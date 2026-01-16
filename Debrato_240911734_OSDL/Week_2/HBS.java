// 1. The Hotel Billing system should calculate the total bill amount for hotel guests based on 
// room charges and additional service charges. Store numeric values such as room tariff, 
// number of days stayed, and service charges using wrapper class objects (Integer, 
// Double) instead of primitive data types. 
// Demonstrate autoboxing by automatically converting primitive values to wrapper class 
// objects when assigning values or storing them in collections. Demonstrate unboxing by 
// automatically converting wrapper class objects back to primitive types while performing 
// arithmetic operations for bill calculation.
import java.util.*;
public class HBS
{
int rtar;
int days;
int sercha;
int total=0;
Integer rtarobj;
Integer daysobj;
Integer serchaobj;

public HBS(int rtar, int days, int sercha)
{
    this.rtar=rtar;
    this.days=days;
    this.sercha=sercha;

}
void input(Scanner sc)
{
    System.out.println("Enter the room tariff:");
    rtar=sc.nextInt();
    System.out.println("Enter the no. of days:");
    days=sc.nextInt();
    System.out.println("Enter the service charges:");
    sercha=sc.nextInt();
}

void autobox()
{
    this.rtarobj=rtar;
    this.daysobj=days;
    this.serchaobj=sercha;
    System.out.println("Primitive values: ");
    System.out.println("the room tariff :"+rtar);
    System.out.println("the no. of days: "+days);
    System.out.println("the service charges: "+ sercha);
    
    System.out.println("AfterAutoboxing:");
    System.out.println("the room tariff obj:"+rtarobj);
    System.out.println("the no. of days obj: "+daysobj);
    System.out.println("the service charges obj: "+ serchaobj);
}
    void calculate()
    {
        total=daysobj*(rtarobj+serchaobj);
        System.out.println("the total is: "+ total);
    }
}
class Main
{
     public static void main(String []args)
     {
        Scanner sc=new Scanner(System.in);
        System.out.println("no. of rooms: ");
        int n=sc.nextInt();
        HBS hbs[]=new HBS[n];
        for (int i=0; i<n;i++)
        {
            hbs[i]=new HBS(0,0,0);
            hbs[i].input(sc);
            hbs[i].autobox();
            hbs[i].calculate();
        }
    }
}
