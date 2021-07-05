package org.example.parcel.dto;

import java.util.ArrayList;
import java.util.List;

public class Truck {

    private final String registrationPlate;
    private final List<Parcel> parcels;

    public Truck(final String registrationPlate){
        this.registrationPlate = registrationPlate;
        this.parcels = new ArrayList<>();
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    /*
    Method for getting truck weight with parcels.
     */
    public int getTruckWeight() {
        int sum = 0;

        for (Parcel parcel:this.parcels) {
            sum += parcel.getParcelWeight();
        }
        return sum;
    }

    /*
    Method for adding parcel.
     */
    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
    }

    public void unload() {
        parcels.clear();
    }
}
