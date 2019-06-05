package com.hulu.reco.model

case class AnalyzedResult(path: String,
                          analyzer: String,
                          context: String,
                          requestsTotal: Long,
                          requestsHit: Long
                          ) {
}
