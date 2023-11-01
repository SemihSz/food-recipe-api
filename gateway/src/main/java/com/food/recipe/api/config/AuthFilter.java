package com.food.recipe.api.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization information");
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }

            HttpHeaders headers = new HttpHeaders();
            final String userId = exchange.getRequest().getHeaders().getFirst("USER_ID");
            final String username = exchange.getRequest().getHeaders().getFirst("username");

            headers.set("USER_ID", userId);
            headers.set("Authorization", authHeader);
            headers.set("username", username);

            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:9898/users/token/validate")
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(responseBody -> {
                        // Yanıtı işleyebilirsiniz
                        // Örneğin, loglayabilir veya başka bir işlem yapabilirsiniz
                        System.out.println("Response: " + responseBody);
                        return responseBody;
                    })
                    .flatMap(responseBody -> {
                        // Değişim zincirini filtreleyebilir veya başka işlemler yapabilirsiniz
                        /*exchange.getRequest().mutate().header("X-auth-user-id", responseBody);*/
                        return chain.filter(exchange);
                    });
        };
    }

    public static class Config {
        // empty class as I don't need any particular configuration
    }
}
