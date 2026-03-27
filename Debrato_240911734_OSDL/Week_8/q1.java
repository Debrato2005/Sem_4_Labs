import java.util.*;

class Room {
    int number;
    String type;
    double price;
    boolean available;

    Room(int n, String t, double p) {
        number = n;
        type = t;
        price = p;
        available = true;
    }
}

class Customer {
    int id;
    String name;
    String contact;
    int roomNo;

    Customer(int i, String n, String c, int r) {
        id = i;
        name = n;
        contact = c;
        roomNo = r;
    }
}

public class Hotel {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static HashMap<Integer, Customer> map = new HashMap<>();

    static Room findRoom(int no) {
        for (Room r : rooms)
            if (r.number == no) return r;
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Room 2.View Rooms 3.Book 4.Checkout 5.Customers 6.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("RoomNo Type Price: ");
                    rooms.add(new Room(sc.nextInt(), sc.next(), sc.nextDouble()));
                    break;

                case 2:
                    Collections.sort(rooms, Comparator.comparingDouble(r -> r.price));
                    for (Room r : rooms)
                        if (r.available)
                            System.out.println(r.number + " " + r.type + " " + r.price);
                    break;

                case 3:
                    System.out.print("RoomNo: ");
                    int rn = sc.nextInt();
                    Room r = findRoom(rn);

                    if (r != null && r.available) {
                        System.out.print("CustID Name Contact: ");
                        Customer c = new Customer(sc.nextInt(), sc.next(), sc.next(), rn);
                        customers.add(c);
                        map.put(rn, c);
                        r.available = false;
                        System.out.println("Booked!");
                    } else System.out.println("Not Available");
                    break;

                case 4:
                    System.out.print("RoomNo: ");
                    int room = sc.nextInt();
                    if (map.containsKey(room)) {
                        map.remove(room);
                        findRoom(room).available = true;
                        System.out.println("Checked Out");
                    }
                    break;

                case 5:
                    Iterator<Customer> it = customers.iterator();
                    while (it.hasNext()) {
                        Customer c = it.next();
                        System.out.println(c.id + " " + c.name + " Room:" + c.roomNo);
                    }
                    break;

                case 6:
                    return;
            }
        }
    }
}