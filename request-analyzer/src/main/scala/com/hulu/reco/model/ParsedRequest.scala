package com.hulu.reco.model

import com.hulu.reco.compress.CompressUtil;

case class ParsedRequest(logId: String,
                         userId: Int,
                         profileId: Long,
                         packages: String,
                         deviceId: Int,
                         requestId: String,
                         requestUrlLength: Int,
                         requestUrl: String,
                         requestUrlParsed: String,
                         requestPath: String,
                         requestParams: String,
                         timestamp: Long,
                         hostname: String,
                         systemName: String,
                         resultsCount: Int,
                         resultsInfoLength: Int,
                         results: String,
                         responseMillsSeconds: Long
                        ) {
}

object ParsedRequest {
  /**
    * Parse RawRequest to ParsedRequest
    *
    */
  def apply(rawRequest: RawRequest): ParsedRequest = {
    val parsedUrl = CompressUtil.decompress(rawRequest.getRequestUrl)
    val strs = parsedUrl.split("\\?")
    val path = if (strs.length > 0) strs(0) else ""
    val params = if (strs.length > 1) strs(1) else ""
    ParsedRequest(
      rawRequest.getLogId,
      rawRequest.getUserId,
      rawRequest.getProfileId,
      rawRequest.getPackages,
      rawRequest.getDeviceId,
      rawRequest.getRequestId,
      rawRequest.getRequestUrlLength,
      rawRequest.getRequestUrl,
      parsedUrl,
      path,
      params,
      rawRequest.getTimestamp,
      rawRequest.getHostname,
      rawRequest.getSystemName,
      rawRequest.getResultsCount,
      rawRequest.getResultsInfoLength,
      rawRequest.getResults,
      rawRequest.getResponseMilliSeconds)
  }
}
