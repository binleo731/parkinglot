package com.thoughtworks.parkinglot;

import com.thoughtworks.exception.CarNotFoundException;
import com.thoughtworks.exception.ParkingLotFullException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class ParkingBoyTest {

    @Test
    public void should_park_to_1st_parking_lot_when_parking_boy_park_given_a_car_and_1st_parking_lot_is_not_full() throws ParkingLotFullException, CarNotFoundException {
        //given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        Car pickedCar = parkingLot1.pickUp(parkingTicket);
        Assert.assertEquals(car, pickedCar);

    }

    @Test
    public void should_park_to_2nd_parking_lot_when_parking_boy_park_given_a_car_and_1st_parking_lot_is_full_and_2nd_parking_lot_is_not_full() throws ParkingLotFullException, CarNotFoundException {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car2);
        Car pickedCar = parkingLot2.pickUp(parkingTicket);
        //then
        Assert.assertEquals(car2, pickedCar);

    }

    @Test
    public void should_park_to_1st_parking_lot_when_parking_boy_park_given_a_car_and_1st_parking_lot_is_not_full_and_2nd_parking_lot_is_full() throws ParkingLotFullException, CarNotFoundException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket ticket1 = parkingBoy.park(car1);
        ParkingTicket ticket2 = parkingBoy.park(car2);
        Car car = parkingLot1.pickUp(ticket1);

        //when
        ParkingTicket ticket3 = parkingBoy.park(car3);

        //then
        Car thirdCar = parkingLot1.pickUp(ticket3);
        Car secondCar = parkingLot2.pickUp(ticket2);
        Assert.assertEquals(car3, thirdCar);
        Assert.assertEquals(car2, secondCar);

    }
}
