package ru.ocrv.library_adm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Library administration Api",
                description = "Library administration", version = "1.0.0",
                contact = @Contact(
                        name = "Fedotov Andrey",
                        email = "iceekb@yandex.ru"
                )
        )
)
public class OpenApiConfig {
}
