package com.tui.mobile.interview;

import com.tui.mobile.interview.model.Branch;
import com.tui.mobile.interview.model.RepositoryResponse;

import java.util.List;

public class TestData {

    public static String ENDPOINT_URL = "/repositories/";
    public static String TEST_LOGIN_NAME = "testName";

    public static RepositoryResponse TEST_REPOSITORY_RESPONSE =
            new RepositoryResponse("test_name", "test_owner_login",
                    List.of(new Branch("branch_name", "commit_sha"))
            );
}
