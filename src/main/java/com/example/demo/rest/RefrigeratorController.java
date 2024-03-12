package com.example.demo.rest;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Refrigerator;
import com.example.demo.entity.VacuumCleaner;
import com.example.demo.service.RefrigeratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Холодильники", description="Создание, поиск и сортировка")
@RestController
@RequestMapping("/api/refrigerator")
public class RefrigeratorController {
    @Autowired
    private RefrigeratorService refrigeratorService;

    @Operation(
            summary = "Создание Холодильников",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "example1",
                                            value = "{\"color\":\"Stainless Steel\",\"size\":\"French Door\",\"price\":2499.99,\"numberOfDoors\":4,\"compressorType\":\"Inverter\",\"techEntity\":{\"id\":1}}",
                                            description = "Этот пример используется когда в реестре уже есть существующая запись, тогда мы создаем объект и указываем внешний ключ для TechEntity"
                                    ),
                                    @ExampleObject(
                                            name = "example2",
                                            value = "{\"color\":\"Stainless Steel\",\"size\":\"French Door\",\"price\":2499.99,\"numberOfDoors\":4,\"compressorType\":\"Inverter\"" +
                                                    ",\"techEntity\":{\"name\":\"TechEntityFridge\",\"manufacturerCountry\":\"CountryFridge\",\"manufacturerCompany\":\"CompanyFridge\",\"onlineOrderAvailable\":true,\"installmentAvailable\":true,\"available\":true}}",
                                            description = "Этот пример используется, когда мы хотим создать новый объект, но для него нет нужного реестра и мы хотим сразу создать и его. " +
                                                    "Если реестр уже существует(сравнение идет по названию, по названию компании и по названию страны, то выдаст ошибку"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное создание компьютера"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный запрос"
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<Refrigerator> createRefrigerator(@RequestBody Refrigerator refrigerator) {
        Refrigerator createdRefrigerator = refrigeratorService.saveRefrigerator(refrigerator);
        return ResponseEntity.ok(createdRefrigerator);
    }

    @Operation(summary = "Поиск", description = "Поиск осуществляется с помощью указанных ниже параметров. Если ни один параметр не будет указан, то выведится весь список")
    @GetMapping("/search")
    public ResponseEntity<List<Refrigerator>> searchRefrigerators(
            @RequestParam(required = false) @Parameter(description = "Цвет") String color,
            @RequestParam(required = false) @Parameter(description = "Количество дверей") Integer numberOfDoors,
            @RequestParam(required = false) @Parameter(description = "Минимальная цена ") Double minPrice,
            @RequestParam(required = false) @Parameter(description = "Максимальная цена") Double maxPrice,
            @RequestParam(required = false) @Parameter(description = "Тип компрессора") String compressorType) {

        List<Refrigerator> result = refrigeratorService.searchByFilters(color, numberOfDoors, minPrice, maxPrice, compressorType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(
            summary = "Сортировка, происходит по алфавиту и по стоимости"
    )
    @GetMapping("/sorted")
    public List<Refrigerator> getAllSorted() {
        return refrigeratorService.getAllSorted();
    }
}
