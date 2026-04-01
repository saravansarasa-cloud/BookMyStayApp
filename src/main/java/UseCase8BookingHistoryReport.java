import java.util.*;

/**
 * Book My Stay App
 * Use Case 8 – Booking History & Reporting
 * Version 8.1
 */

// Reservation Class
class Reservation {

    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// Booking History
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // Add booking to history
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all bookings
    public List<Reservation> getHistory() {
        return history;
    }
}

// Report Service
class BookingReportService {

    public void displayAllBookings(List<Reservation> history) {

        System.out.println("\n===== Booking History =====");

        for (Reservation r : history) {
            r.display();
        }
    }

    public void totalBookings(List<Reservation> history) {
        System.out.println("\nTotal Bookings: " + history.size());
    }
}

// Main Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v8.1 =====");

        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        // Assume confirmed bookings from UC6
        history.addReservation(new Reservation("RES101", "Alice", "Single Room"));
        history.addReservation(new Reservation("RES102", "Bob", "Double Room"));
        history.addReservation(new Reservation("RES103", "Charlie", "Suite Room"));

        // Display report
        report.displayAllBookings(history.getHistory());
        report.totalBookings(history.getHistory());
    }
}