package com.tui.mobile.interview.api.impl;

import com.tui.mobile.interview.api.GithubApi;
import com.tui.mobile.interview.exception.RepositoriesNotFoundException;
import com.tui.mobile.interview.model.Branch;
import com.tui.mobile.interview.model.RepositoryDetails;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GithubApiImpl implements GithubApi {

    final static String JSON_REPOSITORY_FULL_NAME_PATH = "full_name";
    final static String JSON_REPOSITORY_FORK_PATH = "fork";
    final static String JSON_BRANCH_NAME_PATH = "name";
    final static String JSON_BRANCH_COMMIT_PATH = "commit";
    final static String JSON_BRANCH_SHA_PATH = "sha";

    final static Logger logger = Logger.getLogger(GithubApiImpl.class.getName());

    public List<RepositoryDetails> fetchRepositories(String userName) {
        List<RepositoryDetails> resultList = new java.util.ArrayList<>(Collections.emptyList());
        try {
            JSONArray result = readJsonFromUrl(repositoryDetailsUrl(userName));
            for (int i = 0; i < result.length(); i++) {
                JSONObject fetchedDetails = result
                        .getJSONObject(i);
                processRepositoryDetails(fetchedDetails, resultList);
            }
            return resultList;
        } catch (IOException | JSONException e) {
            throw new RepositoriesNotFoundException("Zero repositories fetched");
        }
    }

    public List<Branch> fetchBranches(String ownerName, String repositoryName) {
        List<Branch> resultList = new java.util.ArrayList<>(Collections.emptyList());
        try {
            JSONArray result = readJsonFromUrl(branchDetailsUrl(ownerName, repositoryName));
            for (int i = 0; i < result.length(); i++) {
                String branchName = result
                        .getJSONObject(i)
                        .get(JSON_BRANCH_NAME_PATH)
                        .toString();
                String commitSha = result
                        .getJSONObject(i)
                        .getJSONObject(JSON_BRANCH_COMMIT_PATH)
                        .get(JSON_BRANCH_SHA_PATH).toString();
                Branch branch = new Branch(branchName, commitSha);
                resultList.add(branch);
                log("Successfully fetched branch details" + branch + " for repository:" + repositoryName);
            }
            return resultList;
        } catch (IOException | JSONException e) {
            log("Zero branches fetched for repository:" + repositoryName);
            return Collections.emptyList();
        }
    }


    private String repositoryDetailsUrl(String username) {
        return "https://api.github.com/users/" + username + "/repos?type=sources";
    }

    private String branchDetailsUrl(String ownerMName, String repositoryName) {
        return "https://api.github.com/repos/" + ownerMName + "/" + repositoryName + "/branches";
    }

    private void processRepositoryDetails(
            JSONObject fetchedDetails,
            List<RepositoryDetails> resultList
    ) throws JSONException {
        boolean isFork = Boolean.getBoolean(fetchedDetails
                .get(JSON_REPOSITORY_FORK_PATH).toString());
        if (!isFork) {
            String[] fullRepositoryName = fetchedDetails
                    .get(JSON_REPOSITORY_FULL_NAME_PATH)
                    .toString()
                    .split("/");
            RepositoryDetails details = new RepositoryDetails(fullRepositoryName[1], fullRepositoryName[0]);
            log("Repository details" + fetchedDetails + " added to result");
            resultList.add(details);
        } else {
            log("Repository details" + fetchedDetails + " skipped due to being fork");
        }
    }

    private JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONArray(jsonText);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private void log(String msg) {
        logger.log(Level.INFO, msg);
    }
}
