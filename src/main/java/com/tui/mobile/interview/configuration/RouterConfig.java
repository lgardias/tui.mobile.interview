package com.tui.mobile.interview.configuration;

import com.tui.mobile.interview.handler.RepositoryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> routes(RepositoryHandler handler) {
        return route(GET("/handler/repositories/{userName}").and(accept(MediaType.APPLICATION_JSON)), handler::repositoriesByUserName);
    }
}
