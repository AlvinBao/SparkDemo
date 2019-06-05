package com.hulu.reco.analyzer

import com.hulu.reco.model.ParsedRequest

trait RequestAnalyzer {
  def name(): String = {
    this.getClass.getSimpleName.toLowerCase
  }

  def context(): AnalyzeContext

  /**
    *
    * @param reqs A sequence of ParsedRequest sorted by timestamp.
    * @return
    */
  def analyze(reqs: Seq[ParsedRequest]): Int


  /**
    * Find the first request whose timestamp is greater than or equals the floorTimestamp
    * and return its index.
    *
    * Binary search on timestamp field.
    */
  protected def findIndex(reqs: Seq[ParsedRequest], bottom: Int, up: Int, floorTimestamp: Long): Int = {
    var start = bottom
    var end = up
    while (start + 1 < end) {
      val mid = start + (end - start) / 2
      val req = reqs(mid)
      if (req.timestamp < floorTimestamp) {
        start = mid
      } else {
        end = mid
      }
    }
    if (reqs(start).timestamp >= floorTimestamp) {
      start
    } else if (reqs(end).timestamp >= floorTimestamp) {
      end
    } else {
      0
    }
  }

}
