package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Цвет", example = "Blue")
    private String color;
    @Schema(description = "Размер", example = "Desktop, Laptop")
    private String size;
    @Schema(description = "Цена", example = "1000.0")
    private double price;
    @Schema(description = "Категория", example = "Gaming, Work")
    private String category;
    @Schema(description = "Тип процессора", example = "Intel i5, AMD Ryzen 9")
    private String processorType;

    @ManyToOne
    @JoinColumn(name = "tech_entity_id", referencedColumnName = "id")
//    @JsonIgnore
    @JsonBackReference
    private TechEntity techEntity; // внешний ключ

}
