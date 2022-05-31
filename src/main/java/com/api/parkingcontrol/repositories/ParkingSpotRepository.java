package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository <ParkingSpotModel, UUID>{ //Passa qual vai ser o Model e o tipo de identificador

    //Depois de declarar o metodo podemos utilizar ele no service
    boolean existsByLicensePlateCar (String licensePlacerCar);
    boolean existsByParkingSpotNumber (String parkingSpotNumber);
    boolean existsByApartmentAndBlock (String apartment, String block);

}
