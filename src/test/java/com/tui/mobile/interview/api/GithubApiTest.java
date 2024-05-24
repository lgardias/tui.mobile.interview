package com.tui.mobile.interview.api;

import com.tui.mobile.interview.api.impl.GithubApiImpl;
import com.tui.mobile.interview.exception.RepositoriesNotFoundException;
import com.tui.mobile.interview.model.Branch;
import com.tui.mobile.interview.model.RepositoryDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GithubApiTest {

    private final GithubApiImpl githubApiImpl;

    @Autowired
    GithubApiTest(GithubApiImpl githubApiImpl) {
        this.githubApiImpl = githubApiImpl;
    }

    @Test
    void using_given_userName_should_fetch_repositories() {

        // given
        String repositoryName = "lgardias";

        // when
        List<RepositoryDetails> repositoryDetails = githubApiImpl.fetchRepositories(repositoryName);

        // then
        assert !repositoryDetails.isEmpty();
        assert repositoryDetails.size() == 1;
        assert repositoryDetails.get(0).getName().equals("tui.mobile.interview");
        assert repositoryDetails.get(0).getOwnerLogin().equals("lgardias");

    }

    @Test
    void using_given_non_existing_userName_should_throw_exception() {

        // given
        String repositoryName = "lgardias_dummy_test_name";

        // when
        Exception ex = assertThrows(RepositoriesNotFoundException.class, () -> {
            githubApiImpl.fetchRepositories(repositoryName);
        });

        // then
        assertTrue(ex.getMessage().contains("Zero repositories fetched"));

    }

    @Test
    void using_given_branch_and_owner_name_should_fetch_branch_details() {

        // given
        String branchName = "accelerate";
        String ownerName = "stas00";

        // when
        List<Branch> branches = githubApiImpl.fetchBranches(ownerName, branchName);

        // then
        assert !branches.isEmpty();
        assert branches.size() == 5;
        assert branches.stream()
                .map(Branch::getName)
                .toList()
                .containsAll(List.of("main", "patch-1", "patch-2", "patch-3", "patch-4"));
        assert branches.stream()
                .map(Branch::getLastCommitSHA)
                .toList()
                .size() == 5;
    }

    @Test
    void using_given_wrong_branch_and_owner_name_should_return_empty_list() {

        // given
        String branchName = "accelerate_wrong_test_name";
        String ownerName = "stas00";

        // when
        List<Branch> branches = githubApiImpl.fetchBranches(ownerName, branchName);

        // then
        assert branches.isEmpty();

    }
}
