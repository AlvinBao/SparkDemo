package com.hulu.reco.constants

import org.apache.spark.sql.types.StructType

trait TableConstants {

  def name: String

  def schema: StructType

  def cols: Array[String] = {
    schema.fieldNames
  }
}
