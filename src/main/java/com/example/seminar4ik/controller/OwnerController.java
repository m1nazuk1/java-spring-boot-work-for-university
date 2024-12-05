package com.example.seminar4ik.controller;

import com.example.seminar4ik.entity.Owner;
import com.example.seminar4ik.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerService.createOwner(owner);
    }

    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        return ownerService.updateOwner(id, owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }

    //ниже для JPQL-запросов
    @GetMapping("/by-name/{name}")
    public List<Owner> getOwnersByName(@PathVariable String name) {
        return ownerService.getOwnersByName(name);
    }

    @GetMapping("/with-multiple-cars")
    public List<Owner> getOwnersWithMultipleCars() {
        return ownerService.getOwnersWithMultipleCars();
    }

    @GetMapping("/search-by-name/{namePart}")
    public List<Owner> searchOwnersByName(@PathVariable String namePart) {
        return ownerService.searchOwnersByName(namePart);
    }
}