package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task7
import scala.io.Source

class Day7Tests extends AnyFunSuite {

  test("Ignore correctly") {
    Task7.sizes = scala.collection.mutable.Map("/home" -> 0)
    Task7.inputLines = List("$ cd /", "$ ls")
    Task7.processLog()
    assert(Task7.sizes.size == 1)
    assert(Task7.sizes.equals(scala.collection.mutable.Map("/home" -> 0)))
  }

  test("Go into folder correctly") {
    Task7.sizes = scala.collection.mutable.Map("/home" -> 0)
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
      "$ cd test",
      "$ ls"
    )
    Task7.processLog()

    val expected = scala.collection.mutable.Map("/home" -> 0, "/home/some" -> 0, "/home/some/other" -> 0,"/home/some/other/another" -> 0, "/home/test" -> 0)
    assert(Task7.sizes.size == 5)
    Task7.sizes foreach { case (folder, size) =>
      assert(expected.exists(e => e._1 == folder))
    }
  }

  test("Go out of folder correctly") {
    Task7.sizes = scala.collection.mutable.Map("/home" -> 0)
    Task7.inputLines = List(
      "$ cd /",
      "$ ls",
      "$ cd mcszw",
      "$ cd prpmrtc",
      "$ cd hblbht",
      "$ ls",
      "207362 bjlcfcfq.zmn",
      "$ cd ..",
      "$ cd ..",
      "$ cd ..",
      "$ cd ..",
      "$ cd tgrplmn",
      "$ ls",
    )
    Task7.processLog()

    val expected = scala.collection.mutable.Map("/home" -> 0, "/home/mcszw" -> 0, "/home/mcszw/prpmrtc" -> 0,
      "/home/mcszw/prpmrtc/hblbht" -> 0, "/home/tgrplmn" -> 0)
    println(Task7.sizes)
    Task7.sizes foreach { folder =>
      assert(expected.exists(e => e._1 == folder._1))
    }
  }

  test("Verify log parsed correctly") {
  val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    Task7.sizes = scala.collection.mutable.Map("/home" -> 0)
    Task7.inputLines = Source.fromInputStream(fileStream).getLines.toList
    Task7.processLog()

    val expected = scala.collection.mutable.Map("/home"->106, "/home/a"->26, "/home/a/e"->8,"/home/d"->50)
    println(Task7.sizes)

    Task7.sizes foreach {case (folder, size) =>
        assert(expected.exists(e=>e._1== folder && e._2==size))
  }}

  test("canSeetheTreeFromOutside re") {
    val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    Task7.sizes = scala.collection.mutable.Map("/home" -> 0)
    Task7.inputLines = Source.fromInputStream(fileStream).getLines.toList
    Task7.processLog()

    val expected = scala.collection.mutable.Map("/home" -> 106, "/home/a" -> 26, "/home/a/e" -> 8, "/home/d" -> 50)
    println(Task7.sizes)

    Task7.sizes foreach { case (folder, size) =>
      assert(expected.exists(e => e._1 == folder && e._2 == size))
    }
  }



}
