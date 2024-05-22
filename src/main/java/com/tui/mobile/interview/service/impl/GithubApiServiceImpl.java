package com.tui.mobile.interview.service.impl;

import com.tui.mobile.interview.api.GithubApi;
import com.tui.mobile.interview.model.Repository;
import com.tui.mobile.interview.service.GithubApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    @Autowired
    private GithubApi githubApi;

    public Flux<Repository> getRepositories(String username) {
        List<Repository> result = githubApi.getRepositories(username);
        return Flux.fromIterable(result);
    }
}