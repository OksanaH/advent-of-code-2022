package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task8
import scala.io.Source

class Day8Tests extends AnyFunSuite {

  test("Parse input correctly") {
    val fileStream = getClass.getResourceAsStream("/Day8_testData.txt")
    Task8.inputLines = Source.fromInputStream(fileStream).getLines.toList
    val expected = Array(Array(3,0,3,7,3),Array(2,5,5,1,2),Array(6,5,3,3,2),Array(3,3,5,4,9),Array(3,5,3,9,0))
    Task8.main()

    assert(Task8.sizes.flatMap(_.toList) sameElements expected.flatMap(_.toList))
  }

  //row1, col1, value, expected result
  val input_checkIfTreeVisibleFromTopBottom = List(
    (1,1,5,true),(1,2,5,true),(1,3,1,false),
    (2,1,5, true),(2,2,3, false),(2,3,3, true),
    (3,1,3, false),(3,2,5, true),(3,3,4,false))
  input_checkIfTreeVisibleFromTopBottom foreach { i =>
    test(s"checkIfTreeVisibleFromTopBottom$i returns correct data") {
      Task8.sizes = Array(
        Array(3, 0, 3, 7, 3),
        Array(2, 5, 5, 1, 2),
        Array(6, 5, 3, 3, 2),
        Array(3, 3, 5, 4, 9),
        Array(3, 5, 3, 9, 0))
      val actual = Task8.treeVisibleInColumn(i._1, i._2, i._3)
      assert(actual == i._4)
    }
  }


  val input_checkIfTreeIsVisibleInRow = List(
    (Array(3, 0, 3, 7, 3), 0,1, true), (Array(3, 0, 3, 7, 3), 3,2, true), (Array(3, 0, 3, 7, 3), 7,3, true),
      (Array(2, 5, 5, 1, 2), 5,1, true), (Array(2, 5, 5, 1, 2), 5,2, true), (Array(2, 5, 5, 1, 2), 1,3, false),
    (Array(6,5,3,3,2), 5,1, true),(Array(6,5,3,3,2), 3,2, false),(Array(6,5,3,3,2), 3,3, true),
    (Array(3,3,5,4,9), 3,1, false),(Array(3,3,5,4,9), 5,2, true),(Array(3,3,5,4,9), 4,3, false)
  )
    input_checkIfTreeIsVisibleInRow foreach { i =>
    test(s"checkIfTreeIsVisibleInRow$i returns correct data") {
      //row, tree height, tree index
      val actual = Task8.treeVisibleInRow(i._1, i._2, i._3)
      assert(actual == i._4)
    }
  }

  test("Calculate visible trees correctly") {
    val fileStream = getClass.getResourceAsStream("/Day8_testData.txt")
    Task8.inputLines = Source.fromInputStream(fileStream).getLines.toList
    Task8.sizes = Array[Array[Int]]()
    Task8.treesVisible = 0
    val expected = 21
    Task8.main()

    assert(Task8.treesVisible == expected)
  }

}
