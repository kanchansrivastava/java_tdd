import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private final static int MINIMUM_NUMBER_OF_SLOTS = 1;
    private final int totalSlots;
    private Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
    private Owner owner;
    private TrafficCop trafficCop;

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

        if  (isFull()) {
            throw new ParkingLotAlreadyFullException();
        }

        vehicles.add(vehicle);

        if (isFull()) {
            notifyParkingLotFull();
        }
    }

    private boolean isFull() {
        return vehicles.size() == totalSlots;
    }

    private void notifyParkingLotFull(){
        if (owner != null){
            owner.notifyParkingLotFull();
        }
        if (trafficCop != null){
            trafficCop.notifyParkingLotFull();
        }

    }

    public void unpark(Vehicle vehicle) {

        if (!vehicles.contains(vehicle)) {
            throw new CarNotParkedException();
        }

        vehicles.remove(vehicle);

        if (totalSlots - vehicles.size() == 1) {
            owner.notifySpaceIsAvailable();
        }
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    public void addOwner(Owner owner) {
        this.owner = owner;
    }

    public void addTrafficCop(TrafficCop trafficCop) {
        this.trafficCop =  trafficCop;
    }
}
