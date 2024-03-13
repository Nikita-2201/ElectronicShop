package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.RefrigeratorRepository;
import com.example.demo.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;

    private final TechEntityService techEntityService;
    @Autowired
    public SmartphoneService(SmartphoneRepository smartphoneRepository, TechEntityService techEntityService){
        this.smartphoneRepository = smartphoneRepository;
        this.techEntityService = techEntityService;
    }

    public Smartphone saveSmartphone(Smartphone smartphone) {
        TechEntity techEntity = smartphone.getTechEntity();

        if (techEntity != null) {
            // Сценарий 1: Передано полное тело TechEntity
            if (techEntity.getId() == null) {
                TechEntity savedTechEntity = techEntityService.saveTechEntity(techEntity);
                smartphone.setTechEntity(savedTechEntity);
            } else {
                // Сценарий 2: Передан только ID TechEntity
                TechEntity existingTechEntity = techEntityService.findTechEntityById(techEntity.getId());

                if (existingTechEntity != null) {
                    smartphone.setTechEntity(existingTechEntity);
                } else {
                    throw new IllegalArgumentException("TechEntity not found with ID: " + techEntity.getId());
                }
            }
        }

        return smartphoneRepository.save(smartphone);
    }


    public List<Smartphone> searchSmartphones(String color, Integer memory, Integer numberOfCameras, Double minPrice, Double maxPrice) {
        if (StringUtils.hasText(color) || memory != null || numberOfCameras != null || minPrice != null || maxPrice != null) {
            List<Smartphone> filteredSmartphones = smartphoneRepository.findByFilters(color, memory, numberOfCameras, minPrice, maxPrice);
            if (filteredSmartphones.size() == 0) {
                System.out.println("Ничего не найдено");
            }
            return filteredSmartphones;

        } else {
            return smartphoneRepository.findAll();
        }
    }

    public List<Smartphone> getAllSorted() {
        return smartphoneRepository.findAllByOrderByTechEntityNameAscPriceAsc();
    }

}
