package com.example.demo.repository;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Television;
import com.example.demo.entity.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {
    @Query("SELECT t FROM Television t WHERE " +
            "(:category IS NULL OR LOWER(t.category) = LOWER(:category) OR :category = '') AND " +
            "(:color IS NULL OR LOWER(t.color) = LOWER(:color) OR :color = '') AND " +
            "(:priceFrom IS NULL OR t.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR t.price <= :priceTo)")
    List<Television> findByFilters(
            @Param("category") String category,
            @Param("color") String color,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo
    );

    List<Television> findAllByOrderByTechEntityNameAscPriceAsc();
}
