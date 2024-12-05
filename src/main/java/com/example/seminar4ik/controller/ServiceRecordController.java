package com.example.seminar4ik.controller;

import com.example.seminar4ik.entity.Car;
import com.example.seminar4ik.entity.Owner;
import com.example.seminar4ik.entity.ServiceRecord;
import com.example.seminar4ik.service.CarService;
import com.example.seminar4ik.service.ServiceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.seminar4ik.dto.ServiceRecordDTO;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;
    private final CarService carService;

    @Autowired
    public ServiceRecordController(ServiceRecordService serviceRecordService, CarService carService) {
        this.serviceRecordService = serviceRecordService;
        this.carService = carService;
    }

    @GetMapping
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordService.getAllServiceRecords();
    }

    @GetMapping("/{id}")
    public ServiceRecord getServiceRecordById(@PathVariable Long id) {
        return serviceRecordService.getServiceRecordById(id);
    }

    @PostMapping
    public ServiceRecord createServiceRecord(@RequestBody ServiceRecordDTO serviceRecordDTO) {
        ServiceRecord serviceRecord = forServiceRecord(serviceRecordDTO);
        return serviceRecordService.createServiceRecord(serviceRecord);
    }

    @PutMapping("/{id}")
    public ServiceRecord updateServiceRecord(@PathVariable Long id, @RequestBody ServiceRecord serviceRecord) {
        return serviceRecordService.updateServiceRecord(id, serviceRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceRecord(@PathVariable Long id) {
        serviceRecordService.deleteServiceRecord(id);
    }

    //ниже для JPQL-запросов
    @GetMapping("/by-car/{carId}")
    public List<ServiceRecord> getServiceRecordsByCar(@PathVariable Long carId) {
        return serviceRecordService.getServiceRecordsByCar(carId);
    }

    @GetMapping("/after-date/{date}")
    public List<ServiceRecord> getServiceRecordsAfterDate(@PathVariable String date) {
        return serviceRecordService.getServiceRecordsAfterDate(LocalDate.parse(date));
    }

    @GetMapping("/search-by-description/{description}")
    public List<ServiceRecord> searchServiceRecordsByDescription(@PathVariable String description) {
        return serviceRecordService.searchServiceRecordsByDescription(description);
    }

    public ServiceRecord forServiceRecord(ServiceRecordDTO serviceRecordDTO){
        if (serviceRecordDTO.getCarId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }
        Car car = carService.getCarById(serviceRecordDTO.getCarId());
        ServiceRecord serviceRecord = new ServiceRecord();
        serviceRecord.setDescription(serviceRecordDTO.getDescription());
        serviceRecord.setServiceDate(serviceRecordDTO.getServiceDate());
        serviceRecord.setCar(car);
        serviceRecord.setCost(serviceRecordDTO.getCost());
        return serviceRecord;
    }
}