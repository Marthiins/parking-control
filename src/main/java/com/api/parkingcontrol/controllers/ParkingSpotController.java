package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;


import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins  = "*", maxAge = 3600) //Permissão para acessar de qualquer fonte
@RequestMapping("/parking-spot") //Vai ser acessado apartir desse mapeamento
public class ParkingSpotController {

    //Injeção do Service via construtor

    final  ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

                    //IMPLEMENTAÇÂO DOS METODOS POST, GET, UPDATE e DELETE

    //------------------------- METODO POST -------------------------------------------//
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
          //Colocar essas reponsabilidades na validações na Validation
        //Declarar esses metodos dentro do Service
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {

        }
        var parkingSpotModel = new ParkingSpotModel(); //Dentro do escopo fechado posso utilizar o var em vez de ParkingSpotModel
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); //ParkingSpotDto Converter DTO para model
        //BeanUtils.copyProperties passa o que vai ser convertido e no que vai ser convertido
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC"))); //serando a data de registro que não consta no DTO o cliente não vai enviar essa data
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

   // -------------------------METODO GET por paginação(BUSCAR  TODOS  SEMPRE CRIAR O metodo findall no SERVICE-----------------------------------------
    @GetMapping
   public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){ //Sempre vai enviar uma listagem
       return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }
    // -------------------------METODO GET(BUSCAR)  ID  SEMPRE CRIAR O metodo  dO SERVICE---------------------------

    @GetMapping("/{id}") //Sempre que receber a solicitação /parking-spot/id esse metodo vai ser acionado
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id); //Criar o metodo findByid
        if (!parkingSpotModelOptional.isPresent()) { //Vamos fazer uma verificação se parkingspot não estiver presente
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");//Retornar que a vaga não esta presente
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());//Se estiver presente vamos retornar um status OK e vamos retornar o recurso
    }

   // ------------------METODO DELETE FAZ A MESMA VERIFICAÇÃO DE BUSCAR PELO ID-------------------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){ // Isso n ta funcionando
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get()); //Criar um novo metodo no service de Delete
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }

    //---------------------------METODO ATUALIZAR ----------------------------------------------------------------

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ParkingSpotDto parkingSpotDto){ //Vamos receber esses campos para eles serem atualizados
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        //converter MODEl para DTO parkingSpotModelOptional pode se fazer de 2 maneiras
        //--------------Primeira Maneira SETANDO OS ATRIBUTOS DA CLASSE DTO-----------------------------------------

       /* var parkingSpotModel = parkingSpotModelOptional.get(); //var parkingSpotModel = parkingSpotModelOptional

       parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
       parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
       parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
       parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
       parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
       parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
       parkingSpotModel.setApartment(parkingSpotDto.getApartment());
       parkingSpotModel.setBlock(parkingSpotDto.getBlock());
       return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));*/

       //----------------------------- SEGUNDA MANEIRA EM VEZ DE OBTER O OPTIONAL EU CRIO UMA NOVA INSTANCIA  ---------------------------------------------------

        var parkingSpotModel = new ParkingSpotModel();//Nova instancia
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);//utilizando o BeanUtils passando DTO para MODEL
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());//SETAR O ID
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());//SETAR A DATA DE REGISTRO ESSA QUE VEM AUTOMATICA
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }

   // @GetMapping(value="test")
   // public String teste() {
     //   return "teste";
    }


