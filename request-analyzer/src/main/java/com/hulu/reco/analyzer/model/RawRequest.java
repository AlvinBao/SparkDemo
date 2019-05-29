package com.hulu.reco.analyzer.model;

import lombok.Data;

@Data
public class RawRequest {
    private String logId;
    private int deviceId;
    private int requestUrlLength;
    private String requestUrl;
    private String packages;
    private String hostname;
    private long responseMilliSeconds;
    private int userId;
    private String systemName;
    private long profileId;
    private int resultsInfoLength;
    private int resultsCount;
    private String requestId;
    private String results;
    private long timestamp;
}
