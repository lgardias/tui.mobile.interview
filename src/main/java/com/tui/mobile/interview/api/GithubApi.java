package com.tui.mobile.interview.api;

import com.tui.mobile.interview.model.Branch;
import com.tui.mobile.interview.model.Repository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class GithubApi {

    public List<Repository> getRepositories(String userName){
        List<Branch> branches = Arrays.asList(
                new Branch("Test branch 1", " Test SHA 1"),
                new Branch("Test branch 1", " Test SHA 1")
        );

        List<Repository> resultList = new java.util.ArrayList<>(Collections.emptyList());
        try {
            JSONArray result = readJsonFromUrl(prepareUrl(userName));
            for(int i = 0; i < result.length(); i++){
                String[] fullRepositoryName = result.getJSONObject(i).get("full_name").toString().split("/");
                Repository repo = new Repository(fullRepositoryName[1], fullRepositoryName[0], branches);
                resultList.add(repo);
            }
            return resultList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String prepareUrl(String username){
        return "https://api.github.com/users/"+ username +"/repos?type=sources";
    }
    private JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
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
}
