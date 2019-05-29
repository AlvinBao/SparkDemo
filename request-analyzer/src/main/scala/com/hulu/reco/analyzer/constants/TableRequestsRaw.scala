package com.hulu.reco.analyzer.constants

import org.apache.spark.sql.types.StructType

object TableRequestsRaw {
  val TABLE_NAME = "reco.fastscore_requests_raw"
  val COL_JSON = "json"
  val COL_DS = "ds"

  def schema(): StructType= {
    null
  }
}
