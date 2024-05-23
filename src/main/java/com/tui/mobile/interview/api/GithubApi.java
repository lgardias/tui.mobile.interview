package com.tui.mobile.interview.api;

import com.tui.mobile.interview.model.Branch;
import com.tui.mobile.interview.model.RepositoryDetails;

import java.util.List;

public interface GithubApi {

    List<RepositoryDetails> fetchRepositories(String userName);

    List<Branch> fetchBranches(String ownerName, String repositoryName);
}
