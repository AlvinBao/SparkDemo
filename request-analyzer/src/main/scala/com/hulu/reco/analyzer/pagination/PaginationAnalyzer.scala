package com.hulu.reco.analyzer.pagination

import com.hulu.reco.analyzer.{AnalyzeContext, RequestAnalyzer}
import com.hulu.reco.model.ParsedRequest
import com.hulu.reco.util.QueryStringUtil

import scala.util.control.Breaks._

class PaginationAnalyzer(pc: PaginationContext) extends RequestAnalyzer {
  override def context(): AnalyzeContext = {
    pc
  }

  /**
    *
    * @param reqs A sequence of ParsedRequest sorted by timestamp.
    * @return
    */
  override def analyze(reqs: Seq[ParsedRequest]): Int = {
    var total = 0
    for ((req, index) <- reqs.zipWithIndex) {
      val first = findIndex(reqs, 0, index, req.timestamp - pc.seconds * 1000)
      var hasPrev = false
      breakable {
        for (i <- first until index) {
          hasPrev = isPrevious(reqs(i), req)
          if (hasPrev) break
        }
      }

      total = total + (if (hasPrev) 1 else 0)
    }
    total
  }

  private def isPrevious(prev: ParsedRequest, req: ParsedRequest): Boolean = {
    val prevParams = QueryStringUtil.toMap(prev.requestParams)
    val reqParams = QueryStringUtil.toMap(req.requestParams)
    val offset = "offset"
    val limit = "limit"
    val prevOffset = prevParams(offset)
    val prevLimit = prevParams(limit)
    val reqOffset = reqParams(offset)

    val prevWithoutOffset = prevParams - offset - limit
    val reqWithoutOffset = reqParams - offset - limit
    prevOffset + prevLimit == reqOffset && prevWithoutOffset == reqWithoutOffset
  }
}


object PaginationAnalyzer {
  def apply(pc: PaginationContext): PaginationAnalyzer = new PaginationAnalyzer(pc)
}