package com.hulu.reco.util


object QueryStringUtil {

  def isSame(first: String, second: String): Boolean = {
    val firstMap = toMap(first)
    val secondMap = toMap(second)
    firstMap == secondMap
  }

  def toMap(query: String): Map[String, String] = {
    var map: Map[String, String] = Map()
    val strs = query.split("&")
    for (str <- strs) {
      val pair = str.split("=")
      map = map + (pair(0) -> pair(1))
    }
    map
  }
}
