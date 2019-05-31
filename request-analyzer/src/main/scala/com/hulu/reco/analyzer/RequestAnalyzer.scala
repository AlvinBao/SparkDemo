package com.hulu.reco.analyzer

import com.hulu.reco.analyzer.model.ParsedRequest


trait RequestAnalyzer {
  def name(): String

  def analyze(reqs: Seq[ParsedRequest]): Int
}
