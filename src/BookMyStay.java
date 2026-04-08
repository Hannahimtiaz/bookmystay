import java.util.*;

/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * Goal: Enable safe cancellation of confirmed bookings by reversing system state.
 */
public class BookMyStay {

    // Inventory and Booking state
    private static int availableRooms = 5;
    private static Map<String, String> activeBookings = new HashMap<>();

    // Stack to track released room IDs for LIFO rollback logic
    private static Stack<String> cancelledRoomsRollback = new Stack<>();

    public static void main(String[] args) {
        System.out.println("--- Hotel Booking System: Use Case 10 ---");

        // 1. Setup initial state (Creating some bookings)
        processBooking("B001", "Room_101");
        processBooking("B002", "Room_102");
        displayStatus();

        // 2. Perform Cancellations (State Reversal)
        cancelBooking("B002"); // Most recent
        cancelBooking("B001");

        // 3. Attempting to cancel a non-existent booking (Validation)
        cancelBooking("B003");

        displayStatus();

        System.out.println("\nRollback History (LIFO Order): " + cancelledRoomsRollback);
    }

    /**
     * Simulates the initial booking process.
     */
    private static void processBooking(String bookingId, String roomId) {
        if (availableRooms > 0) {
            activeBookings.put(bookingId, roomId);
            availableRooms--;
            System.out.println("Booking Confirmed: " + bookingId + " for " + roomId);
        }
    }

    /**
     * Use Case 10 Logic: Validates, rolls back inventory, and updates state.
     */
    private static void cancelBooking(String bookingId) {
        System.out.println("\nInitiating cancellation for: " + bookingId);

        // Validation: Ensure the reservation exists
        if (!activeBookings.containsKey(bookingId)) {
            System.out.println("Error: Cancellation failed. Booking ID " + bookingId + " not found.");
            return;
        }

        // State Reversal & LIFO Rollback
        String roomId = activeBookings.remove(bookingId); // Remove from active bookings
        cancelledRoomsRollback.push(roomId);           // Record in Stack for rollback tracking
        availableRooms++;                              // Increment Inventory

        System.out.println("Success: " + roomId + " has been released back to the pool.");
        System.out.println("Inventory restored. Current available: " + availableRooms);
    }

    private static void displayStatus() {
        System.out.println("\n--- Current System State ---");
        System.out.println("Available Inventory: " + availableRooms);
        System.out.println("Active Bookings: " + activeBookings);
        System.out.println("----------------------------");
    }
}