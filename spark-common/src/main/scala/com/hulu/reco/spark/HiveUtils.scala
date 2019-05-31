package com.hulu.reco.spark

import org.apache.spark.sql.{DataFrame, SparkSession}

object HiveUtils {

  def insertOverwritePartition(spark: SparkSession,
                               df: DataFrame,
                               table: String,
                               partitionKey: String,
                               partitionValue: String): Unit = {
    // TODO still have chance to be duplicate
    val tempView = "temp_view_" + System.currentTimeMillis().toString
    df.createOrReplaceTempView(tempView)
    val sql =
      s"""
         |insert overwrite table $table
         |partition($partitionKey='$partitionValue')
         |select * from $tempView
       """.stripMargin
    spark.sql(sql)
  }
}
