package com.example.demo.service;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Refrigerator;
import com.example.demo.entity.TechEntity;
import com.example.demo.entity.VacuumCleaner;
import com.example.demo.repository.RefrigeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefrigeratorService {
    @Autowired
    private RefrigeratorRepository refrigeratorRepository;
    @Autowired
    private TechEntityService techEntityService;

    public Refrigerator saveRefrigerator(Refrigerator refrigerator) {
        TechEntity techEntity = refrigerator.getTechEntity();

        if (techEntity != null) {
            // Сценарий 1: Передано полное тело TechEntity
            if (techEntity.getId() == null) {
                TechEntity savedTechEntity = techEntityService.saveTechEntity(techEntity);
                refrigerator.setTechEntity(savedTechEntity);
            } else {
                // Сценарий 2: Передан только ID TechEntity
                TechEntity existingTechEntity = techEntityService.findTechEntityById(techEntity.getId());

                if (existingTechEntity != null) {
                    refrigerator.setTechEntity(existingTechEntity);
                } else {
                    throw new IllegalArgumentException("TechEntity not found with ID: " + techEntity.getId());
                }
            }
        }

        return refrigeratorRepository.save(refrigerator);
    }



    public List<Refrigerator> searchByFilters(String color, Integer numberOfDoors, Double minPrice, Double maxPrice, String compressorType) {
        if (StringUtils.hasText(color) || numberOfDoors != null || minPrice != null || maxPrice != null || StringUtils.hasText(compressorType)) {
            List<Refrigerator> filteredRefrigerators = refrigeratorRepository.findByFilters(color, numberOfDoors, minPrice, maxPrice);
            if (filteredRefrigerators.isEmpty()) {
                System.out.println("Ничего не найдено");
            }
            return filteredRefrigerators;
        } else {
            return refrigeratorRepository.findAll();
        }
    }

    public List<Refrigerator> getAllSorted() {
        return refrigeratorRepository.findAllByOrderByTechEntityNameAscPriceAsc();
    }

}
