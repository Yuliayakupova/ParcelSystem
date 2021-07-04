package org.example.parcel.repository;

import org.example.parcel.dto.Truck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TruckRepository {

    /*
    Truck storage.
     */
    private HashMap<String, Truck> trucks;

    public TruckRepository() {
        this.trucks = new HashMap<>();
    }

    /*
   Method to getting all created trucks.
     */
    public List<Truck> getAllTrucks(){
        return new ArrayList<>(this.trucks.values());
    }

    /*
    Method to return truck by registration plate.
     */
    public Truck getTruckByRegistrationPlate(final String registrationPlate){
        return this.trucks.get(registrationPlate);
    }

    /*
    Method to check that truck this given registration plate exists.
     */
    public boolean isTruckExists(final String registrationPlate){
        return this.trucks.containsKey(registrationPlate);
    }

    /*
    Method to create a new truck.
     */
    public void add(final Truck truck){
        this.trucks.put(truck.getRegistrationPlate(), truck);
    }
}
