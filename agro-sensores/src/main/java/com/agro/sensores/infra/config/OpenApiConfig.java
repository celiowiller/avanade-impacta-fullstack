package com.agro.sensores.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/*
 * esta classe será uma estrutura de configuração; 
 * aqui, será configurado o Swagger - para testarmos, posteriormente - nossos endpoints
 * portanto, poderemos ter acesso ao Swagger-UI
 **/
@Configuration
public class OpenApiConfig {
	/*
	 * @Bean -> esta annotation é um dos "pilares" do conceito de injeção de dependencia
	 * quando anotamos qualquer estrutura lógica com o @Bean estamos dizendo o seguinte:
	 * "Spring, execute está instrução apenas uma vez! durante a inicialização da aplicação!
	 * 	mantenha este objeto guardado! ou seja, gere um singleton para nós
	 * "
	 * */
	@Bean
	OpenAPI customOpenAPI() {
	
		// 1. definir o nome do esquema de segurança - a partir o JWT
		final String securitySchemeName = "bearerAuth";
		
		
		// vamos definir a expressão de retorno do metodo para que ele 
		// executa a tarefa necessaria - retornar um objeto a partir da classe OpenAPI()
		
		return new OpenAPI()
				// agora, vamos fazer a composição do objeto com informações sobre a OpenAPI()
				.info(new Info()
							.title("Telemetria de Sensores Agricolas")
							.description("API para monitorar dados gerados "
									+ "no campo!")
							.version("1.0.0"))
				
				// configuração de segurança JWT no Swagger
				.addSecurityItem(
							new SecurityRequirement()
							.addList(securitySchemeName))
				
				// definir como o nosso token será enviado 
				.components(
							new Components()
							.addSecuritySchemes(securitySchemeName, 
									new SecurityScheme()
										.name(securitySchemeName)
										.type(SecurityScheme.Type.HTTP)
										.scheme("Bearer")
										.bearerFormat("JWT")
									)
						);	
		
		
	}
}



// Authorization: Bearer asdfgsJKOIjçlkMKJHJbhdbdfjjkh3489798567
