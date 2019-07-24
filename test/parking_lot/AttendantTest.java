package parking_lot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AttendantTest {

    @Mock
    ParkingLot mockedParkingLot;

    @Test
    public void park_shouldParkACar_whenSpaceIsAvailableInParkingLot() {
        List<ParkingLot> parkingLots = Collections.singletonList(mockedParkingLot);
        Attendant attendant = new Attendant(parkingLots);
        Vehicle vehicle = new Vehicle("KA03MC931");

        attendant.park(vehicle);

        verify(mockedParkingLot).park(vehicle);

    }

    @Test
    public void unpark_shouldUnparkACar_whenTheCarIsParked() {
        List<ParkingLot> parkingLots = Collections.singletonList(mockedParkingLot);
        Attendant attendant = new Attendant(parkingLots);
        Vehicle vehicle = new Vehicle("KA03MC931");

        when(mockedParkingLot.isParked(vehicle)).thenReturn(true);

        attendant.park(vehicle);
        attendant.unpark(vehicle);

        verify(mockedParkingLot).unpark(vehicle);

    }

    @Test
    public void park_shouldParkACar_whenSpaceIsAvailableInParkingLot1() {
        ParkingLot parkingLot1 = ParkingLot.create(2);
        ParkingLot parkingLot2 = ParkingLot.create(1);
        Attendant attendant = new Attendant(Arrays.asList(parkingLot1, parkingLot2));

        Vehicle vehicle1 = new Vehicle("KA03MC931");

        attendant.park(vehicle1);

        assertTrue(parkingLot1.isParked(vehicle1));
    }


    @Test
    public void park_shouldParkACar_whenSpaceIsNotAvailableInParkingLot1AndIsAvailableInParkingLot2() {
        ParkingLot parkingLot1 = ParkingLot.create(1);
        ParkingLot parkingLot2 = ParkingLot.create(1);
        Attendant attendant = new Attendant(Arrays.asList(parkingLot1, parkingLot2));

        Vehicle vehicle1 = new Vehicle("KA03MC931");
        Vehicle vehicle2 = new Vehicle("KA03MC932");

        parkingLot1.addNotifiable(attendant);
        parkingLot2.addNotifiable(attendant);

        attendant.park(vehicle1);
        attendant.park(vehicle2);

        assertTrue(parkingLot1.isParked(vehicle1));
        assertTrue(parkingLot2.isParked(vehicle2));
    }


    @Test(expected = NoParkingLotsAvailableException.class)
    public void park_shouldThrow_whenSpaceIsNotAvailableInParkingLot1AndInParkingLot2() {
        ParkingLot parkingLot1 = ParkingLot.create(1);
        ParkingLot parkingLot2 = ParkingLot.create(1);
        Attendant attendant = new Attendant(Arrays.asList(parkingLot1, parkingLot2));

        Vehicle vehicle1 = new Vehicle("KA03MC931");
        Vehicle vehicle2 = new Vehicle("KA03MC932");
        Vehicle vehicle3 = new Vehicle("KA03MC933");

        parkingLot1.addNotifiable(attendant);
        parkingLot2.addNotifiable(attendant);

        attendant.park(vehicle1);
        attendant.park(vehicle2);
        attendant.park(vehicle3);
    }

    @Test
    public void unpark_shouldUnparkACar_whenCarIsParkedInParkingLot1() {
        ParkingLot parkingLot1 = ParkingLot.create(2);
        Attendant attendant = new Attendant(Arrays.asList(parkingLot1));

        Vehicle vehicle1 = new Vehicle("KA03MC931");

        attendant.park(vehicle1);
        attendant.unpark(vehicle1);

        assertFalse(parkingLot1.isParked(vehicle1));
    }

    @Test
    public void unpark_shouldUnparkACar_whenCarIsParkedInParkingLot2() {
        ParkingLot parkingLot1 = ParkingLot.create(1);
        ParkingLot parkingLot2 = ParkingLot.create(1);
        Attendant attendant = new Attendant(Arrays.asList(parkingLot1, parkingLot2));
        Vehicle vehicle1 = new Vehicle("KA03MC931");
        Vehicle vehicle2 = new Vehicle("KA03MC932");

        parkingLot1.addNotifiable(attendant);
        parkingLot2.addNotifiable(attendant);
        attendant.park(vehicle1);
        attendant.park(vehicle2);
        attendant.unpark(vehicle2);

        assertTrue(parkingLot1.isParked(vehicle1));
        assertFalse(parkingLot2.isParked(vehicle2));

    }

    @Test(expected = VehicleNotParkedException.class)
    public void unpark_shouldThrow_whenSpaceIsNotAvailableInParkingLot1AndInParkingLot2() {
        ParkingLot parkingLot1 = ParkingLot.create(1);
        ParkingLot parkingLot2 = ParkingLot.create(1);
        Attendant attendant = new Attendant(Arrays.asList(parkingLot1, parkingLot2));

        Vehicle vehicle1 = new Vehicle("KA03MC931");
        Vehicle vehicle2 = new Vehicle("KA03MC932");
        Vehicle vehicle3 = new Vehicle("KA03MC933");

        parkingLot1.addNotifiable(attendant);
        parkingLot2.addNotifiable(attendant);

        attendant.park(vehicle1);
        attendant.park(vehicle2);
        attendant.unpark(vehicle3);
    }


}