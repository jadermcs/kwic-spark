package br.unb.cic.kwic

import org.apache.spark.rdd._

class FileBasedStorageManager extends DataStorageManager {
  private var lines: RDD[String] = _

  def init() : Unit = {
    print("Enter the name of the input file: ")
    val fileName = scala.io.StdIn.readLine()
    lines = SparkConnection.sc.textFile("file://"+fileName)
  }

  def length = lines.count
}
