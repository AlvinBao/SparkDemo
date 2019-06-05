package com.hulu.reco.constants

import org.apache.spark.sql.types._

object TableRequestsRepoert extends TableConstants {
  val TABLE_NAME = "reco.fastscore_requests_report"
  val COL_ENDPOINT = "endpoint"
  val COL_ANALYER = "analyzer"
  val COL_CONTEXT = "analyze_context"
  val COL_HIT_PERCENTAGE_1D = "hit_percentage_1d"
  val COL_HIT_PERCENTAGE_3D = "hit_percentage_3d"
  val COL_HIT_PERCENTAGE_7D = "hit_percentage_7d"
  val COL_DS = "ds"

  override def name: String = {
    TABLE_NAME
  }

  override def schema: StructType = {
    val fields =
      StructField(COL_ENDPOINT, StringType) ::
        StructField(COL_ANALYER, StringType) ::
        StructField(COL_CONTEXT, StringType) ::
        StructField(COL_HIT_PERCENTAGE_1D, DoubleType) ::
        StructField(COL_HIT_PERCENTAGE_3D, DoubleType) ::
        StructField(COL_HIT_PERCENTAGE_7D, DoubleType) ::
        Nil
    StructType(fields)
  }
}
