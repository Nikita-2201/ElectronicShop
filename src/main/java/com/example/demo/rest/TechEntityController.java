package com.example.demo.rest;

import com.example.demo.entity.Computer;
import com.example.demo.entity.TechEntity;
import com.example.demo.service.TechEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Реестр", description="")
@RestController
@RequestMapping("/api/tech-entity")
public class TechEntityController {

    private final TechEntityService techEntityService;

    public TechEntityController(TechEntityService techEntityService){
        this.techEntityService = techEntityService;
    }
    @Operation(
            summary = "Создание Холодильников",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "example1",
                                            value = "{\"name\":\"AnotherTechEntity\",\"manufacturerCountry\":\"AnotherCountry\",\"manufacturerCompany\":\"AnotherCompany\",\"onlineOrderAvailable\":false,\"installmentAvailable\":true,\"available\":true}",
                                            description = "Пример для создания нового реестра. Если такой уже существует, то выдает ошибку(как при создание с другими моделями)"
                                    ),
                            }
                    )
            )
    )
    @PostMapping("/create")
    public ResponseEntity<TechEntity> createTechEntity(@RequestBody TechEntity techEntity) {
        TechEntity createdtechEntity  = techEntityService.saveTechEntity(techEntity);
        return ResponseEntity.ok(createdtechEntity);
    }
}
