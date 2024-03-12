package com.example.demo.service;

import com.example.demo.entity.Computer;
import com.example.demo.entity.TechEntity;
import com.example.demo.entity.Television;
import com.example.demo.entity.VacuumCleaner;
import com.example.demo.repository.TelevisionRepository;
import com.example.demo.repository.VacuumCleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacuumCleanerService {
    @Autowired
    private VacuumCleanerRepository vacuumCleanerRepository;
    @Autowired
    private TechEntityService techEntityService;


    public VacuumCleaner saveVacuumCleaner(VacuumCleaner vacuumCleaner) {
        TechEntity techEntity = vacuumCleaner.getTechEntity();

        if (techEntity != null) {
            // Сценарий 1: Передано полное тело TechEntity
            if (techEntity.getId() == null) {
                TechEntity savedTechEntity = techEntityService.saveTechEntity(techEntity);
                vacuumCleaner.setTechEntity(savedTechEntity);
            } else {
                // Сценарий 2: Передан только ID TechEntity
                TechEntity existingTechEntity = techEntityService.findTechEntityById(techEntity.getId());

                if (existingTechEntity != null) {
                    vacuumCleaner.setTechEntity(existingTechEntity);
                } else {
                    throw new IllegalArgumentException("TechEntity not found with ID: " + techEntity.getId());
                }
            }
        }

        return vacuumCleanerRepository.save(vacuumCleaner);
    }



    public List<VacuumCleaner> searchFiltered(String color, Double dustBagVolume, Double minPrice, Double maxPrice) {
        if (StringUtils.hasText(color) || dustBagVolume != null || minPrice != null || maxPrice != null) {
            List<VacuumCleaner> filteredVacuumCleaner = vacuumCleanerRepository.findByFilters(color, dustBagVolume, minPrice, maxPrice);

            if (filteredVacuumCleaner.size() == 0) {
                System.out.println("Ничего не найдено");
            }
            return filteredVacuumCleaner;
        } else {
            return vacuumCleanerRepository.findAll();
        }
    }

    public List<VacuumCleaner> getAllSorted() {
        return vacuumCleanerRepository.findAllByOrderByTechEntityNameAscPriceAsc();
    }

}
