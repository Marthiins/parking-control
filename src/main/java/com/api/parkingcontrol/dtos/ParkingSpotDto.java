package com.api.parkingcontrol.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ParkingSpotDto {

    @NotBlank //Verificar se o campo não é nullo e se não tem string Vazia
    private String parkingSpotNumber; //Numero da vaga

    @NotBlank
    @Size(max = 7) //size para limitar o numero de caracteres
    private String licensePlateCar; //Placa do carro

    @NotBlank
    private String brandCar; //Marca do carro

    @NotBlank
    private String modelCar; //Modelo do carro

    @NotBlank
    private String colorCar; //Cor do carro

    @NotBlank
    private String responsibleName; //Nome do responsavel

    @NotBlank
    private String apartment; //Apartamento

    @NotBlank
    private String block; //Bloco

                    //METODOS GET E SETERS


    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
