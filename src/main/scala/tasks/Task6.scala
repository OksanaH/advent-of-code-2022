package tasks

import scala.annotation.tailrec
import scala.io.Source

object Task6 extends App{
  val markerSize = 4
  val fileStream = getClass.getResourceAsStream("/resources/Day6_input.txt")
  var inputLines = Source.fromInputStream(fileStream).toSeq

  main()

  def main(): Unit ={
    var totalCharacters = getStartOfPacket(0)
    println(totalCharacters)
  }

  @tailrec
  def getStartOfPacket(startingIdx: Int): Int = {
    inputLines.drop(startingIdx).take(markerSize).distinct.length match {
      case i if i < markerSize =>getStartOfPacket(startingIdx+1)
      case _ => return startingIdx+markerSize
    }
  }
}
