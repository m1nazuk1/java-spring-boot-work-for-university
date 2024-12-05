package com.example.seminar4ik.repository;

import com.example.seminar4ik.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByCarId(Long carId);

    List<ServiceRecord> findByServiceDateAfter(LocalDate date);

    // JPQL-запрос найти записи с определённым описанием
    @Query("SELECT sr FROM ServiceRecord sr WHERE sr.description LIKE %:description%")
    List<ServiceRecord> findServiceRecordsByDescriptionContaining(String description);
}