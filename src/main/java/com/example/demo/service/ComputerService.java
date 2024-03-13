package com.example.demo.service;

import com.example.demo.entity.Computer;
import com.example.demo.entity.TechEntity;
import com.example.demo.entity.VacuumCleaner;
import com.example.demo.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    private final TechEntityService techEntityService;

    @Autowired
    public ComputerService(ComputerRepository computerRepository, TechEntityService techEntityService){
        this.computerRepository = computerRepository;
        this.techEntityService = techEntityService;
    }

    public Computer saveComputer(Computer computer) {
        TechEntity techEntity = computer.getTechEntity();

        if (techEntity != null) {
            // Сценарий 1: Передано полное тело TechEntity
            if (techEntity.getId() == null) {
                TechEntity savedTechEntity = techEntityService.saveTechEntity(techEntity);
                computer.setTechEntity(savedTechEntity);
            } else {
                // Сценарий 2: Передан только ID TechEntity
                TechEntity existingTechEntity = techEntityService.findTechEntityById(techEntity.getId());

                if (existingTechEntity != null) {
                    computer.setTechEntity(existingTechEntity);
                } else {
                    throw new IllegalArgumentException("TechEntity not found with ID: " + techEntity.getId());
                }
            }
        }

        return computerRepository.save(computer);
    }

    public List<Computer> searchComputers(String color, String category, Double minPrice, Double maxPrice){
        if (StringUtils.hasText(color) || StringUtils.hasText(category) || minPrice != null || maxPrice !=null ) {
            List<Computer> filteredComputers = computerRepository.findByFilters(category,color,minPrice,maxPrice);
            if(filteredComputers.size() == 0){
                System.out.println("Ничего не найдено");
            }
            return filteredComputers;

        } else {
            return computerRepository.findAll();
        }
    }

    public List<Computer> getAllSorted() {
        return computerRepository.findAllByOrderByTechEntityNameAscPriceAsc();
    }

}
