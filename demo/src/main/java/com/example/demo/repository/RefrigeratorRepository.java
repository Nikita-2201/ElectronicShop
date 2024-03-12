package com.example.demo.repository;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Refrigerator;
import com.example.demo.entity.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    @Query("SELECT r FROM Refrigerator r WHERE " +
            "(:color IS NULL OR LOWER(r.color) = LOWER(:color) OR :color = '') AND " +
            "(:numberOfDoors IS NULL OR r.numberOfDoors = :numberOfDoors) AND " +
            "(:priceFrom IS NULL OR r.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR r.price <= :priceTo)")
    List<Refrigerator> findByFilters(
            @Param("color") String color,
            @Param("numberOfDoors") Integer numberOfDoors,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo
    );
    List<Refrigerator> findAllByOrderByTechEntityNameAscPriceAsc();
}
