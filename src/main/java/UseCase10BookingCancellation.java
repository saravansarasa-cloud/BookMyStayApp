import java.util.*;

/**
 * Book My Stay App
 * Use Case 10 – Booking Cancellation & Inventory Rollback
 * Version 10.1
 */

// Reservation Class
class Reservation {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Inventory Service
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

// Cancellation Service
class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();
    private Map<String, Reservation> confirmedBookings = new HashMap<>();

    // Simulate confirmed bookings
    public void addBooking(Reservation r) {
        confirmedBookings.put(r.reservationId, r);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        System.out.println("\nProcessing cancellation for: " + reservationId);

        // Validation
        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed → Reservation not found");
            return;
        }

        // Get reservation
        Reservation r = confirmedBookings.get(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increaseAvailability(r.roomType);

        // Remove booking
        confirmedBookings.remove(reservationId);

        System.out.println("Cancellation Successful → " + reservationId);
    }

    // Display rollback history
    public void displayRollbackHistory() {
        System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v10.1 =====");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Assume confirmed bookings
        service.addBooking(new Reservation("RES101", "Single Room"));
        service.addBooking(new Reservation("RES102", "Double Room"));

        // Perform cancellation
        service.cancelBooking("RES101", inventory);
        service.cancelBooking("RES999", inventory); // invalid case

        // Display results
        inventory.displayInventory();
        service.displayRollbackHistory();
    }
}