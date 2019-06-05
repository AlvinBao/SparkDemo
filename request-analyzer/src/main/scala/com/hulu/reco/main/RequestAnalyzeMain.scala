package com.hulu.reco.main

import com.hulu.reco.analyzer.Analyzers
import com.hulu.reco.constants.{Constants, TableRequestsAnalyzed, TableRequestsParsed}
import com.hulu.reco.model.{AnalyzedResult, ParsedRequest}
import com.hulu.reco.spark.HiveUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Row, SparkSession}

object RequestAnalyzeMain {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName(RequestAnalyzeMain.getClass.getName)
    conf.set("spark.hadoop.fs.defaultFS", "hdfs://warehousestore")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()
    val ds = conf.get(Constants.PARAM_EXECUTION_DATE)
    val sql =
      s"""
         |select * from ${TableRequestsParsed.name} where ds='$ds'
       """.stripMargin
    import spark.implicits._
    val parsedRequests = spark.sql(sql)
    val partitionedDF = parsedRequests.repartition(
      col(TableRequestsParsed.COL_REQUEST_PATH),
      col(TableRequestsParsed.COL_USER_ID),
      col(TableRequestsParsed.COL_PROFILE_ID)
    )
    val sortedDF = partitionedDF.sortWithinPartitions(col(TableRequestsParsed.COL_TIMESTAMP))
    val requests = sortedDF.map(rowToParsedRquest)
    val analyzedDF = requests.mapPartitions(analyze)
    val sumedDF = analyzedDF.groupBy(
      col(TableRequestsAnalyzed.COL_ENDPOINT),
      col(TableRequestsAnalyzed.COL_ANALYER),
      col(TableRequestsAnalyzed.COL_CONTEXT)
    ).sum(
      TableRequestsAnalyzed.COL_REQUESTS_TOTAL,
      TableRequestsAnalyzed.COL_REQUESTS_HIT
    )

    val resultDF = sumedDF.map(row => (
      row.getAs[String](TableRequestsAnalyzed.COL_ENDPOINT),
      row.getAs[String](TableRequestsAnalyzed.COL_ANALYER),
      row.getAs[String](TableRequestsAnalyzed.COL_CONTEXT),
      row.getAs[Long](TableRequestsAnalyzed.COL_REQUESTS_TOTAL),
      row.getAs[Long](TableRequestsAnalyzed.COL_REQUESTS_HIT),
      row.getAs[Long](TableRequestsAnalyzed.COL_REQUESTS_HIT) / row.getAs[Long](TableRequestsAnalyzed.COL_REQUESTS_TOTAL)
    )).toDF(TableRequestsAnalyzed.cols: _*)
    HiveUtils.insertOverwritePartition(spark, resultDF, TableRequestsAnalyzed.name, TableRequestsAnalyzed.COL_DS, ds)
  }

  def rowToParsedRquest(row: Row): ParsedRequest = {
    import com.hulu.reco.constants.TableRequestsParsed._
    ParsedRequest(
      row.getAs(COL_LOG_ID),
      row.getAs(COL_USER_ID),
      row.getAs(COL_PROFILE_ID),
      row.getAs(COL_PACKAGES),
      row.getAs(COL_DEVICE_ID),
      row.getAs(COL_REQUEST_ID),
      row.getAs(COL_REQUEST_URL_LENGTH),
      row.getAs(COL_REQUEST_URL),
      row.getAs(COL_REQUEST_URL_PARSED),
      row.getAs(COL_REQUEST_PATH),
      row.getAs(COL_REQUEST_PARAMS),
      row.getAs(COL_TIMESTAMP),
      row.getAs(COL_HOSTNAME),
      row.getAs(COL_SYSTEM_NAME),
      row.getAs(COL_RESULTS_COUNT),
      row.getAs(COL_RESULTS_INFO_LENGTH),
      row.getAs(COL_RESULTS),
      row.getAs(COL_RESPONSE_MILLI_SECONDS)
    )
  }


  def analyze(iterator: Iterator[ParsedRequest]): Iterator[AnalyzedResult] = {
    val requests = iterator.toSeq
    val head = requests.head
    val total = requests.size
    val analyzers = Analyzers.getAll()
    analyzers.map(analyzer => {
      val result = analyzer.analyze(requests)
      AnalyzedResult(head.requestPath, analyzer.name(), analyzer.context().prettyString(), total, result)
    }).iterator
  }
}
