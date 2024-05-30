package pe.gob.pj.guse.infraestructure.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("pe.gob.pj.depositos.infraestructure.rest.adapter"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Depositos Judicial Centralizado del Poder Judicial")
                .description("Servico REST que brinda el Poder Judicial para consulat, registrar y/o actualizar todo lo referente a ordenes de pago y depositos de pago.")
                .version("1.0.0")
                .build();
    }
}
