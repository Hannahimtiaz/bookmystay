import java.util.HashMap;
import java.util.Map;

// Custom Exception for Booking Errors
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class BookMyStay {
    // Inventory management using a Map
    private static Map<String, Integer> roomInventory = new HashMap<>();

    static {
        // Initializing inventory
        roomInventory.put("Standard", 5);
        roomInventory.put("Deluxe", 3);
        roomInventory.put("Suite", 2);
    }

    public static void main(String[] args) {
        System.out.println("--- Welcome to Book My Stay App ---");

        // Test Cases: Valid and Invalid Scenarios
        processBooking("Alice", "Deluxe", 2);  // Valid
        processBooking("Bob", "Penthouse", 1); // Invalid Room Type
        processBooking("Charlie", "Suite", 5); // Insufficient Inventory
        processBooking("Dave", "Standard", 3); // Valid

        System.out.println("\nFinal Inventory State: " + roomInventory);
    }

    /**
     * Core booking logic with validation and error handling
     */
    public static void processBooking(String guestName, String roomType, int quantity) {
        try {
            System.out.println("\nProcessing booking for: " + guestName + " (" + quantity + " " + roomType + ")");

            // 1. Validate Room Type (Fail-Fast)
            if (!roomInventory.containsKey(roomType)) {
                throw new InvalidBookingException("Error: Room type '" + roomType + "' does not exist.");
            }

            // 2. Validate Inventory Levels
            int availableRooms = roomInventory.get(roomType);
            if (quantity > availableRooms) {
                throw new InvalidBookingException("Error: Insufficient inventory for " + roomType +
                        ". Requested: " + quantity + ", Available: " + availableRooms);
            }

            // 3. Guard System State: Perform update only after all validations pass
            roomInventory.put(roomType, availableRooms - quantity);

            System.out.println("Booking Successful! " + quantity + " " + roomType + " room(s) reserved for " + guestName + ".");

        } catch (InvalidBookingException e) {
            // 4. Graceful Failure Handling
            System.err.println("Booking Failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}