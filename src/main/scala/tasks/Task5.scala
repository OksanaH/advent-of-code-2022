package tasks

import tasks.Task4.getClass

import scala.collection.mutable.{ArrayBuffer, Stack}
import scala.io.Source
import scala.util.matching.Regex

object Task5 extends App{

  var stacks = Stack[Stack[String]]()
  var inputLines = List[String]()

  var filePath = "/resources/Day5_input.txt"

  main()
  def main(): Unit ={
    //task 1
    inputLines.filter(line=>line.startsWith("move")).map(moveElement)
    stacks.map{s=>
      print(s.head)
    }
    stacks.clear()
    createStacks()

    //task2
    inputLines.filter(line => line.startsWith("move")).map(moveByCrane)
    stacks.map { s =>
      print(s.head)
    }
  }

  def createStacks(): Unit ={
    val fileStream = getClass.getResourceAsStream("/resources/Day5_input.txt")
    inputLines = Source.fromInputStream(fileStream).getLines.toList
    toStack(inputLines.filter(l => l.contains("[")))
  }

  def moveByCrane(line: String): Unit = {
    println(stacks)
      val List(elementsToMove, moveFrom, moveTo) = (new Regex("\\d{1,}") findAllIn line).map(_.toInt).toList
      val blockToMove = ArrayBuffer[String]()
      (1 to elementsToMove) foreach (i => {
        blockToMove +=  stacks(moveFrom - 1).pop
      })
      stacks(moveTo - 1).pushAll(blockToMove.reverse)
  }

  def moveElement(line: String): Unit ={
    val List(elementsToMove, moveFrom, moveTo) = (new Regex("\\d{1,}") findAllIn line).map(_.toInt).toList
    (1 to elementsToMove) foreach (_ => {
      val elToMove = stacks(moveFrom - 1).pop
      stacks(moveTo - 1).push(elToMove)
    })
  }

  def toStack(lines: Seq[String]): Unit = {
    stacks = Stack[Stack[String]]()
    lines.map {
      line => {
        val edited = (new Stack).pushAll(line.grouped(4).map(_.replace(" ","")).toList.reverse)
        stacks.push(edited)
      }
    }
    stacks = stacks.reverse.transpose.map{s =>s.filterNot(_.isBlank)}
  }
}
