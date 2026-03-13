public class BookMyStay {

    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");

        queue.addRequest(r1);
        queue.addRequest(r2);
        queue.addRequest(r3);

        queue.showRequests();

    }
}