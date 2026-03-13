class RoomSearchService {

    void searchRooms(RoomInventory inventory) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = inventory.getAvailability("Single");
        int doubleAvailable = inventory.getAvailability("Double");
        int suiteAvailable = inventory.getAvailability("Suite");

        System.out.println("Available Rooms\n");

        if (singleAvailable > 0) {
            single.display("Single", singleAvailable);
        }

        if (doubleAvailable > 0) {
            doubleRoom.display("Double", doubleAvailable);
        }

        if (suiteAvailable > 0) {
            suite.display("Suite", suiteAvailable);
        }
    }
}