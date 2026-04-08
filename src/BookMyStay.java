import java.util.LinkedList;
import java.util.Queue;

class BookingRequest {
    String guestName;
    int roomId;

    public BookingRequest(String guestName, int roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
    }
}

class BookingProcessor implements Runnable {
    private final Queue<BookingRequest> bookingQueue;
    private static int availableRooms = 5; // Shared inventory

    public BookingProcessor(Queue<BookingRequest> queue) {
        this.bookingQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            BookingRequest request = null;

            // Critical Section: Accessing the shared queue
            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) {
                    break;
                }
                request = bookingQueue.poll();
            }

            if (request != null) {
                processBooking(request);
            }
        }
    }

    // Critical Section: Updating shared inventory (Room Allocation)
    private void processBooking(BookingRequest request) {
        synchronized (BookingProcessor.class) {
            System.out.println("Processing booking for: " + request.guestName);

            if (availableRooms > 0) {
                // Simulate processing time
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }

                availableRooms--;
                System.out.println("SUCCESS: Room allocated to " + request.guestName
                        + ". Remaining Rooms: " + availableRooms);
            } else {
                System.out.println("FAILURE: No rooms available for " + request.guestName);
            }
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        Queue<BookingRequest> sharedQueue = new LinkedList<>();

        // Simulating multiple guests adding requests to the queue
        sharedQueue.add(new BookingRequest("Guest_1", 101));
        sharedQueue.add(new BookingRequest("Guest_2", 102));
        sharedQueue.add(new BookingRequest("Guest_3", 103));
        sharedQueue.add(new BookingRequest("Guest_4", 104));
        sharedQueue.add(new BookingRequest("Guest_5", 105));
        sharedQueue.add(new BookingRequest("Guest_6", 106)); // This should fail

        System.out.println("--- Starting Concurrent Booking Simulation ---");

        // Creating multiple threads (Concurrent Processors)
        Thread thread1 = new Thread(new BookingProcessor(sharedQueue));
        Thread thread2 = new Thread(new BookingProcessor(sharedQueue));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- Simulation Completed ---");
    }
}