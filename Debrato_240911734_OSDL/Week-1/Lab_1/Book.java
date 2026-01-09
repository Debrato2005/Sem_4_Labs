// 1. Create a Book class with private data members including book ID, book title,
// author name, price, and availability status. Provide public setter methods to assign
// values to these data members and public getter methods to retrieve their values.
// Include validation in setter methods to ensure that the price is a positive value.
import java.util.*;
public class Book
{
    private int bid;
    private String title;cle
    private String aname; 
    private int price;
    private int avstat;

    public void setbid(int bid) 
    {
    this.bid = bid; 
    }
    public void settitle(String title) 
    {
    this.title = title;
    }
    public void setaname(String aname) 
    {
    this.aname = aname;
    }
    public void setprice(int price) 
    {
        if(price>0)
        {
            this.price = price;
        }
        else
            {
                System.out.println("Price should be greater than zero...");
            } 
    }
    public void setavstat(int avstat) 
    {
    this.avstat = avstat; 
    }

    public int getbid() 
    {
        return bid; 
    }
    public String gettitle() 
    {
        return title;
    }
    public String getaname() 
    {
        return aname;
    }
    public int getprice() 
    {
        return price;
    }
    public int getavstat() 
    {
        return avstat;
    }
}
class Main
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of books:");
        int n=sc.nextInt();
        Book books[]=new Book[n];
        for(int i=0;i<n;i++)
        {
            books[i] = new Book();
            System.out.println("Enter details of the book:");
            books[i].setbid(sc.nextInt());
            books[i].settitle(sc.next());
            books[i].setaname(sc.next());
            books[i].setprice(sc.nextInt());
            books[i].setavstat(sc.nextInt());
        }
        System.out.println("Output details of the book:");
        for(int i=0;i<n;i++)
        {
            System.out.println("Book " + (i+1) + " :");
            System.out.println("Book ID: " + books[i].getbid());
            System.out.println("Book Title: " + books[i].gettitle());
            System.out.println("Author Name: " + books[i].getaname());
            System.out.println("Price: " + books[i].getprice());
            System.out.println("Availability Status: " + books[i].getavstat());
            System.out.println();
        }
        sc.close();

    }
}