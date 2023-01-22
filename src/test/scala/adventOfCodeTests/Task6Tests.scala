package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task6

class Task6Tests extends AnyFunSuite {

  val input = List(
    ("bvwbjplbgvbhsrlpgdmjqwftvncz", 5, 23),
    ("nppdvjthqldpwncqszvftbrmjlhg", 6, 23),
    ("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11, 26)
  )

  input.foreach{i=>
    test(s"Verify short marker identified correctly for ${i._1}") {
      Task6.inputLines = i._1.toSeq
     assert(Task6.getStartOfPacket(0, Task6.shortMarkerSize ) === i._2)
    }
  }

  input.foreach { i =>
    test(s"Verify long marker identified correctly for ${i._1}") {
      Task6.inputLines = i._1.toSeq
      assert(Task6.getStartOfPacket(0, Task6.longMarkerSize) === i._3)
    }
  }

}
