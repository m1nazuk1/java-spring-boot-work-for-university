package com.example.seminar4ik.repository;


import com.example.seminar4ik.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByMake(String make);

    List<Car> findByYearGreaterThan(int year);

    // JPQL-запрос найти машины по владельцу
    @Query("SELECT c FROM Car c WHERE c.owner.id = :ownerId")
    List<Car> findCarsByOwnerId(Long ownerId);
}