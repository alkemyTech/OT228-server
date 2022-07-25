package com.alkemy.ong.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {
	
	private static final String TITLE = "ONG API Documentation";
	private static final String DESCRPTION = "Explore the ONG"
			+ "Para probar la API debe ingresar en \"Authorize\" el TOKEN que obtenga en la cabecera de la respuesta al registrarse.";
	private static final String VERSION = "0.0.1-SNAPSHOT";

	private static final Contact CONTACT = new Contact("Alkemy",
			"https://www.alkemy.org",
			"contacto@alkemy.org");

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
        		.select()
        		.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        		.paths(PathSelectors.any())
        		.build()
        		.apiInfo(apiInfo())
                .ignoredParameterTypes(Iterable.class);
    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title(TITLE)
    			.description(DESCRPTION)
				.version(VERSION)
				.contact(CONTACT)
    			.build();
    }
    
    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
