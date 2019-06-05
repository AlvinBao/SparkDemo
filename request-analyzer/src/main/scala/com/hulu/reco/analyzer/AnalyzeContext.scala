package com.hulu.reco.analyzer

trait AnalyzeContext {
  /**
    * Return a printable string
    */
  def prettyString(): String = {
    this.toString
  }
}
