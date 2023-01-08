package tasks

import scala.io.Source

object Task3 extends App{

  val alphabet ="abcdefghijklmnopqrstuvwxyz" ++ "abcdefghijklmnopqrstuvwxyz".toUpperCase()

  val fileStream = getClass.getResourceAsStream("/Day3_input.txt")
  val inputLines = Source.fromInputStream(fileStream).getLines.toList

  Task3_1()
  Task3_2()

  def Task3_1(): Unit ={
    val total = inputLines.map{line=>
      val first = line.substring(0, line.length /2)
      val second = line.replace(first, "")

      val commonElement = first.intersect(second).take(1)
      alphabet.indexOf(commonElement) + 1
    }.sum

    println(total)
  }

  def Task3_2(): Unit ={
    var total = 0
    for (i <- 0 to inputLines.length by 3){
      val lastIndex = i+3
      val groupOf3Rucksacks = inputLines.slice(i, lastIndex)
      println(groupOf3Rucksacks)

      val commonElementIn3Rucksacks = groupOf3Rucksacks.foldRight(groupOf3Rucksacks.take(1).toString())((x,y)=> {
        x.intersect(y)
      }).last

      total += alphabet.indexOf(commonElementIn3Rucksacks) + 1
    }
    print(total)
  }

}
