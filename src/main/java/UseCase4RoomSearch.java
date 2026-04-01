import java.util.HashMap;

/**
 * Book My Stay App
 * Use Case 4 – Room Search & Availability Check
 * Version 4.1
 */

// Room Class
class Room {
    String type;
    int price;

    public Room(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public void display(int available) {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Available: " + available);
        System.out.println("------------------------");
    }
}

// Inventory Class (same idea from UC3)
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service
class RoomSearchService {

    public void search(RoomInventory inventory) {

        System.out.println("\n===== Available Rooms =====");

        // Room objects
        Room single = new Room("Single Room", 1000);
        Room dbl = new Room("Double Room", 2000);
        Room suite = new Room("Suite Room", 5000);

        // Check availability (READ ONLY)
        if (inventory.getAvailability("Single Room") > 0) {
            single.display(inventory.getAvailability("Single Room"));
        }

        if (inventory.getAvailability("Double Room") > 0) {
            dbl.display(inventory.getAvailability("Double Room"));
        }

        if (inventory.getAvailability("Suite Room") > 0) {
            suite.display(inventory.getAvailability("Suite Room"));
        }
    }
}

// Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v4.1 =====");

        RoomInventory inventory = new RoomInventory();
        RoomSearchService service = new RoomSearchService();

        // Perform search
        service.search(inventory);
    }
}