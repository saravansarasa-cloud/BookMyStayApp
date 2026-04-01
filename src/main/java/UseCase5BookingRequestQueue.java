import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 * Use Case 5 – Booking Request Queue (FIFO)
 * Version 5.1
 */

// Reservation Class
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v5.1 =====");

        // Create Queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests (FIFO)
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));

        System.out.println("\nBooking Requests in Queue:");

        // Display queue (order preserved)
        for (Reservation r : bookingQueue) {
            r.display();
        }

        System.out.println("\nQueue Size: " + bookingQueue.size());
    }
}