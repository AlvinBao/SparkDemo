package com.hulu.reco.analyzer.main

import com.fasterxml.jackson.databind.ObjectMapper
import com.hulu.reco.analyzer.constants.{Constants, TableRequestsRaw}
import com.hulu.reco.analyzer.model.RawRequest
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}

object RequestParseMain {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(RequestParseMain.getClass.getName)
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate();
    val ds = conf.get(Constants.PARAM_EXECUTION_DATE)
    val sql =
      s"""
         |select * from $TableRequestsRaw.TABLE_RAW where ds='$ds';
       """.stripMargin
    val jsonDF = spark.sql(sql)
    import spark.implicits._
  }

  def jsonParse(row: Row): Seq[Any] = {
    val json = row.getAs[String](TableRequestsRaw.COL_JSON)
    val objectMapper = new ObjectMapper()
    val rawRequest = objectMapper.readValue(json, classOf[RawRequest])
    null
  }
}
