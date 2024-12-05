package com.example.seminar4ik.service;

import com.example.seminar4ik.entity.ServiceRecord;
import com.example.seminar4ik.repository.CarRepository;
import com.example.seminar4ik.repository.ServiceRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceRecordService {
    private final ServiceRecordRepository serviceRecordRepository;
    private final CarRepository carRepository;

    @Autowired
    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository, CarRepository carRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.carRepository = carRepository;
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    public ServiceRecord getServiceRecordById(Long id) {
        return serviceRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ServiceRecord not found"));
    }

    public ServiceRecord createServiceRecord(ServiceRecord serviceRecord) {
        carRepository.findById(serviceRecord.getCar().getId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        return serviceRecordRepository.save(serviceRecord);
    }

    public ServiceRecord updateServiceRecord(Long id, ServiceRecord updatedRecord) {
        ServiceRecord record = getServiceRecordById(id);
        record.setServiceDate(updatedRecord.getServiceDate());
        record.setDescription(updatedRecord.getDescription());
        record.setCost(updatedRecord.getCost());
        record.setCar(updatedRecord.getCar());

        return serviceRecordRepository.save(record);
    }

    public void deleteServiceRecord(Long id) {
        serviceRecordRepository.deleteById(id);
    }

    public List<ServiceRecord> getServiceRecordsByCar(Long carId) {
        return serviceRecordRepository.findByCarId(carId);
    }

    public List<ServiceRecord> getServiceRecordsAfterDate(LocalDate date) {
        return serviceRecordRepository.findByServiceDateAfter(date);
    }

    public List<ServiceRecord> searchServiceRecordsByDescription(String description) {
        return serviceRecordRepository.findServiceRecordsByDescriptionContaining(description);
    }
}