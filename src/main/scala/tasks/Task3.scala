package tasks

import scala.io.Source

object Task3 extends App{

  val alphabet ="abcdefghijklmnopqrstuvwxyz" ++ "abcdefghijklmnopqrstuvwxyz".toUpperCase()

  val fileStream = getClass.getResourceAsStream("/Day3_input.txt")

  val total = Source.fromInputStream(fileStream).getLines.map{line=>
    val first = line.substring(0, line.length /2)
    val second = line.replace(first, "")

    val commonElement = first.intersect(second).take(1)
    alphabet.indexOf(commonElement) + 1
  }.sum

  println(total)
}
