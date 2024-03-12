package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@PrimaryKeyJoinColumn(name = "vacuum_cleaner_id")
//@DiscriminatorValue("VacuumCleaner")
public class VacuumCleaner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Цвет", example = "Blue")
    private String color;
    @Schema(description = "Размер", example = "Compact")
    private String size;
    @Schema(description = "Цена", example = "1000.0")
    private double price;
    @Schema(description = "объём пылесборника,", example = "2.0")
    private double dustBagVolume;
    @Schema(description = "Kоличество режимов", example = "3")
    private int modes;

    @ManyToOne
    @JoinColumn(name = "tech_entity_id", referencedColumnName = "id")
    @JsonBackReference
    private TechEntity techEntity; // внешний ключ
}
