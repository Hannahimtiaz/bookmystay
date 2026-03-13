import java.util.HashMap;

class RoomInventory {

    HashMap<String,Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single",5);
        inventory.put("Double",3);
        inventory.put("Suite",2);
    }

    int getAvailability(String type){
        return inventory.get(type);
    }

}