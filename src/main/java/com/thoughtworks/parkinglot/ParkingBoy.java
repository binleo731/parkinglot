package com.thoughtworks.parkinglot;

import com.thoughtworks.exception.ParkingLotFullException;

import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) throws ParkingLotFullException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isNotFull()) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingLotFullException();
    }
}
