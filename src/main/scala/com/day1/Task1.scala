package com.day1
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

//https://adventofcode.com/2022/day/1
object Task1 extends App {
    val fileStream = getClass.getResourceAsStream("/input.txt")
    val lines = Source.fromInputStream(fileStream).getLines.toSeq

    var sums : ArrayBuffer[Int] = new ArrayBuffer[Int]
    var sum = 0
    for (line <- lines)
        if (!line.isBlank)
            sum = sum + line.toInt
        else {
            sums += sum
            sum = 0
        }
    val maximumCalories = sums.max
    println(maximumCalories)
}
