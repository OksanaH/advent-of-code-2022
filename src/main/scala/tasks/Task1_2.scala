package tasks

import scala.io.Source

//https://adventofcode.com/2022/day/2
object Task1_2 extends App {

  val scoreMap = Map(
    "A X" -> (0 + 3),
    "A Y" -> (3 + 1),
    "A Z" -> (6 + 2),
    "B X" -> (0 + 1),
    "B Y" -> (3 + 2),
    "B Z" -> (6 + 3),
    "C X" -> (0 + 2),
    "C Y" -> (3 + 3),
    "C Z" -> (6 + 1)
  )

  val fileStream = getClass.getResourceAsStream("/Day2_input.txt")

  val total = Source.fromInputStream(fileStream).getLines.toList.map(scoreMap(_)).sum

  println(total)
}
