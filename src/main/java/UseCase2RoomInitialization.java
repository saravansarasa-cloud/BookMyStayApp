/**
 * Book My Stay App
 * Use Case 2 – Basic Room Types & Static Availability
 * Version 2.1
 */

// Abstract Class
abstract class Room {

    protected String roomType;
    protected int price;
    protected int beds;

    public Room(String roomType, int price, int beds) {
        this.roomType = roomType;
        this.price = price;
        this.beds = beds;
    }

    public void displayDetails(int availability) {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price: " + price);
        System.out.println("Beds: " + beds);
        System.out.println("Available Rooms: " + availability);
        System.out.println("----------------------------");
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1000, 1);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2000, 2);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000, 3);
    }
}

// Main Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v2.1 =====");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability (simple variables)
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        single.displayDetails(singleAvailable);
        dbl.displayDetails(doubleAvailable);
        suite.displayDetails(suiteAvailable);
    }
}