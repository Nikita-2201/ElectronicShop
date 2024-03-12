package com.example.demo.repository;

import com.example.demo.entity.TechEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechEntityRepository extends JpaRepository<TechEntity, Long> {
    TechEntity findByNameAndManufacturerCountryAndManufacturerCompany(String name, String manufacturerCountry, String manufacturerCompany);

    TechEntity findTechEntityByNameAndManufacturerCountryAndManufacturerCompany(String name, String manufacturerCountry, String manufacturerCompany);

}
