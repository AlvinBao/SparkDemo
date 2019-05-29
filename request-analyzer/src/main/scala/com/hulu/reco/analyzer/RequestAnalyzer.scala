package com.hulu.reco.analyzer


trait RequestAnalyzer {
  def name(): String

  def analyze(reqs: Seq[Int]): Int
}
