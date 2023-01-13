package tasks

import scala.io.Source
import scala.util.matching.Regex

object Task4 extends App{

  val fileStream = getClass.getResourceAsStream("/Day4_input.txt")
  val inputLines = Source.fromInputStream(fileStream).getLines.toList

  Task4_1()
  Task4_2()

  def Task4_1(): Unit ={
    println(inputLines.count(rangeFullyContainsAnotherRange))
  }

  def Task4_2(): Unit ={
    println(inputLines.count(rangesOverlap))
  }

  def rangesOverlap(line: String):Boolean ={
    val (range1, range2) = createRanges(line)
    range1.filter((item=>range2.contains(item))).toSeq.length>=1
  }

  def rangeFullyContainsAnotherRange(line: String):Boolean ={
    val (range1, range2) = createRanges(line)
    range1.subsetOf(range2) || range2.subsetOf(range1)
  }


  def createRanges(line: String): (Set[Int], Set[Int]) ={
    val numbers = (new Regex("\\d{1,}") findAllIn line).toSeq.map(_.toInt)
    numbers.length match {
      case length if length == 4 => {
        val (start1,end1, start2, end2) = (numbers(0),numbers(1),numbers(2),numbers(3))
        ((start1 to end1).toSet, (start2 to end2).toSet)
      }
      case _ => throw new Exception(s"Bad input on line ${inputLines.indexOf(line) +1}")
    }
  }
}
