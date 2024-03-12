package com.example.demo.repository;

import com.example.demo.entity.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacuumCleanerRepository extends JpaRepository<VacuumCleaner, Long> {
    @Query("SELECT v FROM VacuumCleaner v WHERE " +
            "(:color IS NULL OR LOWER(v.color) = LOWER(:color) OR :color = '') AND " +
            "(:dustBagVolume IS NULL OR v.dustBagVolume = :dustBagVolume) AND " +
            "(:priceFrom IS NULL OR v.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR v.price <= :priceTo)")
    List<VacuumCleaner> findByFilters(
            @Param("color") String color,
            @Param("dustBagVolume") Double dustBagVolume,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo
    );

    List<VacuumCleaner> findAllByOrderByTechEntityNameAscPriceAsc();
}
