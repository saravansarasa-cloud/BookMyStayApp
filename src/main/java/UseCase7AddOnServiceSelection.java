import java.util.*;

/**
 * Book My Stay App
 * Use Case 7 – Add-On Service Selection
 * Version 7.1
 */

// Service Class
class Service {
    String name;
    int cost;

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Display services + total cost
    public void displayServices(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services for reservation " + reservationId);
            return;
        }

        int total = 0;

        System.out.println("\nServices for Reservation: " + reservationId);

        for (Service s : services) {
            System.out.println("- " + s.name + " : ₹" + s.cost);
            total += s.cost;
        }

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v7.1 =====");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Assume reservation IDs from UC6
        String res1 = "RES101";
        String res2 = "RES102";

        // Add services
        manager.addService(res1, new Service("Breakfast", 200));
        manager.addService(res1, new Service("Airport Pickup", 500));

        manager.addService(res2, new Service("Extra Bed", 300));

        // Display services
        manager.displayServices(res1);
        manager.displayServices(res2);
    }
}