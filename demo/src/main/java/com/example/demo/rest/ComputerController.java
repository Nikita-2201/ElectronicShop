package com.example.demo.rest;

import com.example.demo.entity.Computer;
import com.example.demo.entity.VacuumCleaner;
import com.example.demo.service.ComputerService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Компьютер", description = "Создание, поиск и сортировка")
@RestController
@RequestMapping("/api/computer")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @PostMapping("/create")
    @Operation(
            summary = "Создание компьютера",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "example1",
                                            value = "{\"color\":\"Black\",\"size\":\"Desktop\",\"price\":1499.99,\"category\":\"Gaming\",\"processorType\":\"AMD Ryzen 9\",\"techEntity\":{\"id\":1}}",
                                            description = "Этот пример используется когда в реестре уже есть существующая запись, тогда мы создаем объект и указываем внешний ключ для TechEntity"
                                    ),
                                    @ExampleObject(
                                            name = "example2",
                                            value = "{\"color\":\"Red\",\"size\":\"Laptop\",\"price\":1299.99,\"category\":\"Office\",\"processorType\":\"Intel Core i7\"," +
                                                    "\"techEntity\":{\"name\":\"TechEntityComputer\",\"manufacturerCountry\":\"CountryComputer\",\"manufacturerCompany\":\"CompanyComputer\",\"onlineOrderAvailable\":true,\"installmentAvailable\":true,\"available\":true}}",
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
    public ResponseEntity<Computer> createComputer(@RequestBody Computer computer) {
        Computer createdComputer = computerService.saveComputer(computer);

        return ResponseEntity.ok(createdComputer);
    }

    @Operation(summary = "Поиск", description = "Поиск осуществляется с помощью указанных ниже параметров. Если ни один параметр не будет указан, то выведится весь список")
    @GetMapping("/search")
    public ResponseEntity<List<Computer>> searchComputers(
            @RequestParam(required = false) @Parameter(description = "Цвет") String color,
            @RequestParam(required = false) @Parameter(description = "Категория ") String category,
            @RequestParam(required = false) @Parameter(description = "Минимальная цена ") Double minPrice,
            @RequestParam(required = false) @Parameter(description = "Максимальная цена") Double maxPrice) {

        List<Computer> result = computerService.searchComputers(color, category, minPrice, maxPrice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(
            summary = "Сортировка, происходит по алфавиту и по стоимости"
    )
    @GetMapping("/sorted")
    public List<Computer> getAllSorted() {
        return computerService.getAllSorted();
    }
}
