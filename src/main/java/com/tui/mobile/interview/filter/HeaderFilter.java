package com.tui.mobile.interview.filter;

import com.tui.mobile.interview.exception.InvalidHeaderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class HeaderFilter implements WebFilter {

    final static String HEADER_NAME = "Accept";
    final static String HEADER_VALUE = "application/json";
    final static Logger logger = Logger.getLogger(HeaderFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        validateHeaders(headers);

        return chain.filter(exchange);
    }

    private void validateHeaders(HttpHeaders headers) {
        if (headers.get(HEADER_NAME) == null ||
                !Objects.equals(Objects.requireNonNull(headers.get(HEADER_NAME)).get(0), HEADER_VALUE)) {
            logValidationMsg();
            throw new InvalidHeaderException("Wrong Header:" + HEADER_NAME + "Expected:" + HEADER_VALUE);
        }
    }

    private void logValidationMsg() {
        logger.log(Level.INFO, "Wrong Header:" + HEADER_NAME + "Expected:" + HEADER_VALUE);
    }

}