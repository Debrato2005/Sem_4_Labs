// 3. Develop a Java application that uses bounded type parameters to calculate room 
// charges. The application should accept only numeric values for pricing and discount 
// calculations. 
// • Create a generic class or method using <T extends Number> 
// • Accept room price and discount as generic parameters 
// • Perform calculations such as: 
// o Total price 
// o Discounted price 
// • Prevent non-numeric data types at compile time
import java.util.Scanner;

class RoomCharges<T extends Number> {

    private T price;
    private T discount;

    public RoomCharges(T price, T discount) {
        this.price = price;
        this.discount = discount;
    }

    public double calculateTotalPrice() {
        return price.doubleValue();
    }

    public double calculateDiscountedPrice() {
        return price.doubleValue() - discount.doubleValue();
    }

    public void displayCharges() {
        System.out.println("Original Price: " + price);
        System.out.println("Discount: " + discount);
        System.out.println("Total Price: " + calculateTotalPrice());
        System.out.println("Discounted Price: " + calculateDiscountedPrice());
    }
}

public class q3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Room Price:");
        double price = sc.nextDouble();

        System.out.println("Enter Discount Amount:");
        double discount = sc.nextDouble();

        // Using Double as numeric type
        RoomCharges<Double> room = new RoomCharges<>(price, discount);

        room.displayCharges();

        sc.close();
    }
}