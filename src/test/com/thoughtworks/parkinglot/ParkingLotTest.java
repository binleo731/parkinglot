package com.thoughtworks.parkinglot;

import com.thoughtworks.exception.CarNotFoundException;
import com.thoughtworks.exception.ParkingLotFullException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_park_given_a_car_and_parking_lot_is_not_full() throws ParkingLotFullException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        Assert.assertNotNull(parkingTicket);

    }

    @Test(expected = ParkingLotFullException.class)
    public void should_return_error_message_when_park_given_a_car_and_parking_lot_is_full() throws ParkingLotFullException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(car1);

        //when
         parkingLot.park(car2);
    }

    @Test
    public void should_get_car_when_pick_given_ticket_and_has_this_car_parked() throws CarNotFoundException, ParkingLotFullException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car pickedCar = parkingLot.pickUp(parkingTicket);

        //then
        Assert.assertEquals(pickedCar, car);
    }

    @Test(expected = CarNotFoundException.class)
    public void should_not_get_car_when_pick_given_ticket_is_faked() throws CarNotFoundException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingTicket parkingTicket = new ParkingTicket();

        //when
        parkingLot.pickUp(parkingTicket);
    }

    @Test
    public void should_get_ticket_when_packing_boy_park_given_a_car_and_one_not_full_parking_lot() throws ParkingLotFullException {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLots = Collections.singletonList(new ParkingLot(10));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        Assert.assertNotNull(parkingTicket);

    }





}
