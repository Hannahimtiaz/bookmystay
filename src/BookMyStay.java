import java.io.*;
import java.util.*;

// Serializable class to hold the system state
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;
    Map<String, Integer> inventory;
    List<String> bookingHistory;

    public SystemState(Map<String, Integer> inventory, List<String> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}

public class bookmystay {
    private static final String STORAGE_FILE = "system_state.ser";
    private Map<String, Integer> inventory = new HashMap<>();
    private List<String> bookingHistory = new ArrayList<>();

    public static void main(String[] args) {
        bookmystay app = new bookmystay();
        app.run();
    }

    public void run() {
        // 1. System Startup: Restore State
        loadSystemState();

        // Initial setup if inventory is empty (first run)
        if (inventory.isEmpty()) {
            inventory.put("Deluxe", 5);
            inventory.put("Suite", 2);
            System.out.println("Initialized new inventory.");
        }

        displayStatus();

        // 2. Simulate Business Activity
        processBooking("Deluxe", "Alice");
        processBooking("Suite", "Bob");

        // 3. System Shutdown: Persist State
        saveSystemState();

        System.out.println("\nSystem shutting down. State saved safely.");
    }

    private void processBooking(String type, String user) {
        if (inventory.getOrDefault(type, 0) > 0) {
            inventory.put(type, inventory.get(type) - 1);
            bookingHistory.add("User: " + user + " | Room: " + type + " | Date: " + new Date());
            System.out.println("Booking successful for " + user);
        } else {
            System.out.println("Booking failed: No " + type + " rooms available.");
        }
    }

    private void displayStatus() {
        System.out.println("\n--- Current System State ---");
        System.out.println("Inventory: " + inventory);
        System.out.println("History Count: " + bookingHistory.size());
        if (!bookingHistory.isEmpty()) {
            System.out.println("Last Booking: " + bookingHistory.get(bookingHistory.size() - 1));
        }
        System.out.println("---------------------------\n");
    }

    // --- Persistence Logic ---

    private void saveSystemState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            SystemState state = new SystemState(inventory, bookingHistory);
            oos.writeObject(state);
            System.out.println("Data persisted to " + STORAGE_FILE);
        } catch (IOException e) {
            System.err.println("Error saving system state: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadSystemState() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            System.out.println("No previous state found. Starting fresh.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE_FILE))) {
            SystemState state = (SystemState) ois.readObject();
            this.inventory = state.inventory;
            this.bookingHistory = state.bookingHistory;
            System.out.println("System state recovered successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failure Tolerance: Could not restore state (file may be corrupted). Starting fresh.");
            // Reset to clean state if file is corrupted
            this.inventory = new HashMap<>();
            this.bookingHistory = new ArrayList<>();
        }
    }
}