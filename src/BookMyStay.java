public class BookMyStay {

    public static void main(String[] args) {


            RoomInventory inventory = new RoomInventory();
            BookingRequestQueue queue = new BookingRequestQueue();

            queue.addRequest(new Reservation("Alice","Single"));
            queue.addRequest(new Reservation("Bob","Double"));
            queue.addRequest(new Reservation("Charlie","Suite"));
            queue.addRequest(new Reservation("David","Single"));

            RoomAllocationService service = new RoomAllocationService();

            service.processBookings(queue, inventory);

        }
}