package tasks

import tasks.Task4.getClass

import scala.collection.mutable.{ArrayBuffer, Stack}
import scala.io.Source
import scala.util.matching.Regex

object Task5 extends App{

  var stacks = Stack[Stack[String]]()

  Task5_1("/resources/Day5_input.txt")
  println(stacks)

  def Task5_1(path:String): Unit ={
    val fileStream = getClass.getResourceAsStream(path)
    val inputLines = Source.fromInputStream(fileStream).getLines.toList
    val stackLines = inputLines.filter(l=>l.contains("["))
    toStack(stackLines)
    inputLines.filter(line=>line.startsWith("move")).map(moveElement)
  }

  def moveElement(line: String): Unit ={
    val List(elementsToMove, moveFrom, moveTo) = (new Regex("\\d{1,}") findAllIn(line)).map(_.toInt).toList
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
    stacks = stacks.reverse.transpose.map{s =>s.filterNot(_.isBlank)
    }
  }
}
