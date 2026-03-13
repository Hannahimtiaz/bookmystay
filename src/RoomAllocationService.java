import java.util.*;

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private HashMap<String, Set<String>> roomAllocations;

    RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    void processBookings(BookingRequestQueue queue, RoomInventory inventory) {

        while (!queue.requests.isEmpty()) {

            Reservation request = queue.requests.poll();
            String roomType = request.roomType;

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                String roomId = roomType.substring(0,1).toUpperCase() + (allocatedRoomIds.size() + 1);

                while (allocatedRoomIds.contains(roomId)) {
                    roomId = roomType.substring(0,1).toUpperCase() + (allocatedRoomIds.size() + 1);
                }

                allocatedRoomIds.add(roomId);

                roomAllocations.putIfAbsent(roomType, new HashSet<>());
                roomAllocations.get(roomType).add(roomId);

                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Reservation confirmed for " + request.guestName);
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);
                System.out.println();
            }
            else {
                System.out.println("Reservation failed for " + request.guestName + " (No rooms available)");
            }
        }
    }
}