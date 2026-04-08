import java.util.ArrayList;
import java.util.List;

// Model class to represent a Reservation
class Reservation {
    private String bookingId;
    private String guestName;
    private String roomType;
    private double price;

    public Reservation(String bookingId, String guestName, String roomType, double price) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Guest: %s | Room: %s | Price: $%.2f",
                bookingId, guestName, roomType, price);
    }

    public double getPrice() {
        return price;
    }
}

// Service to handle historical data storage
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Adds confirmed reservation (Preserves insertion order)
    public void addRecord(Reservation reservation) {
        history.add(reservation);
    }

    // Returns a copy of records to ensure reporting doesn't modify original data
    public List<Reservation> getAllRecords() {
        return new ArrayList<>(history);
    }
}

// Service to generate reports (Separation of concerns)
class BookingReportService {
    public void generateSummaryReport(List<Reservation> records) {
        System.out.println("\n--- Booking Summary Report ---");
        double totalRevenue = 0;

        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Reservation res : records) {
            System.out.println(res);
            totalRevenue += res.getPrice();
        }

        System.out.println("------------------------------");
        System.out.println("Total Bookings: " + records.size());
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        System.out.println("------------------------------\n");
    }
}

// Main Class
public class bookmystay {
    public static void main(String[] args) {
        // Initialize services
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        System.out.println("System: Processing bookings...");

        // Simulate successful confirmations (Flow step 1 & 2)
        Reservation res1 = new Reservation("BK001", "Alice Smith", "Deluxe", 150.00);
        history.addRecord(res1);

        Reservation res2 = new Reservation("BK002", "Bob Jones", "Standard", 100.00);
        history.addRecord(res2);

        Reservation res3 = new Reservation("BK003", "Charlie Brown", "Suite", 300.00);
        history.addRecord(res3);

        // Admin requests reports (Flow step 4 & 5)
        System.out.println("Admin: Requesting historical report...");
        List<Reservation> records = history.getAllRecords();
        reportService.generateSummaryReport(records);
    }
}