import java.util.LinkedList;
import java.util.Queue;

class BookingRequestQueue {

    Queue<Reservation> requests;

    BookingRequestQueue() {
        requests = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        requests.add(reservation);
        System.out.println("Booking request added for " + reservation.guestName);
    }

    void showRequests() {
        System.out.println("\nBooking Requests in Queue\n");

        for (Reservation r : requests) {
            r.display();
        }
    }

}