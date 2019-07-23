package parking_lot;

public interface Notifiable {

    void notifyParkingLotFull(ParkingLot parkingLot);

    void notifySpaceIsAvailable();
}
