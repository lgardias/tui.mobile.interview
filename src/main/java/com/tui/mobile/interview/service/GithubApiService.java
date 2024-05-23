package com.tui.mobile.interview.service;

import com.tui.mobile.interview.model.RepositoryResponse;
import org.json.JSONException;
import reactor.core.publisher.Flux;

import java.io.IOException;

public interface GithubApiService {
    Flux<RepositoryResponse> getRepositories(String userName) throws JSONException, IOException;
}
