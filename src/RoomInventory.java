import java.util.HashMap;

public class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public int getAvailability(String type) {
        return inventory.get(type);
    }

    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
    }

}