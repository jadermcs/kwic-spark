package br.unb.cic.kwic

import java.io.File
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.storage.StorageLevel._
import org.apache.spark.{SparkConf, SparkContext}

object MainProgram {
  def main(args: Array[String]): Unit = {
    val inputFile = "resources/papers.txt"
    val outputFile = "resources/out"
    val config = new SparkConf()
      .setMaster("local[*]")
      .setAppName("KeyWord In Context")

    Runner.run(config, inputFile, outputFile)
  }
}

object Runner {
  def run(conf: SparkConf, inputFile: String,
          outputFile: String): Unit = {
    val sc = new SparkContext(conf)
    val rdd = sc.textFile(inputFile)
    val index = IndexManager.process(rdd).persist(MEMORY_AND_DISK_SER)
    //val words = IndexManager.sortedWords(index)
    index.saveAsTextFile(outputFile)

    sc.stop()
  }
}
