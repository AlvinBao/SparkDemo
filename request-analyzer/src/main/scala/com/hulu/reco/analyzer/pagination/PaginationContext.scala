package com.hulu.reco.analyzer.pagination

import com.hulu.reco.analyzer.AnalyzeContext

case class PaginationContext(seconds: Int) extends AnalyzeContext {
  /**
    * Return a printable string
    */
  override def prettyString(): String = {
    "seconds:" + seconds
  }
}
