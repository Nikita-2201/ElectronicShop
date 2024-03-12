package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Название")
    private String name;
    @Schema(description = "Название страны")
    private String manufacturerCountry;
    @Schema(description = "Название компании")
    private String manufacturerCompany;

    @Schema(description = "Серийный номер")
    private String serialNumber;
    @Schema(description = "Доступность онлайн заказа")
    private boolean onlineOrderAvailable;

    @Schema(description = "Доступность рассрочки")
    private boolean installmentAvailable;

    @Schema(description = "Доступность(в наличии)")
    private boolean available;



    @OneToMany(mappedBy = "techEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Computer> computers;
    @OneToMany(mappedBy = "techEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Refrigerator> refrigerators;
    @OneToMany(mappedBy = "techEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Smartphone> smartphones;
    @OneToMany(mappedBy = "techEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Television> televisions;
    @OneToMany(mappedBy = "techEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VacuumCleaner> vacuumCleaners;

}
