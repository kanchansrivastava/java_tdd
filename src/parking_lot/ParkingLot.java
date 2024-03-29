package parking_lot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {

    private final static int MINIMUM_NUMBER_OF_SLOTS = 1;
    private final int totalSlots;
    private Set<Vehicle> vehicles = new HashSet<Vehicle>(0);
    private List<Notifiable> notifiables = new ArrayList<>();

    private ParkingLot(int maxSlots) {
        this.totalSlots = maxSlots;
    }

    private boolean isFull() {
        return vehicles.size() == totalSlots;
    }

    private void notifySpaceIsAvailable() {
        this.notifiables.forEach(Notifiable::notifySpaceIsAvailable);
    }

    private int getFreeSpace() {
        return this.totalSlots - this.vehicles.size();
    }

    private void notifyParkingLotFull(){
        this.notifiables.forEach(notifiable -> notifiable.notifyParkingLotFull(this));
    }

    public static ParkingLot create(int slots) {
        if (slots < MINIMUM_NUMBER_OF_SLOTS) {
            throw new InvalidParkingLotSpaceException();
        }
        return new ParkingLot(slots);
    }

    public boolean isParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
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

    public void unpark(Vehicle vehicle) {

        if (!vehicles.contains(vehicle)) {
            throw new CarNotParkedException();
        }

        vehicles.remove(vehicle);

        if (totalSlots - vehicles.size() == 1) {
            this.notifySpaceIsAvailable();
        }
    }

    public void addNotifiable(Notifiable notifiable) {
        this.notifiables.add(notifiable);
    }

    public boolean hasMoreSpaceThan(ParkingLot otherParkingLot) {
        return otherParkingLot.getFreeSpace() < this.getFreeSpace();
    }

}


