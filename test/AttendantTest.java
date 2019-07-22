import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttendantTest {

    @Mock
    ParkingLot mockedParkingLot;

    @Test
    public void park_shouldParkACar_whenSpaceIsAvailableInParkingLot() {
        Attendant attendant = new Attendant(mockedParkingLot);
        Vehicle vehicle = new Vehicle("KA03MC931");

        attendant.park(vehicle);

        verify(mockedParkingLot).park(vehicle);

    }

    @Test
    public void unpark_shouldUnparkACar_whenTheCarIsParked() {
        Attendant attendant = new Attendant(mockedParkingLot);
        Vehicle vehicle = new Vehicle("KA03MC931");

        attendant.unpark(vehicle);

        verify(mockedParkingLot).unpark(vehicle);

    }
}