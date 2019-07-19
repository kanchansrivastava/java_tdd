import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

    @Test
    public void park_shouldParkCar_whenSpaceIsAvailableInParkingLot() {
        Vehicle car = new Vehicle("TS08");
        ParkingLot parkingLot = ParkingLot.create(10);

        parkingLot.park(car);

        assertTrue(parkingLot.isParked(car));
    }

    @Test(expected = InvalidParkingLotSpaceException.class)
    public void park_expectThrowInvalidParkingLotSpaceException_whenCreatingParkingLotWithZeroSlots() {
        ParkingLot.create(0);
    }

    @Test(expected = ParkingLotAlreadyFullException.class)
    public void park_expectThrowParkingLotAlreadyFullException_whenTryingToParkACarInFullParkingLot() {
        Vehicle firstCar = new Vehicle("TS08");
        Vehicle secondCar = new Vehicle("TS09");
        ParkingLot parkingLot = ParkingLot.create(1);

        parkingLot.park(firstCar);
        parkingLot.park(secondCar);
    }

    @Test(expected = VehicleAlreadyParkedException.class)
    public void park_expectThrowVehicleAlreadyParkedException_whenParkedCarIsBeingParkedAgain() {
        Vehicle car = new Vehicle("TS08");
        ParkingLot parkingLot = ParkingLot.create(3);

        parkingLot.park(car);
        parkingLot.park(car);
    }

    @Test
    public void unpark_expectUnparkingTheCar_whenGivenCarIsAlreadyParkedInParkingLot() {
        Vehicle car = new Vehicle("123");
        ParkingLot parkingLot = ParkingLot.create(10);

        parkingLot.park(car);
        parkingLot.unpark(car);

        assertFalse(parkingLot.isParked(car));
    }

    @Test(expected = CarNotParkedException.class)
    public void unpark_expectThrowCarNotParkedException_whenCarIsNotParkedInParkingLot() {
        Vehicle car = new Vehicle("KA5678");
        ParkingLot parkingLot = ParkingLot.create(10);

        parkingLot.unpark(car);

    }

}