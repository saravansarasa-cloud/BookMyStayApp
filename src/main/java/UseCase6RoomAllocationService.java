import java.util.*;

/**
 * Book My Stay App
 * Use Case 6 – Reservation Confirmation & Room Allocation
 * Version 6.1
 */

// Reservation Class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Service
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// Booking Service
class BookingService {

    private Set<String> allocatedRooms = new HashSet<>();
    private HashMap<String, Set<String>> roomAllocationMap = new HashMap<>();
    private int roomCounter = 101;

    public void processBookings(Queue<Reservation> queue, RoomInventory inventory) {

        System.out.println("\n===== Processing Bookings =====");

        while (!queue.isEmpty()) {

            Reservation request = queue.poll(); // FIFO

            String roomType = request.roomType;

            if (inventory.getAvailability(roomType) > 0) {

                // Generate unique room ID
                String roomId = roomType.substring(0, 2).toUpperCase() + roomCounter++;

                // Ensure uniqueness
                allocatedRooms.add(roomId);

                // Map room type to assigned IDs
                roomAllocationMap.putIfAbsent(roomType, new HashSet<>());
                roomAllocationMap.get(roomType).add(roomId);

                // Update inventory
                inventory.reduceAvailability(roomType);

                System.out.println("Booking Confirmed → Guest: " + request.guestName +
                        " | Room: " + roomType +
                        " | Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed → Guest: " + request.guestName +
                        " | No rooms available for " + roomType);
            }
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v6.1 =====");

        // Queue from UC5
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room")); // should fail
        queue.add(new Reservation("David", "Suite Room"));

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Booking Service
        BookingService service = new BookingService();

        // Process bookings
        service.processBookings(queue, inventory);
    }
}