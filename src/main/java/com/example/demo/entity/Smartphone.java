package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data

//@PrimaryKeyJoinColumn(name = "smartphone_id")
//@DiscriminatorValue("Smartphone")
public class Smartphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Цвет", example = "Blue")
    private String color;
    @Schema(description = "Размер", example = "6.1 inches")
    private String size;
    @Schema(description = "Цена", example = "1000.0")
    private double price;
    @Schema(description = "Память телефона", example = "128")
    private int memory;
    @Schema(description = "Количество камер", example = "3")
    private int numberOfCameras;

    @ManyToOne
    @JoinColumn(name = "tech_entity_id", referencedColumnName = "id")
    @JsonBackReference
    private TechEntity techEntity; // внешний ключ
}
