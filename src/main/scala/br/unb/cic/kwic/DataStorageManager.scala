package br.unb.cic.kwic

trait DataStorageManager {

  /*
   * Initialize the DataStorageManager.
   */
  def init(): Unit

  /*
   * Get a specific item on the data storage.
   * @param an index for a data storage item
   * @return a specific item on the data storage (String) .
   */

  /*
   * The total number of lines of a file.
   */
  def length() : Long
}
