package org.example.parcel.controller;


import org.example.parcel.dto.Parcel;
import org.example.parcel.dto.Truck;
import org.example.parcel.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TruckController {

    private final TruckRepository repository;

    @Autowired
    public TruckController(TruckRepository repository) {
        this.repository = repository;
    }

    /*
    Method for creating a GET request to get all trucks.
     */
    @GetMapping("/trucks")
    List<Truck> getAllTrucks(){
        return this.repository.getAllTrucks();
    }

    /*
    Method for creating a GET request to get a truck by registration plate.
     */
    @GetMapping("/trucks/{registrationPlate}")
    Truck getTruckByRegistrationPlate(@PathVariable String registrationPlate) {
        final Truck truck = repository.getTruckByRegistrationPlate(registrationPlate);

        if(truck == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Truck is not found");
        }

        return truck;
    }

    /*
    Method for creating a POST request to add a truck with a registration plate.
     */
    @PostMapping("/truck")
    Truck addTruck(@RequestParam String registration_plate) throws ResponseStatusException {

        if(this.repository.isTruckExists(registration_plate)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Truck already exists");
        }

        final Truck truck = new Truck(registration_plate);

        repository.add(truck);

        return truck;
    }

    /*
    Method for creating a POST request to add a parcel to the created truck.
     */
    @PostMapping("/truck/{registrationPlate}/load")
    Truck loadTruck(@PathVariable String registrationPlate, @RequestParam Integer weight) throws ResponseStatusException {

        final Truck truck = repository.getTruckByRegistrationPlate(registrationPlate);

        if(truck == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Truck is not found");
        }

        final Parcel parcel = new Parcel(weight);
        truck.addParcel(parcel);

        return truck;
    }

    /*
    Method for creating a PUT request to unload truck.
     */
    @PutMapping("/truck/{registrationPlate}/unload")
    Truck unloadTruck(@PathVariable String registrationPlate) throws ResponseStatusException {

        final Truck truck = repository.getTruckByRegistrationPlate(registrationPlate);

        if(truck == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Truck is not found");
        }


        truck.unload();

        return truck;
    }
}