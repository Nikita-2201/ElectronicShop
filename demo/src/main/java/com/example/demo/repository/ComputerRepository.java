package com.example.demo.repository;

import com.example.demo.entity.Computer;
import com.example.demo.entity.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    @Query("SELECT c FROM Computer c WHERE " +
            "(:category IS NULL OR LOWER(c.category) = LOWER(:category) OR :category = '') AND " +
            "(:color IS NULL OR LOWER(c.color) = LOWER(:color) OR :color = '') AND " +
            "(:priceFrom IS NULL OR c.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR c.price <= :priceTo)")
    List<Computer> findByFilters(
            @Param("category") String category,
            @Param("color") String color,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo
    );

    List<Computer> findAllByOrderByTechEntityNameAscPriceAsc();
}
