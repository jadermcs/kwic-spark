package br.unb.cic.kwic

import java.io.File
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.storage.StorageLevel._
import org.apache.spark.{SparkConf, SparkContext}
import org.backuity.clist._

object MainProgram extends CliMain[Unit] (
  name="KWIC",
  description="a simple keyword in context implementation") {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  val config = new SparkConf()
    .setMaster("local[*]")
    .setAppName("KeyWord In Context")

  var inputFile = opt[Option[String]](description = "input file")
  var outputFile = opt[Option[String]](description = "output file of the index")
  var stopWord   = opt[Option[File]](description = "file with a list of stop words")

  def run: Unit = {
    Runner.run(config, inputFile, outputFile)
  }
}

object Runner {
  def run(conf: SparkConf, inputFile: Option[String],
          outputFile: Option[String]): Unit = {
    val sc = new SparkContext(conf)
    val rdd = sc.textFile(inputFile.getOrElse("resources/papers.txt"))
    val index = IndexManager.process(rdd).persist(MEMORY_AND_DISK_SER)
    val words = IndexManager.sortedWords(index)
    // index.saveAsObjectFile(outputFile.getOrElse("resources/index"))
    for(w <- words) {
      val keywords = IndexManager.occurrencesOfWord(w, index)
      keywords.foreach{
        case (line, pos) =>
          // println(line.split(' ').toList(pos-1))
          println(WordShift.shift(line.split(' ').toList, pos, 0).mkString(" "))
      }
    }
    sc.stop()
  }
}