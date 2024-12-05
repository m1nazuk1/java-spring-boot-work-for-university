package com.example.seminar4ik.service;

import com.example.seminar4ik.entity.Owner;
import com.example.seminar4ik.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
    }

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner updateOwner(Long id, Owner updatedOwner) {
        Owner owner = getOwnerById(id);
        owner.setName(updatedOwner.getName());
        owner.setPhone(updatedOwner.getPhone());
        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    public List<Owner> getOwnersByName(String name) {
        return ownerRepository.findByName(name);
    }

    public List<Owner> getOwnersWithMultipleCars() {
        return ownerRepository.findOwnersWithMultipleCars();
    }

    public List<Owner> searchOwnersByName(String namePart) {
        return ownerRepository.findOwnersByNameContaining(namePart);
    }
}