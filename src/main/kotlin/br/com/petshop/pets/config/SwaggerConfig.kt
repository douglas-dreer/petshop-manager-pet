package br.com.petshop.pets.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



@Configuration
class SwaggerConfig {
    @Bean
    fun petShopSwagger(): OpenAPI {
        val serverList = listOf(
            Server()
                .url("/v1")
                .description("Servidor Local")
        )

        return OpenAPI()
            .info(
                Info()
                    .title("Pet Shop API")
                    .description("Documentação da API do Pet Shop")
                    .version("1.0.0")
            )
            .servers(serverList)
    }
}