package com.example.demo.rest;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Television;
import com.example.demo.entity.VacuumCleaner;
import com.example.demo.service.TelevisionService;
import com.example.demo.service.VacuumCleanerService;
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

@Tag(name="Пылесос", description="Создание, поиск и сортировка")
@RestController
@RequestMapping("/api/vacuum-cleaner")
public class VacuumCleanerController {
    @Autowired
    private VacuumCleanerService vacuumCleanerService;

    @Operation(
            summary = "Создание Холодильников",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "example1",
                                            value = "{\"color\":\"White\",\"size\":\"Compact\",\"price\":199.99,\"dustBagVolume\":2.5,\"modes\":3, \"techEntity\":{\"id\":1}}",
                                            description = "Этот пример используется когда в реестре уже есть существующая запись, тогда мы создаем объект и указываем внешний ключ для TechEntity"
                                    ),
                                    @ExampleObject(
                                            name = "example2",
                                            value = "{\"color\":\"White\",\"size\":\"Compact\",\"price\":199.99,\"dustBagVolume\":2.5,\"modes\":3," +
                                                    "\"techEntity\":{\"name\":\"TechEntityVacuum\",\"manufacturerCountry\":\"CountryVacuum\",\"manufacturerCompany\":\"CompanyVacuum\",\"onlineOrderAvailable\":true,\"installmentAvailable\":true,\"available\":true}}",
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
    public ResponseEntity<VacuumCleaner> createRefrigerator(@RequestBody VacuumCleaner vacuumCleaner) {
        VacuumCleaner createdVacuumCleaner = vacuumCleanerService.saveVacuumCleaner(vacuumCleaner);
        return ResponseEntity.ok(createdVacuumCleaner);
    }

    @Operation(summary = "Поиск", description = "Поиск осуществляется с помощью указанных ниже параметров. Если ни один параметр не будет указан, то выведится весь список")
    @GetMapping("/search")
    public ResponseEntity<List<VacuumCleaner>> searchVacuumCleaner(
            @RequestParam(required = false) @Parameter(description = "Цвет") String color,
            @RequestParam(required = false) @Parameter(description = "Объём пылесборника, ") Double dustBagVolume,
            @RequestParam(required = false) @Parameter(description = "Минимальная цена ") Double minPrice,
            @RequestParam(required = false) @Parameter(description = "Максимальная цена") Double maxPrice) {

        List<VacuumCleaner> result = vacuumCleanerService.searchFiltered(color, dustBagVolume, minPrice, maxPrice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @Operation(summary = "Сортировка, происходит по алфавиту и по стоимости")
    @GetMapping("/sorted")
    public List<VacuumCleaner> getAllSorted() {
        return vacuumCleanerService.getAllSorted();
    }
}
