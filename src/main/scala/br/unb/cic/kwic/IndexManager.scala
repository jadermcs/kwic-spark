package br.unb.cic.kwic

import org.apache.spark.rdd._

object IndexManager {

  def process(lines: RDD[String]): RDD[(String, List[(String, Int)])] =
    lines.flatMap { l =>
      val words = l.split(' ').toList
      val indexes = (1 to words.length).map(p => List((l,p-1)))
      words.zip(indexes)
    }.reduceByKey(_++_)

  def occurrencesOfWord(word: String,
    indexmap: RDD[(String, List[(String, Int)])]): List[(String, Int)] =
    indexmap.lookup(word).head

  def sortedWords(indexmap: RDD[(String, List[(String, Int)])]): List[String] =
    indexmap.keys.collect.toList.sorted
}
