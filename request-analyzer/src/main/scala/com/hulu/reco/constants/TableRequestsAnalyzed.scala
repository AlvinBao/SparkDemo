package com.hulu.reco.constants

import org.apache.spark.sql.types._

object TableRequestsAnalyzed extends TableConstants {
  val TABLE_NAME = "reco.fastscore_requests_analyzed"
  val COL_ENDPOINT = "endpoint"
  val COL_ANALYER = "analyzer"
  val COL_CONTEXT = "analyze_context"
  val COL_REQUESTS_TOTAL = "requests_total"
  val COL_REQUESTS_HIT = "requests_hit"
  val COL_PERCENTAGE = "percentage"
  val COL_DS = "ds"

  override def schema(): StructType = {
    val fields =
      StructField(COL_ENDPOINT, StringType) ::
        StructField(COL_ANALYER, StringType) ::
        StructField(COL_CONTEXT, StringType) ::
        StructField(COL_REQUESTS_TOTAL, LongType) ::
        StructField(COL_REQUESTS_HIT, LongType) ::
        StructField(COL_PERCENTAGE, DoubleType) ::
        Nil
    StructType(fields)
  }

  override def name: String = {
    TABLE_NAME
  }
}
