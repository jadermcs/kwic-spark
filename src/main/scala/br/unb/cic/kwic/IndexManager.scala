package br.unb.cic.kwic

import org.apache.spark.rdd._

class IndexManager {

  private var indexmap: RDD[(String, List[(String, Int)])] = _

  def process(lines: RDD[String]): RDD[(String, List[(String, Int)])] = {
    indexmap = lines.flatMap { l =>
      val words = l.split(" ")
      val indexes = (1 to words.length).map(List((l,_)))
      words.zipWith(indexes)
    }.reduceByKey(_++_)
  }

  def occurrencesOfWord(word: String): Seq[List[(String, Int)]] =
    indexmap.lookup(word)

  def sortedWords: List[String] = indexmap.keys.collect.toList.sorted
}
