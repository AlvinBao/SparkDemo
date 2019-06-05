package com.hulu.reco.main

import com.fasterxml.jackson.databind.ObjectMapper
import com.hulu.reco.constants.{Constants, TableRequestsParsed, TableRequestsRaw}
import com.hulu.reco.model.{ESDocument, ParsedRequest}
import com.hulu.reco.spark.HiveUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}

object RequestParseMain {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName(RequestParseMain.getClass.getName)
    conf.set("spark.hadoop.fs.defaultFS", "hdfs://warehousestore")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()
    val ds = conf.get(Constants.PARAM_EXECUTION_DATE)
    val sql =
      s"""
         |select * from ${TableRequestsRaw.TABLE_NAME} where ds='$ds'
       """.stripMargin
    val jsonDF = spark.sql(sql)
    import spark.implicits._
    val parsed = jsonDF.map(jsonParse).toDF(TableRequestsParsed.cols: _*)
    HiveUtils.insertOverwritePartition(
      spark,
      parsed,
      TableRequestsParsed.TABLE_NAME,
      TableRequestsParsed.COL_DS,
      ds
    )
  }

  def jsonParse(row: Row): ParsedRequest = {
    val json = row.getAs[String](TableRequestsRaw.COL_JSON)
    val objectMapper = new ObjectMapper()
    val doc = objectMapper.readValue(json, classOf[ESDocument])
    ParsedRequest(doc.getRequest)
  }
}
