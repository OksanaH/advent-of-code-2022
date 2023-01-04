package com.day2

class RoundScoreCalculator {
  def calculate(roundValue: String): Int ={
    val yourResponseScore = ResponseValues.withName(roundValue.takeRight(1)).id
    drawScore(roundValue) + victoryScore(roundValue) + yourResponseScore

  }

  def drawScore(roundValue:String): Int ={
    val drawValues = Seq("AX", "BY", "CZ")
    if (drawValues.contains(roundValue)) return 3
    else return 0
  }

  def victoryScore(roundValue:String): Int ={
    val victoryScores = Seq("AZ","BX","CY")
    if (victoryScores.contains(roundValue)) return 6
    else return 0
  }
}

object ResponseValues extends Enumeration {
  val X: Value = Value(1) //rock
  val Y: Value = Value(2) //paper
  val Z: Value = Value(3) //scissors
}

