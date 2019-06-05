package com.hulu.reco.model;


import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class RawRequest {
    @JsonProperty("log_id")
    private String logId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("profile_id")
    private long profileId;
    @JsonProperty("packages")
    private String packages;
    @JsonProperty("device_id")
    private int deviceId;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("request-url-length")
    private int requestUrlLength;
    @JsonProperty("request-url")
    private String requestUrl;
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("system-name")
    private String systemName;
    @JsonProperty("results-count")
    private int resultsCount;
    @JsonProperty("results-info-length")
    private int resultsInfoLength;
    @JsonProperty("results")
    private String results;
    @JsonProperty("response-milli-seconds")
    private long responseMilliSeconds;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getRequestUrlLength() {
        return requestUrlLength;
    }

    public void setRequestUrlLength(int requestUrlLength) {
        this.requestUrlLength = requestUrlLength;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public int getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }

    public int getResultsInfoLength() {
        return resultsInfoLength;
    }

    public void setResultsInfoLength(int resultsInfoLength) {
        this.resultsInfoLength = resultsInfoLength;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public long getResponseMilliSeconds() {
        return responseMilliSeconds;
    }

    public void setResponseMilliSeconds(long responseMilliSeconds) {
        this.responseMilliSeconds = responseMilliSeconds;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
