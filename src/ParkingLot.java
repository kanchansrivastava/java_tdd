import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private final static int MINIMUM_NUMBER_OF_SLOTS = 1;
    private final int totalSlots;
    private Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
    private Owner owner;

    private ParkingLot(int maxSlots) {
        this.totalSlots = maxSlots;
    }

    public static ParkingLot create(int slots) {
        if (slots < MINIMUM_NUMBER_OF_SLOTS) {
            throw new InvalidParkingLotSpaceException();
        }
        return new ParkingLot(slots);
    }

    public void park(Vehicle vehicle) {

        if (vehicles.contains(vehicle)) {
            throw new VehicleAlreadyParkedException();
        }

        if (vehicles.size() == totalSlots) {
            throw new ParkingLotAlreadyFullException();
        }

        vehicles.add(vehicle);

        if (vehicles.size() == totalSlots) {
            notifyOwner();
        }
    }

    private void notifyOwner(){
        owner.notifyParkingLotFull();
    }

    public void unpark(Vehicle vehicle) {

        if (!vehicles.contains(vehicle)) {
            throw new CarNotParkedException();
        }

        vehicles.remove(vehicle);
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
