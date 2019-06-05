package com.hulu.reco.analyzer

import com.hulu.reco.analyzer.duplicate.DuplicateAnalyzerContainer
import com.hulu.reco.analyzer.pagination.PaginationAnalyzerContainer

object Analyzers {

  private def getAllContainers(): Seq[AnalyzerContainer] = {
    Seq(
      DuplicateAnalyzerContainer,
      PaginationAnalyzerContainer
    )
  }

  def getAll(): Seq[RequestAnalyzer] = {
    val containers = getAllContainers()
    containers.flatMap(container => container.analyzers())
  }

}
