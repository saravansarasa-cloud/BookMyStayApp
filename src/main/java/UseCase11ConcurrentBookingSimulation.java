import java.util.*;

/**
 * Book My Stay App
 * Use Case 11 – Concurrent Booking Simulation
 * Version 11.1
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

// Thread-Safe Inventory
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
    }

    // synchronized method → critical section
    public synchronized boolean bookRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {

            System.out.println(Thread.currentThread().getName() +
                    " booking " + roomType);

            inventory.put(roomType, available - 1);

            return true;
        }

        return false;
    }
}

// Booking Task (Thread)
class BookingTask implements Runnable {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public BookingTask(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (true) {

            Reservation request;

            // synchronized queue access
            synchronized (queue) {
                if (queue.isEmpty()) return;
                request = queue.poll();
            }

            boolean success = inventory.bookRoom(request.roomType);

            if (success) {
                System.out.println("Success → " + request.guestName);
            } else {
                System.out.println("Failed → " + request.guestName);
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v11.1 =====");

        // Shared Queue
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));

        // Shared Inventory
        RoomInventory inventory = new RoomInventory();

        // Multiple threads (simulate users)
        Thread t1 = new Thread(new BookingTask(queue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingTask(queue, inventory), "Thread-2");

        // Start threads
        t1.start();
        t2.start();
    }
}