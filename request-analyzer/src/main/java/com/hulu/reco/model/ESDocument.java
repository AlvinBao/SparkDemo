package com.hulu.reco.model;


import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class ESDocument {
    @JsonProperty("_index")
    private String index;
    @JsonProperty("_type")
    private String type;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_score")
    private int score;
    @JsonProperty("_source")
    private RawRequest request;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public RawRequest getRequest() {
        return request;
    }

    public void setRequest(RawRequest request) {
        this.request = request;
    }
}
