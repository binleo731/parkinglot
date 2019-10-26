package com.thoughtworks.parkinglot;

import com.thoughtworks.exception.CarNotFoundException;
import com.thoughtworks.exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket, Car> carMaps;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        carMaps = new HashMap<>(capacity);
    }

    public ParkingTicket park(Car car) throws ParkingLotFullException {
        if (carMaps.size() < capacity) {
            ParkingTicket parkingTicket = new ParkingTicket();
            carMaps.put(parkingTicket, car);
            return parkingTicket;
        }
        throw new ParkingLotFullException();
    }

    public Car pickUp(ParkingTicket ticket) throws CarNotFoundException {
        Car fetchCar = carMaps.get(ticket);
        if (Objects.nonNull(fetchCar)) {
            carMaps.remove(ticket);
            return fetchCar;
        }
        throw new CarNotFoundException();
    }

    public boolean isNotFull() {
        return carMaps.size() < capacity;
    }
}
