package com.hulu.reco.analyzer

trait AnalyzerContainer {
  def analyzers(): Seq[RequestAnalyzer]
}
