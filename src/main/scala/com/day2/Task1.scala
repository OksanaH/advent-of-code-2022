package com.day2

import com.day2.ResponseValues.Value

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

//https://adventofcode.com/2022/day/2
object Task1 extends App {

    val scoreMap = Map(
        "A X" -> (1 + 3),
        "A Y" -> (2 + 6),
        "A Z" -> (3 + 0),
        "B X" -> (1 + 0),
        "B Y" -> (2 + 3),
        "B Z" -> (3 + 6),
        "C X" -> (1 + 6),
        "C Y" -> (2 + 0),
        "C Z" -> (3 + 3),
    )

    val fileStream = getClass.getResourceAsStream("/Day2_input.txt")
    val total = Source.fromInputStream(fileStream).getLines.toList.map(scoreMap(_)).sum

    println(total)
}



