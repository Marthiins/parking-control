package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service //Camada intermediaria entre o controller e o repository
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository; //Criar os pontos de injeções via construtores

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    //METODO SAVE DO CONTROLLER

   @Transactional //funciona como um rolback pois se alguma coisa da errado ele volta
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel); //retorno aqui vai ser a injeçaõ do jpa repository e receber a entidade parkingSpotModel
    }

    //Declarar esses metodos dentro do repository
    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) { //Passando os dois parametros apartamento e o blocco
        return parkingSpotRepository.existsByApartmentAndBlock(apartment , block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) { //Criar uma Page do parkingSpotModel
        return parkingSpotRepository.findAll(pageable);//Aqui já e um metodo pronto
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id); //Aqui passamos o Id que estamos recebendo do parametro do metodo
    }
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) { //Metodo delete não tem retorno por isso ele é um void
        parkingSpotRepository.delete(parkingSpotModel);

    }
}
