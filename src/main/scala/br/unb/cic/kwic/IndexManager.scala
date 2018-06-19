package br.unb.cic.kwic

import org.apache.spark.rdd._

object IndexManager {

  def process(lines: RDD[String]): RDD[(String, List[String])] =
    lines.flatMap { l =>
      val words = l.split(' ').toList
      val indexes = (1 to words.length).map(p => (words, p-1))
      indexes.map { lw =>
        val (before, after) = lw._1.splitAt(lw._2)
        val h = after.head
        (h, List((after ++ before).mkString(" ")))
      }
    }.reduceByKey((x: List[String], y: List[String]) => x++y)

  def occurrencesOfWord(word: String,
    indexmap: RDD[(String, List[String])]): List[String] =
    indexmap.lookup(word).head

  def sortedWords(indexmap: RDD[(String, List[String])]): List[String] =
    indexmap.keys.collect.toList.sorted
}
