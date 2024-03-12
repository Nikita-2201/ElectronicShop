package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Refrigerator  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Цвет", example = "Blue")
    private String color;
    @Schema(description = "Размер холодильника", example = "French Door")
    private String size;
    @Schema(description = "Цена", example = "1000.0")
    private double price;
    @Schema(description = "Количество дверей", example = "2")
    private int numberOfDoors;
    @Schema(description = "Тип компрессора", example = "Inverter")
    private String compressorType;

    @ManyToOne
    @JoinColumn(name = "tech_entity_id", referencedColumnName = "id")
    @JsonBackReference
    private TechEntity techEntity; // внешний ключ
}
