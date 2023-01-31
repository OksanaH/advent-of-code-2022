package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task7
import scala.io.Source

class Day7Tests extends AnyFunSuite {
  Task7.sizes = scala.collection.mutable.Map("/home" -> 0)

  test("Ignore correctly") {
    val input = List("$ cd /", "$ ls")
    val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    Task7.inputLines = List("$ cd /", "$ ls")

    Task7.processLog()

    val expected = Task7.sizes
    assert(Task7.sizes.size == 1)
    assert(Task7.sizes.equals(expected))

  }

  test("Go into folder correctly") {
    val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    Task7.inputLines = List(
      "$ cd /",
      "$ ls",
      "dir some",
      "dir test",
      "11 sd.as",
      "$ cd some",
      "$ ls",
      "dir other",
      "11 sd.as",
      "$ cd other",
      "$ ls",
      "dir another",
      "11 sd.as",
      "$ cd another",
      "$ cd ..",
      "$ cd ..",
      "$ cd ..",
      "$ cd test"
    )
    Task7.processLog()

    val expected = scala.collection.mutable.Map("/home" -> 0, "/home/some" -> 0, "/home/some/other" -> 0,"/home/some/other/another" -> 0, "/home/test" -> 0)
    assert(Task7.sizes.size == 5)
    Task7.sizes foreach { case (folder, size) =>
      assert(expected.exists(e => e._1 == folder))
    }
  }

  test("Verify log parsed correctly") {

  val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    Task7.inputLines = Source.fromInputStream(fileStream).getLines.toList
    Task7.processLog()

    val expected = scala.collection.mutable.Map("/home"->99, "/home/a"->19, "/home/a/e"->8,"/home/d"->50)
    println(Task7.sizes)

    Task7.sizes foreach {case (folder, size) =>{
        assert(expected.exists(e=>e._1== folder && e._2==size))
      }
  }}

}
