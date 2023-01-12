package tasks

import scala.io.Source
import scala.util.matching.Regex

object Task4 extends App{

  val fileStream = getClass.getResourceAsStream("/Day4_input.txt")
  val inputLines = Source.fromInputStream(fileStream).getLines.toList

  Task4_1()

  def Task4_1(): Unit ={

    var total  =0
    inputLines.map {
      line => {
        val regex: Regex = new Regex("\\d{1,}")
        val numbers = (regex findAllIn line).toSeq.map(_.toInt).toArray

        numbers.length match {
          case length if length == 4 => {
            if (rangesOverlap(numbers)) total += 1
          }
          case _ => throw new Exception(s"Bad input on line ${inputLines.indexOf(line) +1}")
        }
      }
    }
    print(total)
  }

  def rangesOverlap(numbers: Array[Int]): Boolean ={
    val (start1,end1, start2, end2) = (numbers(0),numbers(1),numbers(2),numbers(3))

    var (sections1, sections2) = (Set[Int](),Set[Int]())
    for (i<-start1 to end1)
      sections1 += i
    for (i<-start2 to end2)
      sections2 += i

    sections1.subsetOf(sections2) || sections2.subsetOf(sections1)
  }

}
