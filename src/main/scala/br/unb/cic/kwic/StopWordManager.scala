package br.unb.cic.kwic

import scala.io.Source

class StopWordManager {
  val words = Source.fromFile("stop_words.txt").getLines.toList

  def stopWord(word: String) = words.contains(word)
}
