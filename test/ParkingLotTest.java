import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParkingLotTest {

    @Mock
    Notifiable mockedOwner;

    @Mock
    Notifiable mockedTrafficCop;

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

    @Test
    public void park_shouldSendNotificationToOwner_whenParkingLotIsFull(){
        ParkingLot parkingLot = ParkingLot.create(1);
        parkingLot.addNotifiable(mockedOwner);
        Vehicle car = new Vehicle("123");
        doNothing().when(mockedOwner).notifyParkingLotFull();

        parkingLot.park(car);

        verify(mockedOwner).notifyParkingLotFull();


    }

    @Test
    public void unpark_shouldNotifyOwner_whenSpaceIsAvailableInParkingLot(){
        ParkingLot parkingLot = ParkingLot.create(1);
        parkingLot.addNotifiable(mockedOwner);
        Vehicle car = new Vehicle("123");
        parkingLot.park(car);
        doNothing().when(mockedOwner).notifySpaceIsAvailable();

        parkingLot.unpark(car);

        verify(mockedOwner).notifySpaceIsAvailable();

    }

    @Test
    public void park_shouldSendNotificationToTrafficCop_whenParkingLotIsFull(){
        ParkingLot parkingLot = ParkingLot.create(1);
        parkingLot.addNotifiable(mockedTrafficCop);
        Vehicle car = new Vehicle("123");
        doNothing().when(mockedTrafficCop).notifyParkingLotFull();

        parkingLot.park(car);

        verify(mockedTrafficCop).notifyParkingLotFull();


    }

    @Test
    public void park_shouldSendNotificationToTrafficCop_whenParkingLotIsNotFull(){
        ParkingLot parkingLot = ParkingLot.create(2);
        parkingLot.addNotifiable(mockedTrafficCop);
        Vehicle car = new Vehicle("123");
        doNothing().when(mockedTrafficCop).notifyParkingLotFull();

        parkingLot.park(car);

        verify(mockedTrafficCop, never()).notifyParkingLotFull();


    }

    @Test
    public void park_shouldSendNotificationToTrafficCopAndOwner_whenParkingLotIsFull(){
        ParkingLot parkingLot = ParkingLot.create(1);
        parkingLot.addNotifiable(mockedOwner);
        parkingLot.addNotifiable(mockedTrafficCop);
        Vehicle car = new Vehicle("123");
        doNothing().when(mockedOwner).notifySpaceIsAvailable();
        doNothing().when(mockedTrafficCop).notifyParkingLotFull();

        parkingLot.park(car);

        verify(mockedOwner).notifyParkingLotFull();
        verify(mockedTrafficCop).notifyParkingLotFull();


    }

    @Test
    public void unpark_shouldSendNotificationToTrafficCopAndOwner_whenSpaceIsAvailableInParkingLot(){
        ParkingLot parkingLot = ParkingLot.create(1);
        parkingLot.addNotifiable(mockedOwner);
        parkingLot.addNotifiable(mockedTrafficCop);
        Vehicle car = new Vehicle("123");
        doNothing().when(mockedOwner).notifySpaceIsAvailable();
        doNothing().when(mockedTrafficCop).notifySpaceIsAvailable();
        parkingLot.park(car);

        parkingLot.unpark(car);

        verify(mockedOwner).notifySpaceIsAvailable();
        verify(mockedTrafficCop).notifySpaceIsAvailable();


    }

    @Test
    public void unpark_shouldNotSendNotificationToTrafficCopAndOwner_whenSpaceIsAvailableInParkingLot(){
        ParkingLot parkingLot = ParkingLot.create(2);
        parkingLot.addNotifiable(mockedOwner);
        parkingLot.addNotifiable(mockedTrafficCop);
        Vehicle car = new Vehicle("123");
        doNothing().when(mockedOwner).notifySpaceIsAvailable();
        doNothing().when(mockedTrafficCop).notifySpaceIsAvailable();
        parkingLot.park(car);

        parkingLot.unpark(car);

        verify(mockedOwner, never()).notifySpaceIsAvailable();
        verify(mockedTrafficCop, never()).notifySpaceIsAvailable();


    }
}
