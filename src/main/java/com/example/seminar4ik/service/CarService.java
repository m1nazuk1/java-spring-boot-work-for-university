package com.example.seminar4ik.service;

import com.example.seminar4ik.entity.Car;
import com.example.seminar4ik.entity.Owner;
import com.example.seminar4ik.repository.CarRepository;
import com.example.seminar4ik.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CarService(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
    }

    public Car createCar(Car car) {
        if (car.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }
        Owner owner = ownerRepository.findById(car.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
        car.setOwner(owner);
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);
        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setOwner(updatedCar.getOwner());

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getCarsByMake(String make) {
        return carRepository.findByMake(make);
    }

    public List<Car> getCarsByYear(int year) {
        return carRepository.findByYearGreaterThan(year);
    }

    public List<Car> getCarsByOwner(Long ownerId) {
        return carRepository.findCarsByOwnerId(ownerId);
    }
}