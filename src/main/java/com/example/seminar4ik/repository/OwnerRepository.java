package com.example.seminar4ik.repository;

import com.example.seminar4ik.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByName(String name);

    @Query("SELECT o FROM Owner o WHERE size(o.cars) > 1")
    List<Owner> findOwnersWithMultipleCars();

    // JPQL-запрос найти владельцев по части имени (используем like)
    @Query("SELECT o FROM Owner o WHERE o.name LIKE %:namePart%")
    List<Owner> findOwnersByNameContaining(String namePart);
}