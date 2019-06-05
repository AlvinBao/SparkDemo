package com.hulu.reco.constants

import org.apache.spark.sql.types.{StringType, StructField, StructType}

import scala.collection.immutable.Nil

object TableRequestsRaw extends TableConstants {
  val TABLE_NAME = "reco.fastscore_requests_raw"
  val COL_JSON = "json"
  val COL_DS = "ds"

  override def schema(): StructType = {
    StructType(StructField(COL_JSON, StringType) :: Nil)
  }

  override def name: String = {
    TABLE_NAME
  }
}
