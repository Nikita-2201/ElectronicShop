package com.example.demo.service;

import com.example.demo.entity.Computer;
import com.example.demo.entity.TechEntity;
import com.example.demo.repository.ComputerRepository;
import com.example.demo.repository.TechEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechEntityService {

    @Autowired
    private final TechEntityRepository techEntityRepository;
    @Autowired
    public TechEntityService(TechEntityRepository techEntityRepository) {
        this.techEntityRepository = techEntityRepository;
    }

    public TechEntity saveTechEntity(TechEntity techEntity) {
        TechEntity existingTechEntity = techEntityRepository.findTechEntityByNameAndManufacturerCountryAndManufacturerCompany(
                techEntity.getName(),
                techEntity.getManufacturerCountry(),
                techEntity.getManufacturerCompany()
        );

        if (existingTechEntity != null) {
            throw new IllegalArgumentException("TechEntity with specified fields already exists.");
        } else {
            return techEntityRepository.save(techEntity);
        }
    }


    public TechEntity findTechEntityByFields(String name, String manufacturerCountry, String manufacturerCompany) {
        return techEntityRepository.findByNameAndManufacturerCountryAndManufacturerCompany(name, manufacturerCountry, manufacturerCompany);
    }

    public TechEntity findTechEntityById(Long id) {
        return techEntityRepository.findById(id)
                .orElse(null);
    }

}
