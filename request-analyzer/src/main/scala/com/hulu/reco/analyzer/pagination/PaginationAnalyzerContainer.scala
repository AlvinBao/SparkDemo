package com.hulu.reco.analyzer.pagination

import com.hulu.reco.analyzer.{AnalyzerContainer, RequestAnalyzer}

object PaginationAnalyzerContainer extends AnalyzerContainer {
  override def analyzers(): Seq[RequestAnalyzer] = {
    Seq(
      PaginationAnalyzer(PaginationContext(10)),
      PaginationAnalyzer(PaginationContext(30)),
      PaginationAnalyzer(PaginationContext(60)),
      PaginationAnalyzer(PaginationContext(90)),
      PaginationAnalyzer(PaginationContext(120)),
      PaginationAnalyzer(PaginationContext(150)),
      PaginationAnalyzer(PaginationContext(180))
    )
  }
}
