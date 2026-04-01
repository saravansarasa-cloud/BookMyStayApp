import java.util.*;

/**
 * Book My Stay App
 * Use Case 9 – Error Handling & Validation
 * Version 9.1
 */

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Class
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, -1);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// Validator Class
class BookingValidator {

    public void validate(String roomType, RoomInventory inventory) throws InvalidBookingException {

        // Check valid room type
        if (inventory.getAvailability(roomType) == -1) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        // Check availability
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v9.1 =====");

        RoomInventory inventory = new RoomInventory();
        BookingValidator validator = new BookingValidator();

        String[] requests = {
                "Single Room",
                "Suite Room",      // invalid
                "Single Room"      // no availability after first booking
        };

        for (String roomType : requests) {

            try {
                validator.validate(roomType, inventory);

                // If valid → proceed booking
                inventory.reduceAvailability(roomType);

                System.out.println("Booking Successful for: " + roomType);

            } catch (InvalidBookingException e) {

                System.out.println("Booking Failed → " + e.getMessage());
            }
        }
    }
}