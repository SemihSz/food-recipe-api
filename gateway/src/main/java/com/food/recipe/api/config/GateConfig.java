package com.food.recipe.api.config;

import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Semih, 1.10.2023
 */
@Configuration
//@EnableHystrix
@RequiredArgsConstructor
public class GateConfig {

//    private final AuthenticationFilter authenticationFilter;
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("social-api", r -> r.path("/social/**")
//                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://SOCIAL-API"))
//                .route("auth-service-summary", r -> r.path("/summary/**")
//                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://AUTH-SERVICE"))
//                .route("auth-service-auth", r -> r.path("/auth/**")
//                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://AUTH-SERVICE"))
//                .build();
//    }
}
