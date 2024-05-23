package com.tui.mobile.interview.controller;

import com.tui.mobile.interview.model.RepositoryResponse;
import com.tui.mobile.interview.service.GithubApiService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/repositories")
public class GithubRepositoryController {

    @Autowired
    private GithubApiService githubApiService;

    @SneakyThrows
    @GetMapping(path = "/{userName}")
    public Flux<RepositoryResponse> repositoriesByUserName(@PathVariable("userName") String userName) {
        return githubApiService.getRepositories(userName);
    }
}
