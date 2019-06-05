package com.hulu.reco.analyzer.duplicate

import com.hulu.reco.analyzer.{AnalyzerContainer, RequestAnalyzer}

object DuplicateAnalyzerContainer extends AnalyzerContainer {
  override def analyzers(): Seq[RequestAnalyzer] = {
    Seq(
      DuplicateAnalyzer(DuplicateContext(100)),
      DuplicateAnalyzer(DuplicateContext(200)),
      DuplicateAnalyzer(DuplicateContext(300)),
      DuplicateAnalyzer(DuplicateContext(400)),
      DuplicateAnalyzer(DuplicateContext(500)),
      DuplicateAnalyzer(DuplicateContext(600)),
      DuplicateAnalyzer(DuplicateContext(700)),
      DuplicateAnalyzer(DuplicateContext(800)),
      DuplicateAnalyzer(DuplicateContext(900)),
      DuplicateAnalyzer(DuplicateContext(1000))
    )
  }
}
