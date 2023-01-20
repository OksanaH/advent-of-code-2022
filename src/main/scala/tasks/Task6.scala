package tasks

import tasks.Task4.getClass

import scala.collection.mutable.{ArrayBuffer, Stack}
import scala.io.Source
import scala.util.control.Breaks
import scala.util.control.Breaks.break
import scala.util.matching.Regex

object Task6 extends App{
  val fileStream = getClass.getResourceAsStream("/resources/Day6_input.txt")
  val inputLines = Source.fromInputStream(fileStream)

  main()

  def main(): Unit ={
    var totalCharacters = getStartOfPacket(inputLines.toSeq)
    println(totalCharacters)
  }

  def getStartOfPacket(input:Seq[Char]): Int ={
    var totalBeforeStartOfPacket = 1
    var loop = new Breaks
    loop.breakable{
      (0 to input.length).foreach {
        start => {
          if (input.drop(start).take(4).distinct.length < 4) {
            totalBeforeStartOfPacket += 1
          }
          else {
            totalBeforeStartOfPacket += 3
            loop.break
          }
        }
      }
    }
    totalBeforeStartOfPacket
  }

}
