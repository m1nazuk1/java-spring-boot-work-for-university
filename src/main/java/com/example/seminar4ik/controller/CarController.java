package com.example.seminar4ik.controller;

import com.example.seminar4ik.dto.CarDTO;
import com.example.seminar4ik.entity.Car;
import com.example.seminar4ik.entity.Owner;
import com.example.seminar4ik.repository.OwnerRepository;
import com.example.seminar4ik.service.CarService;
import com.example.seminar4ik.service.OwnerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final OwnerService ownerService;

    public CarController(CarService carService, OwnerService ownerService) {
        this.carService = carService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public Car createCar(@RequestBody CarDTO carDTO) {
        if (carDTO.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }

        Owner owner = ownerService.getOwnerById(carDTO.getOwnerId());
        Car car = new Car();
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setOwner(owner);

        return carService.createCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        Car car = forCarDTO(carDTO);
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    //ниже для JPQL-запросов
    @GetMapping("/by-make/{make}")
    public List<Car> getCarsByMake(@PathVariable String make) {
        return carService.getCarsByMake(make);
    }

    @GetMapping("/by-year/{year}")
    public List<Car> getCarsByYear(@PathVariable int year) {
        return carService.getCarsByYear(year);
    }

    @GetMapping("/by-owner/{ownerId}")
    public List<Car> getCarsByOwner(@PathVariable Long ownerId) {
        return carService.getCarsByOwner(ownerId);
    }


    public Car forCarDTO(CarDTO carDTO){
        if (carDTO.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }
        Owner owner = ownerService.getOwnerById(carDTO.getOwnerId());
        Car car = new Car();
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setOwner(owner);
        return car;
    }
}