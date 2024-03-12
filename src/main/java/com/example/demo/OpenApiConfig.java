package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Electronic Shop API",
                description = "Electronic Shop", version = "1.0.0",
                contact = @Contact(
                        name = "Blinnikov Nikita",
                        email = "ma0skoko03@gmail.com",
                        url = "https://t.me/blinnikita"
                )
        )
)
public class OpenApiConfig {
}
