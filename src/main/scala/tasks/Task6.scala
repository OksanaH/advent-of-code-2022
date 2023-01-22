package tasks

import scala.annotation.tailrec
import scala.io.Source

object Task6 extends App{
  val shortMarkerSize = 4
  val longMarkerSize = 14
  val fileStream = getClass.getResourceAsStream("/resources/Day6_input.txt")
  var inputLines = Source.fromInputStream(fileStream).toSeq

  main()

  def main(): Unit ={
    val charsBeforeShortMarker = getStartOfPacket(0, shortMarkerSize)
    println(charsBeforeShortMarker)

    val charsBeforeLongMarker = getStartOfPacket(0, longMarkerSize)
    println(charsBeforeLongMarker)
  }

  @tailrec
  def getStartOfPacket(startingIdx: Int, markerSize: Int): Int = {
    inputLines.drop(startingIdx).take(markerSize).distinct.length match {
      case i if i < markerSize =>getStartOfPacket(startingIdx+1, markerSize)
      case _ => return startingIdx+markerSize
    }
  }
}
