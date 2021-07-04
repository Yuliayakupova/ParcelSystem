package org.example.parcel.dto;

public class Parcel {
    private final int weight;

    public Parcel (final int weight) {
        this.weight = weight;
    }

    public int getParcelWeight() {
        return this.weight;
    }
}
