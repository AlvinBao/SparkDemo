package com.hulu.reco.analyzer.duplicate

import com.hulu.reco.analyzer.{AnalyzeContext, RequestAnalyzer}
import com.hulu.reco.model.ParsedRequest

import scala.util.control.Breaks._

/**
  * Compute duplicate requests count in the given context.
  */
class DuplicateAnalyzer(dc: DuplicateContext) extends RequestAnalyzer {
  override def context(): AnalyzeContext = {
    dc
  }

  override def analyze(reqs: Seq[ParsedRequest]): Int = {
    var total: Int = 0
    for ((req, index) <- reqs.zipWithIndex) {
      val first = findIndex(reqs, 0, index, req.timestamp - dc.milliseconds)
      var hasDuplicate = false
      breakable {
        for (i <- first until index) {
          hasDuplicate = reqs(i).requestParams == req.requestParams
          if (hasDuplicate) break
        }
      }

      total = total + (if (hasDuplicate) 1 else 0)
    }
    total
  }
}

object DuplicateAnalyzer {
  def apply(dc: DuplicateContext): DuplicateAnalyzer = new DuplicateAnalyzer(dc)
}
