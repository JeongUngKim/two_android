package com.example.two.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class providerList {

    private JSONObject results;

    public JSONObject getResults() {
        return results;
    }

    public void setResults(JSONObject results) {
        this.results = results;
    }
}
