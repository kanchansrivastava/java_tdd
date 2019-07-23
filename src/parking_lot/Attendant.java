package parking_lot;
import java.util.ArrayList;
import java.util.List;

public class Attendant implements Notifiable {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private List<ParkingLot> availableParkingLots = new ArrayList<>();
    private ParkingLot parkingLot;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
        this.availableParkingLots.addAll(parkingLots);
    }

    public void park(Vehicle vehicle) {
        if (availableParkingLots.isEmpty()) {
            throw new NoParkingLotsAvailableException();
        }
        availableParkingLots.get(0).park(vehicle);
    }

    public void unpark(Vehicle vehicle) {
        for (ParkingLot parkingLot:parkingLots) {
            if (parkingLot.isParked(vehicle)) {
                parkingLot.unpark(vehicle);
                return;
            }
        }
        throw new VehicleNotParkedException();
    }

    @Override
    public void notifyParkingLotFull(ParkingLot parkingLot) {
        this.availableParkingLots.remove(parkingLot);
    }

    @Override
    public void notifySpaceIsAvailable() {

    }
}