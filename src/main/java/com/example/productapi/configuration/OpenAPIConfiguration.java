package com.example.productapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Product API",
                version = "1.0",
                description = "Develop a service environment that allows exposing a REST API for consumption by a client.",
                contact = @Contact(
                        name = "cnavarro",
                        url = "https://reflectoring.io",
                        email = "cnavarrocalderon@gmail.com"
                )),
        servers = @Server(url = "http://localhost:8080")
)
class OpenAPIConfiguration {
}

