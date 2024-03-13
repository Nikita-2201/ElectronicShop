package com.example.demo.service;


import com.example.demo.entity.*;

import com.example.demo.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final TechEntityService techEntityService;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, TechEntityService techEntityService){
        this.televisionRepository = televisionRepository;
        this.techEntityService = techEntityService;
    }

    public Television saveTelevision(Television television) {
        TechEntity techEntity = television.getTechEntity();

        if (techEntity != null) {
            // Сценарий 1: Передано полное тело TechEntity
            if (techEntity.getId() == null) {
                TechEntity savedTechEntity = techEntityService.saveTechEntity(techEntity);
                television.setTechEntity(savedTechEntity);
            } else {
                // Сценарий 2: Передан только ID TechEntity
                TechEntity existingTechEntity = techEntityService.findTechEntityById(techEntity.getId());

                if (existingTechEntity != null) {
                    television.setTechEntity(existingTechEntity);
                } else {
                    throw new IllegalArgumentException("TechEntity not found with ID: " + techEntity.getId());
                }
            }
        }

        return televisionRepository.save(television);
    }



    public List<Television> searchTelevisions(String color, String category, Double minPrice, Double maxPrice){
        if (StringUtils.hasText(color) || StringUtils.hasText(category) || minPrice != null || maxPrice !=null ) {
            List<Television> filteredTelevision = televisionRepository.findByFilters(category,color,minPrice,maxPrice);
            if(filteredTelevision.size() == 0){
                System.out.println("Ничего не найдено");
            }
            return filteredTelevision;

        } else {
            return televisionRepository.findAll();
        }
    }

    public List<Television> getAllSorted() {
        return televisionRepository.findAllByOrderByTechEntityNameAscPriceAsc();
    }

}
