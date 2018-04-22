package br.unb.cic.kwic

import org.apache.spark.{SparkConf, SparkContext}

object SparkConnection {
  private val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("KeyWord In Context")
  val sc = new SparkContext(conf)
}
