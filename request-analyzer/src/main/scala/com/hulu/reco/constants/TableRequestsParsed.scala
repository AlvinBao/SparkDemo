package com.hulu.reco.constants

import org.apache.spark.sql.types.{StructField, _}


object TableRequestsParsed extends TableConstants {
  val TABLE_NAME = "reco.fastscore_requests_parsed"
  val COL_LOG_ID = "log_id"
  // user related
  val COL_USER_ID = "user_id"
  val COL_PROFILE_ID = "profile_id"
  val COL_PACKAGES = "packages"
  val COL_DEVICE_ID = "device_id"
  // request related
  val COL_REQUEST_ID = "request_id"
  val COL_REQUEST_URL_LENGTH = "request_url_length"
  val COL_REQUEST_URL = "request_url"
  val COL_REQUEST_URL_PARSED = "request_url_parsed"
  val COL_REQUEST_PATH = "request_path"
  val COL_REQUEST_PARAMS = "request_params"
  val COL_TIMESTAMP = "timestamp"
  // response related
  val COL_HOSTNAME = "hostname"
  val COL_SYSTEM_NAME = "system_name"
  val COL_RESULTS_COUNT = "results_count"
  val COL_RESULTS_INFO_LENGTH = "results_info_length"
  val COL_RESULTS = "results"
  val COL_RESPONSE_MILLI_SECONDS = "response_milli_seconds"
  // partition column
  val COL_DS = "ds"

  override def schema(): StructType = {
    val fields =
      StructField(COL_LOG_ID, StringType) ::
        StructField(COL_USER_ID, IntegerType) ::
        StructField(COL_PROFILE_ID, LongType) ::
        StructField(COL_PACKAGES, StringType) ::
        StructField(COL_DEVICE_ID, IntegerType) ::
        StructField(COL_REQUEST_ID, StringType) ::
        StructField(COL_REQUEST_URL_LENGTH, IntegerType) ::
        StructField(COL_REQUEST_URL, StringType) ::
        StructField(COL_REQUEST_URL_PARSED, StringType) ::
        StructField(COL_REQUEST_PATH, StringType) ::
        StructField(COL_REQUEST_PARAMS, StringType) ::
        StructField(COL_TIMESTAMP, LongType) ::
        StructField(COL_HOSTNAME, StringType) ::
        StructField(COL_SYSTEM_NAME, StringType) ::
        StructField(COL_RESULTS_COUNT, IntegerType) ::
        StructField(COL_RESULTS_INFO_LENGTH, IntegerType) ::
        StructField(COL_RESULTS, StringType) ::
        StructField(COL_RESPONSE_MILLI_SECONDS, LongType) ::
        Nil
    StructType(fields)
  }

  override def name: String = {
    TABLE_NAME
  }
}
