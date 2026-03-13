public class BookMyStay {
    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        System.out.println("Hotel Room Inventory Status\n");

        single.display("Single", inventory.getAvailability("Single"));
        doubleRoom.display("Double", inventory.getAvailability("Double"));
        suite.display("Suite", inventory.getAvailability("Suite"));

    }

}
