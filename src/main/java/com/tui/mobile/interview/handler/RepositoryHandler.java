package com.tui.mobile.interview.handler;

import com.tui.mobile.interview.model.RepositoryResponse;
import com.tui.mobile.interview.service.GithubApiService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RepositoryHandler {

    @Autowired
    private GithubApiService githubApiService;

    @SneakyThrows
    public Mono<ServerResponse> repositoriesByUserName(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(githubApiService.getRepositories(request.pathVariable("userName")), RepositoryResponse.class);
    }
}
