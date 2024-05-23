package com.tui.mobile.interview.service.impl;

import com.tui.mobile.interview.api.GithubApi;
import com.tui.mobile.interview.api.impl.GithubApiImpl;
import com.tui.mobile.interview.model.RepositoryResponse;
import com.tui.mobile.interview.model.RepositoryDetails;
import com.tui.mobile.interview.service.GithubApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    @Autowired
    private GithubApi githubApi;
    final static Logger logger = Logger.getLogger(GithubApiServiceImpl.class.getName());

    public Flux<RepositoryResponse> getRepositories(String username) {
        logger.log(Level.INFO, "Received request for repositories details for username: " + username);

        Flux<RepositoryDetails> repositoriesDetails = Flux.fromIterable(githubApi.fetchRepositories(username));
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(interval, repositoriesDetails, (n1, n2) -> new RepositoryResponse(
                n2.getName(),
                n2.getOwnerLogin(),
                githubApi.fetchBranches(n2.getOwnerLogin(), n2.getName())
        ));
    }
}