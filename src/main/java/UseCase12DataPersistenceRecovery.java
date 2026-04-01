import java.io.*;
import java.util.*;

/**
 * Book My Stay App
 * Use Case 12 – Data Persistence & System Recovery
 * Version 12.1
 */

// Reservation Class (Serializable)
class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

// Inventory Class (Serializable)
class RoomInventory implements Serializable {

    Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // SAVE
    public void save(RoomInventory inventory, List<Reservation> history) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            oos.writeObject(history);

            System.out.println("\nData Saved Successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // LOAD
    public Object[] load() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            RoomInventory inventory = (RoomInventory) ois.readObject();
            List<Reservation> history = (List<Reservation>) ois.readObject();

            System.out.println("\nData Loaded Successfully!");

            return new Object[]{inventory, history};

        } catch (Exception e) {

            System.out.println("\nNo previous data found. Starting fresh...");

            return null;
        }
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v12.1 =====");

        PersistenceService service = new PersistenceService();

        // Try loading previous state
        Object[] data = service.load();

        RoomInventory inventory;
        List<Reservation> history;

        if (data != null) {
            inventory = (RoomInventory) data[0];
            history = (List<Reservation>) data[1];
        } else {
            // Fresh start
            inventory = new RoomInventory();
            history = new ArrayList<>();

            history.add(new Reservation("RES101", "Alice", "Single Room"));
            history.add(new Reservation("RES102", "Bob", "Double Room"));
        }

        // Display data
        inventory.display();

        System.out.println("\nBooking History:");
        for (Reservation r : history) {
            r.display();
        }

        // Save state before exit
        service.save(inventory, history);
    }
}