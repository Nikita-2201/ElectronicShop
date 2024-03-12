package com.example.demo.repository;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Smartphone;
import com.example.demo.entity.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
    @Query("SELECT s FROM Smartphone s WHERE " +
            "(:color IS NULL OR LOWER(s.color) = LOWER(:color) OR :color = '') AND " +
            "(:memory IS NULL OR s.memory = :memory) AND " +
            "(:numberOfCameras IS NULL OR s.numberOfCameras = :numberOfCameras) AND " +
            "(:priceFrom IS NULL OR s.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR s.price <= :priceTo)")
    List<Smartphone> findByFilters(
            @Param("color") String color,
            @Param("memory") Integer memory,
            @Param("numberOfCameras") Integer numberOfCameras,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo
    );
    List<Smartphone> findAllByOrderByTechEntityNameAscPriceAsc();
}
