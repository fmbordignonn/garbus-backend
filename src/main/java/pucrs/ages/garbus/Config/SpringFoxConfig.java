package pucrs.ages.garbus.Config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        AuthorizationScope[] authScopes = new AuthorizationScope[]{
                new AuthorizationScopeBuilder().scope("global").description(
                        "full access").build()};
        SecurityReference securityReference = SecurityReference.builder().reference(
                "Authorization-Key").scopes(authScopes).build();

        ArrayList<SecurityContext> securityContexts = Lists.newArrayList(
                SecurityContext.builder().securityReferences(Lists.newArrayList(
                        securityReference)).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(
                        Lists.newArrayList(
                                new ApiKey("Authorization-Key", "Authorization", "header")))
                .securityContexts(securityContexts);
    }

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo (
            "GARBUS",
            "Garbage logistic handler", "1.0-SNAPSHOT",
            "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

}