package com.tui.mobile.interview.controller;

import com.tui.mobile.interview.exception.RepositoriesNotFoundException;
import com.tui.mobile.interview.service.impl.GithubApiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static com.tui.mobile.interview.TestData.*;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = GithubRepositoryController.class)
@AutoConfigureWebTestClient
public class GithubRepositoryControllerTest {

    private final WebTestClient webTestClient;

    @MockBean
    private final GithubApiServiceImpl githubApiServiceImpl;

    @Autowired
    public GithubRepositoryControllerTest(
            WebTestClient webTestClient,
            GithubApiServiceImpl githubApiServiceImpl
    ) {
        this.webTestClient = webTestClient;
        this.githubApiServiceImpl = githubApiServiceImpl;
    }

    @Test
    void repositoriesByUserName_should_respond_with_status_ok() {
        when(githubApiServiceImpl
                .getRepositories(TEST_LOGIN_NAME))
                .thenReturn(Flux.just(TEST_REPOSITORY_RESPONSE));

        webTestClient
                .get()
                .uri(ENDPOINT_URL + TEST_LOGIN_NAME)
                .header("Accept", "application/json")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void repositoriesByUserName_should_respond_with_not_acceptable_when_used_wrong_header() {
        webTestClient
                .get()
                .uri(ENDPOINT_URL + TEST_LOGIN_NAME)
                .header("Accept", "application/xml")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    void repositoriesByUserName_should_respond_with_not_found_when_no_data_fetched() {
        when(githubApiServiceImpl
                .getRepositories(TEST_LOGIN_NAME))
                .thenThrow(new RepositoriesNotFoundException("Test Error"));

        webTestClient
                .get()
                .uri(ENDPOINT_URL + TEST_LOGIN_NAME)
                .header("Accept", "application/json")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
